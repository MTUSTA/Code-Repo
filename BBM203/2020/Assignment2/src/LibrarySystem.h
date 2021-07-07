#ifndef LIBRARYSYSTEM_H
#define LIBRARYSYSTEM_H

#include <string>
using namespace std;
class Movie{
public:
    int movieId;
    string movieName;
    int year;
    Movie* next_movie;
    // constructer
    Movie(int movieId, string movieName, int year);
    // dummy constructer
    Movie();
    void movie_printer(ofstream* output);
};


template <typename Class_type> class Circular_Singly_Linkedlist{
public:
    /* Start with the empty list */
    Class_type* start;
    //constructor to create an empty LinkedList
    Circular_Singly_Linkedlist();
    // add element
    void add(Class_type* new_movie);
    // delete element
    void deletion(int value);
    // find given value , if not find value return nullptr
    Class_type* search_movie(int movieID);
};

class User{
public:
    User* next_user;
    User* prew_user;
    int userId;
    string userName;
    // User checked linked list
    Circular_Singly_Linkedlist<Movie> checked;
    // constructer
    User(int userId, string userName);
    // dummy constructer
    User();
    // print user info to output file
    void user_printer(ofstream *output);

};


template<typename Class_type> class Circular_Doubly_Linkedlist{
public:
    /* Start with the empty list */
    Class_type* start;
    //constructor to create an empty LinkedList
    Circular_Doubly_Linkedlist();
    // adding element
    void insertEnd(Class_type** start,Class_type* new_User);
    // find given value , if not find value return nullptr
    Class_type* search_user(int userID);
    // find given value , if not find value return nullptr
    Class_type* traverse_and_search_film(int movieID);
    void traverse_and_print_all_film(ofstream* output);
    // delete value from memory
    void delete_(int value);
};


class LibrarySystem {
public:
    Circular_Doubly_Linkedlist<User> users;
    Circular_Singly_Linkedlist<Movie> movies;
    // constructer
    LibrarySystem();
    // deconstructer
    ~LibrarySystem();
    void addMovie(const int movieID, string movieTitle,const int year, ofstream* output);
    void deleteMovie(const int movieID, ofstream* output);

    void addUser(const int userID, string userName, ofstream* output);
    void deleteUser(const int userId, ofstream* output);

    void checkoutMovie(const int movieID,const int userID, ofstream* output);
    void returnMovie(const int movieID, ofstream* output);

    void showAllMovie(ofstream* output);
    void showMovie(const int movieID, ofstream* output);
    void showUser(const int userID, ofstream* output);
};


#endif // LIBRARYSYSTEM_H
