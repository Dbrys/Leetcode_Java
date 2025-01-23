import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking {

    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        System.out.println(backtracking.letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        Map<Character, String> digitsToLetters = new HashMap<>();
        digitsToLetters.put('2', "abc");
        digitsToLetters.put('3', "def");
        digitsToLetters.put('4', "ghi");
        digitsToLetters.put('5', "jkl");
        digitsToLetters.put('6', "mno");
        digitsToLetters.put('7', "pqrs");
        digitsToLetters.put('8', "tuv");
        digitsToLetters.put('9', "wxyz");

        backTrack(digits, 0, new StringBuilder(), result, digitsToLetters);

        return result;
    }

    private void backTrack(String digits, int digitIndx, StringBuilder combination, List<String> result,
                           Map<Character, String> digitsToLetters) {
        if (digitIndx == digits.length()) {
            result.add(combination.toString());
            return;
        }

        String letters = digitsToLetters.get(digits.charAt(digitIndx));
        for (char letter : letters.toCharArray()) {
            combination.append(letter);
            backTrack(digits, digitIndx + 1, combination, result, digitsToLetters);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
