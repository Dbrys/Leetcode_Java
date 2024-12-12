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
    }

    // Graph
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
}
