#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

/*trie node*/
typedef struct{
    /*ALPHABET_SIZE*/
    char* username[26];
    char* password;
    /* isEndOfWord is true if the node represents end of a word */
    bool isEndOfWord;
}Trie;

/* Returns new trie node (initialized to NULLs) */
Trie* getNode(){
    Trie* pNode = (Trie*)malloc(sizeof(Trie));
    pNode->isEndOfWord = false;
    int i;
    /*ALPHABET_SIZE = 26*/
    for (i = 0; i < 26; i++){
        pNode->username[i] = NULL;
    }
    return pNode;
}

/* If not present, inserts key into trie */
/* If the key is prefix of trie node, just marks leaf node */
void insert(Trie* root,char* key,char* password){

    int level,index;
    /*copy main trie*/
    Trie* copy = root;

    for (level = 0; level < strlen(key); level++){
        /* Converts key current character into index use only 'a' through 'z' and lower case*/
        index = key[level] - 'a';
        if (!copy->username[index]){
            copy->username[index] = getNode();
        }
        copy = copy->username[index];
    }

    /* mark last node as leaf */
    copy->isEndOfWord = true;
    copy->password = (char*)malloc(strlen(password)*sizeof(char));
    strcpy(copy->password,password);

}
/* Returns true if key presents in trie, else NULL */
bool check(Trie* root,char* key){

    int level,index;
    Trie* copy = root;

    for (level = 0; level < strlen(key); level++){
        /* Converts key current character into index use only 'a' through 'z' and lower case*/
        index = key[level] - 'a';
        if (!copy->username[index]){
            return false;
        }
        copy = copy->username[index];
    }
    if (copy != NULL && copy->isEndOfWord){
        return true;
    }else{
        return NULL;
    }
}
/* Returns true if key presents in trie, else mtu,mtu2,false */
char* search(Trie* root,char* key){

    int level,index;
    Trie* copy = root;
    for (level = 0; level < strlen(key); level++){
        /* Converts key current character into index use only 'a' through 'z' and lower case*/
        index = key[level] - 'a';
        if (!copy->username[index]){
            if(level == 0){
                return false;
            }
            else if(level != 0){
                /*incorrect username*/
                return "mtu2";
            }
        }
        copy = copy->username[index];
    }
    /*function to checks continue of the word*/
    int count = 0,i;
    for (i = 0; i < 26; i++){
        if(copy->username[i] != NULL){
            count++;
        }
    }
    if (copy != NULL && copy->isEndOfWord == true){
        return copy->password;
    }
    else if (copy != NULL && copy->isEndOfWord == false && count != 0){
        /*not enough username*/
        return "mtu";
    }else{
        /*no record-*/
        return NULL;
    }
}
/* function to check if current node is leaf node or not */
bool isLeafNode(Trie* root){
    return root->isEndOfWord != false;
}
/* function to display the content of Trie*/
void display(Trie* root, char str[], int level,FILE* out,int loop){
    int dont_copy = 0;
    if(level != 0){
        int k,count = 0;
        for (k = 0; k < 26; k++){
            if (root->username[k]){
                count++;
            }
        }
        if(count != 1 ){
            str[level] = '\0';
            int j =0;
            for(j;j<loop;j++){
                fprintf(out,"\t");
            }
            loop=1;
            fprintf(out,"-%s\n",str);
            if (isLeafNode(root)){
                dont_copy = 1;
            }
        }
    }
    /* If node is leaf node, it indiicates end of string, so a null charcter is added and string is displayed*/
    if (isLeafNode(root) && dont_copy == 0){
        str[level] = '\0';
        int j =0;
        for(j;j<loop;j++){
            fprintf(out,"\t");
        }
        loop=1;
        fprintf(out,"-%s\n",str);
    }
    int i;
    for (i = 0; i < 26; i++){
        /* if NON NULL child is found add parent key to str and call the display function recursively for child node*/
        if (root->username[i]){
            str[level] = i + 'a';
            display(root->username[i], str, level + 1,out,loop);
        }
    }
}
/* Returns true if root has no children, else false */
bool isEmpty(Trie* root){
    int i;
    for (i=0; i < 26; i++)
        if (root->username[i])
            return false;
    return true;
}
/* Recursive function to delete a key from given Trie */
Trie* removeTrie(Trie* root,char key[], int depth){
    /* If tree is empty */
    if (!root){
        return NULL;
    }
    /* If last character of key is being processed */
    if (depth == strlen(key)) {
        /* This node is no more end of word after removal of given key */
        if (root->isEndOfWord){
            root->isEndOfWord = false;
        }
        /* If given is not prefix of any other word */
        if (isEmpty(root)){
            free(root);
            root = NULL;
        }
        return root;
    }

    /* If not last character, recur for the child obtained using ASCII value */
    int index = key[depth] - 'a';
    root->username[index] = removeTrie(root->username[index], key, depth + 1);

    /* If root does not have any child (its only child got deleted), and it is not end of another word. */
    if (isEmpty(root) && root->isEndOfWord == false) {
        free(root);
        root = NULL;
    }
    return root;
}
/*given error function*/
void give_error(char* name,char* password,int line_limit,int line_limit2,FILE* out){
    if(password != NULL && strcmp(password,"mtu") == 0){
        /*block newline character*/
        if(line_limit2 != line_limit-1){
            fprintf(out,"\"%s\" not enough username\n",name);
        }else{
            fprintf(out,"\"%s\" not enough username",name);
        }
    }
    else if(password != NULL && strcmp(password,"mtu2") == 0){
        /*block newline character*/
        if(line_limit2 != line_limit-1){
            fprintf(out,"\"%s\" incorrect username\n",name);
        }else{
            fprintf(out,"\"%s\" incorrect username",name);
        }
    }
    else if(password == NULL){
        /*block newline character*/
        if(line_limit2 != line_limit-1){
            fprintf(out,"\"%s\" no record\n",name);
        }else{
            fprintf(out,"\"%s\" no record",name);
        }
    }
}
/*read file, fill trie and create output*/
void Readfile_and_output(Trie* root,FILE* input,FILE* out,int line_limit){
    /*reads input file with fgets() function*/
    char line[999];
    int line_limit2 = 0;
    while (fgets(line,999, input)) {
        /*delete new line character*/
        size_t ln = strlen(line) - 1;
        if (*line && line[ln] == '\n'){
            line[ln] = '\0';
        }
        /*split in c Returns first token*/
        char* token = strtok(line," ");
        /* Keep printing tokens while one of the delimiters present in str[].*/
        while (token != NULL){
            /* add username to the tree with the given password*/
            if(strcmp(token,"-a") == 0){
                char* name = strtok(NULL, " ");
                char* password = strtok(NULL, " ");
                /*convert lowercase*/
                int i;
                for(i = 0; i<strlen(name); i++){
                  name[i] = tolower(name[i]);
                }
                /* checks Constructs trie */
                if(check(root,name) == NULL){
                    insert(root,name,password);
                    /*block newline character*/
                    if(line_limit2 != line_limit-1){
                        fprintf(out,"\"%s\" was added\n",name);
                    }else{
                        fprintf(out,"\"%s\" was added",name);
                    }
                }
                else{
                    /*block newline character*/
                    if(line_limit2 != line_limit-1){
                        fprintf(out,"\"%s\" reserved username\n",name);
                    }else{
                        fprintf(out,"\"%s\" reserved username",name);
                    }
                }
            }
            /* search with the given username and return the password if it is */
            else if(strcmp(token,"-s") == 0){
                char* name = strtok(NULL, " ");
                char* password = search(root,name);
                if(password != NULL && strcmp(password,"mtu") != 0 && strcmp(password,"mtu2") != 0){
                    if(line_limit2 != line_limit-1){
                        fprintf(out,"\"%s\" password \"%s\"\n",name,password);
                    }else{
                        fprintf(out,"\"%s\" password \"%s\"",name,password);
                    }
                }else{
                    give_error(name,password,line_limit,line_limit2,out);
                }
            }
            else if(strcmp(token,"-q") == 0){
                char* name = strtok(NULL, " ");
                char* password = search(root,name);
                if(password != NULL && strcmp(password,"mtu") != 0 && strcmp(password,"mtu2") != 0){
                    char* given = strtok(NULL, " ");
                    if(strcmp(given,password) == 0){
                        if(line_limit2 != line_limit-1){
                            fprintf(out,"\"%s\" successful login\n",name);
                        }else{
                            fprintf(out,"\"%s\" successful login",name);
                        }
                    }else{
                        if(line_limit2 != line_limit-1){
                            fprintf(out,"\"%s\" incorrect password\n",name);
                        }else{
                            fprintf(out,"\"%s\" incorrect password",name);
                        }
                    }
                }else{
                    give_error(name,password,line_limit,line_limit2,out);
                }
            }
            else if(strcmp(token,"-d") == 0){
                token = strtok(NULL, " ");
                char* password = search(root,token);
                if(password != NULL && strcmp(password,"mtu") != 0 && strcmp(password,"mtu2") != 0){
                    removeTrie(root,token,0);
                    if(line_limit2 != line_limit-1){
                        fprintf(out,"\"%s\" deletion is successful\n",token);
                    }else{
                        fprintf(out,"\"%s\" deletion is successful",token);
                    }
                }else{
                    give_error(token,password,line_limit,line_limit2,out);
                }
            }
            else if(strcmp(token,"-l") == 0){
                char str[100];
                display(root, str, 0,out,0);
            }
            token = strtok(NULL, " ");
        }
        /*increase line limit*/
        line_limit2++;
    }
}
/*program start*/
int main(int argc,char* argv[]){
    /*open input.txt and output.txt file*/
    FILE* input = fopen(argv[1], "r");
    FILE* out = fopen("output.txt", "w");
    /*reads input file with fgets() function for define last line*/
    int line_limit = 0;
    char line[999];
    while (fgets(line,999, input)) {
        line_limit++;
    }
    /*re-read function in c*/
    rewind(input);
    /*define empty trie node*/
    Trie* root = getNode();
    /*main function start*/
    Readfile_and_output(root,input,out,line_limit);
    /*close file and deallocate root*/
    free(root);
    fclose(input);
    fclose(out);
    return 0;
}
