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

}
