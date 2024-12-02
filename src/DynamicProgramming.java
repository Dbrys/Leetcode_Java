import java.util.*;

public class DynamicProgramming {

    public static void main(String[] args) {
        //TESTS
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(wordBreak("leetcode", wordDict));
    }


    //WORD_BREAK - leetcode 139:
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

}
