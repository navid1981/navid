package Data;

// implementing stack by using Node
public class Stack2<T> {
	private StackNode<T> top;

	public void push(T data) {
		StackNode<T> node = new StackNode(data);
		node.next = top;
		top = node;

	}

	public T pop() {
		if (top == null) {
			return null;
		} else {
			StackNode<T> node = top;
			top = top.next;
			return node.getData();
		}
	}
}

class StackNode<T> {
	T data;
	StackNode<T> next;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public StackNode<T> getNext() {
		return next;
	}

	public void setNext(StackNode<T> next) {
		this.next = next;
	}

	public StackNode(T data) {
		super();
		this.data = data;
	}
}