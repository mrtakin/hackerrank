import java.util.PriorityQueue;
import java.util.Scanner;

public class GreedyFlorist {

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int k, int[] costs) {
        int n = costs.length;
        int size = (int) Math.floor(1.0 * n / k);
        int remain = n - size * k;
        
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        
        for (int cost : costs) maxQueue.add(cost);
        
        // k person buy size flower
        int totalCost = 0;
        for(int i = 0; i < size; ++i){
            for(int j = 0; j < k; ++j){
                totalCost += (i + 1) * maxQueue.remove();
            }
        }
        
        // n - k*size flower will be shared
        for (int i = 0; i < remain; i++) {
            totalCost += (size + 1) * maxQueue.remove();
        }
        
        return totalCost;
    }

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int k = in.nextInt();
            
            int[] costs = new int[n];
            for (int i = 0; i < n; i++) {
                costs[i] = in.nextInt();
            }
            
            System.out.println(
                    getMinimumCost(k, costs));
        }
    }
}

