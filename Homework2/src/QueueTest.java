public class QueueTest {
    public static void main(String[] args) {


	    ArrayQueueSingleton.store("1 12    3 4");
	    ArrayQueueSingleton.store("   5 -4   1");
	    System.out.println("ArrayQueueSingleton: ");
	    SingletonSum s1 = new SingletonSum();
	    s1.sum();

	    ArrayQueueADT q1 = new ArrayQueueADT();
	    ArrayQueueADT.store(q1, "1 12    3 4");
	    ArrayQueueADT.store(q1, "   5 -4   1");
	    System.out.println("ArrayQueueADT: ");
	    ADTSum s2 = new ADTSum();
	    s2.sum(q1);

	    AbstractQueue q = new ArrayQueue();
	    q.store("1 12    3 4");
	    q.store("   5 -4   1");
	    System.out.println("ArrayQueue: ");
	    Sum s = new Sum();
	    s.sum(q);

	    AbstractQueue q3 = new LinkedQueue();
	    q3.store("1 12    3 4");
	    q3.store("   5 -4   1");
	    System.out.println("LinkedQueue: ");
	    Sum s3 = new Sum();
	    s3.sum(q3);
    }
}
