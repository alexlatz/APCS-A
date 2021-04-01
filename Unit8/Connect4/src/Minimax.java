import java.util.ArrayList;
import java.util.Collections;

public class Minimax {
    private static final double[] weight = {10.0, 100.0, 1000.0};
    private Node node;

    private class Node implements Comparable<Node> {
        private Board board;
        private double value;
        private ArrayList<Node> children;

        public Node(Board board) {
            this.board = board;
            this.children = null;
        }

        public ArrayList<Node> getChildren() {
            if (this.children != null) return this.children;
            else if (!isTerminal()) {
                ArrayList<Node> list = new ArrayList<>();
                for (int i = 0; i < board.getCols(); i++) {
                    if (board.getColHeight(i)+1 < board.getRows()) {
                        Board child = board.copy();
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
        public int compareTo(final Node o) {
            return -Double.compare(this.value, o.value);
        }
    }

    public Minimax(Board start) {
        this.node = new Node(start);
        this.node.getChildren();
    }

    public int getMove(Board board) {
        for (Node child : this.node.getChildren()) {
            if (child.board.getLastMove() == board.getLastMove()) {
                this.node = child;
                break;
            }
        }
        alphaBeta(this.node, 10, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false);
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
            for (Node child : node.getChildren()) {
                node.value = Math.max(node.value, alphaBeta(child, depth-1, alpha, beta, false));
                alpha = Math.max(alpha, node.value);
                if (alpha >= beta) break;
            }
        } else {
            node.value = Double.POSITIVE_INFINITY;
            node.getChildren();
            for (Node child : node.getChildren()) {
                node.value = Math.min(node.value, alphaBeta(child, depth-1, alpha, beta, true));
                beta = Math.min(beta, node.value);
                if (beta <= alpha) break;
            }
        }
        return node.value;
    }

    private static double heuristic(Board board) {
        if (board.getWinner() == 0 && board.getMoves() < board.getCols()*board.getRows()) {
            double heuristic = 0.0;
            heuristic += board.numInRow(1) * Minimax.weight[0];
            heuristic += board.numInRow(2) * Minimax.weight[1];
            heuristic += board.numInRow(3) * Minimax.weight[2];
            return heuristic;
        } else if (board.getWinner() == 2) return 10000.0/board.getMoves();
        else if (board.getWinner() == 1) return -10000.0/board.getMoves();
        else return 0;
    }

}
