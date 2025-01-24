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
    public boolean leafSimilar(BFS.TreeNode root1, BFS.TreeNode root2) {
        List<Integer> leftTreeLeaves = new ArrayList<>();
        List<Integer> rightTreeLeaves = new ArrayList<>();

        dfsLeaf(root1, leftTreeLeaves);
        dfsLeaf(root2, rightTreeLeaves);

        return leftTreeLeaves.equals(rightTreeLeaves);
    }

    public void dfsLeaf(BFS.TreeNode node, List<Integer> collect) {
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
    public int goodNodes(BFS.TreeNode root) {
        return dfsGoodNode(root, Integer.MIN_VALUE);
    }

    public int dfsGoodNode(BFS.TreeNode node, int curMax) {
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

    public int pathSum(BFS.TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        dfsPath(root, targetSum, 0);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return counter;
    }

    public void dfsPath(BFS.TreeNode node, int targetSum, long currentSum) {
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

    public int longestZigZag(BFS.TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfsZigZag(root, true, 0);
        dfsZigZag(root, false, 0);

        return maxZigZag;
    }

    public void dfsZigZag(BFS.TreeNode node, boolean goLeft, int length) {
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
    public BFS.TreeNode lowestCommonAncestor(BFS.TreeNode root, BFS.TreeNode p, BFS.TreeNode q) {
        if (root == p) {
            return p;
        }
        if (root == q) {
            return q;
        }
        if (root == null) {
            return null;
        }
        BFS.TreeNode left = lowestCommonAncestor(root.left, p, q); // 5
        BFS.TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left == null) {
            return right;
        } else {
            return left;
        }
    }
}
