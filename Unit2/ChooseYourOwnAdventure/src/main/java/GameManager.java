package main.java;

public class GameManager {
    public char[][] maze;
    public int playerX, playerY;
    public GameManager(int width, int height) {
        MazeGenerator mazeGenerator = new MazeGenerator(width, height);
        maze = mazeGenerator.getMaze();
        playerX = width*2-1;
        playerY = height*2-1;
        maze[playerX][playerY] = 'P';
        maze[playerX][playerY+1] = ' ';
        maze[1][1] = 'G';
    }
    public boolean moveUp() {
        if (!MazeGenerator.isWall(maze[playerX][playerY-1])) {
            maze[playerX][playerY--] = ' ';
            maze[playerX][playerY] = 'P';
            return true;
        } else return false;
    }
    public boolean moveDown() {
        if (!MazeGenerator.isWall(maze[playerX][playerY+1])) {
            maze[playerX][playerY++] = ' ';
            maze[playerX][playerY] = 'P';
            return true;
        } else return false;
    }
    public boolean moveLeft() {
        if (!MazeGenerator.isWall(maze[playerX-1][playerY])) {
            maze[playerX--][playerY] = ' ';
            maze[playerX][playerY] = 'P';
            return true;
        } else return false;
    }
    public boolean moveRight() {
        if (!MazeGenerator.isWall(maze[playerX+1][playerY])) {
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
