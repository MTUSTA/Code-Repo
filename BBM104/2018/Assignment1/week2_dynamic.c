#include <stdio.h>
#include <stdlib.h>

int main()
{
    int *array, *p;
    int i, no_elements;
    printf( "Enter number of elements: " );
    scanf( "%d", &no_elements );

    printf( "Enter the elements: " );
    array = ( int* )malloc( no_elements * sizeof( int ) );
    for( p = array, i = 0; i < no_elements; i++, p++ )
        scanf( "%d", p );

    printf( "Elements: " );
    for( p = array, i = 0; i < no_elements; i++, p++ )
        printf( "%d ", *p );
    printf( "\n" );

    array = ( int* )realloc( array, (no_elements + 2) * sizeof( int ) );

    printf( "Enter two new elements: " );
    for( p = array, i = 0; i < no_elements; i++, p++ );

    for( ; i < no_elements+2; i++, p++ )
        scanf( "%d",p );

    printf("Elements: ");
    for( p = array, i = 0; i < no_elements+2; i++, p++ )
        printf( "%d ", *p );
    printf( "\n" );

    free(array);
    return 0;
}