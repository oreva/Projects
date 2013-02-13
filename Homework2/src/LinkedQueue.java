public class LinkedQueue extends AbstractQueue {
	private Node head;
	private Node tail;

	private static class Node {
		private Object value;
		private Node next;

		private Node(Object value, Node next) {
			this.value = value;
			this.next = next;
		}
	}
	public void store(Object element) {
		assert element != null;

		Node newNode = new Node(element, null);
		if (size == 0) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	public Object retrieve() {
		assert size > 0;

		Object val = head.value;
		head = head.next;
		size--;
		return val;
	}
}
