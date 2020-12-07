#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

int length_check(char* email, int full_length)
{
    int i=0, length;
    bool at = false;
    while(i < full_length && at == false)
    {
        if (email[i] == '@')
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
    int i, j, mail_length = 10;
    char tmp[mail_length], gmail[] = "@gmail.com", gmailhu[] = "gmail.hu", freemail[] = "@freemail.hu";
    bool equal = true;
    
    for(i = length_check(email, full_length); i < full_length+1; i++)
    {
        tmp[i - length_check(email, full_length)] = email[i];
    }
    if(strcmp(tmp, gmail) == 0 || strcmp(tmp, gmailhu) == 0 || strcmp(tmp, freemail) == 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}

bool char_check(char* email, int full_length)
{
    int i;
    bool value = true;
    for(i = 0; i<length_check(email, full_length); i++)
    {
        if(!(email[i] >= 'A' && email[i] <= 'z' || email[i] == '.' || email[i] == '-'))
        {
            return false;
        }
        if((email[i] == email[i-1] && email[i] == '.') || (email[i] == email[i-1] && email[i] == '-'))
        {
            return false;
        }
        if((email[i+1] == '@' && email[i] == '.') || (email[i+1] == '@' && email[i] == '-'))
        {
            return false;
        }
    }

    return true;    
}

int main()
{
    char email[40];
    printf("Adja meg az ellenorizni kivant e-mail cimet.\n");
    scanf("%s", &email);
    int full_length = strlen(email);

    if(length_check(email, full_length) < 8 || length_check(email, full_length) > 18)
    {
        printf("\nA cim hossza 8-18 karakter kozottinek kell legyen.\n");
        return 0;
    }
    
    if(mail(email, full_length) != true)
    {
        printf("Gmail.hu, Gmail.com illetve freemail.hu amit helyesnek veszek.\n");
        return 0;
    }
    
    if(char_check(email, full_length) != true)
    {
        printf("A megadott email cimben nem megfelelo karakterek talalhatok.\n");
        printf("Az angol ABC betui, illetve '.' es '-' lehet a cimben.\n");
        printf("Nem lehet ket '.' sem ket '-' egymas mellett, sem a '@' elott.");
        return 0;
    }
    printf("Az email cim helyes.");
    return 0;
}
