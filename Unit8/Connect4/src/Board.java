public class Board {
    //Decided on a byte implementation because enums are just as memory heavy as ints
    //This probably won't have any practical effect, but data structure optimizations are good for AI

    //bytes are used to indicate null (0), p1 (1), or p2 (2)
    private final byte[][] board;
    private byte winner;
    private final int[] colHeight;
    private int moves;

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


    private void checkWinner(final int row, final int col) {
        if (checkHorizontal(row, col) || checkVertical(row, col) || checkDiagonal(row, col)) {
            winner = board[row][col];
        }
    }

    private boolean checkHorizontal(final int row, final int col) {
        byte sum = 0;
        for (int i = Math.max(col - 3, 0); i < Math.min(col + 4, getCols()); i++) {
            if (board[row][i] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= 4) return true;
        }
        return false;
    }

    private boolean checkVertical(final int row, final int col) {
        byte sum = 0;
        for (int i = Math.max(row - 3, 0); i < Math.min(row + 4, getRows()); i++) {
            if (board[i][col] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= 4) return true;
        }
        return false;
    }

    private boolean checkDiagonal(final int row, final int col) {
        //bottom left -> top right
        byte sum = 0;
        for (int i = -3; i <= 3; i++) {
            if (row + i < 0 || col + i < 0) continue;
            else if (row + i >= getRows() || col + i >= getCols()) break;
            if (board[row + i][col + i] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= 4) return true;
        }
        //bottom right -> top left
        sum = 0;
        for (int i = -3; i <= 3; i++) {
            if (row + i < 0 || col - i < 0) continue;
            else if (row + i >= getRows() || col - i >= getCols()) break;
            if (board[row + i][col - i] == board[row][col]) sum++;
            else sum = 0;
            if (sum >= 4) return true;
        }
        return false;
    }
}