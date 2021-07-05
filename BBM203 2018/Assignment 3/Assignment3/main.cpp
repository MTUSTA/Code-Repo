#include <iostream>
#include <fstream>

using namespace std;
// A doubly linked list node
typedef struct Node2{
    int minute;
    int match_id;
    string away_team;
    Node2* next;
    Node2* prev;
}Node2;
// A linked list node
typedef struct Node{
    string name;
    string team;
    Node2* MinAndMatch;
    Node* next;
}Node;
/* Given a reference (pointer to pointer) to the head of a list and an int, appends a new node at the end  */
void single_append(Node** head_ref, string name, string team, string away_team, int minute, int match_id){
    /* 1. allocate node */
    Node* new_node = new Node;
    Node *last = *head_ref;  /* used in step 5*/
    /* 2. put in the data  */
    new_node->name = name;
    new_node->team = team;
    new_node->MinAndMatch = new Node2;
    new_node->MinAndMatch->minute = minute;
    new_node->MinAndMatch->match_id = match_id;
    new_node->MinAndMatch->away_team = away_team;
    /* 3. This new node is going to be the last node, so make next of it as NULL*/
    new_node->next = NULL;
    new_node->MinAndMatch->next = NULL;
    new_node->MinAndMatch->prev = NULL;
    /* 4. If the Linked List is empty, then make the new node as head */
    if (*head_ref == NULL){
       *head_ref = new_node;
       return;
    }
    /* 5. Else traverse till the last node */
    while (last->next != NULL)
        last = last->next;
    /* 6. Change the next of last node */
    last->next = new_node;
    return;
}
/* Given a reference (pointer to pointer) to the head
   of a DLL and an int, appends a new node at the end  */
void doubly_append(Node2** head_ref, int minute, int match_id,string away_team){
    /* 1. allocate node */
    Node2* new_node = new Node2;
    Node2* last = *head_ref; /* used in step 5*/
    /* 2. put in the data  */
    new_node->minute = minute;
    new_node->match_id = match_id;
    new_node->away_team = away_team;
    /* 3. This new node is going to be the last node, so
          make next of it as NULL*/
    new_node->next = NULL;
    /* 4. If the Linked List is empty, then make the new
          node as head */
    if (*head_ref == NULL) {
        new_node->prev = NULL;
        *head_ref = new_node;
        return;
    }
    /* 5. Else traverse till the last node */
    while (last->next != NULL)
        last = last->next;
    /* 6. Change the next of last node */
    last->next = new_node;
    /* 7. Make last node as previous of new node */
    new_node->prev = last;
    return;
}

/* Split the nodes of the given list into front and back halves,and return the two lists using the reference parameters.
    If the length is odd, the extra node should go in the front list.Uses the fast/slow pointer strategy. */
void single_FrontBackSplit(Node* source,Node** frontRef,Node** backRef){
    Node* fast;
    Node* slow;
    slow = source;
    fast = source->next;
    /* Advance 'fast' two nodes, and advance 'slow' one node */
    while (fast != NULL){
        fast = fast->next;
        if (fast != NULL){
            slow = slow->next;
            fast = fast->next;
        }
    }
    /* 'slow' is before the midpoint in the list, so split it in two at that point. */
    *frontRef = source;
    *backRef = slow->next;
    slow->next = NULL;
}

Node* single_SortedMerge(Node* a, Node* b){
    Node* result = NULL;
    /* Base cases */
    if (a == NULL){
        return(b);
    }
    else if (b==NULL){
        return(a);
    }
    /* Pick either a or b, and recur */
    if (a->name <= b->name){
        result = a;
        result->next = single_SortedMerge(a->next, b);
    }
    else{
        result = b;
        result->next = single_SortedMerge(a, b->next);
    }
    return(result);
}
/* sorts the linked list by changing next pointers (not data) */
void single_MergeSort(Node** headRef){
    Node* head = *headRef;
    Node* a;
    Node* b;
    /* Base case -- length 0 or 1 */
    if ((head == NULL) || (head->next == NULL)){
        return;
    }
    /* Split head into 'a' and 'b' sublists */
    single_FrontBackSplit(head, &a, &b);
    /* Recursively sort the sublists */
    single_MergeSort(&a);
    single_MergeSort(&b);
    /* answer = merge the two sorted lists together */
    *headRef = single_SortedMerge(a, b);
}

// Split a doubly linked list (DLL) into 2 DLLs of half sizes
Node2* split(Node2* head){
    Node2* fast = head;
    Node2* slow = head;
    while (fast->next && fast->next->next){
        fast = fast->next->next;
        slow = slow->next;
    }
    Node2* temp = slow->next;
    slow->next = NULL;
    return temp;
}
// Function to merge two linked lists
Node2* doubly_merge(Node2* first, Node2* second){
    // If first linked list is empty
    if (!first)
        return second;
    // If second linked list is empty
    if (!second)
        return first;
    // Pick the smaller value
    if (first->match_id < second->match_id){
        first->next = doubly_merge(first->next,second);
        first->next->prev = first;
        first->prev = NULL;
        return first;
    }
    else{
        second->next = doubly_merge(first,second->next);
        second->next->prev = second;
        second->prev = NULL;
        return second;
    }
}
// Function to do merge sort
Node2* doubly_mergeSort(Node2* head){
    if (!head || !head->next)
        return head;
    Node2* second = split(head);

    // Recur for left and right halves
    head = doubly_mergeSort(head);
    second = doubly_mergeSort(second);

    // Merge the two sorted halves
    return doubly_merge(head,second);
}
// function to reads input.txt file , creates player and append(last node) linked list
Node* readinputfile(Node* head,string argv){
    string line ;
    ifstream user_input(argv);
    //In each row,there are the name of the footballer
    string name;
    //the team name of footballer who scored
    string team;
    //the name of the away team
    string away_team;
    //minute of the scored goal
    int minute;
    //the match ID.
    int match_id;
    //reads lines
    while (getline(user_input,line)){
        /*delete new line character*/
        if (!line.empty() && line[line.length()-1] == '\n') {
            line.erase(line.length()-1);
        }
        std::string delimiter = ",";
        /*split string in c++*/
        int i=0;
        size_t pos = 0;
        string token;
        while ((pos = line.find(delimiter)) != string::npos) {
            token = line.substr(0, pos);
            int len = token.length();
            if(i == 0){
                name = token;
            }
            else if(i == 1){
                team = token;
            }
            else if(i == 2){
                away_team = token;
            }
            else if(i == 3){
                minute = stoi(token);
            }
            line.erase(0, pos + delimiter.length());
            i++;
        }
        match_id = stoi(line);
        //checking player
        int esitlik = 0;
        Node* current = head;  // Initialize current
        while (current != NULL){
            if (current->name == name){
                doubly_append(&current->MinAndMatch,minute,match_id,away_team);
                // if linked list contains same player names, esitlik will be 1
                esitlik = 1;
            }
            current = current->next;
        }
        // if esitlik equals 0,new player will append at linked list
        if(esitlik == 0){
            single_append(&head,name,team,away_team,minute,match_id);
        }

    }
    //close input file
    user_input.close();
    return head;
}
void read_operations_and_output(Node* head,string operations,string output){
    string line ;
    ifstream user_input(operations);
    ofstream out(output);
    //hide given footballer
    string given_footballer[6];
    /*reads operations file*/
    int i=0;
    while (getline(user_input,line)){
        /*delete new line character*/
        if (!line.empty() && line[line.length()-1] == '\n') {
            line.erase(line.length()-1);
        }
        std::string delimiter = ",";
        /*split string in c++*/
        size_t pos = 0;
        std::string token;
        while ((pos = line.find(delimiter)) != std::string::npos) {
            token = line.substr(0, pos);
            int len = token.length();
            if(i == 0){
                given_footballer[0] = token;
            }
            else if(i == 2){
                given_footballer[2] = token;
            }
            else if(i == 4){
                given_footballer[4] = token;
            }
            line.erase(0, pos + delimiter.length());
            i++;
        }
        int len2 = line.length();
        if(i == 1){
            given_footballer[1] = line;
        }
        else if(i == 3){
            given_footballer[3] = line;
        }
        else if(i == 5){
            given_footballer[5] = line;
        }
        i++;
    }
    int half = 0;
    //determine max_goal for goal scorer
    int max_goal =0;
    Node* current = head;
    //calculate most scored half
    while (current != NULL){
        Node2* current2 = current->MinAndMatch;
        int goal = 0 ;
        while(current2 !=NULL){
            if(current2->minute > 45){
                half++;
            }
            else if(current2->minute< 46){
                half--;
            }
            goal++;
            current2 = current2->next;
        }
        if(goal>max_goal){
            max_goal=goal;
        }
        current = current->next;
    }

    out << "1)THE MOST SCORED HALF\n";
    if(half>0){
        out << "1\n";
    }else{
        out << "0\n";
    }

    out << "2)GOAL SCORER\n";
    current = head;
    //calculate GOAL SCORER , checks with max goal var.
    while (current != NULL){
        Node2* current2 = current->MinAndMatch;
        int goal =0;

        while(current2 !=NULL){
            goal++;
            current2 = current2->next;
        }
        if(goal == max_goal){
            out << current->name<<endl;
        }
        current = current->next;
    }
    // calculate footballer goal and detect who scored hat-trick
    out << "3)THE NAMES OF FOOTBALLERS WHO SCORED HAT-TRICK\n";
    current = head;
    while (current != NULL){
        Node2* current2 = current->MinAndMatch;
        while(current2 !=NULL){
            int hat_trick =0;
            Node2* current3 = current->MinAndMatch;
            while(current3 != NULL){
                if(current2->match_id == current3->match_id){
                    hat_trick++;
                }
                current3 = current3->next;
            }
            if(hat_trick >= 3){
                out << current->name << endl;
                break;
            }
            current2 = current2->next;

        }
        current = current->next;
    }
    out << "4)LIST OF TEAMS\n";
    current = head;
    int yer =0;
    while (current != NULL){
        Node* current2 = head;
        int yer2 = 0;
        int eslik = 0;
        while (current2 != NULL && yer2<yer){
            if(current->team == current2->team){
                eslik = 1;
            }
            current2 = current2->next;
            yer2++;
        }
        if(eslik == 0){
            out<< current->team<< endl;
        }
        current = current->next;
        yer++;
    }

    out << "5)LIST OF FOOTBALLERS\n";
    current = head;
    while (current != NULL){
        out<< current->name <<endl;
        current = current->next;
    }

    out << "6)MATCHES OF GIVEN FOOTBALLER\n";
    int arraylik = 0,check = 0;
    current = head;
    while (current != NULL){
            if(current->name == given_footballer[arraylik]){
                out << "Matches of "<< current->name<<endl;
                Node2* current2 = current->MinAndMatch;
                while(current2 !=NULL){
                    out << "Footballer Name: "<< current->name << ",Away Team: "<< current2->away_team << ",Min of Goal: "<< current2->minute <<",Match ID: "<<current2->match_id<<endl;
                    current2 = current2->next;
                }
                arraylik++;
                check = 1;
            }
        current = current->next;
        if(check == 1){
            current = head;
            check =0;
        }
        if(arraylik == 2){
            current=NULL;
        }

    }

    out << "7)ASCENDING ORDER ACCORDING TO MATCH ID\n";
    current = head;
    while (current != NULL){
        if(current->name == given_footballer[arraylik]){
            Node2* current2 = current->MinAndMatch;
            while(current2 !=NULL){
                int eslik = 0;
                Node2* current3 = current2->prev;
                while(current3 != NULL){
                    if(current3->match_id == current2->match_id){
                        eslik = 1;
                    }
                    current3 = current3->prev;
                }

                if(eslik == 0){
                    out << "Footballer Name: "<<current->name << ",Match ID: "<<current2->match_id<<endl;
                }
                current2 = current2->next;
            }
            arraylik++;
            check = 1;
        }
        current = current->next;
        if(check == 1){
            current = head;
            check =0;
        }
        if(arraylik == 4){
            current=NULL;
        }

    }

    out << "8)DESCENDING ORDER ACCORDING TO MATCH ID\n";
    current = head;
    while (current != NULL){
            if(current->name == given_footballer[arraylik]){
                //Traversal in forward direction
                Node2* last;
                while(current->MinAndMatch !=NULL){
                    last = current->MinAndMatch;
                    current->MinAndMatch = current->MinAndMatch->next;
                }
                //Traversal in reverse direction
                while (last != NULL) {

                    int eslik = 0;
                    Node2* current3 = last->next;
                    while(current3 != NULL){
                        if(current3->match_id == last->match_id){
                            eslik = 1;
                        }
                        current3 = current3->next;
                    }

                    if(eslik == 0){
                        out <<"Footballer Name: "<< current->name<<",Match ID: "<<last->match_id <<endl;
                    }
                    last = last->prev;
                }
            arraylik++;
            check = 1;
            }
        current = current->next;
        if(check == 1){
            current = head;
            check =0;
        }
        if(arraylik == 6){
            current=NULL;
        }
    }
    // delete current
    delete current;
    //close input and output
    out.close();
    user_input.close();
}
/* Driver program to test above functions*/
int main(int argc,char* argv[]){
    // Start with the empty linked list
    Node* head = NULL;
    //Firstly, you will read input.txt file and create linked list according to the ascending order of the names of the footballers.
    head = readinputfile(head,argv[1]);
    // Sort the above created Linked List
    single_MergeSort(&head);
    //sorts match id
    Node* current = head;
    while (current != NULL){
        current->MinAndMatch = doubly_mergeSort(current->MinAndMatch);
        current = current->next;
    }
    //reads operations.txt and creates output file
    read_operations_and_output(head,argv[2],argv[3]);

    delete head;
return 0;
}
