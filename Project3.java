//Jude Hayes
//Project 3
//8/17/2021

import java.util.*;
import java.io.*;

public class Project3 {

    int problemCount; //Stores number of problems given in the text file
    ArrayList<problemContainer> problemArray = new ArrayList<>();//Stores all information for each problem

    public static void main(String[] args) {
        Project3 p3 = new Project3();//Creates new instance of main class
        for (int i = 0; i < p3.problemCount; i++) {//builds the grid for each problem and prints the minimum amount of jumps
            p3.buildGrid(p3.problemArray.get(i));
            p3.problemArray.get(i).printMinimumJumps();
        }
    }

    Project3() {
        Scanner scanner = null;//creates instance of Scanner
        try {//attempts to open input file, if it fails, exit the program
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.exit(1);
        }

        problemCount = scanner.nextInt();//Reads in and stores number of problems given in the text file
        for (int i = 0; i < problemCount; i++)//Constructs a problem object for each problem
            problemArray.add(new problemContainer(scanner));
    }

    void buildGrid(problemContainer currentProblem) {//Builds the a grid used to solve the problem with dynamic program
        for (int currentPosition = 2; currentPosition < currentProblem.pathSize; currentPosition++) {//Checks each position of the path, starting at 2, since 0 and 1 are base cases
            for (int previousPosition = 0; previousPosition < currentPosition; previousPosition++) {//Checks each previous position until it is equal to the current position
                if (currentProblem.canReachPosition(currentPosition, previousPosition))//Checks if any of the previous positions can jump the current position
                    currentProblem.addJump(currentPosition, previousPosition);//The minimum amount of jumps it takes to the current position there is added to the grid
            }
            currentProblem.sortGrid(currentPosition); //Sorts the current array in the grid in order to put the minimum amount of leaps needed to get to the current position at index 0
        }
    }
}