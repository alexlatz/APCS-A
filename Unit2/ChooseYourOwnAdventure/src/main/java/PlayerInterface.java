package main.java;

import java.util.Scanner;

public class PlayerInterface {
    static Scanner scanner = new Scanner(System.in);
    GameManager gameManager;
    public PlayerInterface(int width, int height) {
        gameManager = new GameManager(width, height);
    }
    public void spacer() {
        System.out.println("----------------------");
        System.out.println();
    }
    private void handleMove(boolean result, String direction) {
        if (!result) System.out.println("You run into the wall of the maze, hitting your head.");
        else System.out.println("You move " + direction + " in the maze.");
    }
    public void gameLoop() {
        boolean gameRunning = true;
        while(gameRunning) {
            System.out.println("VIEW OF MAZE");
            spacer();
            char[][] view = gameManager.playerView();
            for (int y = 0; y < view[0].length; y++) {
                for (int x = 0; x < view.length*2; x++) {
                    if (x % 2 == 0) System.out.print(view[x/2][y]);
                    else System.out.print(" ");
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
            String command = scanner.nextLine();
            switch (command.toUpperCase()) {
                case "UP":
                    handleMove(gameManager.moveUp(), "upwards");
                    break;
                case "DOWN":
                    handleMove(gameManager.moveDown(), "downwards");
                    break;
                case "LEFT":
                    handleMove(gameManager.moveLeft(), "to the left");
                    break;
                case "RIGHT":
                    handleMove(gameManager.moveRight(), "to the right");
                    break;
                case "QUIT":
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
            spacer();
        }
    }

    public static void main(String[] args) {
        System.out.print("What width would you like the maze to be? ");
        int width = Integer.parseInt(scanner.nextLine());
        System.out.print("What height would you like the maze to be? ");
        int height = Integer.parseInt(scanner.nextLine());
        PlayerInterface playerInterface = new PlayerInterface(width, height);
        System.out.println("You are the player, P. Your goal is to exit the maze. You can only see a 5x5 portion of the maze at a time.");
        playerInterface.gameLoop();
    }
}
