import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpecialStringAgain {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {

        List<Character> chars = new ArrayList<>();
        List<Long> counts = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            Long occurrence = 1L;
            for (i = i + 1; i < s.length() && s.charAt(i) == ch; ++i) {
                occurrence++;
            }

            chars.add(ch);
            counts.add(occurrence);

            i--;
        }

        // When all characters of substring are identical. (a, bb ..)
        Long countTotal = counts.stream()
                .map(i -> i * (i + 1) / 2)
                .reduce(Long::sum).get();

        // Special type of palindrome. (aba, aabaa ..(
        for (int i = 1; i < chars.size() - 1; i++) {
            if (counts.get(i) == 1 && chars.get(i - 1).equals(chars.get(i + 1))) 
                countTotal += Math.min(counts.get(i - 1), counts.get(i + 1));
        }

        return countTotal;        
    }

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in);) {
            int n = in.nextInt();
            in.nextLine();
            String str = in.nextLine();

            Long result = substrCount(n, str);

            System.out.println(result);
        }
    }
}

