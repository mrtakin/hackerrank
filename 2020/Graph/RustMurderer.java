
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RustMurderer {

    /*
     * Complete the rustMurdered function below.
     */
    static int[] rustMurderer(int n, int s, int[][] roads) {

        List<Map<Integer, Boolean>> mainRoadMap = prepareAdjacencyList(n, roads);

        // the BFS queue
        Queue<Integer> queue = new LinkedList<>();
        
        // keeps distances from beginning node
        int[] dists = new int[n + 1];
        
        // keeps if a node is visited or not
        boolean[] visited = new boolean[n + 1];
        
        // add beginning node to the queue
        queue.add(s);
        
        // set distance of beginning node
        dists[s] = 0;
        
        // set beginning node as visited 
        visited[s] = true;

        while (!queue.isEmpty()) {
            int from = queue.remove();

            Map<Integer, Boolean> neighboursWithMainRoad = mainRoadMap.get(from);
            
            // If all outgoing ways are village-road, all other nodes can be reachable directly.
            if(neighboursWithMainRoad.isEmpty()){
                for (int i = 1; i < n + 1; i++) {   // set distances of non-visited nodes
                    if(!visited[i]) dists[i] = dists[from] + 1;
                }
                queue.clear();
                continue;
            }

            // loop through neighbours (with village roads)
            for (int to = 1; to <= n; ++to){
                if(from == to || neighboursWithMainRoad.containsKey(to) || visited[to]) continue;
                
                queue.add(to);
                dists[to] = dists[from] + 1;
                visited[to] = true;
            }
        }
        
        return IntStream.range(1, n + 1)
                .filter(i -> i != s)
                .map(i -> dists[i])
                .toArray();
    }

    private static List<Map<Integer, Boolean>> prepareAdjacencyList(int n, int[][] roads) {
        List<Map<Integer, Boolean>> roadMap = IntStream.range(0, n + 1)
                .mapToObj(i -> new HashMap<Integer, Boolean>())
                .collect(Collectors.toList());
        
        for (int[] road : roads) {
            int u = road[0],
                v = road[1];
            
            roadMap.get(u).put(v, Boolean.TRUE);
            roadMap.get(v).put(u, Boolean.TRUE);
        }
        
        return roadMap;
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            int[][] roads = new int[m][2];

            for (int j = 0; j < m; j++) {
                roads[j][0] = scanner.nextInt();
                roads[j][1] = scanner.nextInt();
            }

            int s = scanner.nextInt();

            int[] result = rustMurderer(n, s, roads);

            for (int num : result) {
                System.out.print(num + " ");
            }
            
            System.out.println();
        }

        scanner.close();
    }
}

