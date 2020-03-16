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

public class FrequencyQueries {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> output = new ArrayList<>();

        Map<Integer, Integer> occurrencies = new HashMap<>();
        Map<Integer, Map<Integer, Boolean>> invOccurrencies = new HashMap<>();
        for(List<Integer> query: queries){
            Integer action = query.get(0);
            Integer param = query.get(1);

            Integer occurrence = occurrencies.getOrDefault(param, 0);

            if(action == 1) {
                occurrencies.put(param, occurrence + 1);

                // add it to occurrence + 1
                addOccurrenceToInvOccurrencies(param, occurrence + 1, invOccurrencies);

                // remove it from occurrence
                removeOccurrenceToInvOccurrencies(param, occurrence, invOccurrencies);
            }
            else if(action == 2 && occurrence > 0) {
                occurrencies.put(param, occurrence - 1);
                
                // remove it from occurrence
                removeOccurrenceToInvOccurrencies(param, occurrence, invOccurrencies);

                // add it to occurrence - 1
                addOccurrenceToInvOccurrencies(param, occurrence - 1, invOccurrencies);
            }
            else if(action == 3) {
                Map<Integer, Boolean> def = invOccurrencies.getOrDefault(param, new HashMap<Integer, Boolean>());

                output.add(def.size() > 0 ? 1 : 0);
            }
        }

        return output;
    }

    static void addOccurrenceToInvOccurrencies(Integer number, Integer occurrence, Map<Integer, Map<Integer, Boolean>> invOccurrencies){
        Map<Integer, Boolean> def = invOccurrencies.getOrDefault(occurrence, new HashMap<Integer, Boolean>());
        def.put(number, Boolean.TRUE);
        invOccurrencies.put(occurrence, def);
    }
     
    static void removeOccurrenceToInvOccurrencies(Integer number, Integer occurrence, Map<Integer, Map<Integer, Boolean>> invOccurrencies){
        Map<Integer, Boolean> def = invOccurrencies.getOrDefault(occurrence, new HashMap<Integer, Boolean>());
        def.remove(number);
        invOccurrencies.put(occurrence, def);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
