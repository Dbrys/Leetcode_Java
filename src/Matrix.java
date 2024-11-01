import java.util.Arrays;

public class Matrix {

    public static void main(String[] args){
        //TEST
        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'p', 'p', '.', 'R', '.', 'p', 'B', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}
        };
        System.out.println(numRookCaptures(board));
    }
    private static int numRookCaptures(char[][] board) {
        int eliminateCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board[i].length; k++) {
                if (board[i][k] == 'R') {
                    int rookIndex = k;
                    int leftOfRookIndex = k - 1;
                    int rightOfRookIndex = k + 1;
                    int previousRowIndex = i - 1;
                    int nextRowIndex = i + 1;
                    while (leftOfRookIndex >= 0) {
                        if (board[i][leftOfRookIndex] == 'p') {
                            eliminateCount++;
                            break;
                        }
                        if (board[i][leftOfRookIndex] == 'B') {
                            break;
                        }
                        leftOfRookIndex--;
                    }
                    while (rightOfRookIndex < board[i].length) {
                        if (board[i][rightOfRookIndex] == 'p') {
                            eliminateCount++;
                            break;
                        }
                        if (board[i][rightOfRookIndex] == 'B') {
                            break;
                        }
                        rightOfRookIndex++;
                    }
                    while (previousRowIndex >= 0) {
                        if (board[previousRowIndex][k] == 'p') {
                            eliminateCount++;
                            break;
                        }
                        if (board[previousRowIndex][k] == 'B') {
                            break;
                        }
                        previousRowIndex--;
                    }
                    while (nextRowIndex < board.length) {
                        if (board[nextRowIndex][k] == 'p') {
                            eliminateCount++;
                            break;
                        }
                        if (board[nextRowIndex][k] == 'B') {
                            break;
                        }
                        nextRowIndex++;
                    }
                }
            }
        }
        return eliminateCount;
    }
}
