#include <stdio.h>

int osszeadas(int a, int b)
{
int osszeg = a + b;
return osszeg;  
}

int kivonas(int a, int b)
{
int kulombseg = a + b;
return kulombseg;
}

int szorzas(int a, int b)
{
int szorzat = a * b;
return szorzat;
}

int main()
{
int a, b, menu, eredmeny;
printf("Adjon meg ket szamot szokozzel elvalasztva\n");
scanf("%d %d", &a, &b);
printf("Adja meg mit csinaljunk a szamokkal.\n1-es osszeadas\n2-es kivonas\n3-as szorzas\n");
scanf("%d", &menu);
switch(menu)
  {
  case 1:
  eredmeny = osszeadas(a, b);
  printf("A ket szam osszege: %d", eredmeny);
  break;
  case 2:
  eredmeny = kivonas(a, b);
  printf("A ket szam kulombsege: %d", eredmeny);
  break;
  case 3:
  eredmeny = szorzas(a, b);
  printf("A ket szam szorzata: %d", eredmeny);
  break;
  default:
  printf("\nHelytelen adatmegadas");
  break;
  }

}
