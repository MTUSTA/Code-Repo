#include "Stack.h"



template<typename T>
Stack<T>::Stack() {
    this->_head = nullptr;
    this->_size = 0;
}

template<typename T>
Stack<T>::~Stack() {

}

template<typename T>
bool Stack<T>::isEmpty() const {
    return this->_head == nullptr;
}

template<typename T>
int Stack<T>::size() const {
    return this->_size;
}

template<typename T>
void Stack<T>::push(const T &newItem) {
    ListNode* oldfirst =this->_head;
    this->_head = new ListNode();
    this->_head->item = newItem;
    this->_head->next=oldfirst;
    this->_size++;
}

template<typename T>
void Stack<T>::getTop(T &stackTop) const {
    if (isEmpty()){
        return;
    }
    stackTop = this->_head->item;
}

template<typename T>
void Stack<T>::pop() {
    if (isEmpty()){
        return;
    }
    //T* item = this->_head->item;
    this->_head = this->_head->next;
    this->_size--;
}
