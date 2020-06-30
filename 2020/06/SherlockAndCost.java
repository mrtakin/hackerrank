import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import static java.lang.Math.*;

public class SherlockAndCost {

    /**
    * L(i) : max cost of first i element ending 1
    * H(i) : max cost of first i element ending B[i]
    *
    * total cost f(i) : max(l(i), h(i))
    **/
    static int cost(int[] B) {
        
        int l = 0, h = 0;
        for(int i = 1; i < B.length; ++i){
            int l_prev = l;

            l = max(l, h + abs(B[i - 1] - 1));
            h = max(h + abs(B[i] - B[i - 1]), l_prev + abs(B[i] - 1));
        }

        System.err.println(l + " " + h);

        return max(l, h);
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int c = in.nextInt();
        for (int ii = 0; ii < c; ii++) {
            int n = in.nextInt();

            int[] B = new int[n];
            for (int i = 0; i < n; i++) {
                B[i] = in.nextInt();;
            }

            int result = cost(B);

            System.out.println(result);
        }

        in.close();
    }
}

