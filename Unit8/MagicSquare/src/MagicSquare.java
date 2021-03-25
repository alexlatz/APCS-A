public class MagicSquare {
    public boolean magicSquare(final int[][] square) {
        if (square.length != 3 || square[0].length != 3) return false;
        int sum = square[0][0] + square[0][1] + square[0][2];
        return checkRows(sum, square) && checkCols(sum, square) && checkDiags(sum, square);
    }

    private boolean checkRows(final int sum, final int[][] square) {
        for (int i = 1; i < 3; i++) {
            if (sum != square[i][0] + square[i][1] + square[i][2]) return false;
        }
        return true;
    }

    private boolean checkCols(final int sum, final int[][] square) {
        for (int i = 0; i < 3; i++) {
            if (sum != square[0][i] + square[1][i] + square[2][i]) return false;
        }
        return true;
    }

    private boolean checkDiags(final int sum, final int[][] square) {
        final boolean leftDiag = sum == square[0][0] + square[1][1] + square[2][2];
        final boolean rightDiag = sum == square[0][2] + square[1][1] + square[2][0];
        return leftDiag && rightDiag;
    }
}
