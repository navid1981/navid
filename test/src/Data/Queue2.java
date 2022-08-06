package Data;

//Queue implementation By Node
public class Queue2<T>{
	QueueNode<T> head;
	QueueNode<T> tail;
	
	public void add(T data){
		QueueNode<T> node=new QueueNode(data);
		if(tail==null){
			head=node;
			tail=node;
		}else{
			tail.next=node;
			tail=node;
		}
	}
	
	public T remove(){
		if(head==null){
			return null;
		}else{
			QueueNode<T> node=head;
			if(head==tail){
				tail=null;
				head=null;
				return node.data;
			}else{
				head=node.next;
				return node.data;
			}
		}
	}
}

class QueueNode<T>{
	T data;
	QueueNode<T> next;
	public QueueNode(T data) {
		super();
		this.data = data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setNext(QueueNode<T> next) {
		this.next = next;
	}
	
}
