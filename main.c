/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

char a[100];
int i, b;
int v = 'Z' - 'z';

main ()
{

  printf ("Ird be a nagyC-tani kC-vC!nt szC6veget\n");
  scanf ("%s", a);
  int A = strlen (a);
  for (i = 0; i < A; i++)
    {

      if (a[i] >= 'Z')
	a[i] = a[i] + v;
      printf ("\n%c", a[i]);

    }
  printf ("\n%s", a);
  return 0;
}
