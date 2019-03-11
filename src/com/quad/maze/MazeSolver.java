package com.quad.maze;
/*
 * MazeSolver.java
 *
 * UVic CSC 115, Spring 2017
 *
 * Purpose:
 *   class that contains a single public static method called
 *   "findPath". To involve the method one must have already created
 *   an instance of Maze. The method must return a path through the
 *   maze (if it exists) in the format shown within the Assignment #3
 *   description.
 *
 * Note: You are free to add to this class whatever other methods will
 * help you in writing a solution to A#3 part 2.
 */

import com.quad.stack.StackEmptyException;
import com.quad.stack.StackRefBased;

public class MazeSolver {
	
	 //stack of positions for path
    private StackRefBased<MazeLocation> stack;
    private StackRefBased<MazeLocation> complete;
    
    //locations
    boolean locations[][];
    
    public MazeSolver() {
    	stack = new StackRefBased<MazeLocation>();
    	complete = new StackRefBased<MazeLocation>();
    }
	
    public String findPath(Maze maze) {
        String result = "";
        
        //locations of visited tiles
        locations = new boolean[maze.getNumRows()][maze.getNumCols()];
        
        //current location
        MazeLocation currentLocation;
        
        //push start to stack
        stack.push(maze.getEntry());
        
        
        try {        	
        	//loop until stack is not empty and stack top is not finish location
			while(!stack.isEmpty() && !stack.peek().equals(maze.getExit())) {
				
				//set current location to top of stack
				currentLocation = stack.peek();
				
				//set current location as visited
				locations[currentLocation.getRow()][currentLocation.getCol()] = true;
				
				//check if can move up
				if(currentLocation.getRow() != 0 && locations[currentLocation.getRow()-1][currentLocation.getCol()] == false
						&& !maze.isWall(currentLocation.getRow()-1, currentLocation.getCol())) {
					stack.push(new MazeLocation(currentLocation.getRow()-1,currentLocation.getCol()));
					complete.push(new MazeLocation(currentLocation.getRow()-1,currentLocation.getCol()));
				}
				//check if can move down
				else if(currentLocation.getRow() != maze.getNumRows()-1 && locations[currentLocation.getRow()+1][currentLocation.getCol()] == false
						&& !maze.isWall(currentLocation.getRow()+1, currentLocation.getCol())) {
					stack.push(new MazeLocation(currentLocation.getRow()+1,currentLocation.getCol()));
					complete.push(new MazeLocation(currentLocation.getRow()+1,currentLocation.getCol()));
				}
				//check if can move right
				else if(currentLocation.getCol() != maze.getNumCols()-1 && locations[currentLocation.getRow()][currentLocation.getCol()+1] == false
						&& !maze.isWall(currentLocation.getRow(), currentLocation.getCol()+1)) {
					stack.push(new MazeLocation(currentLocation.getRow(),currentLocation.getCol()+1));
					complete.push(new MazeLocation(currentLocation.getRow(),currentLocation.getCol()+1));
				}
				//check if can move left
				else if(currentLocation.getCol() != 0 && locations[currentLocation.getRow()][currentLocation.getCol()-1] == false
						&& !maze.isWall(currentLocation.getRow(), currentLocation.getCol()-1)) {
					stack.push(new MazeLocation(currentLocation.getRow(),currentLocation.getCol()-1));
					complete.push(new MazeLocation(currentLocation.getRow(),currentLocation.getCol()-1));
				}
				//if it can't move any direction go back steps until it can move
				else {
					stack.pop();
					}
			}
		} catch (StackEmptyException e) {
			e.printStackTrace();
		}
        
        //check whether there has been a path found
        if(stack.isEmpty()) {
        	result = "";
        }else {
        	result = stack.toString();
        }
        
        return result.trim();
    }
    
    public StackRefBased<MazeLocation> getLocations(){
    	return complete;
    }
    
    public StackRefBased<MazeLocation> getLocationsVisited(){
    	return stack;
    }
    
    public boolean[][] getVisited(){
    	return locations;
    }
    
  
}
