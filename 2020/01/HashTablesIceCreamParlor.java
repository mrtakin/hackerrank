import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HashTablesIceCreamParlor {
    
    // Complete the whatFlavors function below.
    static void whatFlavors(int[] costs, int money) {
        Map<Integer, List<Integer>> diffToCostIndexMap = new HashMap<>();

        for(int i = 0; i < costs.length; ++i){
            int diff = money - costs[i];

            if(diff > 0){
                List<Integer> indexes = diffToCostIndexMap.getOrDefault(diff, new ArrayList<>());
                indexes.add(i);

                diffToCostIndexMap.put(diff, indexes);
            }
        }
        
        for(int i = 0; i < costs.length; ++i){
            List<Integer> possibleIndexes = diffToCostIndexMap.getOrDefault(costs[i], new ArrayList<>());

            if(!possibleIndexes.isEmpty()){
                Integer otherCostInd = possibleIndexes.get(0);
                Integer otherCost = costs[otherCostInd];

                if(!otherCost.equals(costs[i])){
                    System.out.println((i + 1) + " " + (otherCostInd + 1));
                    break;
                }else if((possibleIndexes.size() > 1)){
                    System.out.println((i + 1) + " " + (possibleIndexes.get(1) + 1));                    
                    break;
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}

