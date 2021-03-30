import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private Board board;
    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
        startGame();
    }

    public void startGame() {
        System.out.println("Welcome to Connect 4!");
        int rows = -1;
        int cols = -1;
        while (true) {
            System.out.println("How many rows would you like? (Enter 0 for the default: 6, minimum: 4)");
            try {
                rows = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            if (rows > 4 || rows == 0) break;
            else System.out.println("Please enter either 0 or a number greater than 4.");
        }
        while (true) {
            System.out.println("How many columns would you like? (Enter 0 for the default: 7, minimum: 4)");
            try {
                cols = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            if (cols > 4 || cols == 0) break;
            else System.out.println("Please enter either 0 or a number greater than 4.");
        }
        if (rows == 0) rows = 6;
        if (cols == 0) cols = 7;
        gameLoop(rows, cols);
    }

    private void gameLoop(int rows, int cols) {
        this.board = new Board(rows, cols);
        boolean turn = true;
        while (board.getMoves() < rows*cols && board.getWinner() == 0) {
            System.out.println("The board:");
            printBoard();
            board.placeMarker(columnSelection(turn ? 1 : 2), turn);
            turn = !turn;
        }
        if (board.getWinner() != 0) System.out.println("Player " + board.getWinner() + " wins!");
        else System.out.println("The board is full! Game Over");
        printBoard();
    }

    private int columnSelection(int playerNum) {
        int col = -1;
        while (true) {
            System.out.println("Player " + playerNum + ": Enter a column number to place your piece.");
            try {
                col = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            if (col <= board.getCols() && col > 0) break;
            else System.out.println("Please enter a valid column number.");
        }
        return col;
    }

    private void printBoard() {
        byte[][] arr = board.getBoard();
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                System.out.print("├");
                System.out.print(" — ┼".repeat(board.getCols() - 1));
                System.out.print(" — ┤");
            } else {
                for (int j = 1; j <= board.getCols(); j++) {
                    System.out.print("  " + j + " ");
                }
                System.out.println();
                System.out.print("┌");
                System.out.print(" — ┬".repeat(board.getCols() - 1));
                System.out.print(" — ┐");
            }
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < board.getCols(); j++) {
                switch (arr[i][j]) {
                    case 1:
                        System.out.print(" 1");
                        break;
                    case 2:
                        System.out.print(" 2");
                        break;
                    default:
                        System.out.print("  ");
                }
                System.out.print(" |");
            }
            System.out.println();
        }
        System.out.print("└");
        System.out.print(" — ┴".repeat(board.getCols() - 1));
        System.out.print(" — ┘");
        System.out.println();
    }

    public static void main(String[] args) {
        UI ui = new UI();
    }
}
