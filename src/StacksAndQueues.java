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


    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> collidedAstroids = new Stack<>();

        for (int asteroid : asteroids) {
            while (!collidedAstroids.empty() && asteroid < 0 && collidedAstroids.peek() > 0) {
                if (Math.abs(asteroid) > collidedAstroids.peek()) {
                    collidedAstroids.pop();
                } else if (Math.abs(asteroid) == collidedAstroids.peek()) {
                    collidedAstroids.pop();
                    asteroid = 0;
                } else {
                    asteroid = 0;
                }
            }
            if (asteroid != 0) {
                collidedAstroids.push(asteroid);
            }
        }

        int[] result = new int[collidedAstroids.size()];

        for (int i = 0; i < collidedAstroids.size(); i++) {
            result[i] = collidedAstroids.get(i);
        }

        return result;
    }
}
