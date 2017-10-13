package NandreyN;

public class Main {
    private static double getFirst(double x)
    {
        return (Math.pow(x,2) * Math.pow(4,6)) / (Math.pow(3,6) * 6);
    }
    private static double next(double k, double previous){
        return -previous*256 / (81*(2*k + 1)*(2*k));
    }

    public static void main(String[] args) {
        // write your code here
        ExcelWriter writer = new ExcelWriter();

        try {
            if (args.length < 2)
                throw new SeriesException("Invalid parameter count!");

            double eps = Double.parseDouble(args[0]);
            double x = Double.parseDouble(args[1]);

            int k = 2;
            double sum = getFirst(x);
            double delta = eps * 2;
            double prev = sum;

            while (Math.abs(delta) >= eps)
            {
                double current = next(k,prev);
                sum += current;
                k++;
                delta = current - prev;
                prev = current;

                String[] writerArgs = {
                        Integer.toString(k-1),
                        Double.toString(current),
                        Double.toString(sum)
                };
                writer.writeLine(writerArgs);
            }
            writer.save();
            System.out.println("Sum = " + Double.toString(sum) + " , k = " + Integer.toString(k));
        } catch (SeriesException e) {
            System.out.println(e.getMessage());
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid argument :" + e.getMessage());
        }
    }
}
