public class MagicSquare {
    public static boolean isMagicSquare(final int[][] square) {
        int sum = square[0][0] + square[0][1] + square[0][2];
        return checkRows(sum, square) && checkCols(sum, square) && checkDiags(sum, square);
    }

    private static boolean checkRows(final int sum, final int[][] square) {
        for (int i = 1; i < 3; i++) {
            if (sum != square[i][0] + square[i][1] + square[i][2]) return false;
        }
        return true;
    }

    private static boolean checkCols(final int sum, final int[][] square) {
        for (int i = 0; i < 3; i++) {
            if (sum != square[0][i] + square[1][i] + square[2][i]) return false;
        }
        return true;
    }

    private static boolean checkDiags(final int sum, final int[][] square) {
        final boolean leftDiag = sum == square[0][0] + square[1][1] + square[2][2];
        final boolean rightDiag = sum == square[0][2] + square[1][1] + square[2][0];
        return leftDiag && rightDiag;
    }

    public static void main(String[] args) {
        //true test
        final int[][] trueSquare = {{2,7,6},{9,5,1},{4,3,8}};
        //false test
        final int[][] falseSquare = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(isMagicSquare(trueSquare));
        System.out.println(isMagicSquare(falseSquare));
    }
}
