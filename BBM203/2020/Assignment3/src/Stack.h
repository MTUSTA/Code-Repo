#ifndef STACK_H
#define STACK_H



template <typename T>
class Stack {
public:
    Stack();
    ~Stack();
    bool isEmpty() const;
    int size() const;

    //Push: Place item on top of the stack
    void push(const T& newItem);
    //Top: Take a look at the topmost item without removing it
    void getTop(T& stackTop) const;
    //Pop: Remove item from the top of the stack
    void pop();
private:
    struct ListNode {
        T item;
        ListNode* next;
    };
    ListNode* _head;
    int _size;
};




#endif //STACK_H
