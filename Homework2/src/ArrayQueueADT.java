public class ArrayQueueADT {
    private Object[] elements;
	private int headIndex;
	private int tailIndex;
	private int size;

	public ArrayQueueADT() {
		this(100);
	}

	public ArrayQueueADT(int length) {
		elements = new Object[length];
	}

    public static void store(ArrayQueueADT queue, Object element) {
        assert element != null;

	    ensureQueueSize(queue);
	    queue.elements[queue.tailIndex] = element;
	    moveTail(queue);
    }

	public static Object retrieve(ArrayQueueADT queue) {
        assert queue.size > 0;

	    Object head = queue.elements[queue.headIndex];
		moveHead(queue);
	    return head;
    }

	public static int size(ArrayQueueADT queue) {
		return queue.size;
	}

	public static boolean isEmpty(ArrayQueueADT queue) {
		return size(queue) == 0;
	}

	private static void ensureQueueSize(ArrayQueueADT queue) {
		if (queue.size > 0 && queue.size == queue.elements.length) {
			//Increase elements array (create new larger array)
			Object[] newElements = new Object[2 * queue.size];
			int i = 0;
			while (!isEmpty(queue)) {
				newElements[i++] = retrieve(queue);
			}
			queue.headIndex = 0;
			queue.tailIndex = queue.elements.length;
			queue.size = queue.elements.length;
			queue.elements = newElements;
		}
	}

	private static void moveTail(ArrayQueueADT queue) {
		queue.tailIndex = (queue.tailIndex == queue.elements.length - 1) ? 0 : queue.tailIndex + 1;
		queue.size++;
	}

	private static void moveHead(ArrayQueueADT queue) {
		queue.headIndex = (queue.headIndex == queue.elements.length - 1) ? 0 : queue.headIndex + 1;
		queue.size--;
	}
}
