package AdventCalendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventDay4 {

    public static void main(String[] args) {
        try {
            List<String> input = IOUtil.readFile("board.data");
            System.out.println(x_mas(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class IOUtil {
        public static List<String> readFile(String fileName) throws IOException {
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
            return lines;
        }
    }

    private static long x_mas(List<String> input) {
        long result = 0;

        for (int i = 1; i < input.size() - 1; i++) {
            String xLine = input.get(i);
            int j = xLine.indexOf('A');
            while (j != -1) {
                result += xmasOccurrence(input, i, j);
                j = xLine.indexOf('A', j + 1);
            }
        }
        return result;
    }

    private static int xmasOccurrence(List<String> input, int i, int j) {
        if (i < 1 || j < 1 || i > input.size() - 2 || j > input.get(0).length() - 2) {
            return 0;
        }
        char tl = input.get(i - 1).charAt(j - 1);
        char br = input.get(i + 1).charAt(j + 1);
        char tr = input.get(i - 1).charAt(j + 1);
        char bl = input.get(i + 1).charAt(j - 1);

        boolean bar1 = (tl == 'M' && br == 'S') || (tl == 'S' && br == 'M');
        boolean bar2 = (tr == 'M' && bl == 'S') || (tr == 'S' && bl == 'M');

        return bar1 && bar2 ? 1 : 0;
    }

}
