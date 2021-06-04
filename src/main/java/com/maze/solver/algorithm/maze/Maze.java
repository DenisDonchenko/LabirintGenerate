package com.maze.solver.algorithm.maze;

import java.util.Arrays;
import java.util.LinkedList;

public class Maze {
    private int[][] maze;
    private LinkedList<Position> path = new LinkedList<Position>();
    private Position start;

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public LinkedList<Position> getPath() {
        return path;
    }

    public void setPath(LinkedList<Position> path) {
        this.path = path;
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return  "Path - " + path +"; Start position in maze - " + start + ';';
    }
}
