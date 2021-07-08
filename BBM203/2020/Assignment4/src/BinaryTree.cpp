#include "BinaryTree.h"
#include <bits/stdc++.h>

static const int asci_size = 256;
/**
  * Initializes an empty symbol table.
  */
BinaryTree::BinaryTree() {

}

// build the Huffman trie given frequencies
void BinaryTree::buildTrie(int* frequency) {
    // initialze priority queue with singleton trees
    priority_queue <Node*, vector<Node*>, compare> min_queue;
    for (int c = 0; c < asci_size ; c++){
        //cout << char(c) << " "<< c << endl;
        if (frequency[c] > 0) {
            min_queue.push(new Node( char(c), frequency[c], nullptr, nullptr));
        }
    }

    // merge two smallest trees
    while (min_queue.size() > 1) {
        // get min Node store on left and delete from minqueue
        Node *left = min_queue.top();
        min_queue.pop();

        // get min Node store on right and delete from minqueue
        Node *right = min_queue.top();
        min_queue.pop();

        // create new Node store in minqueue
        Node *parent = new Node('\0', left->freq + right->freq, left, right);
        min_queue.push(parent);
    }
    // save constructed tree on root variable
    this->root = min_queue.top();
    min_queue.pop();

}

// write encoded trie to standard output file
void BinaryTree::writeTrie(Node *x, ofstream* output) {
    if (x->isLeaf()) {
        *output << int(x->ch) <<" "<<x->freq <<" T"<< endl;
        return;
    }
    *output << int(x->ch) <<" "<<x->freq << " F" << endl;
    writeTrie(x->left, output);
    writeTrie(x->right, output);
}

// make a lookup table from symbols and their encodings
void BinaryTree::buildCode(string *st, Node* x, string s) {
    if (!x->isLeaf()) {
        buildCode(st, x->left,  s + '0');
        buildCode(st, x->right, s + '1');
    }
    else {
        st[x->ch] = s;
    }
}

/**
   * Reads a sequence of char(8-bit bytes) from standard input; compresses them
   * using Huffman codes with an char(8-bit) alphabet; and writes the results
   * to standard output file.
   */

void BinaryTree::encoding(int frequency[256], string line) {
    // tabulate frequency counts
    this->buildTrie(frequency);

    // build code table
    string st[asci_size];
    this->buildCode(st, this->root, "");

    // open output file
    ofstream output("saved_tree.txt");
    // write trie for decoder
    this->writeTrie(this->root, &output);
    output.close();

    // open output file
    ofstream encodeResult("encode_result.txt");
    // use Huffman code to encode input
    for (int i = 0; i < line.size(); i++) {
        // get encoded value from table and write output file
        string code = st[tolower(line[i])];
        encodeResult << code ;
    }
    encodeResult.close();
}

// split line by given delimiter
vector<string> BinaryTree::split (const string &s, char delim) {
    vector<string> result;
    stringstream ss (s);
    string item;

    while (getline (ss, item, delim)) {
        result.push_back (item);
    }

    return result;
}

// helper function for readTrie function
bool BinaryTree::strTobool(string s) {
    if (s == "T"){
        return true;
    }
    return false;
}

// constructor function. read trie node from file and construct
Node* BinaryTree::readTrie(Node *x, ifstream* input_file) {
    // read line from given file
    string input_line;
    getline(*input_file, input_line);
    // split line by given delimiter
    vector<string> splitted_line = split(input_line, ' ');
    // string to integer
    int asci_int_value = stoi(splitted_line[0]);
    // int to char
    char value2 = char(asci_int_value);
    // string to integer
    int freq = stoi(splitted_line[1]);


    bool isLeaf = strTobool(splitted_line[2]);
    if (isLeaf) {
        return new Node(value2, freq, nullptr, nullptr);
    }
    else {
        return new Node('\0', freq, readTrie(x, input_file), readTrie(x, input_file));
    }
}

/**
 * Reads a sequence of bits that represents a Huffman-compressed message from
 * standard input; expands them; and writes the results to standard output file.
 */
void BinaryTree::decoding(ifstream* input_file,ifstream* tree_file) {
    // loading tree from tree_file
    this->root = readTrie(this->root, tree_file);

    // store line from input file
    string input_line;

    // Read line from the input text file
    getline(*input_file, input_line);
    ofstream decodeResult("decode_result.txt");

    // decode using the Huffman trie
    for (int i = 0; i < input_line.size();) {
        Node* x = this->root;
        while (!x->isLeaf()) {
            // char to integer
            int bit = input_line[i]-48;
            // if bit equals 1 go right
            if (bit) {
                x = x->right;
            }
            // if bit equals 0 go left
            else {
                x = x->left;
            }
            i++;
        }
        // write char in output file
        decodeResult << x->ch ;
    }
    // close file
    decodeResult.close();
}

// print Binary Tree
void BinaryTree::printBT(const string& prefix, Node* x, bool isLeft) {
    if( x != nullptr ) {
        cout << prefix;

        cout << (isLeft ? "---" : "+--");

        // print the value of the node
        if(x->isLeaf()){
            cout << " " << x->freq << " " << x->ch << endl;
        }
        else{
            cout <<" " << x->freq << endl;
        }

        // enter the next tree level - left and right branch
        printBT(prefix + (isLeft ? "|   " : "    "), x->left, true);
        printBT(prefix + (isLeft ? "|   " : "    "), x->right, false);
    }
}

// return huffman value of given character
string BinaryTree::huffman_value(char user_input, Node* x, string s) {
    // if node is leaf
    if (x->isLeaf()) {
        // node character is compared to the given character
        if(x->ch == user_input){
            // if equals return huffman value
            return s;
        }
        // not equals return empty string
        return "";
    }
    string result;
    // go left node
    result = huffman_value(user_input,x->left,  s + '0');
    // returned value doesnt empty -> return value -> like a early stopper
    if(result != ""){
        return result;
    }
    // go right node
    result = huffman_value(user_input, x->right, s + '1');
    // returned value doesnt empty -> return value -> like a early stopper
    if(result != ""){
        return result;
    }
    // return nothing
    return "";
}


