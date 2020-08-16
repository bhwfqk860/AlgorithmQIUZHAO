package algorithm.week05;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindWords {
    public List<String> findWords(char[][] board, String[] words) {
        WordTrie trie = new WordTrie();
        WordTrieNode root = trie.root;
        for (String s : words)
            trie.insert(s);

        Set<String> result = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                find(board, visited, i, j, m, n, result, root);
            }
        }
        return new LinkedList<String>(result);
    }

    private void find(char[][] board, boolean[][] visited, int i, int j, int m, int n, Set<String> result, WordTrieNode node) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j])
            return;
        node = node.children[board[i][j] - 'a'];
        if (node == null) {
            visited[i][j] = false;
            return;
        }
        if (node.isEnd) {
            result.add(node.val);
        }
        visited[i][j] = true;
        find(board, visited, i+1, j, m, n, result, node);
        find(board, visited, i-1, j, m, n, result, node);
        find(board, visited, i, j+1, m, n, result, node);
        find(board, visited, i, j-1, m, n, result, node);
        visited[i][j] = false;
    }
}
class WordTrie {
    public WordTrieNode root = new WordTrieNode();

    public WordTrie() {

    }

    public void insert(String s) {
        WordTrieNode node = root;
        for (char ch : s.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new WordTrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
        node.val = s;
    }
}
class WordTrieNode {
    public String val;
    public WordTrieNode[] children = new WordTrieNode[26];
    public boolean isEnd = false;;

    public WordTrieNode() {

    }
}
