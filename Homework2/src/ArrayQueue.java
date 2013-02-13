public class ArrayQueue extends AbstractQueue {
    private Object[] elements;
	private int headIndex;
	private int tailIndex;

	public ArrayQueue() {
		this(100);
	}

	public ArrayQueue(int length) {
		elements = new Object[length];
	}

    public void store(Object element) {
        assert element != null;

	    ensureQueueSize();
	    elements[tailIndex] = element;
	    moveTail();
    }

	public Object retrieve() {
        assert size > 0;

	    Object head = elements[headIndex];
	    moveHead();
	    return head;
    }

	private void ensureQueueSize() {
		if (size > 0 && size == elements.length) {
			//Increase elements array (create new larger array)
			Object[] newElements = new Object[2 * size];
			int i = 0;
			while (!isEmpty()) {
				newElements[i++] = retrieve();
			}
			headIndex = 0;
			tailIndex = elements.length;
			size = elements.length;
			elements = newElements;
		}
	}

	private void moveTail() {
		tailIndex = (tailIndex == elements.length - 1) ? 0 : tailIndex + 1;
		size++;
	}

	private void moveHead() {
		headIndex = (headIndex == elements.length - 1) ? 0 : headIndex + 1;
		size--;
	}
}
