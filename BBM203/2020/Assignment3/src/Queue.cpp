#include "Queue.h"



template<typename T>
Queue<T>::Queue() {
    this->_firstNode = nullptr; // beginning of queue
    this->_lastNode = nullptr; // end of queue
    this->_size = 0; // number of elements on queue
}

template<typename T>
Queue<T>::~Queue() {

}

// Returns true if this queue is empty.
template<typename T>
bool Queue<T>::isEmpty() const {
    return this->_firstNode == nullptr;
}

// Returns the number of items in this queue.
template<typename T>
int Queue<T>::size() const {
    return this->_size;
}

// Adds the item to this queue.
template<typename T>
void Queue<T>::enqueue(const T &newItem) {
    // set last node to add new node
    QueueNode* oldlast = this->_lastNode;
    this->_lastNode = new QueueNode() ;
    this->_lastNode->item = newItem;
    this->_lastNode->next = nullptr;
    // if start is empty , lastnode will be first node
    if (isEmpty()){
        this->_firstNode = this->_lastNode;
    }
    // add new node at the end of the linked list
    else{
        oldlast->next = this->_lastNode;
    }
    // increase size number
    this->_size++;
}
// Removes the item on this queue
template<typename T>
void Queue<T>::dequeue() {
    // if empty do nothing
    if(isEmpty()){
        return;
    }
    // forward new node to next node
    this->_firstNode = this->_firstNode->next;
    // decrease size
    this->_size--;
    //if linked list is empty , set last node to NULL
    if (isEmpty()){
        this->_lastNode = nullptr;
    }
}

// Returns the item least recently added to this queue.
template<typename T>
void Queue<T>::getFront(T &queueTop) const {
    if(isEmpty()){
        return;
    }
    queueTop = this->_firstNode->item;
}
