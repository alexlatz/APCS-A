import java.util.ArrayList;

public class Minimax {
    private static double weight3;
    private static double weight2;
    private static double weight1;

    private class Node {
        private Board board;
        private double heuristic;

        public Node(Board board) {
            this.board = board;
            if (this.board.getWinner() == 0 && this.board.getMoves() < this.board.getCols()*this.board.getRows()) {
                this.heuristic = Minimax.heuristic(board);
            } else if (this.board.getWinner() == 2) this.heuristic = Double.POSITIVE_INFINITY;
            else if (this.board.getWinner() == 1) this.heuristic = Double.NEGATIVE_INFINITY;
            else this.heuristic = 0;
        }

        public Iterable<Node> getChildren() {
            ArrayList<Node> list = new ArrayList<>();
            for (int i = 0; i < board.getCols(); i++) {
                if (board.getColHeight(i)+1 < board.getRows()) {
                    Board child = board.copy();
                    child.placeMarker(i, board.getMoves() % 2 == 0);
                    list.add(new Node(child));
                }
            }
            return list;
        }
    }

    private static double getWeight(int i) {
        switch (i) {
            case 1:
                return weight1;
            case 2:
                return weight2;
            default:
                return weight3;
        }
    }

    private static double heuristic(Board board) {
        int max = 0;
        for (int i = 0; i < board.getCols(); i++) max = Math.max(board.getColHeight(i), max);
        byte[][] arr = board.getBoard();
        return horizontalHeuristic(arr, max) + verticalHeuristic(arr, board) + diagHeuristic(arr, max);
    }

    //TODO: all of the heuristics bc they're bad
    private static double horizontalHeuristic(byte[][] board, int max) {
        double heuristic = 0.0;
        for (int i = 0; i < max; i++) {
            byte current = 0;
            boolean lastSpace = false;
            byte sum = 0;
            for (int j = 0; j < board[0].length; j++) {
                if (current == 0 && board[i][j] != current) current = board[i][j];
                if (board[i][j] == current) sum++;
                else if (board[i][j] != current && lastSpace) {
                    if (board[i][j] == 2) heuristic += Minimax.getWeight(sum);
                    else heuristic -= Minimax.getWeight(sum);
                    sum = 0;
                    lastSpace = false;
                    current = board[i][j];
                }
                else lastSpace = true;
            }
        }
        return heuristic;
    }

    private static double verticalHeuristic(byte[][] arr, Board board) {
        double heuristic = 0.0;
        for (int j = 0; j < board.getCols(); j++) {
            byte sum = 0;
            byte current = arr[0][j];
            for (int i = 0; i < board.getColHeight(j); i++) {
                if (arr[i][j] == current) sum++;
                else {
                    if (current == 2) heuristic += Minimax.getWeight(sum);
                    else heuristic -= Minimax.getWeight(sum);
                    current = arr[i][j];
                    sum = 0;
                }
            }
            if (current == 2) heuristic += Minimax.getWeight(sum);
            else if (current == 1) heuristic -= Minimax.getWeight(sum);
        }
        return heuristic;
    }

    private static double diagHeuristic(byte[][] board, int max) {
        //dont bother running if max < 4
        //go thru each bottom left -> top right diag
        //i = bottom of diag
        //j = horizontal
        if (max < 3) return 0.0;
        for (int i = 0; i < max - 3 && i < board.length-3; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j >= 3) {
                    for (int k = 0; k <= 4; k++) {

                    }
                }
                if (j <= board[0].length - 3) {

                }
            }
        }
    }
}
