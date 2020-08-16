package algorithm.week05;

public class Solve {

    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0) return;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            dfs(board, row, col, i, 0);
            dfs(board, row, col, i, col - 1);
        }
        for (int i = 0; i < col; i++) {
            dfs(board, row, col, 0, i);
            dfs(board, row, col, row - 1, i);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'A')
                    board[i][j] = 'O';
            }
        }
    }

    public void dfs(char[][] board, int row, int col, int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] != 'O')
            return;
        board[x][y] = 'A';
        dfs(board, row, col, x + 1, y);
        dfs(board, row, col, x - 1, y);
        dfs(board, row, col, x, y - 1);
        dfs(board, row, col, x, y + 1);
    }
}
