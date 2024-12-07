package AdventCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdventDay5 {

    public static void main(String[] args) {
        try {
            List<String> rules = AdventDay4.IOUtil.readFile("day05rules.data");
            List<String> pages = AdventDay4.IOUtil.readFile("day05pages.data");
            System.out.println(printQueue(rules, pages));
            //System.out.println(incorrectPrintQueue(rules, pages));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long printQueue(List<String> rules, List<String> pages) {
        HashMap<String, ArrayList<String>> mappedPages = new HashMap<>();
        ArrayList<List<String>> orderedList = new ArrayList<>();
        long total = 0;

        for (int i = 0; i < rules.size(); i++) {
            String[] splitArry = rules.get(i).split("[|]");
            mappedPages.put(
                    splitArry[0],
                    mappedPages.getOrDefault(splitArry[0], new ArrayList<>())
            );
            mappedPages.get(splitArry[0]).add(splitArry[1]);
        }

        for (int i = 0; i < pages.size(); i++) {
            List<String> pageList = Arrays.stream(pages.get(i).split("[,]")).toList();
            boolean isCorrectOrder = true;
            for (int j = 1; j < pageList.size(); j++) {
                int k = j;
                ArrayList<String> orderedPages = mappedPages.get(pageList.get(j - 1));
                if (orderedPages == null) {
                    isCorrectOrder = false;
                    break;
                }
                while (k < pageList.size()) {
                    if (!orderedPages.contains(pageList.get(k))) {
                        isCorrectOrder = false;
                        break;
                    }
                    k++;
                }
            }
            if (isCorrectOrder) {
                orderedList.add(pageList);
            }
        }

        for (List<String> correctList : orderedList) {
            total += Integer.parseInt(correctList.get(Math.round(correctList.size() / 2)));
        }
        return total;
    }


    private static long incorrectPrintQueue(List<String> rules, List<String> pages) {
        HashMap<String, ArrayList<String>> mappedPages = new HashMap<>();
        ArrayList<List<String>> incorrectOrderList = new ArrayList<>();
        long total = 0;

        for (int i = 0; i < rules.size(); i++) {
            String[] splitArry = rules.get(i).split("[|]");
            mappedPages.put(
                    splitArry[0],
                    mappedPages.getOrDefault(splitArry[0], new ArrayList<>())
            );
            mappedPages.get(splitArry[0]).add(splitArry[1]);
        }

        for (int i = 0; i < pages.size(); i++) {
            List<String> pageList = new ArrayList<>(Arrays.stream(pages.get(i).split("[,]")).toList());
            boolean isCorrectOrder = false;
            for (int j = 1; j < pageList.size(); j++) {
                int k = j;
                ArrayList<String> orderedPages = mappedPages.get(pageList.get(j - 1));
                if (orderedPages == null) {
                    isCorrectOrder = true;
                    break;
                }
                while (k < pageList.size()) {
                    if (!orderedPages.contains(pageList.get(k))) {
                        isCorrectOrder = true;
                        break;
                    }
                    k++;
                }
            }
            if (isCorrectOrder) {
                incorrectOrderList.add(pageList);
            }
        }

        return total;
    }


}
