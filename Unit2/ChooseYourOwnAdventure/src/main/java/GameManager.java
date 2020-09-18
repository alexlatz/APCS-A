package main.java;

import java.util.ArrayList;

public class GameManager {
    public char[][] maze;
    public int playerX, playerY;
    public ArrayList<Integer> trapX;
    public ArrayList<Integer> trapY;
    private char lastMove = ' ';
    public GameManager(int width, int height) {
        MazeGenerator mazeGenerator = new MazeGenerator(width, height);
        maze = mazeGenerator.getMaze();
        playerX = width*2-1;
        playerY = height*2-1;
        maze[playerX][playerY] = 'P';
        maze[playerX][playerY+1] = ' ';
        maze[1][1] = 'G';
        maze[1][0] = ' ';
        //🙂😵🕸🕷🦴➵🏹
    }
    public boolean moveUp() {
        if (!MazeGenerator.isWall(maze[playerX][playerY-1])) {
            maze[playerX][playerY--] = ' ';
            maze[playerX][playerY] = 'P';
            lastMove = 'u';
            return true;
        } else return false;
    }
    public boolean moveDown() {
        if (!MazeGenerator.isWall(maze[playerX][playerY+1])) {
            maze[playerX][playerY++] = ' ';
            maze[playerX][playerY] = 'P';
            lastMove = 'd';
            return true;
        } else return false;
    }
    public boolean moveLeft() {
        if (!MazeGenerator.isWall(maze[playerX-1][playerY])) {
            maze[playerX--][playerY] = ' ';
            maze[playerX][playerY] = 'P';
            lastMove = 'l';
            return true;
        } else return false;
    }
    public boolean moveRight() {
        if (!MazeGenerator.isWall(maze[playerX+1][playerY])) {
            maze[playerX++][playerY] = ' ';
            maze[playerX][playerY] = 'P';
            lastMove = 'r';
            return true;
        } else return false;
    }
    public boolean lastMove() {
        switch (lastMove) {
            case 'u':
                return moveUp();
            case 'd':
                return moveDown();
            case 'l':
                return moveLeft();
            case 'r':
                return moveRight();
        }
        return false;
    }
    public char[][] playerView() {
        char[][] view = new char[5][5];
        int newX=0, newY=0;
        for (int y = playerY-2; y <= playerY+2; y++) {
            for (int x = playerX-2; x <= playerX+2; x++) {
                if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) view[newX++][newY] = ' ';
                else view[newX++][newY] = maze[x][y];
            }
            newY++;
            newX = 0;
        }
        return view;
    }
}
