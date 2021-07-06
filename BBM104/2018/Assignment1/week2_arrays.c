#include <stdio.h>
#include <stdlib.h>

int SumIntegerArray(int a[], int n);

int main()
{
    int list[4] = {0, 1, 2, 3};
    int *arrPtr;

    /* Array is a pointer too! */
    printf( "Address of array:\n" );
    printf( "%d\n", &list );
    printf( "%d\n", list );
    printf( "%d\n", &list[0] );

    printf( "\n" );

    /* Different ways to access array values. */
    printf( "Value of array:\n" );
    printf( "%d\n", *list );
    printf( "%d\n", list[0] );

    printf( "\n" );

    /* How to access array elements by another pointer. */
    printf( "Access array elements with ptr:\n" );
    arrPtr = list;
    printf( "%d\n", *(++arrPtr) );
    printf( "%d\n", list[1] );

    printf("\n");

    /* How to access array elements by increasing array address. */
    printf( "Increment array address:\n" );
    printf( "%d\n", list + 2 );
    printf( "%d\n", &list[2] );

    printf("\n");

    /* Arrays are passed to functions by reference. */
    printf( "Sum: %d\n", SumIntegerArray( list, 4 ) );
    printf( "Sum: %d\n", list[0] );

    printf( "\n" );

    /* Size of array. */
    arrPtr = list;
    printf( "Size of total bytes in array: %d\n", sizeof( list ) );
    printf( "Size of pointer points the array: %d\n", sizeof( *arrPtr ) );
    printf( "The size of array is: %d\n", sizeof( list ) / sizeof( int ) );

    return 0;
}

/* Sums the integers in given array. */
int SumIntegerArray( int a[], int n )
{
    int i, sum;
    sum = 0;
    for ( i = 0; i < n; i++ ) {
        sum += a[i];
    }

    /* You can change array element due to passing by reference. */
    a[0] = sum;

    return sum;
}