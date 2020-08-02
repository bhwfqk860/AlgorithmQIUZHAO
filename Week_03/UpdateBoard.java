package algorithm.week03;

public class UpdateBoard {
    int[] dx = {-1,-1,-1,0,0,1,1,1};
    int[] dy = {0,-1,1,-1,1,-1,0,1};

    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board, click[0], click[1]);
        return board;
    }

    public void dfs(char[][] board, int x, int y) {
        int row = board.length;
        int col = board[0].length;
        if (x < 0 || y < 0 || x >= row || y >= col)
            return;

        if (board[x][y] == 'E') {
            board[x][y] = 'B';
            int count = judge(board, x, y);
            if (count == 0) {
                for (int i = 0; i < 8; i++) {
                    dfs(board, x + dx[i], y + dy[i]);
                }
            } else {
                board[x][y] = (char) (count+'0');
            }
        } else if (board[x][y] == 'M') {
            board[x][y] = 'X';
        }
    }

    public int judge(char[][] board, int x, int y) {
        int row = board.length;
        int col = board[0].length;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int r = x + dx[i];
            int c = y + dy[i];
            if (r < 0 || c < 0 || r >= row || c >= col)
                continue;
            if (board[r][c] == 'M')
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        new UpdateBoard().updateBoard(board, new int[]{3, 0});
    }
}
