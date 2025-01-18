import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        int[] test = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        System.out.println(dictionary.uniqueOccurrences(test));
    }

    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> numCountMap = new HashMap<>();

        for (int num : arr) {
            if (numCountMap.containsKey(num)) {
                numCountMap.put(num, numCountMap.get(num) + 1);
            } else {
                numCountMap.put(num, 1);
            }
        }

        Set<Integer> countSet = new HashSet<>();
        for (int val : numCountMap.values()) {
            if (countSet.contains(val)) {
                return false;
            }
            countSet.add(val);
        }
        return true;
    }
}
