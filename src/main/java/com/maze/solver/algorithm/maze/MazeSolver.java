package com.maze.solver.algorithm.maze;

public class MazeSolver {
    //0 = wall
    //1 = path
    //2 = destination


        private static int[][] copy_array(int[][] arr){
            int[][]arrayOut=new int[arr.length][arr[0].length];

            for (int i=0;i<arr.length;i++){
                for (int j=0;j<arr[i].length;j++){
                arrayOut[i][j] = arr[i][j];
                }
            }

            return arrayOut;
        }

        public static int[][] maze_solve(int[][] arrMazeStart, int x,int y){

            Maze m = new Maze();

            int[][] arrMazeFinish=copy_array(arrMazeStart);
                    /*{
                            {1, 1, 1, 1, 1, 1},
                            {1, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 0, 1},
                            {1, 0, 1, 0, 0, 2},
                            {1, 1, 1, 1, 1, 1} };
*/
            m.setMaze(arrMazeStart);
            m.setStart(new Position(y,x));

            if(solveMaze(m)) {
                System.out.println("You won!");
            } else {
                System.out.println("No path");
            }

            for (Position position:m.getPath()){
                arrMazeFinish[position.getY()][position.getX()]=4;
            }
            arrMazeFinish[y][x]=3;
            return arrMazeFinish;
        }


    private static boolean solveMaze(Maze m) {
        Position p = m.getStart();
        m.getPath().push(p);

        while(true) {
            int y = m.getPath().peek().getY();
            int x = m.getPath().peek().getX();
            m.getMaze()[y][x] = 1;

            //up
            if(isValid(y-1, x, m)) {
                if(m.getMaze()[y-1][x] == 2) {
                    System.out.println("Moved up");
                    return true;
                } else if(m.getMaze()[y-1][x] == 0) {
                    System.out.println("Moved up");
                    m.getPath().push(new Position(y-1, x));
                    continue;
                }
            }
            //right
            if(isValid(y, x+1, m)) {
                if(m.getMaze()[y][x+1] == 2) {
                    System.out.println("Moved right");
                    return true;
                } else if(m.getMaze()[y][x+1] == 0) {
                    System.out.println("Moved right");
                    m.getPath().push(new Position(y, x+1));
                    continue;
                }
            }
            //down
            if(isValid(y+1, x, m)) {
                if(m.getMaze()[y+1][x] == 2) {
                    System.out.println("Moved down");
                    return true;
                } else if(m.getMaze()[y+1][x] == 0) {
                    System.out.println("Moved down");
                    m.getPath().push(new Position(y+1, x));
                    continue;
                }
            }
            //left
            if(isValid(y, x-1, m)) {
                if(m.getMaze()[y][x-1] == 2) {
                    System.out.println("Moved left");
                    return true;
                } else if(m.getMaze()[y][x-1] == 0) {
                    System.out.println("Moved left");
                    m.getPath().push(new Position(y, x-1));
                    continue;
                }
            }

            m.getPath().pop();
            System.out.println("Moved back");
            if(m.getPath().size() <= 0) {
                return false;
            }
        }
    }

    private static boolean isValid(int y, int x, Maze m) {
        if(y < 0 ||
                y >= m.getMaze().length ||
                x < 0 ||
                x >= m.getMaze()[y].length
        ) {
            return false;
        }
        return true;
    }

}