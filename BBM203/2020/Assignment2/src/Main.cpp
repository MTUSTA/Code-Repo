#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>

#include "LibrarySystem.h"

using namespace std;
// for string delimiter
vector<string> split (const string &s, char delim) {
    vector<string> result;
    stringstream ss (s);
    string item;

    while (getline (ss, item, delim)) {
        result.push_back (item);
    }

    return result;
}
void apply_commands(LibrarySystem* library, string file_name, ofstream* output) {
    // for output
    string title="";
    *output << "===Movie Library System===" << endl;
    string line;
    // Read from the text file
    ifstream command(file_name);
    // Use a while loop together with the getline() function to read the file line by line
    while (getline (command, line)) {
        // split line by tabs
        vector<string> splitted_line = split(line,'\t');

        if(splitted_line[0] == "addMovie"){
            if(title != "addMovie"){
                *output << "\n===addMovie() method test===" << endl;
                title = "addMovie";
            }
            // transform string to int
            int movieID = stoi(splitted_line[1]);
            int year = stoi(splitted_line[3]);
            // addmovie commands
            library->addMovie(movieID,splitted_line[2],year, output);
        }
        else if(splitted_line[0] == "deleteMovie"){
            if(title != "deleteMovie"){
                *output << "\n===deleteMovie() method test===" << endl;
                title = "deleteMovie";
            }
            // transform string to int
            int movieID = stoi(splitted_line[1]);
            // deleteMovie commands
            library->deleteMovie(movieID, output);
        }
        else if(splitted_line[0] == "addUser"){
            if(title != "addUser"){
                *output << "\n===addUser() method test===" << endl;
                title = "addUser";
            }
            // transform string to int
            int userID = stoi(splitted_line[1]);
            // addUser commands
            library->addUser(userID, splitted_line[2], output);

        }
        else if(splitted_line[0] == "deleteUser"){
            if(title != "deleteUser"){
                *output << "\n===deleteUser() method test===" << endl;
                title = "deleteUser";
            }
            // transform string to int
            int userID = stoi(splitted_line[1]);
            // deleteUser commands
            library->deleteUser(userID, output);
        }
        else if(splitted_line[0] == "checkoutMovie"){
            if(title != "checkoutMovie"){
                *output << "\n===checkoutMovie() method test===" << endl;
                title = "checkoutMovie";
            }
            // transform string to int
            int movieID = stoi(splitted_line[1]);
            int userID = stoi(splitted_line[2]);
            // checkoutMovie commands
            library->checkoutMovie(movieID, userID, output);
        }
        else if(splitted_line[0] == "returnMovie"){
            if(title != "returnMovie"){
                *output << "\n===returnMovie() method test===" << endl;
                title = "returnMovie";
            }
            // transform string to int
            int movieID = stoi(splitted_line[1]);
            // returnMovie commands
            library->returnMovie(movieID, output);
        }
        else if(splitted_line[0] == "showAllMovie"){
            if(title != "showAllMovie"){
                *output << "\n===showAllMovie() method test===" << endl;
                title = "showAllMovie";
            }
            // showAllMovie commands
            library->showAllMovie(output);
        }
        else if(splitted_line[0] == "showMovie"){
            if(title != "showMovie"){
                *output << "\n===showMovie() method test===" << endl;
                title = "showMovie";
            }
            // transform string to int
            int movieID = stoi(splitted_line[1]);
            // showMovie commands
            library->showMovie(movieID, output);
        }
        else if(splitted_line[0] == "showUser"){
            if(title != "showUser"){
                *output << "\n===showUser() method test===" << endl;
                title = "showUser";
            }
            // transform string to int
            int userID = stoi(splitted_line[1]);
            // showUser commands
            library->showUser(userID, output);
        }

    }
    // close file
    command.close();
}

//program starts
int main(int argc, char** argv) {
    // create library object
    LibrarySystem* library = new LibrarySystem();

    // create output.txt
    ofstream output(argv[2]);
    // apply commands
    apply_commands(library, argv[1], &output);
    // free allocated area then finish program
    delete library;
    // close output file
    output.close();

    return 0;
}
