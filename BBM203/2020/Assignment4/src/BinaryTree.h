#ifndef BINARYTREE_H
#define BINARYTREE_H

#include <string>
#include "Node.h"
#include <vector>

#include <bits/stdc++.h>

using namespace std;

class BinaryTree {

public:

    // for priority_queue -> it's a comparator
    struct compare{
        bool operator()(const Node*  a, const Node* b) {
            // if frequencies equal, compare char
            if(a->freq == b->freq){

                return a->ch > b->ch;
            }
            // compare frequencies
            return a->freq > b->freq;
        }
    };
    // dummy constructer
    BinaryTree();
    Node* root;


    void buildTrie(int* frequency);
    void buildCode(string st[],Node* x, string s);
    void writeTrie(Node *x, ofstream* pOfstream);
    void encoding(int frequency[256], string line);

    vector<string> split (const string &s, char delim);
    bool strTobool(string s);
    Node* readTrie(Node *x, ifstream *pIfstream);
    void decoding(ifstream* inputfile,ifstream* tree_file);


    void printBT(const string& prefix, Node* node, bool isLeft);
    string huffman_value(char user_input, Node *root, string s);


};


#endif //BINARYTREE_H
