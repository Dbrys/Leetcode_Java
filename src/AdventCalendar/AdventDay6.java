package AdventCalendar;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdventDay6 {

    public static void main(String[] args) {
        try {
            List<String> board = AdventDay4.IOUtil.readFile("day06board.data");
            System.out.println(guardPath(board));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int guardPath(List<String> board) {
        int moveCount = 1;

        // Find the guard's starting position and initial direction
        int guardRow = 0, guardCol = 0;
        for (int row = 0; row < board.size(); row++) {
            if (board.get(row).contains("^")) {
                guardRow = row;
                guardCol = board.get(row).indexOf("^");
                break;
            }
        }

        char direction = 'U'; // Starting direction

        Set<String> visited = new HashSet<>();
        visited.add(guardRow + "," + guardCol);

        while (true) {
            int nextRow = guardRow, nextCol = guardCol;

            // Determine the next position based on the current direction
            switch (direction) {
                case 'U':
                    nextRow = guardRow - 1;
                    break;
                case 'R':
                    nextCol = guardCol + 1;
                    break;
                case 'D':
                    nextRow = guardRow + 1;
                    break;
                case 'L':
                    nextCol = guardCol - 1;
                    break;
            }

            // Check if the guard is about to step outside the map
            if (nextRow < 0 || nextRow >= board.size() || nextCol < 0 || nextCol >= board.get(0).length()) {
                return moveCount;
            }

            char nextCell = board.get(nextRow).charAt(nextCol);

            if (nextCell == '#') {
                direction = turnRight(direction);
            } else {
                guardRow = nextRow;
                guardCol = nextCol;

                // Only increment moveCount if the cell is new
                String position = nextRow + "," + nextCol;
                if (!visited.contains(position)) {
                    visited.add(position);
                    moveCount++;
                }
            }
        }
    }

    private static char turnRight(char direction) {
        switch (direction) {
            case 'U':
                return 'R';
            case 'R':
                return 'D';
            case 'D':
                return 'L';
            case 'L':
                return 'U';
            default:
                return 'U';
        }
    }
}
