import java.io.*;
import java.util.*;

public class NewYearChaos {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int[] qCopy = q.clone();
        int countBribes = 0;
//        System.err.println(" --- ");
//        System.err.println(Arrays.toString(q));  
        for(int i = q.length - 1; i >= 0; --i){
            if(qCopy[i] - (i + 1) > 2){
                System.out.println("Too chaotic");
                return;
            }

            if(i - 2 >= 0 && q[i - 2] > q[i - 1]){
                int tmp = q[i - 2];
                q[i - 2] = q[i - 1];
                q[i - 1] = tmp;

                countBribes++;
            }
            
            if(i - 1 >= 0 && q[i - 1] > q[i]){
                int tmp = q[i];
                q[i] = q[i - 1];
                q[i - 1] = tmp;

                countBribes++;
            }

 //           System.err.println(Arrays.toString(q));         
        }   
        System.out.println(countBribes);     
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}

