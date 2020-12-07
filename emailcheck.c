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

bool mail(char* email, int full_length, int check_menu)
{
    int i, j, com_length, mail_length = 10;
    char tmp[mail_length], gmailcom[] = "@gmail.com", gmailhu[] = "gmail.hu", freemail[] = "@freemail.hu";
    bool equal = true;
    
    for(i = length_check(email, full_length); i < full_length+1; i++)
    {
        tmp[i - length_check(email, full_length)] = email[i];
    }
    switch(check_menu)
    {
        case 1: 
        if(strcmp(tmp, gmailhu) == 0)
        {
            return true;
        }
        break;
        
        case 2:
        if(strcmp(tmp, gmailcom) == 0)
        {
            return true;
        }
        break;
        
        case 3:
        if(strcmp(tmp, freemail) == 0)
        {
            return true;
        }
        break;
        
        case 4:
        for(i=0; i<strlen(tmp); i++)
            if(tmp[i] == '.')
            {
                com_length = i;
                if(i<=3)
                {
                    return false;
                }
            }
        if((i - com_length)<3)
        {
            return false;
        }
        return true;
        break;
        
        default:
        break;
    }

    return false;
}


bool char_check(char* email, int full_length)
{
    int i;
    for(i = 0; i<length_check(email, full_length); i++)
    {
        if(email[(length_check(email, full_length))+1] == '@')
        {
            return false;
        }
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
    int main_menu, check_menu;
    char email[40];
    printf("Valasszon az adott opciok kozul.\n1 - ellenorzes\n2 - belepes\n");
    scanf("%d", &main_menu);
    if(main_menu < 1 || main_menu> 4)
    {
        printf("Helytelen menu valasztas.");
        return 0;
    }
    
    printf("Adja meg az e-mail cimet.\n");
    scanf("%s", &email);
    int full_length = strlen(email);

    switch (main_menu)
    {
    case 1:
        printf("\nMilyen e-mailt szeretne ellenorizni?\n");
        printf("1. Gmail.hu\n2. Gmail.com\n3. Freemail.hu\n4. egyeb\n");
        scanf("%d", &check_menu);
        if(check_menu < 1 || check_menu> 4)
        {
            printf("Helytelen menu valasztas.");
            return 0;
        }
    
        if(email[length_check(email, full_length)] != '@')
        {
            printf("\nAz e-mail cimnek tartalmazni kell '@' jelet.");
            return 0;
        }
    
        if(length_check(email, full_length) < 8 || length_check(email, full_length) > 18)
        {
            printf("\nA cim hossza 8-18 karakter kozottinek kell legyen.\n");
            return 0;
        }
    
        if(mail(email, full_length, check_menu) != true)
        {
            switch(check_menu)
            {
            case 1:
            printf("\nNem gmail.hu fiokot adott meg, vagy helytelen formaban.");
            return 0;
            break;
        
            case 2:
            printf("\nNem gmail.com fiokot adott meg, vagy helytelen formaban.");
            return 0;
            break;
        
            case 3:
            printf("\nNem freemail.hu fiokot adott meg, vagy helytelen formaban.");
            return 0;
            break;
        
            case 4:
            printf("\nA megadott fiok formailag nem helyes.");
            return 0;
            break;
        
            default:
            printf("\nRossz menuvalasztas.");
            break;
            return 0;
            }
        }
    
        if(char_check(email, full_length) != true)
        {
            printf("A megadott e-mail cimben nem megfelelo karakterek talalhatok.\n");
            printf("Az angol ABC betui, illetve '.' es '-' lehet a cimben.\n");
            printf("Nem lehet ket '.' sem ket '-' egymas mellett, sem a '@' elott.\n");
            printf("Egynel tobb '@' nem lehet az e-mail cimben.\n");
            return 0;
        }
        
        printf("Az email cim formailag helyes.");
        
        return 0;
        break;
    
    case 2:
    
    break;

    default:
    break;
    }
    
    return 0;

}


