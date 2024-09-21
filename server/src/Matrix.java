public class Matrix {
    public static int[] minInRow(int[][] matrix) {
        int rowIndex = 0, columnIndex = 0, min = matrix[0][0], max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (min > matrix[i][j]) {
                    min = matrix[i][j];
                    rowIndex = i;
                }

            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (max < matrix[j][i]) {
                    max = matrix[j][i];
                    columnIndex = i;
                }
            }
        }
        return new int[]{rowIndex, columnIndex};
    }
}
