public class ADTSum {
    public int sum(ArrayQueueADT queue) {
        int sum = 0;
        while (!ArrayQueueADT.isEmpty(queue)) {
            String[] strArgs = ArrayQueueADT.retrieve(queue).toString().trim().split("\\s+");

            for (String sArg: strArgs) {
                sum += Integer.valueOf(sArg);
            }
        }
        System.out.println("sum = " + sum);
	    return sum;
    }
}
