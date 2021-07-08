#include <stdio.h>
#include <stdlib.h>

int cmpfunc (const void * a, const void * b) {
   return ( *(int*)a - *(int*)b );
}
/*print function*/
void print(char* string,int array[],int size){
    printf("%s[",string);
    int i = 0;
    for(i; i<size ;i++){
        printf("%d",array[i]);
        if(i!=size-1){
            printf(", ");
        }
    }
    printf("]\n");
}
/*combine 2 array*/
int* combine_array(int array1[],int array2[],int size1,int size2){
    int* array3 = (int*)malloc(sizeof(int)*(size1+size2));
    int i = 0;
    for(i;i<size1;i++){
        array3[i] = array1[i];
    }
    for(i;i<size1+size2;i++){
        array3[i] = array2[i-size1];
    }
    return array3;
}

int main(){
    /* listA = [12, 29, 15, 8, 36, 6, 9, 2, 4, 7] # list length must be 10*/
    int listA[10] = {12, 29, 15, 8, 36, 6, 9, 2, 4, 7};
    /*print("List A: " + str(listA))*/
    print("List A: ",listA, sizeof(listA) / sizeof(int));
    /*listB =[39, 41, 1, 3, 27, 14, 5, 11, 90, 43] # list length must be 10*/
    int listB[10] = {39, 41, 1, 3, 27, 14, 5, 11, 90, 43};
    /*print("List B: " + str(listB))*/
    print("List B: ", listB, sizeof(listB) / sizeof(int));
    /*sortedListA = sorted(listA)*/
    qsort(listA, sizeof(listA) / sizeof(int), sizeof(int), cmpfunc);
    /*print("Sorted List A: " + str(sortedListA))*/
    print("Sorted List A: ", listA, sizeof(listA) / sizeof(int));
    /*sortedListB = sorted(listB)*/
    qsort(listB,sizeof(listB) / sizeof(int),sizeof(int),cmpfunc);
    /*print("Sorted List B: " + str(sortedListB))*/
    print("Sorted List B: ", listB, sizeof(listB) / sizeof(int));
    /*listC = sortedListA + sortedListB*/
    int* listC = combine_array(listA , listB,sizeof(listA) / sizeof(int),sizeof(listB) / sizeof(int));
    /*print("List C: " + str(listC))*/
    print("List C: " , listC, sizeof(listA) / sizeof(int)+sizeof(listB) / sizeof(int));
    /*sortedListC = sorted(listC)*/
    qsort(listC,sizeof(listA) / sizeof(int)+sizeof(listB) / sizeof(int),sizeof(int),cmpfunc);
    /*print("Sorted List C: " + str(sortedListC))*/
    print("Sorted List C: " , listC, sizeof(listA) / sizeof(int)+sizeof(listB) / sizeof(int));
    /*free allocated memory*/
    free(listC);
    return 0;
}
