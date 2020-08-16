# 学习笔记

## 单词搜索II

```java
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        TrieNode root = trie.root;
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

    private void find(char[][] board, boolean[][] visited, int i, int j, int m, int n, Set<String> result, TrieNode node) {
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

class Trie {
    public TrieNode root = new TrieNode();

    public Trie() {
        
    }

    public void insert(String s) {
        TrieNode node = root;
        for (char ch : s.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
        node.val = s;
    }
}

class TrieNode {
    public String val;
    public TrieNode[] children = new TrieNode[26];
    public boolean isEnd = false;

    public TrieNode() {

    }
}
```

