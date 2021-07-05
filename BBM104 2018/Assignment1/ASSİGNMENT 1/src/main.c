#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define true  1
#define false 0
typedef struct {
    /*character type*/
    char karakter_turu[200];
    /*character name*/
    char karakter_adi[200];
    /*character hp*/
    int karakter_cani;
    /*character damage*/
    int karakter_hasari;
    /*character experiment*/
    int karakter_tecrube;
    /*coordinate x and y*/
    int koor_x,koor_y;
}karakter;
    /*this function controls given attack state*/
    int kontrol(int gelen_row ,int gelen_colon,int gelen_x,int gelen_y,char **matris2,char *tur,karakter *gelen_karakter2,int gelen_array_sayisi2,int *dayak){
        /*this status controls coordinate .if out of map, return false */
        if((0>gelen_x) || (gelen_x==gelen_row) || (0>gelen_y) || (gelen_y==gelen_colon)){
            return false;
        }
        /*if x,y coordinate is empty in map, return false*/
        else if(matris2[gelen_x][gelen_y] == '.'){
            return false;
        }
        /*this status controls same type of characters can not attack each other.*/
        else if(matris2[gelen_x][gelen_y] != '.'){
                int gezici=0;
                for(gezici;gezici<gelen_array_sayisi2;gezici++){
                    if(gelen_karakter2[gezici].karakter_adi[0] == matris2[gelen_x][gelen_y]){
                        if(strcmp(gelen_karakter2[gezici].karakter_turu,tur)==0){
                            return false;
                        }
                        else{
                            *dayak=gezici;
                            return true ;
                        }
                    }
                }
        }
    }
    /*the characters for the given <type>will attack and others will lose their health*/
    void azaltma(int dayak2,karakter *gelen_karakter2,int dovenin_inti,char **matris2){
        if(strcmp(gelen_karakter2[dovenin_inti].karakter_turu,"HERO")==0){
                gelen_karakter2[dayak2].karakter_cani -= gelen_karakter2[dovenin_inti].karakter_hasari;
                if(gelen_karakter2[dayak2].karakter_cani <=0){
                    gelen_karakter2[dayak2].karakter_cani=0;
                    gelen_karakter2[dovenin_inti].karakter_tecrube++;
                    matris2[gelen_karakter2[dayak2].koor_x][gelen_karakter2[dayak2].koor_y]='.';
                }
        }
        else if(strcmp(gelen_karakter2[dovenin_inti].karakter_turu,"MONSTER")==0){
                gelen_karakter2[dayak2].karakter_cani -= gelen_karakter2[dovenin_inti].karakter_hasari;
                if(gelen_karakter2[dayak2].karakter_cani <=0){
                    gelen_karakter2[dayak2].karakter_cani=0;
                    matris2[gelen_karakter2[dayak2].koor_x][gelen_karakter2[dayak2].koor_y]='.';
                }
        }
    }
    /*Characters can attack any adjacent square,this function sets the direction for the attack of the character*/
    int saldirma(int row,int colon,char **matris1,karakter *gelen_karakter,int gelen_gezici_int,int gelen_array_sayisi){
        int dayak_yiyecek;
        /*North*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x-1,gelen_karakter[gelen_gezici_int].koor_y,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true){
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
        /*North East*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x-1,gelen_karakter[gelen_gezici_int].koor_y+1,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true ){
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
        /*East*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x,gelen_karakter[gelen_gezici_int].koor_y+1,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true){
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
        /*South East*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x+1,gelen_karakter[gelen_gezici_int].koor_y+1,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true ){
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
        /*South*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x+1,gelen_karakter[gelen_gezici_int].koor_y,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true){/*saldirmasi icin adam var mi kontrol et*/
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
        /*South West*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x+1,gelen_karakter[gelen_gezici_int].koor_y-1,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true ){
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
        /*West*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x,gelen_karakter[gelen_gezici_int].koor_y-1,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true){
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
        /*North West*/
        if(kontrol(row,colon,gelen_karakter[gelen_gezici_int].koor_x-1,gelen_karakter[gelen_gezici_int].koor_y-1,matris1,gelen_karakter[gelen_gezici_int].karakter_turu,gelen_karakter,gelen_array_sayisi,&dayak_yiyecek) == true ){
            azaltma(dayak_yiyecek,gelen_karakter,gelen_gezici_int,matris1);
        }
    }
/*Run the program */
int main(int argc,char* argv[]){
    FILE *karakter_girdi= fopen(argv[1],"r");/*argument for reading information of character */
    FILE *komutlar= fopen(argv[2],"r");/*argument for reading commands*/
    char *sayicik = strtok(argv[1], "_");/*define input number for output number */
    sayicik = strtok(NULL, ".");/*determine and obtain input number*/
    const char str1[] = "output";/*for output file*/
    const char str2[] = ".txt";/*for output file*/
    /*allocate memory for output file*/
    char *output= malloc(strlen(str1) + strlen(str2) + strlen(sayicik));
    /*output variable will be "output"*/
    strcpy(output, str1);
    /*merge "output"+sayicik result is output<input number>*/
    strcat(output, sayicik);
    /*merge "output<input number>*+str result is output<input number>.txt*/
    strcat(output, str2);
    FILE *cikti = fopen(output,"w");
    /*while_girdi1 and while_girdi2 for read file*//*matris1 2d char matrix variable*/
    char while_girdi1[1000],while_girdi2[1000],**matris1;
    /*array_sayisi is defined as how many characters are counted in the file*/
    /*row and colon is defined for loadmap function*//*yasayan is if all the characters die in a species,this variable stop loop. yasayan has a task to finish*/
    int array_sayisi =0,array_doldurma =0,row,colon,yasayan=0;
    /*function obtains the number of rows*/
    while(fscanf(karakter_girdi, "%s\n",&while_girdi1) != EOF) {
            array_sayisi++;
    }
    /*divide the structure by the number of rows */
    karakter* karakter_array=(karakter*)malloc(sizeof(karakter)*array_sayisi);
    rewind(karakter_girdi);/*Reading the same file twice in the code in C*/
    /*this loop reads character argument lines*/
    while(fscanf(karakter_girdi, "%s\n",&while_girdi1) != EOF) {
            /* Returns first token*/
            char *token = strtok(while_girdi1, ",");
            /* Keep printing tokens while one of the delimiters present in while_girdi1 */
            int karakter_struct_doldurma=0;
            /*this loop sets the structure elements*/
            while (token != NULL){
                /*this condition sets character type of element*/
                if (karakter_struct_doldurma==0){
                    strcpy(karakter_array[array_doldurma].karakter_turu,token);
                    karakter_struct_doldurma++;
                }
                /*this condition sets character name of element*/
                else if(karakter_struct_doldurma==1){
                    strcpy(karakter_array[array_doldurma].karakter_adi,token);
                    karakter_struct_doldurma++;
                }
                /*this condition sets character health of element*/
                else if(karakter_struct_doldurma==2){
                    int tamsayilastirma_can_icin = atoi (token);
                    karakter_array[array_doldurma].karakter_cani=tamsayilastirma_can_icin;
                    karakter_struct_doldurma++;
                }
                /*this condition sets character character damage and character experiment of element*/
                else if(karakter_struct_doldurma==3){
                    int tamsayilastirma_kahraman_hasar = atoi (token);
                    karakter_array[array_doldurma].karakter_hasari=tamsayilastirma_kahraman_hasar;
                    karakter_array[array_doldurma].karakter_tecrube=0;
                    karakter_struct_doldurma++;
                }
                token = strtok(NULL, ",");
            }
            array_doldurma++;
    }
    /*this loop reads command file lines. if all monster die,this function will stop*/
    while(fgets(while_girdi2, 1000, komutlar) && yasayan<1) {
        /*length of the sentence*/
        int string_sayi =strlen(while_girdi2);
        /*this if condition codes delete newline character*/
        if(while_girdi2[string_sayi-1]=='\n'){
            while_girdi2[string_sayi-1]='\0';
        }
        /*this code takes first word of the command line*/
        char *emir = strtok(while_girdi2, " ");
        if (strcmp(emir, "SHOW") == 0){
                emir = strtok(NULL, " ");
                /*write map status in output file*/
                if (strcmp(emir, "MAP") == 0){
                    /*print 2d array */
                        fprintf(cikti,"MAP STATUS\n");
                        int i,j;
                        for (i = 0; i < row; i++) {
                            for (j = 0; j < colon; j++) {
                                fprintf(cikti,"%c ", matris1[i][j]);
                            }
                            fprintf(cikti,"\n");
                        }
                        fprintf(cikti,"\n");
                }
                 /*write heroes health and experiment in output file*/
                else if (strcmp(emir, "HERO") == 0){
                        fprintf(cikti,"HERO STATUS\n");
                        int gezici_int =0;
                        for(gezici_int;gezici_int<array_sayisi;gezici_int++){
                            if(strcmp(karakter_array[gezici_int].karakter_turu, "HERO") == 0){
                                fprintf(cikti,"%s HP: %d XP: %d \n",karakter_array[gezici_int].karakter_adi,karakter_array[gezici_int].karakter_cani,karakter_array[gezici_int].karakter_tecrube);
                            }
                        }
                        fprintf(cikti,"\n");
                }
                /*write monsters health in output file*/
                else if (strcmp(emir, "MONSTER") == 0){
                    fprintf(cikti,"MONSTER STATUS\n");
                    int gezici_int2 =0;
                    for(gezici_int2;gezici_int2<array_sayisi;gezici_int2++){
                        if(strcmp(karakter_array[gezici_int2].karakter_turu, "MONSTER") == 0){
                            fprintf(cikti,"%s HP: %d \n",karakter_array[gezici_int2].karakter_adi,karakter_array[gezici_int2].karakter_cani);
                        }
                    }
                    fprintf(cikti,"\n");
                }
        }
        /*this function creates 2d array and allocates the necessary space*/
        else if (strcmp(emir, "LOADMAP") == 0){
                    row = atoi(strtok(NULL, " "));
                    colon = atoi(strtok(NULL, " "));
                    /*adjust the width of the matrix*/
                    int callocla_yer_acma,i,j;
                    matris1=(char**)calloc(row,sizeof(char*));
                    for(callocla_yer_acma=0;callocla_yer_acma<row;callocla_yer_acma++){
                        matris1[callocla_yer_acma]=(char*)calloc(colon,sizeof(char));
                    }
                    /*fill matrix with '.'*/
                    for (i = 0; i < row; i++) {
                        for (j = 0; j < colon; j++) {
                            matris1[i][j] = '.';
                        }
                    }
        }
        /*this function places characters on the map and determines the coordinates of the characters in the structure*/
        else if (strcmp(emir, "PUT") == 0){
            emir = strtok(NULL, " ");
            char ad[200];int karakter_yerlestirme =0,koor_1,koor_2;
            while (emir != NULL){
                emir = strtok(NULL, " ");
                if(emir != NULL){
                    if(karakter_yerlestirme==0){
                        strcpy(ad,emir);
                        karakter_yerlestirme++;
                    }
                    else if(karakter_yerlestirme==1){
                        int gezici_int3=0;
                        karakter_yerlestirme++;
                        koor_1=atoi(emir);
                        for(gezici_int3;gezici_int3<array_sayisi;gezici_int3++){
                            if(strcmp(karakter_array[gezici_int3].karakter_adi,ad) == 0){
                                karakter_array[gezici_int3].koor_x=koor_1;
                            }
                        }
                    }
                    else if(karakter_yerlestirme==2){
                        int gezici_int4=0;
                        karakter_yerlestirme++;
                        koor_2=atoi(emir);
                        for(gezici_int4;gezici_int4<array_sayisi;gezici_int4++){
                            if(strcmp(karakter_array[gezici_int4].karakter_adi,ad) == 0){
                                karakter_array[gezici_int4].koor_y=koor_2;
                            }
                        }
                    }
                    if(karakter_yerlestirme==3){
                        matris1[koor_1][koor_2]=ad[0];
                        karakter_yerlestirme=0;
                    }
                }
            }
        }
        /*Executes the attack command for the given <type>.*//*If all the characters of a species die, the karar will increase and game will finish. free allocated memory*/
        else if (strcmp(emir, "ATTACK") == 0){
            emir = strtok(NULL, " ");
                if(strcmp(emir,"MONSTER")==0){
                    fprintf(cikti,"MONSTERS ATTACKED\n\n");
                    int gezici_int5=0;
                    for(gezici_int5;gezici_int5<array_sayisi;gezici_int5++){
                        if(strcmp(karakter_array[gezici_int5].karakter_turu,"MONSTER") == 0){
                            if(karakter_array[gezici_int5].karakter_cani>0){
                                saldirma(row,colon,matris1,karakter_array,gezici_int5,array_sayisi);
                            }
                        }
                    }
                }
                if(strcmp(emir,"HERO")==0){
                    fprintf(cikti,"HEROES ATTACKED\n\n");
                    int gezici_int6=0;
                    for(gezici_int6;gezici_int6<array_sayisi;gezici_int6++){
                        if(strcmp(karakter_array[gezici_int6].karakter_turu,"HERO") == 0){
                            if(karakter_array[gezici_int6].karakter_cani>0){
                                    saldirma(row,colon,matris1,karakter_array,gezici_int6,array_sayisi);
                            }
                        }
                    }
                    int gezici_int7=0,karar=0,gezici_int8=0,karar2=0;
                    for(gezici_int7;gezici_int7<array_sayisi;gezici_int7++){
                        if(strcmp(karakter_array[gezici_int7].karakter_turu,"MONSTER") == 0){
                            if(karakter_array[gezici_int7].karakter_cani>0){
                                karar++;
                            }
                        }
                    }
                    for(gezici_int8;gezici_int8<array_sayisi;gezici_int8++){
                        if(strcmp(karakter_array[gezici_int8].karakter_turu,"HERO") == 0){
                            if(karakter_array[gezici_int8].karakter_cani>0){
                                karar2++;
                            }
                        }
                    }
                    if(karar==0){
                        yasayan++;
                        fprintf(cikti,"ALL MONSTERS ARE DEAD!");
                        free(matris1);
                        free(output);
                    }
                    else if (karar2==0){
                        yasayan++;
                        fprintf(cikti,"ALL HEROES ARE DEAD!");
                        free(matris1);
                        free(output);
                    }
                }
        }
        /*This command will be only for heroes.this function controls the hero's condition with 3 conditions.if condition is appropriate, moves*/
        else if (strcmp(emir, "MOVE") == 0){
            fprintf(cikti,"HEROES MOVED\n");
            emir = strtok(NULL, " ");/*STRING HERO*/
            char isim[200];int x,y;
            while (emir !=NULL){
                        emir = strtok(NULL, " ");
                        if(emir !=NULL){
                            strcpy(isim,emir);
                            emir = strtok(NULL, " ");
                            x=atoi(emir);
                            emir = strtok(NULL, " ");
                            y=atoi(emir);
                            int gezme=0;
                            for(gezme;gezme<array_sayisi;gezme++){
                                    if(strcmp(karakter_array[gezme].karakter_adi,isim) == 0){
                                            if ((0>x) || (x==row) || (0>y) || (y==colon)){
                                                fprintf(cikti,"%s can't move. There is a wall.\n",isim);
                                            }
                                            else if(karakter_array[gezme].karakter_cani==0){
                                                fprintf(cikti,"%s can't move. Dead.\n",isim);
                                            }
                                            else if(matris1[x][y]!='.'){
                                                fprintf(cikti,"%s can't move. Place is occupied.\n",isim);
                                            }
                                            else{
                                                matris1[karakter_array[gezme].koor_x][karakter_array[gezme].koor_y]='.';
                                                karakter_array[gezme].koor_x=x;
                                                karakter_array[gezme].koor_y=y;
                                                matris1[x][y]=isim[0];
                                            }
                                    }
                            }
                        }
            }
            fprintf(cikti,"\n");
        }
    }
    /*close inputs and output*/
    fclose(karakter_girdi);
    fclose(komutlar);
    fclose(cikti);
    return 0;
}
