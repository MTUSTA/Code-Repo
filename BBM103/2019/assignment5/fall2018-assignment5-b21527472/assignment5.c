#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct{
    char type;
}node;
typedef struct{
    char protein;
    char* Nucleotide;
}node2;

node*** fill_array(node*** arr){

    int i,j,k;
    arr = (node ***)malloc(4*sizeof(node**));
    for (i = 0; i< 4; i++) {
        arr[i] = (node **) malloc(4*sizeof(node *));
        for (j = 0; j < 4; j++) {
            arr[i][j] = (node *)malloc(4*sizeof(node));
        }
    }
    arr[0][0][0].type='F';
    arr[0][0][1].type='F';
    arr[0][0][2].type='L';
    arr[0][0][3].type='L';
    arr[0][1][0].type='S';
    arr[0][1][1].type='S';
    arr[0][1][2].type='S';
    arr[0][1][3].type='S';
    arr[0][2][0].type='Y';
    arr[0][2][1].type='Y';
    arr[0][2][2].type=' ';
    arr[0][2][3].type=' ';
    arr[0][3][0].type='C';
    arr[0][3][1].type='C';
    arr[0][3][2].type=' ';
    arr[0][3][3].type='W';

    arr[1][0][0].type='L';
    arr[1][0][1].type='L';
    arr[1][0][2].type='L';
    arr[1][0][3].type='L';
    arr[1][1][0].type='P';
    arr[1][1][1].type='P';
    arr[1][1][2].type='P';
    arr[1][1][3].type='P';
    arr[1][2][0].type='H';
    arr[1][2][1].type='H';
    arr[1][2][2].type='Q';
    arr[1][2][3].type='Q';
    arr[1][3][0].type='R';
    arr[1][3][1].type='R';
    arr[1][3][2].type='R';
    arr[1][3][3].type='R';

    arr[2][0][0].type='I';
    arr[2][0][1].type='I';
    arr[2][0][2].type='I';
    arr[2][0][3].type='M';
    arr[2][1][0].type='T';
    arr[2][1][1].type='T';
    arr[2][1][2].type='T';
    arr[2][1][3].type='T';
    arr[2][2][0].type='N';
    arr[2][2][1].type='N';
    arr[2][2][2].type='K';
    arr[2][2][3].type='K';
    arr[2][3][0].type='S';
    arr[2][3][1].type='S';
    arr[2][3][2].type='R';
    arr[2][3][3].type='R';

    arr[3][0][0].type='V';
    arr[3][0][1].type='V';
    arr[3][0][2].type='V';
    arr[3][0][3].type='V';
    arr[3][1][0].type='A';
    arr[3][1][1].type='A';
    arr[3][1][2].type='A';
    arr[3][1][3].type='A';
    arr[3][2][0].type='D';
    arr[3][2][1].type='D';
    arr[3][2][2].type='E';
    arr[3][2][3].type='E';
    arr[3][3][0].type='G';
    arr[3][3][1].type='G';
    arr[3][3][2].type='G';
    arr[3][3][3].type='G';

    return arr;
}

/*adenine (A), thymine (T), guanine (G), and cytosine (C). Thymine (T) becomes urasil (U) when it comes to RNA  */
int find_index(char letter){
    if(letter == 'U' || letter == 'T'){
        return 0;
    }
    if(letter == 'C'){
        return 1;
    }
    if(letter == 'A'){
        return 2;
    }
    if(letter == 'G'){
        return 3;
    }
}
/*create and fill example table*/
node2* fill_exptable(node2* exp_tablo){
     /*Dynamic memory allocate 2d array for example proteins*/
    int k;
    exp_tablo = (node2 *)malloc(5 * sizeof(node2));
    for (k=0; k<5; k++){
        exp_tablo[k].Nucleotide = (char*)malloc(30*sizeof(char));
    }
    strcpy(exp_tablo[0].Nucleotide,"AUGGUGGCGGAGGGGACGAAGAGGAUCUAA");
    exp_tablo[0].protein = 'A';
    strcpy(exp_tablo[1].Nucleotide,"AUGGGAGAAGCAGUAAGAAAAACAAUAUAG");
    exp_tablo[1].protein = 'B';
    strcpy(exp_tablo[2].Nucleotide,"AUGUUUUCCUAUUGCCUGCCACAACGCUGA");
    exp_tablo[2].protein = 'C';
    strcpy(exp_tablo[3].Nucleotide,"AUGUUCUUGGUCCCUACUUACGAUCAUUAA");
    exp_tablo[3].protein = 'D';
    strcpy(exp_tablo[4].Nucleotide,"AUGUUUUCCUAUUGCCUGCCAAAACGCUGA");
    exp_tablo[4].protein = 'E';

    return exp_tablo;
}
/*create array[3] and compare 3 string*/
int check_stop_code(char array[],int size){
    char* last_three = (char*)malloc(sizeof(char)*3);
    last_three[0] = array[size-3];
    last_three[1] = array[size-2];
    last_three[2] = array[size-1];
    last_three[3] = '\0';
    if(strcmp(last_three,"UAG") == 0 || strcmp(last_three,"UAA") == 0 || strcmp(last_three,"UGA") == 0){
        free(last_three);
        return 1;
    }else{
        free(last_three);
        return 0;
    }
}
/*print protein function*/
void print_protein(char protein[]){
    int i;
    for(i=0;i<9;i++){
        printf("%c",protein[i]);
        if(protein[i+1] == ' ' || i == 8){
            break;
        }else{
        printf("-");
        }
    }
    printf("\n");

}
int main(int argc,char* argv[]){
    /*opens inputs and empty output*/
    FILE* input = fopen(argv[1], "r");

    node*** genetic = fill_array(genetic);
    node2* exp_tablo = fill_exptable(exp_tablo);

    char line[999];
    while(fscanf(input,"%s\n",line) != EOF) {
        printf("Fname: %s File content: %s\n\n",argv[1],line);
        int len_of_string = strlen(line);
        /*AUG is an initiation (start) codon*/
        if(line[0] != 'A' || line[1] !='U' ||line[2] !='G'){
            printf("Not start with AUG it is not a protein\n");
        }
        /*UAA, UAG, and UGA are termination (stop) codons.*/
        else if(check_stop_code(line,len_of_string) == 0) {
            printf("It is not a protein! No Stop code\n");
        }
        else{
            int k,i,row,col,piece;
            char* protein = (char*)malloc(sizeof(char)*9);
            for(i=0;i<len_of_string-3;i+=3){
                row = find_index(line[i]);
                col = find_index(line[i+1]);
                piece = find_index(line[i+2]);
                protein[i/3] = genetic[row][col][piece].type;
            }
            int row2,col2,piece2,count = 0;
            for(k=0;k<5;k++){
                    int i;
                    for(i=0;i<strlen(exp_tablo[k].Nucleotide)-3;i+=3){
                        row2 = find_index(exp_tablo[k].Nucleotide[i]);
                        col2 = find_index(exp_tablo[k].Nucleotide[i+1]);
                        piece2 = find_index(exp_tablo[k].Nucleotide[i+2]);
                        if(protein[i/3] == genetic[row2][col2][piece2].type){
                            count++;
                        }
                    }
                    if(count == 9){
                        printf("MyProtein%c is identified in sequence.\n",exp_tablo[k].protein);
                        printf("the amino acids of MyProtein%c:  ",exp_tablo[k].protein);
                        print_protein(protein);
                        printf("protein%c:  ",exp_tablo[k].protein);
                        print_protein(protein);
                        break;
                    }
                    count = 0;
            }
            if(count !=9){
                printf("It is not a known protein.\nIt is probably a new protein.\t");
                print_protein(protein);
            }
        }
    }
    /*free Dynamic memory allocate 3d array and close file*/
    fclose(input);
    /*free 3d array*/
    int i,j;
    for(i=0;i<4;i++){
        for(j=0;j<4;j++){
            free(genetic[i][j]);
        }
        free(genetic[i]);
    }
    free(genetic);
    /*free 2d array*/
    for(i=0;i<5;i++){
        free(exp_tablo[i].Nucleotide);
    }
    free(exp_tablo);

    return 0;
}
