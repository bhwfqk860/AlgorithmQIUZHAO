package algorithm.week03;

public class NumIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0) return count;
        int row = grid.length;
        int col = grid[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    helper(grid, r, c);
                }
            }
        }
        return count;
    }

    private void helper(char[][] grid, int r, int c) {
        int row = grid.length;
        int col = grid[0].length;
        if (r >= row || c >= col || r < 0 || c < 0 || grid[r][c] == '0')
            return;

        grid[r][c] = '0';

        helper(grid, r+1, c);
        helper(grid, r-1, c);
        helper(grid, r, c+1);
        helper(grid, r, c-1);
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        new NumIslands().numIslands(grid);
    }
}
