public class SingletonSum {
    public int sum() {
        int sum = 0;
        while (!ArrayQueueSingleton.isEmpty()) {
            String[] strArgs = ArrayQueueSingleton.retrieve().toString().trim().split("\\s+");

            for (String sArg: strArgs) {
                sum += Integer.valueOf(sArg);
            }
        }
        System.out.println("sum = " + sum);
	    return sum;
    }
}
