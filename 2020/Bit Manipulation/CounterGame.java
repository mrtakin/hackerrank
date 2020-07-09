import java.util.Scanner;

public class CounterGame {
    // Complete the counterGame function below.
    static String counterGame(long n) {      
        boolean isLouiseTurn = true;
        
        while(n != 1){
            if(isPowerOfTwo(n)){
                n = n >> 1;
            }else{
                n = reduceByPrevPowerOfTwo(n);
            }
            isLouiseTurn = !isLouiseTurn;
        }
        
        return isLouiseTurn ? "Richard" : "Louise";
        
    }

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int t = in.nextInt();

        for (int tItr = 0; tItr < t; tItr++) {
            long n = in.nextLong();

            String result = counterGame(n);
            
            System.out.println(result);
        }

        in.close();
    }
    
    private static boolean isPowerOfTwo(long n){
        return (n & (n - 1)) == 0;
    }
    
    private static long reduceByPrevPowerOfTwo(long n){
        long nIter = 1;
        while(nIter < n){
            nIter <<= 1;
        }
        
        return n - (nIter >> 1);
    }
}

