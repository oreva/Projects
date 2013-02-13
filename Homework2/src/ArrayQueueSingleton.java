public class ArrayQueueSingleton  {
    private static Object[] elements = new Object[100];
	private static int headIndex;
	private static int tailIndex;
	private static int size;

	private ArrayQueueSingleton() {
	}

    public static void store(Object element) {
        assert element != null;

	    ensureQueueSize();
	    elements[tailIndex] = element;
	    moveTail();
    }

	public static Object retrieve() {
        assert size > 0;

	    Object head = elements[headIndex];
	    moveHead();
	    return head;
    }

	public static int size() {
		return size;
	}

	public static boolean isEmpty() {
		return size() == 0;
	}

	private static void ensureQueueSize() {
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

	private static void moveTail() {
		tailIndex = (tailIndex == elements.length - 1) ? 0 : tailIndex + 1;
		size++;
	}

	private static void moveHead() {
		headIndex = (headIndex == elements.length - 1) ? 0 : headIndex + 1;
		size--;
	}
}
