public class Sum {
    public int sum(Queue queue) {
        int sum = 0;
        while (!queue.isEmpty()) {
            String[] strArgs = queue.retrieve().toString().trim().split("\\s+");

            for (String sArg: strArgs) {
                sum += Integer.valueOf(sArg);
            }
        }
        System.out.println("sum = " + sum);
	    return sum;
    }
}
