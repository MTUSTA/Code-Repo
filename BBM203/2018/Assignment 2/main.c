#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

typedef struct{
char* Timestamp;
char* Message;
int Number_of_frames;
char* Sender_ID;
char* Receiver_ID;
char* Activity;
bool Success;
}Logs;

/* A structure to represent a layer*/
typedef struct{
    char* sender_info;
    char* receiver_info;
    char* message;
}Layer;
/* A structure to represent a stack*/
typedef struct{
    int top,size;
    Layer Array[4];
}Frame;

/* A structure to represent a queue*/
typedef struct{
    int front, rear,capacity;
    Frame* frame_queue;
}Queue;

/* A structure to represent a RoutingTable*/
typedef struct{
    char Intended[2];
    char Neighbor[2];
}RoutingTable;

/* A structure to represent a client*/
typedef struct {
    char* ClientID;
    char* ClientIpAdress;
    char* ClientMacAdress;
    RoutingTable* RTable;
    Queue* Incoming_queue;
    Queue* Outgoing_queue;
    Logs* Client_Log;
    int log_yer;
    int Number_of_hops;
}Client;

/* function to create a stack. It initializes size of stack as 0*/
Frame* createStack(int max_message_size){
    Frame* stack = (Frame*) malloc(sizeof(Frame));
    stack->top = -1;
    stack->size = 4;
    int i;
    for(i=0;i<4;i++){
        if(i == 0){
            stack->Array[i].message = (char*)malloc(max_message_size*sizeof(char));
            stack->Array[i].receiver_info = (char*)malloc(max_message_size*sizeof(char));
            stack->Array[i].sender_info = (char*)malloc(max_message_size*sizeof(char));
        }else{
            stack->Array[i].message = (char*)malloc(sizeof(char));
            stack->Array[i].receiver_info = (char*)malloc(max_message_size*sizeof(char));
            stack->Array[i].sender_info = (char*)malloc(max_message_size*sizeof(char));
        }
    }

    return stack;
}
/* function to create a queue of given capacity. It initializes size of queue as 0*/
Queue* createQueue(int capacity){
    Queue* queue = (Queue*) malloc(sizeof(Queue));
    queue->capacity = capacity;
    queue->front = -1;
    queue->rear = -1;
    queue->frame_queue = (Frame*) malloc(capacity * sizeof(Frame));
    return queue;
}
/* function to create a Logs of given capacity.*/
Logs* createLogs(int capacity){
    Logs* log = (Logs*) malloc(capacity*sizeof(Logs));
    int i =0;
    for(i;i<capacity;i++){
        log[i].Number_of_frames = 0;
    }
    return log;
}
/* function to reads files, creates clients, allocates memory, fills clientID clientIpAddress ClientMacAddress routing table*/
Client* createClient(FILE* clientsdat,FILE* routingdat,int cli_num){
    char id[20],ip[20],mac[20];
    /*defines client*/
    Client* clients = (Client*) malloc(sizeof(Client)*cli_num);
    /*order structures*/
    int i=0;
    while(fscanf(clientsdat, "%s %s %s\n",id,ip,mac) != EOF) {
        /*allocate struct arrays with malloc*/
        clients[i].ClientID = (char*)malloc(sizeof(char)*strlen(id));
        clients[i].ClientIpAdress = (char*)malloc(sizeof(char)*strlen(ip));
        clients[i].ClientMacAdress = (char*)malloc(sizeof(char)*strlen(mac));
        clients[i].RTable = (RoutingTable*)malloc((cli_num-1) * sizeof(RoutingTable*));
        clients[i].Incoming_queue = createQueue(100);
        clients[i].Outgoing_queue = createQueue(100);
        clients[i].Client_Log = createLogs(100);
        clients[i].log_yer = 0;
        clients[i].Number_of_hops = 0;

        /*fills arrays with string.h library*/
        strcpy(clients[i].ClientID, id);
        strcpy(clients[i].ClientIpAdress, ip);
        strcpy(clients[i].ClientMacAdress, mac);
        i++;
    }
    /*read routingdat file and fills RoutingTable*/
    char dest_client_id[2],client_id_nearest[2],temp[1];
    int row=0,kullanici=0;
    while(fscanf(routingdat,"%s %s \n",dest_client_id,client_id_nearest) != EOF) {
        /*fills RoutingTable*/
        strcpy(clients[kullanici].RTable[row].Intended,dest_client_id);
        strcpy(clients[kullanici].RTable[row].Neighbor,client_id_nearest);
        row++;
        /*reset row*/
        if(row == cli_num-1){
            row=0;
            kullanici++;
            fscanf(routingdat,"%s\n",temp);
        }
    }
    return clients;
}

/* Queue is empty when size is 0*/
bool isEmptyQueue(Queue* queue){
    if(queue->front == -1 && queue->rear == -1 ){
        return true;
    }else{
        return false;
    }
}

/* Queue is full when rear becomes equal to the capacity*/
bool isFullQueue(Queue* queue){
    if(queue->rear == queue->capacity-1){
            printf("queue is full\n");
            return true;
    }else{
        return false;
    }
}


/* Function to add an frame* item to the queue.It changes rear */
void enqueue(Queue* queue, Frame* item){
    if (isFullQueue(queue)){
        return ;
    }
    else if(isEmptyQueue(queue)){
        queue->front = 0;
        queue->rear = 0;
    }
    else{
        queue->rear++;
    }
    int i;
    for(i=0;i<4;i++){
        /*allocate memory in queue frame*/
        queue->frame_queue[queue->rear].Array[i].message = (char*)malloc(strlen(item->Array[i].message)*sizeof(char));
        queue->frame_queue[queue->rear].Array[i].receiver_info = (char*)malloc(strlen(item->Array[i].receiver_info)*sizeof(char));
        queue->frame_queue[queue->rear].Array[i].sender_info = (char*)malloc(strlen(item->Array[i].sender_info)*sizeof(char));
        /*copy string in item to queue array*/
        strcpy(queue->frame_queue[queue->rear].Array[i].message,item->Array[i].message);
        strcpy(queue->frame_queue[queue->rear].Array[i].receiver_info,item->Array[i].receiver_info);
        strcpy(queue->frame_queue[queue->rear].Array[i].sender_info,item->Array[i].sender_info);
    }
    queue->frame_queue[queue->rear].size = item->size;
    queue->frame_queue[queue->rear].top = item->top;
}

/* Function to add an Frame item to the queue.It changes rear */
void enqueue2(Queue* queue, Frame item){
    if (isFullQueue(queue)){
        return ;
    }
    else if(isEmptyQueue(queue)){
        queue->front = 0;
        queue->rear = 0;
    }
    else{
        queue->rear++;
    }
    int i;
    for(i=0;i<4;i++){
        /*allocate memory in queue frame*/
        queue->frame_queue[queue->rear].Array[i].message = (char*)malloc(strlen(item.Array[i].message)*sizeof(char));
        queue->frame_queue[queue->rear].Array[i].receiver_info = (char*)malloc(strlen(item.Array[i].receiver_info)*sizeof(char));
        queue->frame_queue[queue->rear].Array[i].sender_info = (char*)malloc(strlen(item.Array[i].sender_info)*sizeof(char));
        /*copy string in item to queue array*/
        strcpy(queue->frame_queue[queue->rear].Array[i].message,item.Array[i].message);
        strcpy(queue->frame_queue[queue->rear].Array[i].receiver_info,item.Array[i].receiver_info);
        strcpy(queue->frame_queue[queue->rear].Array[i].sender_info,item.Array[i].sender_info);
    }
    queue->frame_queue[queue->rear].size = item.size;
    queue->frame_queue[queue->rear].top = item.top;
}

/* Function to remove an item from queue.It changes front*/
void dequeue(Queue* queue){
    if (isEmptyQueue(queue)){
        return ;
    }
    else if(queue->front == queue->rear){
        queue->front = -1;
        queue->rear = -1;
    }
    else{
        queue->front++;
    }
}

/* Function to get all queue */
Frame* Allqueue (Queue* queue){
    if (isEmptyQueue(queue)){
        return ;
    }
    return queue->frame_queue;
}

/* Stack is full when top is equal to the last index*/
bool isFullStack(Frame* stack){
    if(stack -> top == (stack -> size)-1){
        return true;
    }else{
        return false;
    }
}
/* Stack is empty when top is equal to -1*/
bool isEmptyStack(Frame* stack){
    if(stack->top == -1){
        return true;
    }else{
        return false;
    }
}
/* Function to add an item to stack.  It increases top by 1*/
void push(Frame* stack, char* mesaj,char* receiver,char* sender){

    if (isFullStack(stack)){
        printf("stack is fulll \n");
    }else{
        stack->top++;
        strcpy(stack->Array[stack->top].message,mesaj);
        strcpy(stack->Array[stack->top].receiver_info,receiver);
        strcpy(stack->Array[stack->top].sender_info,sender);
    }
}

/*function to finds client id*/
int findIdNumber(int cli_num,Client* clients,char* aranan){
    int find_cli;
    for(find_cli=0;find_cli<cli_num;find_cli++){
        if(strcmp(clients[find_cli].ClientID,aranan)==0){
            return find_cli;
        }
    }
}
/*C substring function definition*/
char* substring(char message[], int max_message_size, int start_point) {
    char* sub = (char *)malloc(max_message_size * sizeof(char));
    int c = 0;
    while (c < max_message_size) {
       sub[c] = message[start_point+c];
       c++;
    }
    sub[c] = '\0';
    return sub;
}
/*function to finds neighbor id */
int findneighbor(int cli_num,Client* clients,int senderclientid,char* receiver_id){
    int k;
    for (k=0; k< cli_num-1; k++){
        if(strcmp(clients[senderclientid].RTable[k].Intended,receiver_id)==0){
                return findIdNumber(cli_num,clients,clients[senderclientid].RTable[k].Neighbor);;
        }
    }
}
/*function to catch time*/
char* time_stamp(){

    char* timestamp = (char *)malloc(sizeof(char) * 50);
    time_t ltime = time(NULL);
    struct tm *p_tm = localtime(&ltime);
    int year = p_tm->tm_year+1900;
    sprintf(timestamp,"%04d-%02d-%02d %02d:%02d:%02d", year, p_tm->tm_mon,p_tm->tm_mday, p_tm->tm_hour, p_tm->tm_min, p_tm->tm_sec);
    return timestamp;
}
/*concat 2 string function*/
char* concat(const char *s1, const char *s2){
    const size_t len1 = strlen(s1);
    const size_t len2 = strlen(s2);
    char *result = malloc(len1 + len2 + 1); /* +1 for the null-terminator*/
    /* in real code you would check for errors in malloc here*/
    memcpy(result, s1, len1);
    memcpy(result + len1, s2, len2 + 1); /* +1 to copy the null-terminator*/
    return result;
}

/* Driver program to test above functions*/
int main(int argc,char* argv[]){
    /*open clients.dat file*/
    FILE* clientsdat = fopen(argv[1], "r");
    /*store client number*/
    int cli_num;
    fscanf(clientsdat, "%d\n", &cli_num);
    /*routing.dat = Destination_Client_ID<space>Client_ID_of_nearest_neighbor*/
    FILE* routingdat = fopen(argv[2], "r");
    Client* clients = createClient(clientsdat,routingdat,cli_num);
    /*commands.dat */
    FILE* commandsdat = fopen(argv[3], "r");
    /*reads input file with fgets() function*/
    int base=0;
    char line[999];
    /*store line limit number*/
    int line_limit = atoi(fgets(line,999, commandsdat));
    int senderclientid,receiverclientid,nei,max_message_size = atoi(argv[4]);
    char* sender_id;
    char* receiver_id;
    while (fgets(line,999, commandsdat)&& base<line_limit) {
            /*delete new line character*/
            size_t ln = strlen(line) - 1;
            if (*line && line[ln] == '\n'){
                line[ln] = '\0';
            }
            /*prints command line*/
            printf("-------------------------------------------------------------------------------\n");
            printf("Command: %s\n",line);
            printf("-------------------------------------------------------------------------------\n");
            /*split in c Returns first token*/
            char *token = strtok(line," ");
            /* Keep printing tokens while one of the delimiters present in str[].*/
            while (token != NULL){
                if(strcmp(token,"MESSAGE")==0){
                    /*MESSAGE<space>sender_ID<space>receiver_ID<space>#message#*/
                    sender_id = strtok(NULL, " ");
                    receiver_id = strtok(NULL, " ");
                    senderclientid = findIdNumber(cli_num,clients,sender_id);
                    receiverclientid = findIdNumber(cli_num,clients,receiver_id);
                    /*obtain message*/
                    char* message = strtok(strtok(NULL, ""), "#");
                    /*prints message*/
                    printf("Message to be sent: %s\n\n",message);
                    /*create frame and enqueue in client outgoing queue*/
                    int i = 0,frame_num = 1;
                    while(i<strlen(message)){
                        /*split message or substring*/
                        char* part_of_string = substring(message,max_message_size,i);
                        /*creates stack and push into stack*/
                        Frame* stack = createStack(max_message_size);
                        push(stack,part_of_string,clients[receiverclientid].ClientID,clients[senderclientid].ClientID);
                        push(stack,"",argv[6],argv[5]);
                        push(stack,"",clients[receiverclientid].ClientIpAdress,clients[senderclientid].ClientIpAdress);
                        nei = findneighbor(cli_num,clients,senderclientid,receiver_id);
                        push(stack,"",clients[nei].ClientMacAdress,clients[senderclientid].ClientMacAdress);
                        enqueue(clients[senderclientid].Outgoing_queue,stack);
                        /*prints frame info*/
                        printf("Frame #%d\n",frame_num);
                        printf("Sender MAC address: %s, Receiver MAC address: %s\n",clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[3].sender_info,clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[3].receiver_info);
                        printf("Sender IP address: %s, Receiver IP address: %s\n",clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[2].sender_info,clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[2].receiver_info);
                        printf("Sender port number: %s, Receiver port number: %s\n",clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[1].sender_info,clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[1].receiver_info);
                        printf("Sender ID: %s, Receiver ID: %s\n",clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[0].sender_info,clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[0].receiver_info);
                        printf("Message chunk carried: %s\n",clients[senderclientid].Outgoing_queue->frame_queue[frame_num-1].Array[0].message);
                        printf("--------\n");
                        i += max_message_size;
                        frame_num++;
                    }

                }
                /*if token == SHOW_FRAME_INFO*/
                else if(strcmp(token,"SHOW_FRAME_INFO")==0){

                    char* showcli = strtok(NULL, " ");
                    int client_id = findIdNumber(cli_num,clients,showcli);
                    char* where = strtok(NULL, " ");
                    int number = atoi(strtok(NULL, " "));
                    /*if where == in*/
                    if(strcmp(where,"in") == 0){
                        if(number<=clients[client_id].Incoming_queue->rear+1){
                            printf("Current Frame #%d on the outgoing queue of client %s\n",number,showcli);
                            printf("Carried Message: \" %s\"\n",clients[client_id].Incoming_queue->frame_queue[number-1].Array[0].message);
                            printf("Sender ID: %s, Receiver ID: %s\n",clients[client_id].Incoming_queue->frame_queue[number-1].Array[0].sender_info,clients[client_id].Incoming_queue->frame_queue[number-1].Array[0].receiver_info);
                            printf("Sender port number: %s, Receiver port number: %s\n",clients[client_id].Incoming_queue->frame_queue[number-1].Array[1].sender_info,clients[client_id].Incoming_queue->frame_queue[number-1].Array[1].receiver_info);
                            printf("Sender IP address: %s, Receiver IP address: %s\n",clients[client_id].Incoming_queue->frame_queue[number-1].Array[2].sender_info,clients[client_id].Incoming_queue->frame_queue[number-1].Array[2].receiver_info);
                            printf("Sender MAC address: %s, Receiver MAC address: %s\n",clients[client_id].Incoming_queue->frame_queue[number-1].Array[3].sender_info,clients[client_id].Incoming_queue->frame_queue[number-1].Array[3].receiver_info);
                            printf("Number of hops so far: %d",clients[client_id].Number_of_hops);
                        }else{
                            printf("No such frame.\n");
                        }
                    }
                    /*if where == out*/
                    else if (strcmp(where,"out") == 0){
                        if(number<=clients[client_id].Outgoing_queue->rear+1){
                            printf("Current Frame #%d on the outgoing queue of client %s\n",number,showcli);
                            printf("Carried Message: \" %s\"\n",clients[client_id].Outgoing_queue->frame_queue[number-1].Array[0].message);
                            printf("Sender ID: %s, Receiver ID: %s\n",clients[client_id].Outgoing_queue->frame_queue[number-1].Array[0].sender_info,clients[client_id].Outgoing_queue->frame_queue[number-1].Array[0].receiver_info);
                            printf("Sender port number: %s, Receiver port number: %s\n",clients[client_id].Outgoing_queue->frame_queue[number-1].Array[1].sender_info,clients[client_id].Outgoing_queue->frame_queue[number-1].Array[1].receiver_info);
                            printf("Sender IP address: %s, Receiver IP address: %s\n",clients[client_id].Outgoing_queue->frame_queue[number-1].Array[2].sender_info,clients[client_id].Outgoing_queue->frame_queue[number-1].Array[2].receiver_info);
                            printf("Sender MAC address: %s, Receiver MAC address: %s\n",clients[client_id].Outgoing_queue->frame_queue[number-1].Array[3].sender_info,clients[client_id].Outgoing_queue->frame_queue[number-1].Array[3].receiver_info);
                            printf("Number of hops so far: %d\n",clients[client_id].Number_of_hops);

                        }else{
                            printf("No such frame.\n");
                        }
                    }
                }
                /*if token == SHOW_Q_INFO*/
                else if(strcmp(token,"SHOW_Q_INFO")==0){
                    char* showcli = strtok(NULL, " ");
                    int client_id = findIdNumber(cli_num,clients,showcli);
                    char* where = strtok(NULL, " ");
                    /*if where == in*/
                    if(strcmp(where,"in") == 0){
                        printf("Client %s Incoming Queue Status\n",showcli);
                        printf("Current total number of frames: %d \n",clients[client_id].Incoming_queue->rear+1);
                    }
                    /*if where == out*/
                    else if (strcmp(where,"out") == 0){
                        printf("Client %s Outgoing Queue Status\n",showcli);
                        printf("Current total number of frames: %d \n",clients[client_id].Outgoing_queue->rear+1);
                    }
                }
                /*if token == SEND*/
                else if(strcmp(token,"SEND")==0){
                        int hop = 1;
                        char* send_sender = strtok(NULL, " ");
                        int send_sender_id = findIdNumber(cli_num,clients,send_sender);
                        int finish = 0;
                        while(finish<1){

                            Frame* temp1 = Allqueue(clients[send_sender_id].Outgoing_queue);
                            /*save receiver name*/
                            char receiver[1];
                            strcpy(receiver,temp1[0].Array->receiver_info);
                            int send_nei =findneighbor(cli_num,clients,send_sender_id,clients[send_sender_id].Outgoing_queue->frame_queue[0].Array[0].receiver_info);
                            int uzunluk = max_message_size*clients[send_sender_id].Outgoing_queue->rear+1;
                            char* mesaj = (char*)malloc(uzunluk*sizeof(char));
                            /*clear mesaj array for bad chars*/
                            memset(mesaj, 0, sizeof(mesaj));
                            /*sending frame target client incoming queue*/
                            int i=0;
                            for(i;i<clients[send_sender_id].Outgoing_queue->rear+1;i++){
                                strcpy(mesaj,concat(mesaj,temp1[i].Array[0].message));
                                enqueue2(clients[send_nei].Incoming_queue,temp1[i]);
                                dequeue(clients[send_sender_id].Outgoing_queue);
                            }
                            if(strcmp(clients[send_nei].ClientID,clients[send_sender_id].Outgoing_queue->frame_queue[0].Array[0].receiver_info)!=0){
                                printf("A message received by client %s, but intended for client %s. Forwarding...\n",clients[send_nei].ClientID,clients[send_sender_id].Outgoing_queue->frame_queue[0].Array[0].receiver_info);

                            }
                            /*filling logs,message forwarded from sender*/
                            /*stores time*/
                            clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Timestamp = (char*)malloc(20*sizeof(char));
                            strcpy( clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Timestamp,time_stamp());
                            /*stores number of frame*/
                            clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Number_of_frames = i;
                            /*increases number of hops*/
                            clients[send_sender_id].Number_of_hops++;
                            /*allocates informations and store*/
                            clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Sender_ID = (char*)malloc(20*sizeof(char));
                            clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Receiver_ID = (char*)malloc(20*sizeof(char));
                            clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Message = (char*)malloc(uzunluk*sizeof(char));
                            clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Activity = (char*)malloc(20*sizeof(char));
                            strcpy(clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Sender_ID,send_sender);
                            strcpy(clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Receiver_ID,receiver);
                            strcpy(clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Message,mesaj);
                            /*store activity*/
                            strcpy(clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Activity,"Message Forwarded");
                            /*if succes store true, else false*/
                            clients[send_sender_id].Client_Log[clients[send_sender_id].log_yer].Success = true;
                            clients[send_sender_id].log_yer++;
                            /*filling logs,message Received from sender*/
                            /*stores time*/
                            clients[send_nei].Client_Log[clients[send_nei].log_yer].Timestamp = (char*)malloc(20*sizeof(char));
                            strcpy( clients[send_nei].Client_Log[clients[send_nei].log_yer].Timestamp,time_stamp());
                            /*stores number of frame*/
                            clients[send_nei].Client_Log[clients[send_nei].log_yer].Number_of_frames = i;
                            /*increases number of hops*/
                            clients[send_nei].Number_of_hops++;
                            /*allocates informations and store*/
                            clients[send_nei].Client_Log[clients[send_nei].log_yer].Sender_ID = (char*)malloc(20*sizeof(char));
                            clients[send_nei].Client_Log[clients[send_nei].log_yer].Receiver_ID = (char*)malloc(20*sizeof(char));
                            clients[send_nei].Client_Log[clients[send_nei].log_yer].Message = (char*)malloc(uzunluk*sizeof(char));
                            clients[send_nei].Client_Log[clients[send_nei].log_yer].Activity = (char*)malloc(20*sizeof(char));
                            strcpy(clients[send_nei].Client_Log[clients[send_nei].log_yer].Sender_ID,send_sender);
                            strcpy(clients[send_nei].Client_Log[clients[send_nei].log_yer].Receiver_ID,receiver);
                            strcpy(clients[send_nei].Client_Log[clients[send_nei].log_yer].Message,mesaj);
                            /*stores activity*/
                            strcpy(clients[send_nei].Client_Log[clients[send_nei].log_yer].Activity,"Message Received");
                            /*if succes store true, else false*/
                            clients[send_nei].Client_Log[clients[send_nei].log_yer].Success = true;
                            clients[send_nei].log_yer++;
                            /*changing sender*/
                            send_sender_id = send_nei;

                            /*changing mac address*/
                            if(strcmp(clients[send_sender_id].ClientID,clients[send_sender_id].Incoming_queue->frame_queue[0].Array[0].receiver_info)!= 0){
                                hop++;
                                Frame* temp2 = Allqueue(clients[send_sender_id].Incoming_queue);
                                send_nei = findneighbor(cli_num,clients,send_sender_id,clients[send_sender_id].Incoming_queue->frame_queue[0].Array[0].receiver_info);
                                int deneme = clients[send_sender_id].Incoming_queue->front;
                                for(i=0;i<clients[send_sender_id].Incoming_queue->rear+1-deneme;i++){
                                    printf("Frame #%d MAC address change: New sender MAC %s, new receiver MAC %s\n",i+1,clients[send_sender_id].ClientMacAdress,clients[send_nei].ClientMacAdress);

                                    strcpy(temp2->Array[3].sender_info,clients[send_sender_id].ClientMacAdress);
                                    strcpy(temp2->Array[3].receiver_info,clients[send_nei].ClientMacAdress);
                                    enqueue2(clients[send_sender_id].Outgoing_queue,temp2[i]);
                                    dequeue(clients[send_sender_id].Incoming_queue);
                                }
                            }
                            /*finish progress statement*/
                            if(strcmp(clients[send_sender_id].ClientID,clients[send_sender_id].Incoming_queue->frame_queue[0].Array[0].receiver_info) == 0){

                                printf("A message received by client %s from client %s after a total of %d hops.\n",clients[send_sender_id].Incoming_queue->frame_queue[0].Array[0].receiver_info,clients[send_sender_id].Incoming_queue->frame_queue[0].Array[0].sender_info,hop);
                                int k;
                                int deneme = clients[send_sender_id].Incoming_queue->front;
                                printf("Message: ");
                                for(k=0;k<clients[send_sender_id].Incoming_queue->rear+1-deneme;k++){
                                    printf("%s",clients[send_sender_id].Incoming_queue->frame_queue[k].Array[0].message);
                                    dequeue(clients[send_sender_id].Incoming_queue);
                                    /*free frame*/
                                    int i=0;
                                    for(i;i<4;i++){
                                        free(clients[send_sender_id].Incoming_queue->frame_queue[k].Array[i].message);
                                        free(clients[send_sender_id].Incoming_queue->frame_queue[k].Array[i].receiver_info);
                                        free(clients[send_sender_id].Incoming_queue->frame_queue[k].Array[i].sender_info);
                                    }
                                }
                                printf("\n");
                                finish++;
                                break;
                            }
                        }
                }
                /*if token == PRINT_LOG*/
                else if(strcmp(token,"PRINT_LOG")==0){
                        char* print_logs = strtok(NULL, " ");
                        printf("Client %s Logs:\n",print_logs);
                        int print_logs_id = findIdNumber(cli_num,clients,print_logs);
                        int i =0;
                        for(i;i<clients[print_logs_id].log_yer;i++){
                            printf("--------------\n");
                            printf("Log Entry #%d:\n",i+1);
                            printf("Timestamp: %s\n",clients[print_logs_id].Client_Log[i].Timestamp);
                            printf("Message: %s\n",clients[print_logs_id].Client_Log->Message);
                            printf("Number of frames: %d\n",clients[print_logs_id].Client_Log[i].Number_of_frames);
                            printf("Number of hops: %d\n",clients[print_logs_id].Number_of_hops);
                            printf("Sender ID: %s\n",clients[print_logs_id].Client_Log[i].Sender_ID);
                            printf("Receiver ID: %s\n",clients[print_logs_id].Client_Log[i].Receiver_ID);
                            printf("Activity: %s\n",clients[print_logs_id].Client_Log[i].Activity);
                            if(clients[print_logs_id].Client_Log[i].Success == true){
                                printf("Success: Yes\n");

                            }
                            else if(clients[print_logs_id].Client_Log[i].Success == false){
                                printf("Success: No\n");
                            }
                        }
                }
                else{
                    printf("Invalid command.\n");
                }
                break;
            }
        base++;
        }
    /*free memories and close inputs*/
    fclose(clientsdat);
    fclose(routingdat);
    fclose(commandsdat);
    int j=0;
    for(j;j<cli_num-1;j++){
        free(clients[j].ClientID);
        free(clients[j].ClientIpAdress);
        free(clients[j].ClientMacAdress);
        free(clients[j].RTable);
        free(clients[j].Client_Log);
        free(clients[j].Incoming_queue);
        free(clients[j].Outgoing_queue);
    }
    free(clients);
return 0;
}
