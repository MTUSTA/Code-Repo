#include <string>
#include <sstream>
#include <vector>
#include "BinaryTree.h"

using namespace std;
// ascci size
static const int asci_size = 256;
// Count the number of occurrences of each character.
void count_frequency(int* frequency, string line){
    for (int i = 0; i < line.size(); i++){
        frequency[tolower(line[i])]++;
    }
}

// program starts
int main(int argc, char* argv[]) {
    // if command line queals = -i inputfile.txt -encode
    if(string(argv[1])=="-i" && string(argv[3])=="-encode") {

        // Read line from the text file
        ifstream input_file(argv[2]);

        // store line from input file
        string input_line;
        // read first line
        getline(input_file, input_line);

        // create empty frequency table , ascii size is a static const 256
        int frequency[asci_size] = {0};
        // counts the frequency of the letters in the line
        count_frequency(frequency, input_line);

        // huffman BinaryTree object
        BinaryTree* huffman = new BinaryTree();

        //huffman encoding start
        huffman->encoding(frequency, input_line);

        // close input file
        input_file.close();
    }
    // if command line queals = -i inputfile.txt -decode
    else if(string(argv[1])=="-i" && string(argv[3])=="-decode") {
        // Read line from the text file
        ifstream input_file(argv[2]);
        // Read tree from saved file
        ifstream tree_file("saved_tree.txt");
        // huffman BinaryTree object
        BinaryTree* huffman = new BinaryTree();
        //huffman decoding start
        huffman->decoding(&input_file,&tree_file);
        // close files
        input_file.close();
        tree_file.close();
    }
    // if command line queals = -l
    else if(string(argv[1]) == "-l") {
        // Read tree from file
        ifstream tree_file("saved_tree.txt");
        // huffman BinaryTree object
        BinaryTree* huffman = new BinaryTree();
        // read in Huffman trie from input stream
        huffman->root = huffman->readTrie(huffman->root,&tree_file);
        // // print Huffman trie
        huffman->printBT("",huffman->root,false);
        tree_file.close();
    }
    // if command line queals = -s char
    else if(string(argv[1]) == "-s") {

        // Read tree from file
        ifstream tree_file("saved_tree.txt");
        // if file exist
        if(tree_file.good()) {
            if (strlen(argv[2]) == 1) {
                // huffman BinaryTree object
                BinaryTree *huffman = new BinaryTree();
                // read in Huffman trie from input stream
                huffman->root = huffman->readTrie(huffman->root, &tree_file);
                // if input lenght equals store returned value
                string result;
                // search input value
                result = huffman->huffman_value(char(tolower(argv[2][0])), huffman->root, "");
                // if result doesnt exist, print character not found
                if (result == "") {
                    cout << "character not found" << endl;
                } else {
                    // if result exist, print huffman result
                    cout << "your input: " << argv[2][0] << endl;
                    cout << "Huffman trie equals: " << result << endl;
                }
            } else {
                cout << "input is string or empty! Enter a character!" << endl;
            }
        }
        else{
            cout << "saved tree does not exist"<<endl;
        }
    }

    return 0;
}
