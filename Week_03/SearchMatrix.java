package algorithm.week03;

public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int n = matrix[mid / col][mid % col];
            if (n == target)
                return true;
            else if (n > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        new SearchMatrix().searchMatrix(matrix, 3);
    }
}
