/*Function templates are special functions that can operate 
with generic types. This allows us to create a function 
template whose functionality can be adapted to more than 
one type or class without repeating the entire code for each type.*/

/*To use this function template we use the following format for the function call:

function_name <type> (parameters);

*/

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
	
	
