import java.util.*;

public class DynamicProgramming {

    public static void main(String[] args) {
        //TESTS
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(wordBreak("leetcode", wordDict));
        int[] houses = new int[]{2, 1, 1, 2};
        System.out.println(rob(houses));
    }


    //WORD_BREAK
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];

        // Empty string is always valid
        dp[0] = true;

        // For each position i in the string
        for (int i = 1; i <= s.length(); i++) {
            // Try all possible substrings ending at i
            for (int k = 0; k < i; k++) {
                // If we can segment string up to j AND substring from j to i is in dictionary
                if (dp[k] && dictionary.contains(s.substring(k, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    //Tribonacci
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        int[] threeCount = new int[]{0, 1, 1};
        int nValue = 0;
        for (int i = 3; i <= n; i++) {
            nValue = threeCount[0] + threeCount[1] + threeCount[2];
            threeCount[0] = threeCount[1];
            threeCount[1] = threeCount[2];
            threeCount[2] = nValue;
        }
        return threeCount[2];
    }


    //ClimbingStairs
    public int minCostClimbingStairs(int[] cost) {
        int prevStairCost = cost[0];
        int curStairCost = cost[1];
        if (cost.length == 2) {
            return Math.min(prevStairCost, curStairCost);
        }
        for (int i = 2; i < cost.length; i++) {
            int nextStairCost = cost[i] + Math.min(prevStairCost, curStairCost);
            prevStairCost = curStairCost;
            curStairCost = nextStairCost;
        }
        return Math.min(prevStairCost, curStairCost);
    }

    //Robber
    public static int rob(int[] houses) {
        int prevHouse = 0;
        int maxVal = 0;

        for (int house : houses) {
            int tempMax = Math.max(maxVal, prevHouse + house);
            prevHouse = maxVal;
            maxVal = tempMax;
        }
        return maxVal;
    }


    //Multidimensional
    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];

        // Fill cache
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                cache[r][c] = -1;
            }
        }
        return numPaths(m - 1, n - 1, cache);
    }

    private int numPaths(int r, int c, int[][] cache) {
        if (r == 0 || c == 0) {
            return 1;
        }
        if (cache[r - 1][c] == -1) {
            cache[r - 1][c] = numPaths(r - 1, c, cache);
        }
        if (cache[r][c - 1] == -1) {
            cache[r][c - 1] = numPaths(r, c - 1, cache);
        }
        return cache[r - 1][c] + cache[r][c - 1];
    }

}
