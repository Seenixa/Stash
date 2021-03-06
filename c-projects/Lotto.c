#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void delay(int seconds)
{
    int realsec = CLOCKS_PER_SEC * seconds;
    clock_t start_time = clock();
    while(clock() < start_time + realsec);
}

int* lottohuzas(int darabossz, int darabnyer)
{
    int random, i, j, szamok[darabossz], tmp;
    int *nyeroszamok = malloc(sizeof(darabnyer));
    
    if (nyeroszamok == NULL)
    {
        return NULL;
    }
    
    for(i=0; i<darabossz; i++)
    {
        szamok[i] = i+1;
    } 
    
    for(i=0; i<darabnyer; i++)
    {
        random = rand() % darabossz-i;
        nyeroszamok[i] = szamok[random];
        
        for(j=random; j<darabossz; j++)
        {
            szamok[j] = szamok[j+1];
        }
    }
    
    return nyeroszamok;
}

int main() 
{
    int i, seconds = 1, darabossz=90, darabnyer=5, *p;
    p = lottohuzas(darabossz, darabnyer);
    
    for(i=0; i<darabnyer; i++)
    {
        printf("A nyeroszam: %d\n",*(p+i));
        delay(1);
    }
}
