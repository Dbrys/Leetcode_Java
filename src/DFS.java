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

    // Similar-leafs
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

    // Good count of nodes
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
}
