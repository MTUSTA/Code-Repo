#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include<sys/socket.h>
#include<arpa/inet.h> //inet_addr
#include<unistd.h>    //write
#include<pthread.h> //for threading , link with lpthread

// store videofile name for connection_handler
// I moved the values to global to avoid using struct in parameter
char videofile1[100] ;
char videofile2[100] ;
char videofile3[100] ;

// port number must be > 1023
int port_number = 1024;
int streams_number = 0;
// if connection increase counter
int thread_counter = 0;
/*
 * This will handle connection for each client
 * */
void *connection_handler(void *socket_desc){
    //Get the socket descriptor
    int sock = *(int*)socket_desc;
    int read_size;
    char *message , client_message[2000];
	
	
	int channel_id = 0;
	int process_counter = 0;

    //Receive a message from client
    while( (read_size = recv(sock , client_message , 2000 , 0)) > 0){
        //end of string marker
		client_message[read_size] = '\0';
		if (process_counter == 0){
			// string to int
			channel_id = atoi(client_message);
			// if connection succesfull give response for client
			strcpy(client_message,"I took your channel id succesfully\n");
			//Send the message back to client
			write(sock , client_message , strlen(client_message));
			process_counter++;
		}
		// check number
		else if(process_counter == 1){
			if(channel_id > streams_number){
				message = "Your Channel ID Wrong";
				write(sock , message , strlen(message));
			}
			// send video data
			else{
				
				FILE * fp;
				char * line = NULL;
				size_t len = 0;
				ssize_t read;
				// file selection
				if(channel_id == 1){
					fp = fopen(videofile1, "r");
				}
				else if(channel_id == 2){
					fp = fopen(videofile1, "r");
				}	
				else if(channel_id == 3){
					fp = fopen(videofile1, "r");
				}   
				// if no file give an error
				if (fp == NULL)
					exit(EXIT_FAILURE);
				// send read file by server to client
				while ((read = getline(&line, &len, fp)) != -1) {
					write(sock , line , strlen(line));
				}

				fclose(fp);
				if (line)
					free(line);
				
				
			}
			// close connection by server
			close(sock);

		}

		//clear the message buffer
		memset(client_message, 0, 2000);
    }


    // if connection ended by client
    if(read_size == 0){
        printf("Client disconnected\n");
        fflush(stdout);
    }
    else if(read_size == -1){
        //perror("recv failed");
		printf("recv failed or connection ended by server\n");
    }
         
    return 0;
} 

int main(int argc, char *argv[]){

	// set port number
	if (strcmp(argv[1], "-p") == 0){
		port_number = atoi(argv[2]);
	}
	// bad arguments error -> -p
	else{
		printf("First argument not equals -p\n");
		exit(0);
	}
	/*-s streams: specifies the number of videos to be streamed. It can take values between 1 and 3.*/
	if (strcmp(argv[3], "-s") == 0){
		streams_number = atoi(argv[4]);
		// store file name in global variable
		if(streams_number >0){
			strcpy(videofile1,argv[6]);
		}
		if(streams_number >1){
			strcpy(videofile2,argv[8]);
		}
		if(streams_number >2){
			strcpy(videofile3,argv[10]);
		}

	}
	// bad arguments error -> -s
	else{
		printf("Third argument not equals -s\n");
		exit(0);
	}
	
	// thread size
	pthread_t thread_storage[streams_number];
	
	
    int socket_desc , client_sock , c;
    struct sockaddr_in server , client;
     
    //Create socket
    socket_desc = socket(AF_INET , SOCK_STREAM , 0);

    if (socket_desc == -1){
        printf("Could not create socket");
    }
    printf("Socket created\n");
     
    //Prepare the sockaddr_in structure
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons( port_number );
    
	printf("local address: %s\n", inet_ntoa( server.sin_addr));
	printf("local port: %d\n", (int) ntohs(server.sin_port));
	
    //Bind
    if( bind(socket_desc,(struct sockaddr *)&server , sizeof(server)) < 0){
        //print the error message
        perror("bind failed. Error\n");
        return 1;
    }
    printf("bind done\n");
     
    //Listen
    listen(socket_desc , streams_number);
     
    //Accept and incoming connection
    printf("Waiting for incoming connections...\n");
    c = sizeof(struct sockaddr_in);

    while( (client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c)) ){
        printf("\nConnection accepted\n");
         
        if( pthread_create( &thread_storage[thread_counter] , NULL ,  connection_handler , (void*) &client_sock) < 0){
            perror("could not create thread\n");
            return 1;
        }

        //Now join the thread , so that we dont terminate before the thread
        pthread_join( thread_storage[thread_counter] , NULL);
		
		thread_counter++;
		// reset counter
		if (streams_number == thread_counter){
			thread_counter = 0;
		}

    }
	
	if (client_sock < 0){
        perror("accept failed\n");
        return 1;
    }
	
	close(socket_desc);
	return 0;
}

