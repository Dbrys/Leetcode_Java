import java.util.Stack;
import java.util.stream.Collectors;

public class StacksAndQueues {

    public static void main(String[] args) {
        StacksAndQueues stacksAndQueues = new StacksAndQueues();
        String testString = "erases*****";
        System.out.println(stacksAndQueues.removeStars(testString));
    }

    public String removeStars(String s) {
        char[] sArray = s.toCharArray();
        Stack<Character> starStack = new Stack<>();

        for (char ch : sArray) {
            if (ch == '*') {
                starStack.pop();
            } else {
                starStack.push(ch);
            }
        }

        return starStack.stream().map(Object::toString)
                .collect(Collectors.joining(""));
    }
}
