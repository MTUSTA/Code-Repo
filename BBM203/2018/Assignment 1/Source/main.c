#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define TRUE 1
#define FALSE 0

/*recursion problem solving function*/
int recursion(int** map_matrix,int base_row,int base_colomn,int** key_matrix,int map_row,int map_column,int key_len,FILE* output){

    /*check coordinates ,out of matrix area*/
    if(base_row < 0 || base_colomn<0|| base_row >= map_row || base_row >= map_column){
        return FALSE;
    }

    /*Multiplication of matrices A and B*/
    int row,colomn,result=0;
    for(row = 0; row < key_len; row++){
            for(colomn=0;colomn<key_len;colomn++){
                result += map_matrix[base_row+row-key_len/2][base_colomn+colomn-key_len/2]*key_matrix[row][colomn];
            }
    }

    /*Remaining part of the mod (5,C)*/
    int result2 =result%5;
    fprintf(output,"%d,%d:%d",base_row,base_colomn,result);

    /*0: Found treasure*/
    if(result2 == 0){
        return TRUE;
    }

    /*1: Go up*/
    else if(result2 == 1){
        fprintf(output,"\n");
        if(recursion(map_matrix,base_row-key_len,base_colomn,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
        else if(recursion(map_matrix,base_row+key_len,base_colomn,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
    }

    /*2: Go down*/
    else if(result2 == 2){
        fprintf(output,"\n");
        if(recursion(map_matrix,base_row+key_len,base_colomn,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
        else if(recursion(map_matrix,base_row-key_len,base_colomn,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
    }

    /*3: Go right*/
    else if(result2 == 3){
        fprintf(output,"\n");
        if(recursion(map_matrix,base_row,base_colomn+key_len,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
        else if(recursion(map_matrix,base_row,base_colomn-key_len,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
    }

    /*4: Go left*/
    else if(result2 == 4){
        fprintf(output,"\n");
        if(recursion(map_matrix,base_row,base_colomn-key_len,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
        else if(recursion(map_matrix,base_row,base_colomn+key_len,key_matrix,map_row,map_column,key_len,output) == TRUE){
            return TRUE;
        }
    }
    /*Exit or no path*/
    return FALSE;
}

/*Reads file and fills 2d array with fgets() function*/
void read_fill(int** arr,FILE* matrix){
    int row=0;

    /*reads input file with fgets() function*/
    char line[999];

    while (fgets(line,999, matrix)) {

        /*delete new line character*/
        size_t ln = strlen(line) - 1;
        if (*line && line[ln] == '\n'){
            line[ln] = '\0';
        }

        /*split in c Returns first token*/
        char *token = strtok(line," ");

        /* Keep printing tokens while one of the delimiters present in str[].*/
        int column=0;
        while (token != NULL){
            arr[row][column] = atoi(token);
            token = strtok(NULL, " ");
            column++;
        }
        row++;
    }
}
 int main(int argc,char* argv[]){

    char *token1 = strtok(argv[1],"x");

    /*mapmatrix row,mapmatrix colomn*/
    int map_row = atoi(token1);
    token1 = strtok(NULL, "x");
    int map_column = atoi(token1);

    /*keymatrix length*/
    int key_len = atoi(argv[2]);

    /*opens inputs and empty output*/
    FILE* mapmatrix = fopen(argv[3], "r");
    FILE* keymatrix = fopen(argv[4], "r");
    FILE* output = fopen(argv[5], "w");

    /*Dynamic memory allocate 2d array for map matrix*/
    int i;
    int **arr= (int **)malloc(map_row * sizeof(int *));
    for (i=0; i<map_row; i++){
        arr[i] = (int *)malloc(map_column * sizeof(int));
    }

    /*Reads map matrix file and fill map matrix 2d array with fgets() function*/
    read_fill(arr,mapmatrix);

    /*allocate memory 2d array for key matrix*/
    int **arr2 = (int **)malloc(key_len * sizeof(int *));
    for (i=0; i<key_len; i++){
        arr2[i] = (int *)malloc(key_len * sizeof(int));
    }
    /*Reads key matrix file and fill key matrix 2d array with fgets() function*/
    read_fill(arr2,keymatrix);
    /*recursion problem solving function*/
    recursion(arr,key_len/2,key_len/2,arr2,map_row,map_column,key_len,output);
    /*free 2d arrays*/
    int deneme;
    for ( deneme = 0; deneme < map_row; deneme++){
        int* Ptr = arr[deneme];
        free(Ptr);
    }
    free(arr);
    for ( deneme = 0; deneme < key_len; deneme++){
        int* Ptr2 = arr2[deneme];
        free(Ptr2);
    }
    free(arr2);
    /*close file i/o*/
    fclose(mapmatrix);
    fclose(keymatrix);
    fclose(output);
    return 0;
}
