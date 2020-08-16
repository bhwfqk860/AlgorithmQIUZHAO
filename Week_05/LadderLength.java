package algorithm.week05;

import javafx.util.Pair;

import java.util.*;

public class LadderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // init
        int len = beginWord.length();
        Map<String, List<String>> dict = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < len; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i+1, len);
                List<String> list = dict.getOrDefault(newWord, new ArrayList<>());
                list.add(word);
                dict.put(newWord, list);
            }
        });

        // BFS
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));

        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Pair<String, Integer> pair = queue.remove();
            String word = pair.getKey();
            int level = pair.getValue();
            for (int i = 0; i < len; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i+1, len);
                for (String candicateWord : dict.getOrDefault(newWord, new ArrayList<>())) {
                    if (candicateWord.equals(endWord))
                        return level+1;

                    if (!visited.getOrDefault(candicateWord, false)) {
                        visited.put(candicateWord, true);
                        queue.add(new Pair(candicateWord, level+1));
                    }
                }
            }
        }
        return 0;
    }
}
