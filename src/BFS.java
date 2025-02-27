import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //###################  BFS Trees
    // Right side of binary tree
    private static List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> rightSideResult = new ArrayList<>();
        if (root == null) {
            return rightSideResult;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (i == levelSize - 1) {
                    rightSideResult.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return rightSideResult;
    }

    //Maximum level sum of binary tree
    public int maxLevelSum(TreeNode root) {
        int levelCount = 1;
        int maxVal = Integer.MIN_VALUE;
        int maxLevel = 0;

        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelValue = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode curNode = queue.poll();
                levelValue += curNode.val;
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            if (levelValue > maxVal) {
                maxVal = levelValue;
                maxLevel = levelCount;
            }
            levelCount++;
        }

        return maxLevel;
    }


    //###################### BFS Graphs
    public int nearestExit(char[][] maze, int[] entrance) {
        int rowCount = maze.length;
        int colCount = maze[0].length;
        Queue<int[]> directionQueue = new LinkedList<>();
        // Good to store directions to reference
        // Up, down,left,right
        int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        //Need to add entrance to start queue row, col, step count
        directionQueue.add(new int[]{entrance[0], entrance[1], 0});
        maze[entrance[0]][entrance[1]] = '+'; // Mark as visited

        while (!directionQueue.isEmpty()) {
            // Get queue values
            int[] queueVals = directionQueue.poll();
            int rowVal = queueVals[0];
            int colVal = queueVals[1];
            int steps = queueVals[2];

            for (int[] direction : directions) {
                int newRow = rowVal + direction[0];
                int newCol = colVal + direction[1];

                if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount && maze[newRow][newCol] == '.') {

                    if (newRow == 0 || newRow == rowCount - 1 || newCol == 0 || newCol == colCount - 1) {
                        return steps + 1;
                    }
                    //Mark a visited
                    maze[newRow][newCol] = '+';
                    directionQueue.add(new int[]{newRow, newCol, steps + 1});
                }
            }
        }
        return -1;
    }

    public int orangesRotting(int[][] grid) {
        int rowSize = grid.length;
        int colSize = grid[0].length;

        Queue<int[]> orangeQueue = new LinkedList<>();
        int freshOrangeCount = 0;
        // Add rotten oranges to queue and get fresh orange count
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (grid[r][c] == 2) {
                    orangeQueue.offer(new int[]{r, c});
                }
                if (grid[r][c] == 1) {
                    freshOrangeCount++;
                }
            }
        }

        if (freshOrangeCount == 0) {
            return 0;
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        int minMinutes = 0;

        while (!orangeQueue.isEmpty()) {
            int queueSize = orangeQueue.size();
            boolean addedRotten = false;

            for (int i = 0; i < queueSize; i++) {
                int[] oranges = orangeQueue.poll();
                int row = oranges[0];
                int col = oranges[1];

                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newRow < rowSize && newCol >= 0 && newCol < colSize
                            && grid[newRow][newCol] == 1) {
                        addedRotten = true;
                        grid[newRow][newCol] = 2;
                        orangeQueue.offer(new int[]{newRow, newCol});
                        freshOrangeCount--;
                    }
                }
            }
            if (addedRotten) {
                minMinutes++;
            }
        }
        return freshOrangeCount == 0 ? minMinutes : -1;
    }
}
