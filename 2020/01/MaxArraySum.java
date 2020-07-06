import java.util.Scanner;

public class MaxArraySum {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(dp[0], arr[1]);

        for (int i = 2; i < arr.length; ++i) {
            int curr = arr[i];

            dp[i] = max(dp[i - 2] + curr,
                        dp[i - 1],
                        curr);
        }

        return dp[arr.length - 1];
    }

    private static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }

            System.out.println(maxSubsetSum(arr));
        }
    }
}

