

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

char a[100];
int i, b;
int v = 'Z' - 'z';

void main ()
{
char a[100];
int i, b;
int v = 'Z' - 'z';

printf ("Ird be a nagyitani kivant szoveget\n");
scanf ("%s", a);
int A = strlen (a);
for (i = 0; i < A; i++)
        {
        if (a[i] >= 'Z')
        a[i] = a[i] + v;
        printf ("\n%c", a[i]);
        }
printf ("\n%s", a);
}
