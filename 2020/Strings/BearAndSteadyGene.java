import java.util.Arrays;
import java.util.Scanner;

public class BearAndSteadyGene {
    
    private static boolean check(int[] freqArr){
        return Arrays.stream(freqArr).allMatch(i -> i >= 0);
    }
    
    // Complete the steadyGene function below.
    static int steadyGene(String gene) {
        // genearate a frequency array
        int[] freqArr = new int[20];
        gene.chars()
            .mapToObj(c -> (char) c)
            .forEach(ch -> freqArr[ch - 'A']++);
        
        // expected frequency
        int avg = gene.length() / 4;
        
        // differences between expected and given freqs
        for (int i = 0; i < freqArr.length; i++) {
            freqArr[i] = avg - freqArr[i];
        }
        
        // diff = 0, not neccesary to change
        if(check(freqArr)) return 0;
        
        // find first available range
        int from = 0;
        while(freqArr[gene.charAt(from) - 'A'] >= 0) from++;
        
        int to = from - 1;
        while(!check(freqArr)) freqArr[gene.charAt(++to) - 'A']++;
        
        // find minimum range
        int currMin = to - from + 1;
        while(to < gene.length() - 1 && from < gene.length()){
            freqArr[gene.charAt(++to) - 'A']++;
        
            // extract unneccesary chars
            while(freqArr[gene.charAt(from) - 'A'] > 0) {
                freqArr[gene.charAt(from) - 'A']--;
                from++;
            }
            
            currMin = Integer.min(currMin, to - from + 1);;
        }
        
        return currMin;
    }

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int n = in.nextInt();

        // skip new-line 
        in.nextLine();
        
        String gene = in.nextLine();

        int result = steadyGene(gene);

        System.out.println(result);

        in.close();
    }
}

