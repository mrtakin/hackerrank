
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CrosswordPuzzle {

    private static char PLACED = '+';
    private static boolean stop = false;

    private static class PlaceHolder {
        int r1;
        int c1;
        char orientation;
        int length;

        public PlaceHolder(int r1, int c1, int r2, int c2, char orientation) {
            this.r1 = r1;
            this.c1 = c1;
            this.orientation = orientation;
            this.length = orientation == 'H' ? c2 - c1 + 1 : r2 - r1 + 1;
        }
    }

    private static void printArray(char[][] crossword) {
        for (char[] cs : crossword) {
            for (char c : cs) System.out.print(c);
            
            System.out.println("");
        }
    }

    static int skip(char[][] crossword, char ch, int r, int c, int dr, int dc) {
        for (int i = 0; i < 10; i++) {
            if (r < 10 && c < 10 && crossword[r][c] == ch) {
                r += dr;
                c += dc;
            }
        }
        return dr == 1 ? r : c;
    }

    static List<PlaceHolder> find(char[][] crossword) {
        List<PlaceHolder> placeholders = new ArrayList<>();

        // horizontal
        for (int i = 0; i < 10; i++) {
            int j = 0;
            while (j < 10) {
                j = skip(crossword, PLACED, i, j, 0, 1);
                int jEnd = skip(crossword, '-', i, j, 0, 1);

                if (jEnd - j > 1) {
                    placeholders.add(new PlaceHolder(i, j, i, jEnd - 1, 'H'));
                }

                j = jEnd;
            }
        }

        // vertical 
        for (int i = 0; i < 10; i++) {
            int j = 0;
            while (j < 10) {
                j = skip(crossword, PLACED, j, i, 1, 0);
                int jEnd = skip(crossword, '-', j, i, 1, 0);

                if (jEnd - j > 1) placeholders.add(new PlaceHolder(j, i, jEnd - 1, i, 'V'));

                j = jEnd;
            }
        }

        return placeholders;
    }

    private static void solveRecursive(char[][] crossword, List<PlaceHolder> placeholders, List<String> words, int index) {
        if (stop || index >= 2 * words.size()) return;
        
        // base case
        if (placeholders.isEmpty() || words.isEmpty()) {
            printArray(crossword);
            stop = true;
            return;
        }

        String word = words.get(index % words.size());

        for (PlaceHolder placeholder : placeholders) {

            if (placeholder.length == word.length() && fill(crossword, placeholder, word, true)) {
                char[][] crosswordCopy = copy(crossword);
                fill(crosswordCopy, placeholder, word, false);

                ArrayList<PlaceHolder> placeholdersCopy = new ArrayList<>(placeholders);
                placeholdersCopy.remove(placeholder);
                solveRecursive(copy(crosswordCopy), placeholdersCopy, words, index + 1);
            }

            solveRecursive(copy(crossword), placeholders, words, index + 1);
        }
    }

    private static boolean fill(char[][] crossword, PlaceHolder p, String word, boolean check) {
        if (p.orientation == 'H') {
            return fill(crossword, p, word, 0, 1, check);
        }

        return fill(crossword, p, word, 1, 0, check);
    }

    private static char[][] copy(char[][] crossword) {
        char[][] crosswordCopy = new char[10][10];
        for (int i = 0; i < 10; i++) {
            crosswordCopy[i] = crossword[i].clone();
        }
        return crosswordCopy;
    }

    private static boolean fill(char[][] crossword, PlaceHolder p, String word, int dr, int dc, boolean check) {
        int r = p.r1, c = p.c1;
        for (int i = 0; i < word.length(); i++) {
            if (crossword[r][c] != '-' && crossword[r][c] != word.charAt(i)) {
                return false;
            }

            if (!check) {
                crossword[r][c] = word.charAt(i);
            }

            r += dr;
            c += dc;
        }

        return true;
    }

    // Complete the crosswordPuzzle function below.
    static void crosswordPuzzle(String[] crossword, List<String> words) {
        int r = crossword.length, c = crossword[0].length();
        
        // convert String[] to char[][]
        char[][] crosswordArr = new char[r][c];
        for (int i = 0; i < r; i++) {
            crosswordArr[i] = crossword[i].toCharArray();
        }

        // get all placeholders ('-' areas)
        List<PlaceHolder> placeholders = find(crosswordArr);

        // solve recursively
        solveRecursive(crosswordArr, placeholders, words, 0);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] crossword = new String[10];
        for (int i = 0; i < 10; i++) {
            crossword[i] = scanner.nextLine();
            
            if(crossword[i].contains("X")) PLACED = 'X';
        }

        List<String> words = new ArrayList(
                Arrays.asList(scanner.nextLine().split(";")));
        
        crosswordPuzzle(crossword, words);
        
        scanner.close();
    }
}

