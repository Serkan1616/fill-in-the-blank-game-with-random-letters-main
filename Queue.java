
public class Queue {
	private int rear, front;
	private Object[] elements;
	public Queue(int capacity) {
		elements = new Object[capacity];
		rear = -1;
		front = 0;
	}
	public boolean isFull() {
		return (rear + 1 == elements.length);
	}
	public void enqueue(Object data) {
		if(isFull())
			System.out.println("queue is overflow");
		else
			rear++;
			elements[rear] = data;
	}
	public boolean isEmpty() {
		return rear < front;
	}
	public Object dequeue() {
		if(isEmpty()) {
			System.out.println("queue  is empty");
			return null;
		}	
		else {
			Object retData = elements[front];
			elements[front] = null;
			front++;
			return retData;
		}
	}
	public Object peek() {
		if(isEmpty()) {
			System.out.println("queue  is empty");
			return null;
		}
		else 
			return elements[front];
	}
	public int size() {
		return rear - front + 1;
	}
}
