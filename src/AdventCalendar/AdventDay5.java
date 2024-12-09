package AdventCalendar;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AdventDay5 {

    public static void main(String[] args) {
        try {
            List<String> rules = AdventDay4.IOUtil.readFile("day05rules.data");
            List<String> pages = AdventDay4.IOUtil.readFile("day05pages.data");
            //System.out.println(printQueue(rules, pages));
            System.out.println(incorrectPrintQueue(rules, pages));
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
        HashMap<String, Set<String>> mappedPages = new HashMap<>();

        for (String rule : rules) {
            String[] splitArray = rule.split("[|]");
            mappedPages.putIfAbsent(splitArray[0], new HashSet<>());
            mappedPages.get(splitArray[0]).add(splitArray[1]);
        }

        List<List<String>> correctedOrderList = new ArrayList<>();
        for (String page : pages) {
            List<String> pageList = new ArrayList<>(Arrays.asList(page.split(",")));

            if (isIncorrectOrder(pageList, mappedPages)) {
                System.out.println(mappedPages);
                List<String> correctOrder = correctOrder(pageList, mappedPages);
                correctedOrderList.add(correctOrder);
            }
        }

        long total = 0;
        for (List<String> correctedList : correctedOrderList) {
            int middleIndex = (correctedList.size() - 1) / 2;
            total += Integer.parseInt(correctedList.get(middleIndex));
        }
        return total;
    }

    private static boolean isIncorrectOrder(List<String> pageList, Map<String, Set<String>> orderGraph) {
        for (String page : pageList) {
            if (orderGraph.containsKey(page)) {
                Set<String> dependencies = orderGraph.get(page);
                for (String dependency : dependencies) {
                    // Check if the dependency exists in the list
                    int pageIndex = pageList.indexOf(page);
                    int dependencyIndex = pageList.indexOf(dependency);

                    // If dependency exists and is out of order
                    if (dependencyIndex != -1 && dependencyIndex < pageIndex) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static List<String> correctOrder(List<String> pageList, Map<String, Set<String>> orderGraph) {
        for (int pi = 0; pi < pageList.size() - 1; pi++) {
            String currentPage = pageList.get(pi);
            Set<String> matchingPages = orderGraph.get(currentPage);

            if (matchingPages == null) {
                String swap = pageList.remove(pi); // Removes and shifts the list
                pageList.add(swap);
                pi--;
                continue;
            }

            for (int pj = pi + 1; pj < pageList.size(); pj++) {
                String nextPage = pageList.get(pj);

                if (!matchingPages.contains(nextPage)) {
                    pageList.set(pj, currentPage);
                    pageList.set(pi, nextPage);
                    break;
                }
            }
        }
        return pageList;
    }


}
