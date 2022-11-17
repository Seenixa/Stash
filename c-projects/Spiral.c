#include <stdio.h>
const int size = 100;

int convertStringToInt(char input[]){
    int intValue = 0;
    if( input[0] != '-'){
        for(int i = 0; input[i] != '\0'; i++){
            if (input[i] >= '0' && input[i] <= '9'){
                intValue *= 10;
                intValue += input[i] - '0';
            }
        }  
    }

    if( input[0] == '-'){
        for(int i = 1; input[i] != '\0'; i++){
            if (input[i] >= '0' && input[i] <= '9'){
                intValue *= 10;
                intValue += input[i] - '0';
            }
        } 
        intValue *= -1; 
    }

    return intValue;
}

int countColumns(char input[]){
    int columns = 0;
    int secondLine = 0;

    for(int i = 0; (input[i] != '\n' || secondLine == 0) && input[i] != '\0'; i++){
        
        if (input[i - 1] == '\n'){
            secondLine = 1;
        }

        if (secondLine == 1 && (input[i] == ',' || input[i + 1] == '\n')){
            columns++;
        }
    }

    return columns;
}

int countLines(char input[]){
    int lines = 0;

    for(int i = 0; input[i] != '\0'; i++){
        if( input[i] == '\n'){
            lines++;
        }
    }

    return (lines - 2);
}

void readNumbersFromString(char input[], int output[]){
    int intCounter = 0;
    int stringCounter = 0;
    char tempString[10];

    for(int i = 0; input[i] != '\0'; i++){

        if(input[i] != ',' && input[i] != '\n'){
            tempString[i - stringCounter] = input[i];
        }

        if(input[i] == ',' || input[i] == '\n'){
            stringCounter = i + 1;
            output[intCounter] = convertStringToInt(tempString);
            for (int j = 0; tempString[j] != '\0'; j++){
                tempString[j] = '\n';
            }
            intCounter++;
        }
    }

    output[intCounter] = -1;
}

void fillMatrix(int output[20][20], char inputString[], int inputInt[]){
    int inCounter = 0;

    for(int i = 0; i < 20 ; i++){
        for(int j = 0; j < 20; j++){
            output[i][j] = -1;
            inCounter++;
        }
    }

    inCounter = 0;

    for(int i = 0; i <= countLines(inputString); i++){
        for(int j = 0; j < countColumns(inputString); j++){
            output[i][j] = inputInt[2 + inCounter];
            inCounter++;
        }
    }

}

void readStringFromFile(char filename[], char input[]){
    FILE* readFrom;
    readFrom = fopen(filename, "r");
    while(fscanf(readFrom, "%[^'EOF']", input) == 1);
    fclose(readFrom);
}

void setTable(int table[size][size], int matrix[20][20], char inputString[]){
    for(int i = 0; i <= size; i++){
        for(int j = 0; j <= size; j++){
            if((i >= 20 && j >= 20) && i <= 19 +countLines(inputString) && j <= 19 +countColumns(inputString)){
                table[i][j] = matrix[i - 20][j - 20];
            }
            else{
                table[i][j] = -1;
            }
        }
    }
}

int changeStep(int step){
    step *= -1;
    if (step > 0){
        step++;
    }
    else{
        step--;
    }
    return step;
}

void spiral(int table[size][size], int inputInt[], int output[]){
    int stepLength = -1;
    int stepCounter = 0;
    int intCounter = 0;
    int startingPoint[2] = {inputInt[0] + 19, inputInt[1] + 19};
    int x = startingPoint[0];
    int y = startingPoint[1];

    while(output[intCounter] != -2 && x > 0 && y > 0){
        if(stepLength == -1){
            if (table[x][y] != -1){
                output[intCounter] = table[x][y];
                intCounter++;
            }
        }
        
        if(stepCounter % 2 == 0){
            stepCounter++;
            if(stepLength > 0){
                for(int i = 0; i < stepLength; i++){
                    x += 1;
                    if (table[x][y] != -1){
                        output[intCounter] = table[x][y];
                        intCounter++;
                    }
                }
            }

            if(stepLength < 0){
                for(int i = 0; i < (-1 * stepLength); i++){
                    x -= 1;
                    if (table[x][y] != -1){
                        output[intCounter] = table[x][y];
                        intCounter++;
                    }
                }
            }
        }
        
        if(stepCounter % 2 == 1){
            stepCounter++;
            if(stepLength > 0){
                for(int i = 0; i < stepLength; i++){
                    y += 1;
                    if (table[x][y] != -1){
                        output[intCounter] = table[x][y];
                        intCounter++;
                    }
                }
            }
            if(stepLength < 0){
                for(int i = 0; i < (-1 * stepLength); i++){
                    y -= 1;
                    if (table[x][y] != -1){
                        output[intCounter] = table[x][y];
                        intCounter++;
                    }
                }
            }
        }
        stepLength = changeStep(stepLength);
    }
}

int main(){
    FILE *ki;
    ki = fopen("ki.txt", "w");
    char bemenet[2048];
    int bemenetInt[512];
    int matrix[20][20];
    int table [size][size];
    
    readStringFromFile("be.txt", bemenet);
    if(countLines(bemenet) * countColumns(bemenet) <= 0){
        fprintf(ki, "\n");
        return 0;
    }
    readNumbersFromString(bemenet, bemenetInt);
    fillMatrix(matrix, bemenet, bemenetInt);
    setTable(table, matrix, bemenet); 

    int kimenetInt[countColumns(bemenet) * countLines(bemenet)];
    kimenetInt[countColumns(bemenet) * countLines(bemenet)] = -2;

    if(countLines(bemenet) * countColumns(bemenet) == 0){
        fprintf(ki, "\n");
        return 0;
    }

    spiral(table, bemenetInt, kimenetInt);

    for(int i = 0; kimenetInt[i] != -2; i++){
        if(kimenetInt[i + 1] == -2){
            fprintf(ki, "%d\n", kimenetInt[i]);
        }
        else{
            fprintf(ki, "%d,", kimenetInt[i]);
        }
    }

    fclose(ki);

    return 0;
}
