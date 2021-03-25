package main.java;

import java.util.Scanner;

public class PlayerInterface {
    static Scanner scanner = new Scanner(System.in);
    GameManager gameManager;

    public PlayerInterface(final int width, final int height) {
        gameManager = new GameManager(width, height);
    }

    public static void main(final String[] args) {
        System.out.print("What width would you like the maze to be? ");
        final int width = Integer.parseInt(scanner.nextLine());
        System.out.print("What height would you like the maze to be? ");
        final int height = Integer.parseInt(scanner.nextLine());
        final PlayerInterface playerInterface = new PlayerInterface(width, height);
        System.out.println("You are the player, P. Your goal is to exit the maze. You can only see a 5x5 portion of the maze at a time.");
        playerInterface.gameLoop();
    }

    public void spacer() {
        System.out.println("----------------------");
        System.out.println();
    }

    private void handleMove(final boolean result, final String direction, final String command) {
        final String[] move = command.split(" ");
        if (move.length > 1) {
            boolean hitWall = true;
            if (move[1].toUpperCase().equals("WALL")) {
                while (hitWall) hitWall = gameManager.lastMove();
            } else {
                try {
                    if (Integer.parseInt(move[1]) > 1) {
                        for (int i = 0; i < Integer.parseInt(move[1]); i++) {
                            if (hitWall) hitWall = gameManager.lastMove();
                            else break;
                        }
                    }
                } catch (final NumberFormatException e) {
                    System.out.println("Please add a valid command after the direction.");
                }
            }
        }
        if (!result) System.out.println("You run into the wall of the maze, hitting your head.");
        else System.out.println("You move " + direction + " in the maze.");
    }

    public void gameLoop() {
        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("VIEW OF MAZE");
            spacer();
            final char[][] view = gameManager.playerView();
            for (int y = 0; y < view[0].length; y++) {
                for (int x = 0; x < view.length * 2; x++) {
                    if (x % 2 == 0) {
                        System.out.print(view[x / 2][y]);
                    } else System.out.print(" ");
                }
                System.out.println();
            }
            spacer();
            System.out.println("What would you like to do?");
            System.out.println("Enter \"UP\" to move upwards.");
            System.out.println("Enter \"DOWN\" to move downwards.");
            System.out.println("Enter \"LEFT\" to move to the left.");
            System.out.println("Enter \"RIGHT\" to move to the right.");
            System.out.println("Enter \"QUIT\" to quit the maze.");
            System.out.println("To move multiple spaces at once, add a number after the directional command or add \"WALL\" to move to the next wall.");
            final String command = scanner.nextLine();
            switch (command.toUpperCase().split(" ")[0]) {
                case "UP":
                    handleMove(gameManager.moveUp(), "upwards", command);
                    break;
                case "DOWN":
                    handleMove(gameManager.moveDown(), "downwards", command);
                    break;
                case "LEFT":
                    handleMove(gameManager.moveLeft(), "to the left", command);
                    break;
                case "RIGHT":
                    handleMove(gameManager.moveRight(), "to the right", command);
                    break;
                case "QUIT":
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
            if (gameManager.playerX == 1 && gameManager.playerY == 1) {
                System.out.println("You have found the exit! Congratulations on escaping the maze.");
                gameRunning = false;
            }
            spacer();
        }
    }
}
