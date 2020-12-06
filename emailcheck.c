#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int length_check(char* email, int full_length, char segment)
{
    int i=0, length;
    bool at = false;
    while(i<full_length && at==false)
    {
        if (email[i]==segment)
        {
            at = true;
            length = i;
        }
        i++;
    }

    return length;
}

bool mail(char* email, int full_length)
{
    int i, j, mail_length=10;
    char tmp[mail_length];
    char gmail[] = "@gmail";
    bool dot = false, equal = true;
    for(i=0; i<full_length && dot==false; i++)
    {
        if(email[i]=='@')
        {
            for(j=0; email[i+j]!='.' && j<mail_length; j++)
            {
                tmp[j] = email[i+j];
                dot = true;
            }
            mail_length = j;
        }
    }

    for(i=0; i<mail_length && equal == true; i++)
    {
        if (tmp[i]!=gmail[i])
        {
            equal = false;
        }
    }
    if(equal == false)
    {
        return false;
    }
    else
    {
        return true;
    }
}

void main()
{
    int full_length = 40;
    char first_seg = '@';
    char email[full_length];
    scanf("%s", &email);

    if(length_check(email, full_length, first_seg) < 8 || length_check(email, full_length, first_seg) > 18)
    {
        printf("\nA cim hossza 8-18 karakter kozottinek kell legyen.\n");
    }
    if(mail(email, full_length) != true)
    {
        printf("Jelenleg csak gmail-t ellenorzok.");
    }
}
