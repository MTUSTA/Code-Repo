#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define true  1
#define false 0


int main(int argc,char* argv[]){
    FILE *maze_input= fopen(argv[1],"r");/*argument for reading*/
    FILE *cikti = fopen("path.txt", "w");/*write output.txt file*/
    char **matris1;/*define matris*/
    char line[30],tek;
    int genislik_belirleme_dongu=0,genislik_belirleme;

    while(fgets(line, sizeof(line), (FILE*) maze_input)) {/*determine the width*/
            if(genislik_belirleme_dongu==0){
            genislik_belirleme=strlen(line)-1;
            genislik_belirleme_dongu=1;
        }
    }

    int mallocla_yer_acma;
    matris1=(int**)malloc(genislik_belirleme*sizeof(int*));/*adjust the width of the matrix*/
    for(mallocla_yer_acma=0;mallocla_yer_acma<genislik_belirleme;mallocla_yer_acma++){
        matris1[mallocla_yer_acma]=(int*)malloc((genislik_belirleme*sizeof(int)));
    }

    rewind(maze_input);/*re-reading input file*/
    int satir=0,sutun=0;
    while( (tek=fgetc(maze_input))  != EOF ) {/*fill matrix*/
            if(tek != '\n'){
                matris1[satir][sutun]=tek;
                sutun++;
                if(sutun==genislik_belirleme){
                satir++;
                sutun=0;
                }
            }
    }
    int bas_row,bas_colon,row,colon;
    for(row=0;row<1;row++){/*determine start coordinates(S)*/
        for(colon=0;colon<genislik_belirleme;colon++){
                if(matris1[row][colon]=='S'){
                    bas_row=row;
                    bas_colon=colon;
                }
        }
    }

    int anahtar_deger=0;
    char anahtarimiz,kapi;
    fprintf(cikti,"%s","Start ");
    start(bas_row,bas_colon,genislik_belirleme,matris1,anahtar_deger,anahtarimiz,kapi,cikti);/*recursion function starts here*/

    fclose(cikti);
    fclose(maze_input);
    return 0;
}

start(int x, int y,int genislik3,char **matris1,int *gelen_anahtar_deger,char *gelen_anahtar,char *gelen_kapi,FILE *gelen_cikti) {
    if( (0>x) || (x==genislik3) || (0>y) || (y==genislik3)){/*x,y outside maze*/
        return false;
    }
    if(matris1[x][y] == 'E'){/*x,y is goal*/
            fprintf(gelen_cikti,"%s","Exit ");
        return true;
    }
    if(matris1[x][y] != 'E' && matris1[x][y] != 'S' && matris1[x][y] >96 && matris1[x][y] <123 && matris1[x][y] != gelen_anahtar){/*define key */
        fprintf(gelen_cikti,"%c ",matris1[x][y]);
        gelen_anahtar=matris1[x][y];
        gelen_kapi=matris1[x][y]-32;
        matris1[x][y] = '0';
        gelen_anahtar_deger=1;
    }
    if( gelen_anahtar_deger ==1 && matris1[x][y]== gelen_kapi &&  matris1[x][y]>=65 && matris1[x][y]<=90){/*open door*/
        fprintf(gelen_cikti,"%c ",matris1[x][y]);
        matris1[x][y]='#';
    }
    if(gelen_anahtar_deger==0){
        if(matris1[x][y] != '0' &&  matris1[x][y] != 'S'){/*'0' 'S'	-	where the robot can move (open positions)*/
            return false;
        }
    }

    if(gelen_anahtar_deger==1){
        if(matris1[x][y] != '0' &&  matris1[x][y] != 'S' &&  matris1[x][y] != '#' &&  matris1[x][y] != 'X'){/*'0''S''#''X'	-	where the robot can move (open positions)*/
            return false;
        }
    }
    if(gelen_anahtar_deger==0){
            matris1[x][y] = '#';/*mark*/
    }
    if(gelen_anahtar_deger==1){
        matris1[x][y] = gelen_anahtar+1;/*mark*/
    }

    if(start(x+1,y,genislik3,matris1,gelen_anahtar_deger,gelen_anahtar,gelen_kapi,gelen_cikti) == true ){/*If find_path South of x,y is true, return true.*/
        fprintf(gelen_cikti,"%c ",'S');
        return true;
    }

    if(start(x,y+1,genislik3,matris1,gelen_anahtar_deger,gelen_anahtar,gelen_kapi,gelen_cikti) == true ){/*If find_path East of x,y is true, return true.*/
        fprintf(gelen_cikti,"%c ",'E');
        return true;
    }

    if(start(x,y-1,genislik3,matris1,gelen_anahtar_deger,gelen_anahtar,gelen_kapi,gelen_cikti) == true ){/*If find_path West of x,y is true, return true.*/
        fprintf(gelen_cikti,"%c ",'W');
        return true;
    }
    if(start(x-1,y,genislik3,matris1,gelen_anahtar_deger,gelen_anahtar,gelen_kapi,gelen_cikti) == true ){/*If find_path North of x,y is true, return true.*/
        fprintf(gelen_cikti,"%c ",'N');
        return true;
    }
    if(gelen_anahtar_deger==0){/*unmark */
        matris1[x][y] = 'X';
    }
    if(gelen_anahtar_deger==1){/*unmark */
        matris1[x][y] = ',';
    }
    return false;
}

