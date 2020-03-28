import java.util.Arrays;
import java.util.Scanner;

public class TripleSum {

    // Complete the triplets function below.
    static long triplets(int[] as, int[] bs, int[] cs) {
        as = Arrays.stream(as).distinct().sorted().toArray();
        bs = Arrays.stream(bs).distinct().toArray();
        cs = Arrays.stream(cs).distinct().sorted().toArray();

        long count = 0;
        for (int b : bs) {
            int indA = Arrays.binarySearch(as, b + 1);
            int indC = Arrays.binarySearch(cs, b + 1);

            if (indA < 0) indA = (-1 * indA - 1);
            if (indC < 0) indC = (-1 * indC - 1);
            
            count += 1L * indA * indC;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int lenA = in.nextInt();
            int lenB = in.nextInt();
            int lenC = in.nextInt();

            int[] as = new int[lenA];
            for (int i = 0; i < lenA; i++) {
                as[i] = in.nextInt();
            }

            int[] bs = new int[lenB];
            for (int i = 0; i < lenB; i++) {
                bs[i] = in.nextInt();
            }

            int[] cs = new int[lenC];
            for (int i = 0; i < lenC; i++) {
                cs[i] = in.nextInt();
            }

            System.out.println(
                    triplets(as, bs, cs));
        }
    }
}

