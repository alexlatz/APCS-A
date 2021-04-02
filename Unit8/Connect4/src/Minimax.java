import java.util.ArrayList;
import java.util.Collections;

public class Minimax {
    private static final double[] weight = {10.0, 100.0, 1000.0, 10000.0};
    private Node node;

    private class Node implements Comparable<Node> {
        private final Board board;
        private double value;
        private ArrayList<Node> children;

        public Node(Board board) {
            this.board = board;
            this.children = null;
        }

        public ArrayList<Node> getChildren() {
            if (this.children != null) return this.children;
            else if (!isTerminal()) {
                final ArrayList<Node> list = new ArrayList<>();
                for (int i = 0; i < board.getCols(); i++) {
                    if (board.getColHeight(i)+1 < board.getRows()) {
                        final Board child = board.copy();
                        child.placeMarker(i+1, board.getMoves() % 2 == 0);
                        list.add(new Node(child));
                    }
                }
                this.children = list;
            }
            return null;
        }

        public boolean isTerminal() {
            return !(board.getMoves() < board.getRows()*board.getCols() && board.getWinner() == 0);
        }

        @Override
        public int compareTo(Node o) {
            return -Double.compare(this.value, o.value);
        }
    }

    public Minimax(Board start) {
        this.node = new Node(start);
        this.node.getChildren();
    }

    public int getMove(Board board) {
        for (final Node child : this.node.getChildren()) {
            if (child.board.getLastMove() == board.getLastMove()) {
                this.node = child;
                break;
            }
        }
        alphaBeta(this.node, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
        this.node.getChildren();
        Collections.sort(this.node.getChildren());
        this.node = this.node.getChildren().get(0);
        return this.node.board.getLastMove();
    }

    public double alphaBeta(Node node, int depth, double alpha, double beta, boolean maximizing) {
        if (depth == 0 || node.isTerminal()) return heuristic(node.board);
        if (maximizing) {
            node.getChildren();
            node.value = Double.NEGATIVE_INFINITY;
            for (final Node child : node.getChildren()) {
                node.value = Math.max(node.value, alphaBeta(child, depth-1, alpha, beta, false));
                alpha = Math.max(alpha, node.value);
                if (alpha >= beta) break;
            }
        } else {
            node.value = Double.POSITIVE_INFINITY;
            node.getChildren();
            for (final Node child : node.getChildren()) {
                node.value = Math.min(node.value, alphaBeta(child, depth-1, alpha, beta, true));
                beta = Math.min(beta, node.value);
                if (beta <= alpha) break;
            }
        }
        return node.value;
    }

    //TODO: calculate 1,2 at once
    private static double heuristic(Board board) {
        final int[][] arr = board.getBoard();
        if (board.getWinner() == 0 && board.getMoves() < board.getCols()*board.getRows()) {
            double heuristic = 0.0;
            heuristic += numInRow(1, board, arr);
            heuristic += numInRow(2, board, arr);
            heuristic += numInRow(3, board, arr);
            return heuristic;
        } else if (board.getWinner() == 2) return Double.POSITIVE_INFINITY;
        else if (board.getWinner() == 1) return Double.NEGATIVE_INFINITY;
        else return 0;
    }

    private static double numInRow(int num, Board board, int[][] arr) {
        //Not actually the best way to get number in a row, but the heuristic really doesn't have to be that good
        double sum = 0;
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                int current = 0;
                if (arr[i][j] == 0) continue;
                if (board.getColHeight(i) != 0) current += (board.checkVertical(i, j, num) ? Minimax.weight[num-1] : 0);
                if (num == 3) current += diagonalThrees(board, arr, i, j);
                else current += (board.checkDiagonal(i, j, num) ? Minimax.weight[num-1] : 0);
                current += (board.checkHorizontal(i, j, num) ? Minimax.weight[num-1] : 0);
                current *= (arr[i][j] == 2 ? 1 : -1);
                sum += current;
            }
        }
        return sum;
    }

    private static double diagonalThrees(Board board, int[][] arr, int row, int col) {
        double result = 0;
        boolean threeFound = false;
        //bottom left -> top right
        int sum = 0;
        for (int i = -2; i <= 2; i++) {
            if (row + i < 0 || col + i < 0) continue;
            else if (row + i >= board.getRows() || col + i >= board.getCols()) break;
            if (arr[row + i][col + i] == arr[row][col]) {
                sum++;
                if (sum >= 3 && board.getColHeight(col + i) == row + i - 1) {
                    result += arr[row][col] == 1 ? -Minimax.weight[3] : Minimax.weight[3];
                } else if (!threeFound) {
                    result += arr[row][col] == 1 ? -Minimax.weight[2] : Minimax.weight[2];
                    threeFound = true;
                }
            }
            else sum = 0;
        }
        //bottom right -> top left
        sum = 0;
        for (int i = -2; i <= 2; i++) {
            if (row + i < 0 || col - i < 0) continue;
            else if (row + i >= board.getRows() || col - i >= board.getCols()) break;
            if (arr[row + i][col - i] == arr[row][col]) {
                sum++;
                if (sum >= 3 && board.getColHeight(col - i) == row + i - 1) {
                    result += arr[row][col] == 1 ? -Minimax.weight[3] : Minimax.weight[3];
                } else if (!threeFound) {
                    result += arr[row][col] == 1 ? -Minimax.weight[2] : Minimax.weight[2];
                    threeFound = true;
                }
            }
            else sum = 0;
        }
        return result;
    }

}
