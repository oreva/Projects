public class Sum {
    public static void main(String[] args) {
        Integer sum = 0;
        for(int i = 0; i < args.length; i++) {
            String[] strArgs = args[i].trim().split(" ");

            for (int j = 0; j < strArgs.length; j++) {
                String s = strArgs[j].trim();
                sum += new Integer(s);
            }
        }
        System.out.println("sum = " + sum);
    }
}
