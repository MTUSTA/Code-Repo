#include "Node.h"

Node::Node(char ch, int freq,Node* left, Node* right) {
    this->ch = ch;
    this->freq = freq;
    this->left  = left;
    this->right = right;
}
// is the node a leaf node?
bool Node::isLeaf() {
    return (left == nullptr) && (right == nullptr);
}
// compare, based on frequency
int Node::compareTo(Node *that) {
    return this->freq - that->freq;
}