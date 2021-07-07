#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>

#include "Queue.h"
#include "Stack.h"

#include "Stack.cpp"
#include "Queue.cpp"

using namespace std;
// package class
class package{
public:
    string package_name;
    package(string name){
        this->package_name = name;
    }
    // dummy constructer
    package(){}
};

//
template<typename T> class DoublyLinkedList{
private:
    struct DoublyLinkedListNode {
        T item;
        DoublyLinkedListNode *next;
        DoublyLinkedListNode *prev;
    };

    /* Start with the empty list */
    DoublyLinkedListNode* first; // link to least recently added node
    DoublyLinkedListNode* last; // link to most recently added node
    int n ; // number of items on the queue

public:
    // constructer
    DoublyLinkedList(){
        this->first = nullptr;
        this->last = nullptr;
        this->n = 0;
    }
    // Returns true if the doubly linked list does not contain any item.
    bool isEmpty() {
        return this->n == 0; // Or: this->first == nullptr
    }
    // Returns the number of items in the doubly linked list.
    int size() {
        return this->n;
    }

    // add item end of the linked list
    void addToLast(T &item) {
        // Save the old node
        DoublyLinkedListNode* oldlast = this->last;

        // Make a new node and assign it to tail. Fix pointers.
        this->last = new DoublyLinkedListNode();
        this->last->item = item;
        this->last->next = nullptr;
        this->last->prev = oldlast;

        // if first node to be added, adjust head to it.
        if (this->first == nullptr) {
            this->first = this->last;
        }
        else{
            oldlast->next = last;
        }
        // increase size
        this->n++;
    }


    // Retrieves and removes the item at the specified index
    T deleteNodeAGivenPos(int index) {
        // remove first index
        if(index == 0){
            DoublyLinkedListNode* oldFirst = first;
            // Fix pointers.
            first = first->next;
            // at least 1 nodes left.
            if (first != nullptr) {
                first->prev = nullptr;
            } else {
                last = nullptr; // remove final node.
            }
            oldFirst->next = nullptr;
            n--;

            return oldFirst->item;
        }
        // remove last index
        else if(index == this->size() - 1){
            DoublyLinkedListNode* temp = this->last;
            last = last->prev;
            // if there was only one node in the doubly linked list.
            if (last == nullptr) {
                first = nullptr;
            } else {
                last->next = nullptr;
            }
            this->n--;
            return temp->item;
        }else{
            DoublyLinkedListNode* previous = nullptr;
            DoublyLinkedListNode* finger = this->first;
            // search for value indexed, keep track of previous
            while (index > 0) {
                previous = finger;
                finger = finger->next;
                index--;
            }
            previous->next = finger->next;
            finger->next->prev = previous;
            this->n--;
            // finger's value is old value, return it
            return finger->item;
        }

    }


};

class truck : public package {

public:
    // dummy constructer
    DoublyLinkedList<package> packages = DoublyLinkedList<package>();

    string truck_name;
    // dummy constructer
    truck(){}

    truck(string t_name){
        this->truck_name = t_name;
        package truckk = *new truck();
        truckk.package_name = t_name;

        this->packages.addToLast(truckk);
    }
};

class destination{
public:
    string destination_name;
    // every destination has a packages
    Stack<package> packages = Stack<package>();
    // every destination has a truck
    Queue<truck> garage = Queue<truck>();
    destination(string d_name){
        this->destination_name=d_name;
    }
    // dummy constructer
    destination(){}
};


// split sentence by delimiter
vector<string> split (const string &s, char delim) {
    vector<string> result;
    stringstream ss (s);
    string item;

    while (getline (ss, item, delim)) {
        result.push_back (item);
    }

    return result;
}

// init function -> create all destinations
vector<destination> create_destination(string filename){
    // store destionations
    vector<destination> dest = vector<destination>();
    string line;
    // Read from the text file
    ifstream dest_file(filename);
    while (getline (dest_file, line)) {
        // split line by given delimiter
        vector<string> splitted_line = split(line, ' ');
        // create object and add on vector
        dest.emplace_back(splitted_line[0]);
    }
    dest_file.close();
    return dest;
}
// find destination-> create packet and add stack
vector<destination> create_package(string filename, vector<destination> dest) {
    string line;
    // Read from the text file
    ifstream command(filename);
    while (getline (command, line)) {
        // split line by given delimiter
        vector<string> splitted_line = split(line, ' ');
        // find destination and push item(package)
        for(int i = 0; i<dest.size();i++){
            if(dest[i].destination_name == splitted_line[1]){
                // create item
                package item = *(new package(splitted_line[0]));
                // adding on stack
                dest[i].packages.push(item);
                break;
            }
        }
    }
    command.close();
    return dest;
}
// find destination-> create truck and add queue
vector<destination> create_truck(string filename, vector<destination> dest) {
    string line;
    // Read from the text file
    ifstream command(filename);
    while (getline (command, line)) {
        // split line by given delimiter
        vector<string> splitted_line = split(line, ' ');
        // find destination and push item(package)
        for(int i = 0; i<dest.size();i++){
            if(dest[i].destination_name == splitted_line[1]){
                // create truck
                truck item = *new truck(splitted_line[0]);
                // add queue
                dest[i].garage.enqueue(item);
            }
        }
    }
    command.close();
    return dest;
}
// apply commands or mission
vector<destination> apply_mission(string filename, vector<destination> dest){
    string line;
    // Read from the text file
    ifstream command(filename);
    while (getline (command, line)) {
        // split line by given delimiter
        vector<string> splitted_line = split(line, '-');
        // store index of stations
        int starway_index = 0, midway_index = 0, target_index = 0;
        // get index from array and save on variables
        for (int i = 0; i < dest.size(); i++) {
            // starway_index
            if (splitted_line[0] == dest[i].destination_name) {
                starway_index = i;
            }
                // midway_index
            else if (splitted_line[1] == dest[i].destination_name) {
                midway_index = i;
            }
                // target_index
            else if (splitted_line[2] == dest[i].destination_name) {
                target_index = i;
            }
        }
        truck selected_truck;
        // get truck from garage
        dest[starway_index].garage.getFront(selected_truck);
        // remove truck from garage
        dest[starway_index].garage.dequeue();
        // convert string to integer. x is a number of cargo packages you take from the starting station
        int x = stoi(splitted_line[3]);
        // adding package starway station to truck
        for(int j = 0; j < x ;j++) {
            package dummy;
            dest[starway_index].packages.getTop(dummy);
            dest[starway_index].packages.pop();
            selected_truck.packages.addToLast(dummy);
        }
        // convert string to integer. y is a number of cargo packages you take from the midway station
        int y = stoi(splitted_line[4]);
        // adding package midway station to truck
        for(int k = 0; k < y ;k++) {
            package dummy;
            dest[midway_index].packages.getTop(dummy);
            dest[midway_index].packages.pop();
            selected_truck.packages.addToLast(dummy);
        }
        // split z values
        vector<string> z_values = split(splitted_line[5], ',');
        vector<int> converted_z_values;
        for(string s : z_values){
            converted_z_values.push_back(stoi(s));
        }

        // add package from truck to midway station
        for(int l = 0; l < converted_z_values.size(); l++){
            // A helper method to check if an index is in range 0<=index<n
            if(converted_z_values[l]+1 > selected_truck.packages.size() || converted_z_values[l]+1 < 0){
                // out of index do nothing
                // why adding +1 -> first index is truck -> I ignored it
            }else{
                package dummy;
                // delete package from truck doubly linked list and return value
                dummy = selected_truck.packages.deleteNodeAGivenPos(converted_z_values[l]+1);
                // add on midway station
                dest[midway_index].packages.push(dummy);
                // Preventing an index shift
                for(int index=l+1; index < converted_z_values.size();index++){
                    if(converted_z_values[l] < converted_z_values[index]){
                        converted_z_values[index] -= 1;
                    }
                }
            }
        }
        // cargo truck reaches to target station then start process
        // first cargo element is truck name
        while(selected_truck.packages.size() != 1){
            package dummy;
            // delete package from truck doubly linked list and return value
            dummy = selected_truck.packages.deleteNodeAGivenPos(1);
            // add on midway station
            dest[target_index].packages.push(dummy);
        }
        // add truck to target station garage
        dest[target_index].garage.enqueue(selected_truck);

    }
    return dest;
}
// print all result on file
void print_output(ofstream* output, vector<destination> dest) {
    for(int i = 0; i<dest.size();i++){
        *output << dest[i].destination_name << endl;
        *output << "Packages:" << endl;
        // printing all package
        while (!dest[i].packages.isEmpty()){
            package deneme ;
            dest[i].packages.getTop( deneme);
            *output << deneme.package_name << endl;
            dest[i].packages.pop();
        }
        *output << "Trucks:" << endl;
        // printing all Trucks
        while (!dest[i].garage.isEmpty()){
            truck deneme ;
            dest[i].garage.getFront( deneme);
            *output << deneme.truck_name << endl;
            dest[i].garage.dequeue();
        }
        *output << "-------------" << endl;
    }

}

//program starts
int main(int argc, char** argv) {
    // create destinations
    vector<destination> destinations = create_destination(argv[1]);
    // create packages and saved on destinations
    destinations = create_package(argv[2], destinations);
    // create create_truck and saved on destinations
    destinations = create_truck(argv[3],destinations);
    // apply mission
    destinations = apply_mission(argv[4],destinations);
    // create output file
    ofstream output(argv[5]);
    // print all information on output file
    print_output(&output, destinations);
    // close output file
    output.close();
    return 0;
}


