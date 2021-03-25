package main.java;

import java.util.ArrayList;

public class MazeGenerator {
    boolean[][] adjMatrix;
    boolean[] visited;
    int v;
    int width;
    char[][] maze;

    public MazeGenerator(final int width, final int height) {
        v = width * height;
        this.width = width;
        adjMatrix = new boolean[v][v];
        visited = new boolean[v];
        dfs(width * height - 1);
        maze = maze();
        modifyMaze();
    }

    public static boolean isWall(final char c) {
        return c == '—' || c == '|' || c == '┌' || c == '┬' || c == '┐' || c == '├' ||
                c == '┼' || c == '┤' || c == '└' || c == '┴' || c == '┘';
    }

    public static void main(final String[] args) {
        final MazeGenerator mazeGenerator = new MazeGenerator(10, 10);
        final char[][] maze = mazeGenerator.getMaze();
        for (int y = 0; y < maze[0].length; y++) {
            for (final char[] chars : maze) {
                System.out.print(chars[y]);
            }
            System.out.println();
        }
    }

    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }

    private char[][] maze() {
        //┌ ┬ ┐ —
        //├ ┼ ┤
        //└ ┴ ┘
        final char[][] maze = new char[width * 2 + 1][(v / width) * 2 + 1];
        maze[0][0] = '┌';
        maze[maze.length - 1][0] = '┐';
        maze[0][maze[0].length - 1] = '└';
        maze[maze.length - 1][maze[0].length - 1] = '┘';
        for (int i = 1; i < maze.length - 1; i++) {
            maze[i][0] = '—';
            maze[i][maze[0].length - 1] = '—';
        }
        for (int y = 1; y < maze[0].length - 1; y++) {
            maze[0][y] = '|';
            maze[maze.length - 1][y] = '|';
            if (y % 2 != 0) {
                for (int x = 1; x < maze.length - 1; x++) {
                    if (x % 2 != 0) maze[x][y] = ' ';
                    else {
                        final int xVal = (x / 2) - 1;  //2->0, 4->1
                        final int yVal = ((y - 1) / 2) * width;  //1->0, 3->1, 5->2
                        final int xValT = x / 2;  //2->1, 4->2
                        if (adjMatrix[yVal + xVal][yVal + xValT]) maze[x][y] = ' ';
                        else maze[x][y] = '|';
                    }
                }
            } else {
                for (int x = 1; x < maze.length - 1; x++) {
                    if (x % 2 != 0) {
                        final int xVal = (x - 1) / 2;  //1->0, 3->1, 5->2
                        final int yVal = ((y / 2) - 1) * width;  //2->0, 4->1
                        final int yValT = (y / 2) * width; //2->1, 4->2
                        if (adjMatrix[yVal + xVal][yValT + xVal]) maze[x][y] = ' ';
                        else maze[x][y] = '—';
                    } else maze[x][y] = '—';
                }
            }
        }
        return maze;
    }

    private void modifyMaze() {
        for (int i = 2; i < maze.length - 1; i += 2) {
            if (maze[i][1] == '|') maze[i][0] = '┬';
            if (maze[i][maze[0].length - 2] == '|') maze[i][maze[0].length - 1] = '┴';
        }
        for (int i = 2; i < maze[0].length - 1; i += 2) {
            if (maze[1][i] == '—') maze[0][i] = '├';
            if (maze[maze.length - 2][i] == '—') maze[maze.length - 1][i] = '┤';
        }
        for (int y = 1; y < maze[0].length - 1; y++) {
            for (int x = 1; x < maze.length - 1; x++) {
                if (isWall(maze[x][y])) {
                    if (isWall(maze[x][y - 1])) {
                        checkUp(x, y);
                    } else if (isWall(maze[x][y + 1])) {
                        checkDown(x, y);
                    } else maze[x][y] = '—';
                }
            }
        }
    }

    private void checkUp(final int x, final int y) {
        if (isWall(maze[x][y + 1])) {
            if (isWall(maze[x - 1][y])) {
                if (isWall(maze[x + 1][y])) maze[x][y] = '┼';
                else maze[x][y] = '┤';
            } else if (isWall(maze[x + 1][y])) maze[x][y] = '├';
            else maze[x][y] = '|';
        } else if (isWall(maze[x - 1][y])) {
            if (isWall(maze[x + 1][y])) maze[x][y] = '┴';
            else maze[x][y] = '┘';
        } else if (isWall(maze[x + 1][y])) maze[x][y] = '└';
        else maze[x][y] = '|';
    }

    private void checkDown(final int x, final int y) {
        if (isWall(maze[x - 1][y])) {
            if (isWall(maze[x + 1][y])) maze[x][y] = '┬';
            else maze[x][y] = '┐';
        } else if (isWall(maze[x + 1][y])) maze[x][y] = '┌';
        else maze[x][y] = '|';
    }

    public char[][] getMaze() {
        return maze;
    }

    private void dfs(final int n) {
        visited[n] = true;
        int i = neighbor(n);
        while (i != -1) {
            adjMatrix[i][n] = true;
            adjMatrix[n][i] = true;
            dfs(i);
            i = neighbor(n);
        }
    }

    private int neighbor(final int n) {
        final ArrayList<Integer> neighbors = new ArrayList<>();
        if (n >= width && !visited[n - width]) neighbors.add(n - width);
        if (n % width != 0 && !visited[n - 1]) neighbors.add(n - 1);
        if (n % width != width - 1 && !visited[n + 1]) neighbors.add(n + 1);
        if (n < v - width && !visited[n + width]) neighbors.add(n + width);
        if (neighbors.size() == 0) return -1;
        return neighbors.get((int) (Math.random() * (double) neighbors.size()));
    }
}
