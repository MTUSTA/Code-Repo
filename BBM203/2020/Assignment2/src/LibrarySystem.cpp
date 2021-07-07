#include "LibrarySystem.h"
#include <iostream>
#include <fstream>

// constructer
User::User(int userId, string userName) {
    this->userId = userId;
    this->userName = userName;
    this->checked = Circular_Singly_Linkedlist<Movie>();
}
// dummy constructer
User::User() {

}
// print user info
void User::user_printer(ofstream *output) {
    *output << "User id: " <<this->userId << " User name: " <<this->userName << endl;
    *output << "User " << this->userId <<" checked out the following Movies:" << endl;
    if(this->checked.start != nullptr) {
        *output << "Movie id - Movie name - Year" << endl;
        Movie* temp = this->checked.start;
        do {
            *output << temp->movieId << " " <<temp->movieName << " " << temp->year << endl;
            temp = temp->next_movie;
        } while (temp != this->checked.start);
    }
}
// constructer
Movie::Movie(int movieId, string movieName, int year) {
    this->movieId = movieId;
    this->movieName = movieName;
    this->year = year;

}
// dummy constructer
Movie::Movie() {

}
// prints all movie data
void Movie::movie_printer(ofstream* output) {
    *output << this->movieId << " " <<this->movieName << " " << this->year <<" Not checked out"<< endl;
}
// constructer
template<typename Class_type>
Circular_Doubly_Linkedlist<Class_type>::Circular_Doubly_Linkedlist() {
    this->start = NULL;
}
// add element in to Circular Doubly Linkedlist
template<typename Class_type>
void Circular_Doubly_Linkedlist<Class_type>::insertEnd(Class_type **start, Class_type *new_user) {
    // If the list is empty, create a single object circular and doubly list
    if((*start) == NULL){
        new_user->next_user = new_user->prew_user = new_user;
        *start= new_user;
        return;
    }
    // If list is not empty

    /* Find last node */
    Class_type* last = (*start)->prew_user;

    // Start is going to be next of new_node
    new_user->next_user = *start;

    // Make new node previous of start
    (*start)->prew_user = new_user;

    // Make last preivous of new node
    new_user->prew_user = last;

    // Make new node next of old last
    last->next_user = new_user;
}
// search element from Circular Doubly Linkedlist
template<typename Class_type>
Class_type *Circular_Doubly_Linkedlist<Class_type>::search_user(int userID) {
    // if list is empty empty return null
    if (this->start == NULL) {
        return nullptr;
    }

    User *temp = this->start;

    while (temp->next_user != this->start) {
        //cout << temp->userId << endl;
        if (temp->userId == userID) {
            return temp;
        }
        temp = temp->next_user;
    }
    //cout << temp->userId << endl;
    if (temp->userId == userID) {
        return temp;
    }
    return nullptr;
}

// delete element from Circular Doubly Linkedlist
template<typename Class_type>
void Circular_Doubly_Linkedlist<Class_type>::delete_(int value) {
    // If list is empty
    if (this->start == NULL)
        return;

    // Find the required node
    // Declare two pointers and initialize them
    User* curr = this->start, *prev_1 = nullptr;
    while (curr->userId != value) {
        // If node is not present in the list
        if (curr->next_user == this->start) {
            printf("\nList doesn't have node with value = %d", value);
            return;
        }
        prev_1 = curr;
        curr = curr->next_user;
    }

    // Check if node is the only node in list
    if (curr->next_user == this->start && prev_1 == nullptr) {
        this->start = NULL;
        free(curr);
        return;
    }

    // If list has more than one node,
    // check if it is the first node
    if (curr == this->start) {
        // Move prev_1 to last node
        prev_1 = this->start->prew_user;

        // Move start ahead
        this->start = this->start->next_user;

        // Adjust the pointers of prev_1 and start node
        prev_1->next_user = this->start;
        this->start->prew_user = prev_1;
        free(curr);
    }

        // check if it is the last node
    else if (curr->next_user == this->start) {
        // Adjust the pointers of prev_1 and start node
        prev_1->next_user = this->start;
        this->start->prew_user = prev_1;
        free(curr);
    }
    else {
        // create new pointer, points to next of curr node
        User* temp = curr->next_user;

        // Adjust the pointers of prev_1 and temp node
        prev_1->next_user = temp;
        temp->prew_user = prev_1;
        free(curr);
    }
}
// find movie from all user checked list
template<typename Class_type>
Class_type * Circular_Doubly_Linkedlist<Class_type>::traverse_and_search_film(int movieID) {


    // If list is empty, return.
    if (this->start == NULL){
        return nullptr;
    }

    // Pointing to first Node of the list.
    Class_type* p = this->start->next_user;
    Movie* founded = nullptr;
    // Traversing the list.
    do{
        founded = p->checked.search_movie(movieID);
        if(founded != nullptr){
            return p;
        }
        p = p -> next_user;
    }
    while(p != this->start->next_user);

    return nullptr;
}
// print all movie from all user checked list
template<typename Class_type>
void Circular_Doubly_Linkedlist<Class_type>::traverse_and_print_all_film(ofstream* output) {
    // If list is empty, return.
    if (this->start == NULL){
        return;
    }

    // Pointing to first Node of the list.
    Class_type* p = this->start->next_user;
    // Traversing the list.
    do{
        // If list is empty, return.
        if ( p->checked.start != NULL){
            Movie* iter = p->checked.start;
            do{
                *output << iter->movieId << " " <<iter->movieName << " " << iter->year <<" Checked out by User "<< p->userId<<endl;
                iter = iter->next_movie;
            }while (iter !=  p->checked.start);
        }

        p = p -> next_user;
    }
    while(p != this->start->next_user);

}

// constructer
template<typename Class_type>
Circular_Singly_Linkedlist<Class_type>::Circular_Singly_Linkedlist() {
    this->start = NULL;
}

// add element in to Circular Singly Linkedlist
template<typename Class_type>
void Circular_Singly_Linkedlist<Class_type>::add(Class_type *new_movie) {

    // if link is empty -> add new node to empty list
    if (this->start == NULL) {
        // Assigning the data.
        this->start = new_movie;
        // link single node to itself.
        this->start->next_movie = this->start;
    }
    else {
        new_movie->next_movie = this->start->next_movie;
        this->start->next_movie = new_movie;
    }
}
// delete element from Circular Singly Linkedlist
template<typename Class_type>
void Circular_Singly_Linkedlist<Class_type>::deletion(int value) {
    // If linked list is empty
    if (this->start == NULL)
        return;

    // If the list contains only a single node
    if(this->start->movieId == value && this->start->next_movie == this->start) {
        free(this->start);
        this->start = NULL;
        return;
    }

    Class_type *last = this->start,*d;
    // If head is to be deleted
    if(this->start->movieId == value) {

        // Find the last node of the list
        while (last->next_movie != this->start)
            last = last->next_movie;

        // Point last node to the next of head i.e.
        // the second node of the list
        last->next_movie = this->start->next_movie;
        free(this->start);
        this->start = last->next_movie;
    }

    // Either the node to be deleted is not found
    // or the end of list is not reached
    while(last->next_movie != this->start && last->next_movie->movieId != value) {
        last = last->next_movie;
    }

    // If node to be deleted was found
    if(last->next_movie->movieId == value) {
        d = last->next_movie;
        last->next_movie = d->next_movie;
        free(d);
    }
    //else
        //cout<<"no such keyfound";
}
// search element from Circular Singly Linkedlist
template<typename Class_type>
Class_type *Circular_Singly_Linkedlist<Class_type>::search_movie(int movieID) {
    // If list is empty, return.
    if ( this->start != NULL){
        Class_type* founded = this->start;
        do{
            if(founded->movieId == movieID){
                return founded;
            }
            founded = founded->next_movie;
        }while (founded != this->start);

        return nullptr;
    }
    return nullptr;
}


// constructer
LibrarySystem::LibrarySystem() {
    this->users = Circular_Doubly_Linkedlist<User>();
    this->movies = Circular_Singly_Linkedlist<Movie>();
}

// adding movie on circular singly linklist
void LibrarySystem::addMovie(const int movieID, string movieTitle,const int year, ofstream* output) {
    // movieID must be positive
    if(movieID <= 0){
        *output << "Movie " << movieID << " must be positive" << endl;
    }
    // if added, print Movie movieID already exists0
   if(this->movies.search_movie(movieID) != nullptr){
        *output << "Movie " << movieID << " already exists" << endl;
    }
    // add object in linked list
    else{
        // creating new movie object
        Movie* new_movie = new Movie(movieID, movieTitle, year);

        this->movies.add(new_movie);
        *output << "Movie " << movieID << " has been added" << endl;
    }
}


/* Function to delete a given node from the circular linked list */
void LibrarySystem::deleteMovie(const int movieID, ofstream* output) {
    Movie* found_movie = this->movies.search_movie(movieID);
    User* user_area = this->users.traverse_and_search_film(movieID);

    if(found_movie == nullptr && user_area == nullptr){
        *output << "Movie " << movieID << " does not exist" << endl;
    }else if(found_movie != nullptr && user_area == nullptr){
        *output << "Movie " << movieID << " has not been checked out" << endl;
        this->movies.deletion(movieID);
        *output << "Movie " << movieID << " has been deleted" << endl;
    }else if(found_movie == nullptr && user_area != nullptr){
        *output << "Movie " << movieID << " has been checked out" << endl;
        user_area->checked.deletion(movieID);
        *output << "Movie " << movieID << " has been deleted" << endl;
    }

}

// if user does not exist , add it
void LibrarySystem::addUser(const int userID, string userName, ofstream* output) {
    User* found_user = this->users.search_user(userID);
    if(found_user == nullptr){
        User *new_user = new User(userID, userName);
        this->users.insertEnd(&(this->users.start), new_user);
        *output << "User " << userID << " has been added" << endl;
    }else {
        *output << "User " << userID << " already exists" << endl;
    }
}
// if user exists , add it
void LibrarySystem::deleteUser(const int userId, ofstream* output) {
    User* found_user = this->users.search_user(userId);
    if (found_user == nullptr){
        *output << "User "<<userId<<" does not exist" << endl;
    }else{
        this->users.delete_(userId);
        *output << "User "<<userId<<" has been deleted" << endl;
    }
}



void LibrarySystem::checkoutMovie(int movieID, int userID, ofstream* output) {
    // find movie
    Movie* found_movie = this->movies.search_movie(movieID);
    if(found_movie == nullptr){
        *output << "Movie " << movieID << " does not exist for checkout" << endl;
        return;
    }
    // find user
    User* found_user = this->users.search_user(userID);
    if(found_user == nullptr){
        *output << "User "<< userID <<" does not exist for checkout" << endl;
        return;
    }
    else {
        // add movie to user check linked list
        found_user->checked.add(new Movie(found_movie->movieId, found_movie->movieName, found_movie->year));
        // delete movie from linked list
        this->movies.deletion(movieID);
        *output << "Movie " << movieID << " has been checked out by User " << userID << endl;
    }

}

void LibrarySystem::returnMovie(int movieID, ofstream* output) {
    Movie* found_movie = this->movies.search_movie(movieID);
    User* user_area = this->users.traverse_and_search_film(movieID);
    if(found_movie != nullptr){
        *output << "Movie "<<movieID<<" has not been checked out" << endl;
    }else if(user_area != nullptr){
        // get movie from user checkout
        Movie* temp = user_area->checked.search_movie(movieID);
        // add movie to movies (not checkout)
        this->movies.add(new Movie(temp->movieId, temp->movieName, temp->year));
        // delete movie from user
        user_area->checked.deletion(movieID);
        *output << "Movie " << movieID <<" has been returned" <<endl;
    }
    else{
        *output << "Movie "<<movieID<<" not exist in the library" << endl;
    }
}

// Movie* temp = this->movies.start;
void LibrarySystem::showAllMovie(ofstream* output) {
    *output << "Movie id - Movie name - Year - Status" << endl;
    Movie* temp = this->movies.start;
    if (this->movies.start != NULL) {
        do {
            temp->movie_printer(output);
            temp = temp->next_movie;
        } while (temp != this->movies.start);
    }
    this->users.traverse_and_print_all_film(output);

}

void LibrarySystem::showMovie(int movieID, ofstream* output) {
    Movie* found_movie = this->movies.search_movie(movieID);
    User* user_area = this->users.traverse_and_search_film(movieID);
    if(found_movie == nullptr && user_area == nullptr){
        *output << "Movie with the id "<<movieID<<" does not exist" << endl;
    }else if(found_movie != nullptr && user_area == nullptr){
        found_movie->movie_printer(output);
    }
    else if(found_movie == nullptr && user_area != nullptr){
       Movie* print = user_area->checked.search_movie(movieID);
        *output << print->movieId << " " <<print->movieName << " " << print->year <<" Checked out by User "<< user_area->userId <<endl;

    }
}
// if user is valid show user info
void LibrarySystem::showUser(int userID, ofstream* output) {
    User* found_user = this->users.search_user(userID);
    if (found_user == nullptr){
        *output << "User with the id "<<userID<<" does not exist" << endl;
    }else{
        found_user->user_printer(output);
    }
}

// deconstructer
LibrarySystem::~LibrarySystem() {
    // save start point for blocking error
    Movie* temp = this->movies.start;
    // If linked list is not empty
    if (this->movies.start != nullptr){
        // Keep printing nodes till we reach the first node again
        this->movies.start = this->movies.start->next_movie;
        do{
            Movie* temp2 = this->movies.start;
            this->movies.start = this->movies.start->next_movie;
            delete temp2;
        }
        while (this->movies.start != temp);

    }
    // if allocated delete it
    if(temp != nullptr) {
        delete temp;
    }


    User* temp_user = this->users.start;
    // If linked list is not empty
    if (this->users.start != nullptr){
        // Keep printing nodes till we reach the first node again
        this->users.start = this->users.start->next_user;
        do{
            User* temp2 = this->users.start;
            this->users.start = this->users.start->next_user;
            delete temp2;
        }
        while (this->users.start != temp_user);

    }
    // if allocated delete it
    if(temp_user != nullptr) {
        delete temp_user;
    }

}
