import java.util.HashMap;

public class Strings {

    public static void main(String args[]) {
        //Test
        System.out.println(uniqueCharacter("alias"));
    }

    private static int uniqueCharacter(String str) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();

        for (char letter : str.toCharArray()) {
            charMap.put(letter, charMap.getOrDefault(letter, 0) + 1);
        }

        for (int i = 0; i < str.length(); i++) {
            if (charMap.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
