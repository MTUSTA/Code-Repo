#ifndef NODE_H
#define NODE_H


// Huffman trie node
class Node{
public:
    char ch;
    int freq;
    Node* left;
    Node* right;

    Node(char ch, int freq,Node* left, Node* right);
    // is the node a leaf node?
    bool isLeaf();

    // compare, based on frequency
    int compareTo(Node* that);
};

#endif //NODE_H
