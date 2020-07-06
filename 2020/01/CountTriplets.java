import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CountTriplets {

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> freqMap = new HashMap<>();           // Frequency map for num * r
        Map<Long, Long> possibleTriplets = new HashMap<>();  // How many possible triplets are there waiting for x
        
        Long count = 0L;
        for (Long num : arr) {
            // Found num, count waiting possible triplets
            count += possibleTriplets.getOrDefault(num, 0L);    
            
            // Found another num, count it as possible tripler for num * r
            possibleTriplets.put(num * r, 
                    possibleTriplets.getOrDefault(num * r, 0L) +    
                    freqMap.getOrDefault(num, 0L));
            
            // Update frequeny map for num * r
            freqMap.put(num * r, freqMap.getOrDefault(num * r, 0L) + 1);
        }
        
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

