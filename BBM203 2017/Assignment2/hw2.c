#include <stdio.h>
#include <stdlib.h>
/*order struct for stack and queue*/
typedef struct stack1{
    int stack_tepe;
    char *stack_dizi;
    char *queue_dizi;
    char hata_array[1000];
    int front,rear;
    int max_queue_size,max_stack_size;
    int hata_yerlestirme;

}client_server;
/*if stack has a empty space ,function will fill the stack*/
int push (int musteri,char islem,client_server *gelen_stack){
    /*•	If a specific client’s stack is full, you should give an error character ’2’*/
        if(gelen_stack[musteri-1].stack_tepe>=gelen_stack[musteri-1].max_stack_size){/*control*/
            gelen_stack[musteri-1].hata_array[gelen_stack[musteri-1].hata_yerlestirme++]='2';
            return ;
        }
    gelen_stack[musteri-1].stack_dizi[gelen_stack[musteri-1].stack_tepe++]=islem;
}
/*if stack has elements,last element will go first out in the stack*/
int pop(int server,client_server *gelen_stack){
    gelen_stack[server-1].hata_array[gelen_stack[server-1].hata_yerlestirme++]=gelen_stack[server-1].stack_dizi[gelen_stack[server-1].stack_tepe-1];
    return gelen_stack[server-1].stack_dizi[--gelen_stack[server-1].stack_tepe];
}
/*control queue structure empty or not for enqueue method*/
int isFull(int musteri,client_server *gelen_queue)
{
    if( (gelen_queue[musteri-1].front == gelen_queue[musteri-1].rear + 1) || (gelen_queue[musteri-1].front == 0 && gelen_queue[musteri-1].rear == gelen_queue[musteri-1].max_queue_size-1)) {
            return 1;
    }
    return 0;
}
/*control queue structure empty or not for dequeue method*/
int isEmpty(int musteri,client_server *gelen_queue)
{
    if(gelen_queue[musteri-1].front == -1){
        return 1;
    }
    return 0;
}
/*if queue has a empty space ,function will fill the queue*/
int enqueue(int musteri, char islem,client_server *gelen_queue){

    /*is empty give error '1'*/
    if(isFull(musteri,gelen_queue)){
        gelen_queue[musteri-1].hata_array[(gelen_queue[musteri-1].hata_yerlestirme)++]='1';
    }
    else{
        if(gelen_queue[musteri-1].front == -1){
            gelen_queue[musteri-1].front = 0;
        }
        gelen_queue[musteri-1].rear = (gelen_queue[musteri-1].rear + 1) % gelen_queue[musteri-1].max_queue_size;
        gelen_queue[musteri-1].queue_dizi[gelen_queue[musteri-1].rear] = islem;
    }
}
/*if queue has  elements ,function will go first in first out in the queue*/
int dequeue(int musteri,client_server *gelen_queue){
    int element;
    /*if empty give error '3' and return 'k' for s progress*/
    if(isEmpty(musteri,gelen_queue)) {
        gelen_queue[musteri-1].hata_array[gelen_queue[musteri-1].hata_yerlestirme++]='3';

        return 'k';
    }
    else {
        element = gelen_queue[musteri-1].queue_dizi[gelen_queue[musteri-1].front];
        if (gelen_queue[musteri-1].front == gelen_queue[musteri-1].rear){
            gelen_queue[musteri-1].front = -1;
            gelen_queue[musteri-1].rear = -1;
        } /* Q has only one element, so we reset the queue after dequeing it.  */
        else {
            gelen_queue[musteri-1].front = (gelen_queue[musteri-1].front + 1) % gelen_queue[musteri-1].max_stack_size;
        }

        gelen_queue[musteri-1].hata_array[gelen_queue[musteri-1].hata_yerlestirme++]=element;
        return(element);
    }
}
/*generates output and fills with log history*/
void dosya_yazma(client_server *item1,int server_sayi,FILE *output1){
    int donmesi_gerek=0;
    for(donmesi_gerek;donmesi_gerek<server_sayi;donmesi_gerek++){
            int array_icin=0;
            for(array_icin;array_icin<item1[donmesi_gerek].hata_yerlestirme;array_icin++){
                fprintf(output1, "%c ",item1[donmesi_gerek].hata_array[array_icin]);
            }
            fprintf(output1, "\n");
    }
}
int main(int argc,char* argv[]){
    /*Defined for command line*/
    FILE *girdi1= fopen(argv[1],"r");
    FILE *girdi2= fopen(argv[2],"r");
    FILE *output1 = fopen(argv[3], "w");

    int server_and_clients_doldurma=0,while_girdi1,while_girdi2,server_icin_sayi;
    /*determine clients and server number*/
    fscanf(girdi1, "%d\n",&server_icin_sayi);
    /*defines client and server struct*/
    client_server* item=(client_server*)malloc(sizeof(client_server)*while_girdi1);
    /*order structs*/
    while(fscanf(girdi1, "%d %d\n",&while_girdi1,&while_girdi2) != EOF) {
            /*set queue structure*/
            item[server_and_clients_doldurma].max_queue_size=while_girdi1;
            item[server_and_clients_doldurma].front=-1;
            item[server_and_clients_doldurma].rear=-1;
            item[server_and_clients_doldurma].queue_dizi=(char*)malloc(sizeof(char)*while_girdi1);
            /*set stack structure*/
            item[server_and_clients_doldurma].max_stack_size=while_girdi2;
            item[server_and_clients_doldurma].stack_tepe=0;
            item[server_and_clients_doldurma].stack_dizi=(char*)malloc(sizeof(char)*while_girdi2);
            server_and_clients_doldurma++;
            item[server_and_clients_doldurma].hata_yerlestirme=0;
    }
    int islem_sayisi,while2;
    char while1,while3,while4;
    /*determines the number of transactions to be performed*/
    fscanf(girdi2, "%d\n",&islem_sayisi);
    int okuma_sayisi=0;
    /*start operations according to first letter*/
    while(fscanf(girdi2,"%c ",&while1)!= EOF){
        if(while1!='O'){
            fscanf(girdi2,"%d %c\n",&while2,&while3);
            if(while1=='A' && okuma_sayisi<islem_sayisi){/*Start enqueue operation*/
                enqueue(while2,while3,item);
            }
            if(while1=='I' && okuma_sayisi<islem_sayisi){ /*Start push operation for client or server stack*/
               push(while2,while3,item);
            }
            /*control client stack. if client stack has elements, pop it and start enqueue for server enqueue.
            if client has no elements in stack but has element is queue,dequeue it and start enqueue for server enqueue */
            if(while1=='S' && okuma_sayisi<islem_sayisi){
                /*control stack. İf stack is empty, dequeue client's queue element and enqueue element for server */
                if(item[while2-1].stack_tepe==0){
                    char koparilcak =dequeue(while2,item);
                    if(koparilcak!='k'){
                        enqueue(server_icin_sayi,koparilcak,item);
                    }
                }
                /*control stack. İf stack is not empty, pop client's stack element and enqueue element for server */
                if(item[while2-1].stack_tepe!=0){
                    enqueue(server_icin_sayi,pop(while2,item),item);
                }
            }
        }
        /*this progress deletes stack and queue*/
        /*control server stack.if server has stack elements,delete stack one by one
        if server has no stack elements,delete queue one by one*/
        if(while1=='O'){
            fscanf(girdi2,"%c %c\n",&while4,&while3);
            if(item[server_icin_sayi-1].stack_tepe==0 ){
                dequeue(server_icin_sayi,item);
            }
            if(item[server_icin_sayi-1].stack_tepe!=0){
                    pop(server_icin_sayi,item);
            }
        }
    }
    /*write output starts here*/
    dosya_yazma(item,server_icin_sayi,output1);
    /*close input file*/
    fclose(girdi1);
    fclose(girdi2);
    fclose(output1);
    return 0;
}
