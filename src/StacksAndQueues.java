import java.util.LinkedList;
import java.util.Queue;
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

    public int[] dailyTemperatures(int[] temperatures) {
        int tempLength = temperatures.length;
        int[] dayCount = new int[tempLength];

        Stack<Integer> stack = new Stack<>();

        // Right to left
        for (int k = tempLength - 1; k >= 0; k--) {

            while (!stack.isEmpty() && temperatures[k] >= temperatures[stack.peek()]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                dayCount[k] = stack.peek() - k;
            } else {
                dayCount[k] = 0;
            }

            stack.push(k);

        }
        return dayCount;
    }

    public String decodeString(String s) {
        Stack<Integer> counter = new Stack<>();
        Stack<String> stringResult = new Stack<>();
        String res = "";
        int index = 0;

        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = 10 * count + (s.charAt(index) - '0');
                    index += 1;
                }
                counter.push(count);
            } else if (s.charAt(index) == '[') {
                stringResult.push(res);
                res = "";
                index += 1;

            } else if (s.charAt(index) == ']') {
                StringBuilder tempString = new StringBuilder(stringResult.pop());
                int count = counter.pop();
                for (int i = 0; i < count; i++) {
                    tempString.append(res);
                }
                res = tempString.toString();
                index += 1;
            } else {
                res += s.charAt(index);
                index += 1;
            }

        }

        return res;
    }


    public String predictPartyVictory(String senate) {
        Queue<Integer> rad = new LinkedList<>();
        Queue<Integer> dir = new LinkedList<>();
        int n = senate.length();
        // Add indexes to the queue. Lower index = priority
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                rad.add(i);
            } else {
                dir.add(i);
            }
        }
        // Use increasing n to keep track of position
        while (!rad.isEmpty() && !dir.isEmpty()) {
            if (rad.peek() < dir.peek()) {
                rad.add(n++);
            } else {
                dir.add(n++);
            }
            rad.poll();
            dir.poll();
        }
        return (rad.isEmpty()) ? ("Dire") : ("Radiant");
    }
}
