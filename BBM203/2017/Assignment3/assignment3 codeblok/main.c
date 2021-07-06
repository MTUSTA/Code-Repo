/* A complete working C program to demonstrate all insertion methods
/ on Linked List*/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <ctype.h>
#include <string.h>
/* A linked list node*/
typedef struct Node{
    char dosya[256];
    char kelime[256];
    int data;
    struct Node *next;
    struct Node *alt_alta;

}Node_t;
/* Given a reference (pointer to pointer) to the head of a list and an int, inserts a new node on the front of the list. */
void push( Node_t** head_ref, int new_data){
	/* 1. allocate node */
	 Node_t* new_node = ( Node_t*) malloc(sizeof(Node_t));
	/* 2. put in the data */
	new_node->data = new_data;
	/* 3. Make next of new node as head */
	new_node->next = (*head_ref);
	/* 4. move the head to point to the new node */
	(*head_ref) = new_node;
}
/* Given a node prev_node, insert a new node after the given prev_node */
void insertAfter(Node_t* prev_node, int new_data,char* gelen_string){
	/*1. check if the given prev_node is NULL */
	if (prev_node == NULL)	{
	printf("the given previous node cannot be NULL");
	return;
	}
	/* 2. allocate new node */
	Node_t* new_node =(Node_t*) malloc(sizeof(Node_t));
	/* 3. put in the data */
	new_node->data = new_data;
	strcpy(new_node->kelime,gelen_string);
	/* 4. Make next of new node as next of prev_node */
	new_node->next = prev_node->next;
	/* 5. move the next of prev_node as new_node */
	prev_node->next = new_node;
}
/* Given a reference (pointer to pointer) to the head of a list and an int, appends a new node at the end */
void append(Node_t** head_ref, int new_data,char* gelen_string){
	/* 1. allocate node */
	Node_t* new_node = (Node_t*) malloc(sizeof(Node_t));
	Node_t* last = *head_ref; /* used in step 5*/
	/* 2. put in the data */
	new_node->data = new_data;
	strcpy(new_node->kelime,gelen_string);
	/* 3. This new node is going to be the last node, so make next of it as NULL*/
	new_node->next = NULL;
	new_node->alt_alta=NULL;
	/* 4. If the Linked List is empty, then make the new node as head */
	if (*head_ref == NULL){
            *head_ref = new_node;
        return;
	}
	/* 5. Else traverse till the last node */
	while (last->next != NULL)
		last = last->next;
	/* 6. Change the next of last node */
	last->next = new_node;
	return;
}
void deleteNode(Node_t **head_ref, char* kelime){
    Node_t* temp = *head_ref, *prev;
    if (temp != NULL && strcmp(temp->kelime,kelime)==0){
        *head_ref = temp->next;
        free(temp);
        return;
    }
    while (temp != NULL && strcmp(temp->kelime,kelime)!=0){
        prev = temp;
        temp = temp->next;
    }
    if (temp == NULL) return;
    prev->next = temp->next;
    free(temp);
}
/* Bubble sort the given linked lsit */
void bubbleSort(Node_t *start){
    int swapped, i;
    Node_t *ptr1;
    Node_t *lptr = NULL;
    /* Checking for empty list */
    if (ptr1 == NULL)
        return;
    do{
        swapped = 0;
        ptr1 = start;
        while (ptr1->next != lptr){
            if (ptr1->data > ptr1->next->data){
                swap(ptr1, ptr1->next);
                swapped = 1;
            }
            ptr1 = ptr1->next;
        }
        lptr = ptr1;
    }
    while (swapped);
}
/* function to swap data of two nodes a and b*/
void swap(struct Node *a, struct Node *b){
    Node_t* temp4 = (Node_t*) malloc(sizeof(Node_t));
    char temp2[256],temp3[256];
    int temp = a->data;
    strcpy(temp2 , a->kelime);
    strcpy(temp3 , a->dosya);
    temp4->alt_alta = a->alt_alta;

    a->data = b->data;
    strcpy(a->kelime,b->kelime);
    strcpy(a->dosya ,b->dosya);
    a->alt_alta=b->alt_alta;

    strcpy(b->kelime,temp2);
    strcpy(b->dosya ,temp3);
    b->data = temp;
    b->alt_alta=temp4->alt_alta;
}
/* Function to reverse the linked list */
void reverse( Node_t** head_ref){
    Node_t* prev   = NULL;
    Node_t* current = *head_ref;
    Node_t* next;
    while (current != NULL){
        next  = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }
    *head_ref = prev;
}
/* This function prints contents of linked list starting from head*/
void printList(Node_t *node){
    while (node != NULL){
        printf("data %d ,kelime %s, \n", node->data,node->kelime);
        while (node->alt_alta != NULL){
            printf("\n altalt %s %d \n", node->alt_alta->kelime,node->alt_alta->data);
            node->alt_alta = node->alt_alta->next;
        }
        printf("\n");
        node = node->next;
    }
}
/*control same word in linked list*/
int kontrol_mekanizmasi(Node_t *gelen_node,char* gelen_kelime,int *alt_icin,char* alt_icin2){/*alt liste 0 geliyor*/

    while(gelen_node != NULL){
        if(strcmp(gelen_node->kelime, gelen_kelime)==0){
            /*sonrakini eklemek icin veri sakla*/
            strcpy(alt_icin2,gelen_node->kelime);
            (*alt_icin)=1;


            gelen_node->data=gelen_node->data+1;
            return gelen_node->data;
        }
        else{
            gelen_node=gelen_node->next;
        }
    }
    return 1;
}
int kontrol_mekanizmasi_alt(Node_t *gelen_node,char* gelen_kelime){/*alt liste 0 geliyor*/
    Node_t *deneme=gelen_node;
    while(deneme != NULL){
        if(strcmp(deneme->kelime, gelen_kelime)==0){
                printf("%s %d",deneme->kelime,deneme->data);
            return deneme->data;
        }
            deneme=deneme->next;
    }
    return 1;
}


void ozel_ekleme(Node_t *gelen_node,char* kelime,int gelen_data){
    strlwr(kelime);
    Node_t* yer_belirleme;
        while(gelen_node != NULL){
                if(gelen_node->data<gelen_data){
                    insertAfter(yer_belirleme,gelen_data,kelime);
                    break;
                }
                else{
                    yer_belirleme= gelen_node;
                    gelen_node=gelen_node->next;
                }
    }
}
int matris_kontrol(Node_t *birinci,Node_t *ikinci){

    Node_t *ikinci_koruma = ikinci;
    while (birinci != NULL){
            while (ikinci_koruma != NULL){
                if(strcmp(ikinci_koruma->kelime,birinci->kelime)==0){
                    printf("birinci %s ikinci  %s ikinci data %d \n",birinci->kelime,ikinci_koruma->kelime,ikinci_koruma->data);
                        return ikinci_koruma->data;
                }
                ikinci_koruma=ikinci_koruma->next;
            }
        birinci = birinci->next;
        ikinci_koruma=ikinci;
    }
    return 0;
}
int matris_kontrol_2(Node_t *birinci,Node_t *ikinci){
    Node_t *ikinci_koruma = ikinci;
    printf("data %d , kelime %s\n", birinci->data,birinci->kelime);

    while (birinci != NULL){/*bir kelime sectim*/
            char kullandik[256];
            int yokluk=0,kullanilmis_sec=0;
            while (ikinci_koruma != NULL){/*ikinci geleni sectim
                printf("data %d ,kelime %s\n", ikinci_koruma->data,ikinci_koruma->kelime);*/

                if(strcmp(ikinci_koruma->kelime,birinci->kelime)==0){/*ikinci geleni sectim
                    printf("birinci %s ikinci  %s ikincidata %d \n",birinci->kelime,ikinci_koruma->kelime,ikinci_koruma->data);*/
                    yokluk++;
                    if(kullanilmis_sec==0){
                        strcpy(kullandik,ikinci_koruma->kelime);
                        kullanilmis_sec++;
                    }
                    break;
                }
                ikinci_koruma=ikinci_koruma->next;
            }
            if(yokluk==0){
                printf("00000 %s\n",birinci->kelime);
                strcpy(birinci->kelime,kullandik);
                printf("00000 %s\n",birinci->kelime);
                return 0;
            }
        birinci = birinci->next;
        ikinci_koruma=ikinci;
    }
    return 0;
}
/* Driver program to test above functions*/
int main(){
    Node_t* head = NULL;
    Node_t* head2 = NULL;
    int surekli=1,ilki_okudu=0;
    while(surekli!=0){
        char komut[256];
        char emir1[256],emir2[256],emir3[256];
        printf("command:");
        gets(komut);
        char *token = strtok(komut," ");
        int yonlendirme=1;
        while (token != NULL){
            if(yonlendirme==3){
                strcpy(emir3,token);
                yonlendirme++;
            }
            if(yonlendirme==2){
                strcpy(emir2,token);
                yonlendirme++;
            }
            if(yonlendirme==1){
                strcpy(emir1,token);
                yonlendirme++;
            }
            token = strtok(NULL, " ");
        }
        int alt_liste_icin=0;
        char alt_string[256];
        if(strcmp(emir1, "-r") == 0){
            FILE * girdi1=fopen(emir2,"r");
            char while_girdi1[256];
            while(fscanf(girdi1,"%s",&while_girdi1) != EOF) {
                /*printf("kelimeler %s \n",while_girdi1);*/
                strlwr(while_girdi1);/*C Program to change uppercase characters to lowercase characters in a string using strlwr function */
                /*sondan silme*/
                while( while_girdi1[strlen(while_girdi1)-1]<48 || while_girdi1[strlen(while_girdi1)-1]>57 && while_girdi1[strlen(while_girdi1)-1]<65 || while_girdi1[strlen(while_girdi1)-1]>90 && while_girdi1[strlen(while_girdi1)-1]<97 || while_girdi1[strlen(while_girdi1)-1]>122){
                    while_girdi1[strlen(while_girdi1)-1]=0;
                    strcpy(while_girdi1,strtok(while_girdi1," "));
                }
                /*bastan silme*/
                while(while_girdi1[0]<48 || while_girdi1[0]>57 && while_girdi1[0]<65 || while_girdi1[0]>90 && while_girdi1[0]<97 || while_girdi1[0]>122){
                    int bastan_silme,bastan_silme_dongu=0;
                    bastan_silme=strlen(while_girdi1);
                    for(bastan_silme_dongu;bastan_silme_dongu<bastan_silme;bastan_silme_dongu++){
                        while_girdi1[bastan_silme_dongu]=while_girdi1[bastan_silme_dongu+1];
                    }
                }
                int i_1=0,split=0;
                for(i_1;i_1<strlen(while_girdi1);i_1++){
                    if(while_girdi1[i_1]<48 || while_girdi1[i_1]>57 && while_girdi1[i_1]<65 || while_girdi1[i_1]>90 && while_girdi1[i_1]<97 || while_girdi1[i_1]>122){
                        split++;
                        break;
                    }
                }
                Node_t *yedek2=head;
                if(split==1){
                    while_girdi1[i_1]=' ';
                    char *splitleme_kelime=strtok(while_girdi1," ");
                    while (splitleme_kelime != NULL){

                        if(kontrol_mekanizmasi(head,splitleme_kelime,&alt_liste_icin,alt_string)==1){
                            append(&head,1,splitleme_kelime);

                        }
                        splitleme_kelime = strtok(NULL, " ");
                    }
                }
                Node_t *yedek1=head;
                if(split==0){/*important point!! if we use && not good result*/
                        if(alt_liste_icin==1){
                            int sayi=kontrol_mekanizmasi_alt(yedek1,while_girdi1);/*sayi ögren*/
                            printf("sayi %d \n",sayi);
                            while(yedek1 != NULL){
                                        if(strcmp(yedek1->kelime, alt_string)==0){
                                        printf("%s , %s , %s \n",yedek1->kelime,alt_string,while_girdi1);
                                        append(&(yedek1->alt_alta),sayi,while_girdi1);/*1 sayisi dinamiklestirilmeli*/
                                    }
                                    yedek1=yedek1->next;

                            }
                            alt_liste_icin=0;
                        }
                        if(kontrol_mekanizmasi(head,while_girdi1,&alt_liste_icin,alt_string)==1){
                                append(&head,1,while_girdi1);

                        }
                }
            }
            /*bubbleSort(head);
            reverse(&head);*/
            strcpy(head->dosya,emir2);
            printList(head);

            if(ilki_okudu==0){
                head2=head;
                head=NULL;
                ilki_okudu++;
            }
            /*printf("\n linked listler \n");
            printList(head);
            printf("\n 2. \n");
            printList(head2);
            printf("\n \n\n");*/
        }
        if(strcmp(emir1, "-a") == 0){
            if(strcmp(head2->dosya, emir3)==0){
                ozel_ekleme(head2,emir2,3);
                printf("\nson\n");
                printList(head2);
            }
            if(head !=NULL){
                if(strcmp(head->dosya, emir3)==0){
                    ozel_ekleme(head,emir2,3);
                    /*printf("\nson\n");
                    printList(head);*/
                }
            }

        }
        if(strcmp(emir1, "-n2") == 0){
            printf("emir2 %s \n",emir2);
        }
        if(strcmp(emir1, "-d") == 0){
            if(strcmp(head2->dosya, emir3)==0){
                deleteNode(&head2,emir2);
                /*printf("\nson\n");
                printList(head2);*/
            }
            if(head !=NULL){
                if(strcmp(head->dosya, emir3)==0){
                    deleteNode(&head,emir2);
                    /*printf("\nson\n");
                    printList(head);*/
                }
            }
        }
        if(strcmp(emir1, "-s") == 0){
            Node_t* yedek = head;/*d2*/
            Node_t* yedek2 = head2;/*d1*/
            int baslangic_i=0,baslangic_k=10;
            int matris[2][20];
            if(strcmp(head2->dosya, emir3)==0){

                while (yedek2 != NULL && baslangic_i<10){
                    printf("data %d ,kelime %s\n", yedek2->data,yedek2->kelime);
                    matris[0][baslangic_i]=yedek2->data;
                    matris[1][baslangic_i]=matris_kontrol(yedek2,yedek);

                    yedek2 = yedek2->next;
                    baslangic_i++;
                }
                yedek2 = head2;
            }

            if(strcmp(head2->dosya, emir2)==0){

                while (yedek2 != NULL && baslangic_i<10){
                    printf("data %d ,kelime %s\n", yedek2->data,yedek2->kelime);

                    matris[0][baslangic_i]=yedek2->data;
                    matris[1][baslangic_i]=matris_kontrol(yedek2,yedek);

                    yedek2 = yedek2->next;
                    baslangic_i++;
                }
                yedek2 = head2;
            }
            printf("\n \n \n");
            if(strcmp(head->dosya, emir3)==0){
                while (yedek != NULL && baslangic_k<20){
                    /*printf("data %d ,kelime %s\n", yedek->data,yedek->kelime);*/

                    matris[0][baslangic_k]=matris_kontrol_2(yedek,yedek2);
                    matris[1][baslangic_k]=yedek->data;

                    yedek = yedek->next;
                    baslangic_k++;
                }
                yedek = head;
            }

            if(strcmp(head->dosya, emir2)==0){
                while (yedek != NULL && baslangic_k<20){
                    printf("data %d ,kelime %s\n", yedek->data,yedek->kelime);

                    matris[0][baslangic_k]=matris_kontrol_2(yedek,yedek2);
                    matris[1][baslangic_k]=yedek->data;

                    yedek = yedek->next;
                    baslangic_k++;
                }
                yedek = head;
            }
            int s,s_1,s_2,nokta_carpim,uzunluk_A=0,uzunluk_B=0;
            /*dot product*/
            for(s=0;s<20;s++){
                nokta_carpim = matris[0][s]*matris[1][s];
            }
            for(s_1=0;s_1<20;s_1++){
                uzunluk_A = uzunluk_A+(matris[0][s]*matris[0][s]);
            }
            uzunluk_A=sqrt(uzunluk_A);
            for(s_2=0;s_2<20;s_2++){
                uzunluk_B = uzunluk_B+(matris[1][s]*matris[1][s]);
            }
            uzunluk_B=sqrt(uzunluk_B);
            int benzerlik=0;
            benzerlik=nokta_carpim/(uzunluk_A*uzunluk_B);
            double sonuc;
            sonuc=cos(benzerlik);
            printf("Cosine Similarity of %s and %s is %lf ",head2->dosya,head->dosya,sonuc);
        }
        if(strcmp(emir1, "-q") == 0){
            surekli=0;
            printf("CIK GIT LA BURDAN \n");
        }
    }
    return 0;
}
