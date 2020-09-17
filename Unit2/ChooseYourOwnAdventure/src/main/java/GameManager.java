package main.java;

public class GameManager {
    public char[][] maze;
    public int playerX, playerY;
    public GameManager(int width, int height) {
        MazeGenerator mazeGenerator = new MazeGenerator(width, height);
        maze = mazeGenerator.getCharMaze();
        for (int y = maze[0].length-1; y >= 0; y--) {
            for (int x = 0; x < maze.length; x++) {
                if (maze[x][y] == ' ') {
                    playerX = x;
                    playerY = y;
                    break;
                }
            }
            if (playerX != 0 || playerY != 0) break;
        }
        maze[playerX][playerY] = 'P';
    }
    public boolean moveUp() {
        if (maze[playerX][playerY-1]!= '#') {
            maze[playerX][playerY--] = ' ';
            maze[playerX][playerY] = 'P';
            return true;
        } else return false;
    }
    public boolean moveDown() {
        if (maze[playerX][playerY+1]!= '#') {
            maze[playerX][playerY++] = ' ';
            maze[playerX][playerY] = 'P';
            return true;
        } else return false;
    }
    public boolean moveLeft() {
        if (maze[playerX-1][playerY]!= '#') {
            maze[playerX--][playerY] = ' ';
            maze[playerX][playerY] = 'P';
            return true;
        } else return false;
    }
    public boolean moveRight() {
        if (maze[playerX+1][playerY]!= '#') {
            maze[playerX++][playerY] = ' ';
            maze[playerX][playerY] = 'P';
            return true;
        } else return false;
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
