package main.java;

import java.util.ArrayList;

public class MazeGenerator {
    boolean[][] adjMatrix;
    boolean[] visited;
    int v;
    int width;
    char[][] charMaze;
    public MazeGenerator(int width, int height) {
        v = width * height;
        this.width = width;
        adjMatrix = new boolean[v][v];
        visited = new boolean[v];
        dfs(0);
        charMaze = charMaze();
        modifyMaze();
    }
    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }
    private char[][] charMaze() {
        //┌ ┬ ┐ —
        //├ ┼ ┤
        //└ ┴ ┘
        char[][] maze = new char[width*2+1][(v/width)*2+1];
        maze[0][0] = '┌';
        maze[maze.length-1][0] = '┐';
        maze[0][maze[0].length-1] = '└';
        maze[maze.length-1][maze[0].length-1] = '┘';
        for (int i = 1; i < maze.length-1; i++) {
            maze[i][0] = '—';
            maze[i][maze[0].length-1] = '—';
        }
        for (int y = 1; y < maze[0].length-1; y++) {
            maze[0][y] = '|';
            maze[maze.length-1][y] = '|';
            if (y % 2 != 0) {
                for (int x = 1; x < maze.length - 1; x++) {
                    if (x % 2 != 0) maze[x][y] = ' ';
                    else {
                        int xVal = (x/2)-1;  //2->0, 4->1
                        int yVal = ((y-1)/2) * width;  //1->0, 3->1, 5->2
                        int xValT = x/2;  //2->1, 4->2
                        if (adjMatrix[yVal + xVal][yVal + xValT]) maze[x][y] = ' ';
                        else maze[x][y] = '|';
                    }
                }
            } else {
                for (int x = 1; x < maze.length - 1; x++) {
                    if (x % 2 != 0) {
                        int xVal = (x-1)/2;  //1->0, 3->1, 5->2
                        int yVal = ((y/2)-1) * width;  //2->0, 4->1
                        int yValT = (y/2) * width; //2->1, 4->2
                        if (adjMatrix[yVal + xVal][yValT + xVal]) maze[x][y] = ' ';
                        else maze[x][y] = '—';
                    } else maze[x][y] = '—';
                }
            }
        }
        return maze;
    }
    private void modifyMaze() {
        for (int i = 2; i < charMaze.length-1; i+=2) {
            if (charMaze[i][1] == '|') charMaze[i][0] = '┬';
            if (charMaze[i][charMaze[0].length-2] == '|') charMaze[i][charMaze[0].length-1] = '┴';
        }
        for (int i = 2; i < charMaze[0].length-1; i+=2) {
            if (charMaze[1][i] == '—') charMaze[0][i] = '├';
            if (charMaze[charMaze.length-2][i] == '—') charMaze[charMaze.length-1][i] = '┤';
        }
    }
    public char[][] getCharMaze() {
        return charMaze;
    }
    private void dfs(int n) {
        visited[n] = true;
        int i = neighbor(n);
        while (i != -1) {
            adjMatrix[i][n] = true;
            adjMatrix[n][i] = true;
            dfs(i);
            i = neighbor(n);
        }
    }
    private int neighbor(int n) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        if (n >= width && !visited[n-width]) neighbors.add(n-width);
        if (n % width != 0 && !visited[n-1]) neighbors.add(n-1);
        if (n % width != width-1 && !visited[n+1]) neighbors.add(n+1);
        if (n < v - width && !visited[n+width]) neighbors.add(n+width);
        if (neighbors.size() == 0) return -1;
        return neighbors.get((int)(Math.random() * (double)neighbors.size()));
    }

    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(10, 10);
        char[][] maze = mazeGenerator.getCharMaze();
        for (int y = 0; y < maze[0].length; y++) {
            for (int x = 0; x < maze.length; x++) {
                System.out.print(maze[x][y]);
            }
            System.out.println();
        }
    }
}
