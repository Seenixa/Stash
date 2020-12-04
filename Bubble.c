#include <stdio.h>
#include <stdbool.h>

void bubbleup(int vektor[], int size)
{
int i, j, s, tmp;
bool csere = true;
for(i=0;i<size && csere==true;i++)
{
    csere = false;
    for(j=0;j<size-1;j++)
    {
        if(vektor[j]>vektor[j+1])
        {
        tmp = vektor[j];
        vektor[j] = vektor[j+1];
        vektor[j+1] = tmp;
        csere = true;
        }
    }
}
}

void bubbledown(int vektor[], int size)
{
int i, j, s, tmp;
bool csere = true;
for(i=0;i<size && csere==true;i++)
{
    csere = false;
    for(j=0;j<size-1;j++)
    {
        if(vektor[j]<vektor[j+1])
        {
        tmp = vektor[j];
        vektor[j] = vektor[j+1];
        vektor[j+1] = tmp;
        csere = true;
        }
    }
}
}

void main()
{
int size=100;
int i, j, osszeg=0, vektor[size];
float atlag=0;

for(i=0;i<size;i++)
{
    vektor[i] = rand() % 1000;
    osszeg = osszeg + vektor[i];
    atlag = (float)osszeg/i;
    printf("%d ", vektor[i]);
    if((i+1)%10==0)
    {
        printf("\n");        
    }
}
printf("\n\nA szamok osszege: %d, atlaguk, %.1f\n\n", osszeg, atlag);

printf("A szamok csokkeno sorrendben: \n");
bubbledown(vektor, size);
for(i=0;i<size;i++)
{
    printf("%d ", vektor[i]);
    if((i+1)%10==0)
    {
        printf("\n");
    }
}

printf("\nA szamok novekvo sorrendben: \n");
bubbleup(vektor, size);
for(i=0;i<size;i++)
{
    printf("%d ", vektor[i]);
    if((i+1)%10==0)
    {
        printf("\n");
    }
}
}
