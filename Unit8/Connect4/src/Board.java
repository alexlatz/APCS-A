public class Board {
    //Decided on a byte implementation because enums are just as memory heavy as ints
    //This probably won't have any practical effect, but data structure optimizations are good for AI

    //bytes are used to indicate null (0), p1 (1), or p2 (2)
    private final byte[][] board;
    private byte winner;
    private final int[] colHeight;
    private int moves;
    private int lastMove;

    public Board() {
        this.board = new byte[6][7];
        this.colHeight = new int[7];
        this.moves = 0;
        this.winner = 0;
    }

    public Board(final int rows, final int cols) {
        if (rows < 4 || cols < 4) throw new IllegalArgumentException("The board must be at least 4x4");
        this.board = new byte[rows][cols];
        this.colHeight = new int[cols];
        this.winner = 0;
    }

    public Board(final byte[][] board, final int[] colHeight, final byte winner) {
        this.board = board;
        this.colHeight = colHeight;
        this.winner = winner;
    }

    public void placeMarker(int col, final boolean player1) {
        col = col-1;
        if (moves > getRows() * getCols()) throw new IndexOutOfBoundsException("There are no more available spaces");
        if (colHeight[col] >= getRows()) throw new IndexOutOfBoundsException("This column is full");
        final int height = getRows() - 1 - colHeight[col]++;
        this.board[height][col] = (byte) (player1 ? 1 : 2);
        lastMove = col;
        moves++;
        checkWinner(height, col);
    }

    public byte[][] getBoard() {
        //defensive copy for safety
        byte[][] copy = new byte[getRows()][getCols()];
        for (int i = 0; i < getRows(); i++) {
            System.arraycopy(this.board[i], 0, copy[i], 0, getCols());
        }
        return copy;
    }

    public int getCols() {
        return this.board[0].length;
    }

    public int getRows() {
        return this.board.length;
    }

    public int getMoves() {
        return this.moves;
    }

    public byte getWinner() {
        return this.winner;
    }

    public int getLastMove() {
        return this.lastMove;
    }

    public int getColHeight(int i) {
        return colHeight[i];
    }

    public Board copy() {
        final byte[][] newBoard = new byte[getRows()][getCols()];
        final int[] newColHeight = new int[getCols()];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, getCols());
        }
        System.arraycopy(colHeight, 0, newColHeight, 0, colHeight.length);
        return new Board(newBoard, newColHeight, winner);
    }

    public int numInRow(int num) {
        //Not actually the best way to get number in a row, but the heuristic really doesn't have to be that good
        int sum = 0;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                int current = 0;
                if (board[i][j] == 0) continue;
                if (colHeight[i] != 0) current += (checkVertical(i, j, num) ? 1 : 0);
                current += (checkDiagonal(i, j, num) ? 1 : 0);
                current += (checkHorizontal(i, j, num) ? 1 : 0);
                current *= (board[i][j] == 2 ? 1 : -1);
                sum += current;
            }
        }
        return sum;
    }


    private void checkWinner(final int row, final int col) {
        if (checkHorizontal(row, col, 4) || checkVertical(row, col, 4) || checkDiagonal(row, col, 4)) {
            winner = board[row][col];
        }
    }


    private boolean checkHorizontal(final int row, final int col, int num) {
        byte sum = 0;
        for (int i = Math.max(col - (num-1), 0); i < Math.min(col + num, getCols()); i++) {
            if (board[row][i] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= num) return true;
        }
        return false;
    }

    private boolean checkVertical(final int row, final int col, int num) {
        byte sum = 0;
        for (int i = Math.max(row - (num-1), 0); i < Math.min(row + num, getRows()); i++) {
            if (board[i][col] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= num) return true;
        }
        return false;
    }

    private boolean checkDiagonal(final int row, final int col, int num) {
        //bottom left -> top right
        byte sum = 0;
        for (int i = -(num-1); i <= (num-1); i++) {
            if (row + i < 0 || col + i < 0) continue;
            else if (row + i >= getRows() || col + i >= getCols()) break;
            if (board[row + i][col + i] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= num) return true;
        }
        //bottom right -> top left
        sum = 0;
        for (int i = -(num-1); i <= (num-1); i++) {
            if (row + i < 0 || col - i < 0) continue;
            else if (row + i >= getRows() || col - i >= getCols()) break;
            if (board[row + i][col - i] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= num) return true;
        }
        return false;
    }
}