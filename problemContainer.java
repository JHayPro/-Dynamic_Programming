//Jude Hayes
//Project 3
//8/17/2021

import java.util.*;

public class problemContainer {

    ArrayList<Integer> path = new ArrayList<>();//stores the path given in the text file
    ArrayList<ArrayList<Integer>> grid = new ArrayList<>();//The grid used for dynamic programming
    int pathSize;//Stores the amount of positions in the graph

    problemContainer(Scanner _scanner) {
        pathSize = _scanner.nextInt();//Reads in the amount of positions in the path
        for (int i = 0; i < pathSize; i++) {
            path.add(_scanner.nextInt());//Adds the jump distance of each position to the path
            grid.add(new ArrayList<>());//Initializes the second dimension to the array
        }
        //Base cases of the dynamic programming grid
        grid.get(0).add(0);//It will always take 0 jumps to be at the starting position
        grid.get(1).add(1);//Since position 0 is the only way to reach position 1 and position 0 can always jump a distance of at least 1,
                            //or the problem is invalid, position 1 will always take one jump to get to
    }

    void addJump(int currentPosition, int previousPosition) {//Adds the minimum amount of jumps to get to the current position of the grid from the previous one to the grid
        grid.get(currentPosition).add(grid.get(previousPosition).get(0) + 1);
    }

    void sortGrid(int currentPosition) {//Sorts the amount of jumps needed to get to the current position of the grid, so index 0 is minimum amount of jumps needed to reach the current position
        Collections.sort(grid.get(currentPosition));
    }

    boolean canReachPosition(int currentPosition, int previousPosition) {//Checks if the frog can jump from the previous position to the current position
        return (currentPosition - previousPosition) <= path.get(previousPosition);//Checks if the distance between the current and previous position
    }                                                                               //is <= than the distance the frog can jump from the previous position

    void printMinimumJumps() {//Prints the minimum amount of jumps needed to reach the last position on the path
        System.out.println(grid.get(pathSize - 1).get(0));
    }
}