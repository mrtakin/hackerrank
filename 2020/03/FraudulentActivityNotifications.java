import java.util.Scanner;

public class FraudulentActivityNotifications {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditures, int d) {
        int[] countArray = new int[200 + 1];
        for (int i = 0; i < d; i++) {
            countArray[expenditures[i]]++;
        }

        int count = 0;
        double median = getMedian(countArray, d);
        for (int i = d; i < expenditures.length; i++) {

            if (expenditures[i] >= 2 * median){
                count++;
            }

            countArray[expenditures[i - d]]--;
            countArray[expenditures[i]]++;

            median = getMedian(countArray, d);
        }

        return count;
    }

    private static double getMedian(int[] countArray, int d){
        int count = 0;
        for (int i = 0; i < countArray.length; i++) {
            count += countArray[i];

            if(count > d / 2.0){
                if(d % 2 == 1){
                    return i;
                }else{
                    if(countArray[i] > 1 && count - countArray[i] != d / 2.0) return i;
                    else return (i - 1 + i) / 2.0;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int d = in.nextInt();
        int[] expenditures = new int[n];
        for (int i = 0; i < n; i++) {
            expenditures[i] = in.nextInt();
        }

        System.out.println(
                activityNotifications(expenditures, d));
    }
}
