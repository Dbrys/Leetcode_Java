import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DFS {

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>(Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                new ArrayList<>()
        ));
        System.out.println(canVisitAllRooms(rooms));
        int[][] cities = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(cities));
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

    //====== Graphs
    // Key to room
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(rooms.size(), false));
        dfs(rooms, rooms.get(0), visited);
        visited.set(0, true);
        // loop through visited after dfs
        for (boolean vis : visited) {
            if (!vis) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(List<List<Integer>> rooms, List<Integer> room, List<Boolean> vis) {
        for (int key : room) {
            if (!vis.get(key)) {
                vis.set(key, true);
                dfs(rooms, rooms.get(key), vis);
            }
        }
    }


    // Number of Provinces
    public static int findCircleNum(int[][] isConnected) {
        boolean[] isProvince = new boolean[isConnected.length]; // [false,false,false]
        int provinceCount = 0;
        for (int i = 0; i < isConnected.length; i++) { //[[1,1,0],[1,1,0],[0,0,1]] - 3
            if (!isProvince[i]) {
                dfs(isConnected, isProvince, i);
                provinceCount++;
            }
        }
        return provinceCount;
    }

    public static void dfs(int[][] isConnected, boolean[] isAProvince, int cityNum) {
        isAProvince[cityNum] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[cityNum][i] == 1 && isAProvince[i] == false) {
                dfs(isConnected, isAProvince, i); // 1
            }
        }
    }


    private int minChange = 0;
    public void dfs(List<List<Pair<Integer, Integer>>> adj, boolean[] visited, int currCity) {
        visited[currCity] = true;
        for (Pair<Integer, Integer> neighbourCity : adj.get(currCity)) {
            if (!visited[neighbourCity.getKey()]) {
                if (neighbourCity.getValue() == 1) {
                    minChange += 1;
                }
                dfs(adj, visited, neighbourCity.getKey());
            }
        }
    }

    public int minReorder(int n, int[][] connections) {
        List<List<Pair<Integer, Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] connection : connections) {
            adj.get(connection[0]).add(new Pair<>(connection[1], 1));
            adj.get(connection[1]).add(new Pair<>(connection[0], -1));
        }
        boolean[] visited = new boolean[n];
        dfs(adj, visited, 0);
        return minChange;
    }

    public class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }


    //====== Trees
    // === Similar-leafs
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leftTreeLeaves = new ArrayList<>();
        List<Integer> rightTreeLeaves = new ArrayList<>();

        dfsLeaf(root1, leftTreeLeaves);
        dfsLeaf(root2, rightTreeLeaves);

        return leftTreeLeaves.equals(rightTreeLeaves);
    }

    public void dfsLeaf(TreeNode node, List<Integer> collect) {
        if (node == null) {
            return;
        }

        if (node.right == null && node.left == null) {
            collect.add(node.val);
        }

        dfsLeaf(node.right, collect);
        dfsLeaf(node.left, collect);
    }

    // === Good count of nodes
    public int goodNodes(TreeNode root) {
        return dfsGoodNode(root, Integer.MIN_VALUE);
    }

    public int dfsGoodNode(TreeNode node, int curMax) {
        if (node == null) {
            return 0;
        }
        int goodCount = node.val >= curMax ? 1 : 0;
        if (node.val >= curMax) {
            curMax = node.val;
        }
        goodCount += dfsGoodNode(node.left, curMax);
        goodCount += dfsGoodNode(node.right, curMax);
        return goodCount;
    }


    // === Path sum equals target
    int counter = 0; // Global variable

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        dfsPath(root, targetSum, 0);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return counter;
    }

    public void dfsPath(TreeNode node, int targetSum, long currentSum) {
        if (node == null) {
            return;
        }
        currentSum += node.val;
        if (currentSum == targetSum) {
            counter++;
        }
        dfsPath(node.left, targetSum, currentSum);
        dfsPath(node.right, targetSum, currentSum);
    }

    // Zig Zag path
    private int maxZigZag = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfsZigZag(root, true, 0);
        dfsZigZag(root, false, 0);

        return maxZigZag;
    }

    public void dfsZigZag(TreeNode node, boolean goLeft, int length) {
        if (node == null) {
            return;
        }
        maxZigZag = Math.max(maxZigZag, length);
        if (goLeft) {
            dfsZigZag(node.left, false, length + 1);
            dfsZigZag(node.right, true, 1);

        } else {
            dfsZigZag(node.right, true, length + 1);
            dfsZigZag(node.left, false, 1);
        }
    }

    // LCA
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p) {
            return p;
        }
        if (root == q) {
            return q;
        }
        if (root == null) {
            return null;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q); // 5
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left == null) {
            return right;
        } else {
            return left;
        }
    }

    //############
    public boolean isHeightBalanced(TreeNode root) {
        return dfsDepth(root) != -1;
    }

    public int dfsDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = dfsDepth(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = dfsDepth(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }
    //############

    public boolean isSymmetric(TreeNode root) {
        // Check values as I go down the tree
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
    //############
    
    // Word Search
    public boolean exist(char[][] board, String word) {
        int numRows = board.length;
        int numCols = board[0].length;
        boolean[][] visited = new boolean[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backTracking(i, j, board, 0, visited, word)) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean backTracking(int row, int col, char[][] board, int indx, boolean[][] visited, String word) {
        if (indx == word.length()) {
            return true;
        }

        // Failed cases
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]
                || board[row][col] != word.charAt(indx)) {
            return false;
        }
        visited[row][col] = true;

        if (backTracking(row, col + 1, board, indx + 1, visited, word))
            return true;
        if (backTracking(row, col - 1, board, indx + 1, visited, word))
            return true;
        if (backTracking(row + 1, col, board, indx + 1, visited, word))
            return true;
        if (backTracking(row - 1, col, board, indx + 1, visited, word))
            return true;

        visited[row][col] = false;
        return false;
    }

    //##############
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> targetList = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();
        dfs(root, targetSum, curPath, targetList);
        return targetList;
    }

    public void dfs(TreeNode node, int passedSum, List<Integer> curPath, List<List<Integer>> targetList) {
        if (node == null) {
            return;
        }
        curPath.add(node.val);

        if (node.left == null && node.right == null && passedSum == node.val) {
            targetList.add(new ArrayList<>(curPath));
        } else {
            dfs(node.left, passedSum - node.val, curPath, targetList);
            dfs(node.right, passedSum - node.val, curPath, targetList);
        }

        curPath.remove(curPath.size() - 1);
    }
}
