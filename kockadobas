#include <stdio.h>
#include <stdlib.h>

int* dobas (int darabkocka, int darabdobas)
{
int *eredmeny = malloc(sizeof(darabkocka*6));
    if(eredmeny==NULL)
    {
        return NULL;
    }    
int i, random;
for(i=0;i<darabkocka*6;i++)
{
    eredmeny[i] = 0;
}
for(i=0;i<darabdobas;i++)
{
    random = darabkocka + rand() % ((darabkocka*6+1)-darabkocka);
    eredmeny[random-1]++;
}
return eredmeny;
}    

void main()
{
long int darabkocka, darabdobas;
int i, *p, INT_MAX = 2147483647;
printf("Hany kockaval dobjunk?\n");
scanf("%ld", &darabkocka);
    if(darabkocka<0)
    {
        printf("Negativ szamu kockaval nem tudunk dobni");
        return NULL;
    }
    else if(darabkocka*6>INT_MAX)
    {
        printf("Ennyi kockaval egyszerre nem tudunk dobni.");
        return NULL;
    }
printf("Hanyszor dobjunk a kockaval/kockakkal.\n");
scanf("%ld", &darabdobas);
    if(darabdobas<0)
    {
        printf("Negativ szamu dobas nem lehetseges");
        return NULL;
    }
    else if (darabdobas > INT_MAX)
    {
        printf("Ennyi dobassal nem tudunk szamolni.%d",darabdobas );
        return NULL;
    }
p = dobas(darabkocka, darabdobas);
    if(p==NULL)
    {
         return NULL;    
    }
free;
for(i=darabkocka-1;i<darabkocka*6;i++)
{
    printf("%d alkalommal dobtunk %d-t.\n", *(p+i), (i+1));
}
}
