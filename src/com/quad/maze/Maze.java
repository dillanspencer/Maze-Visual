package com.quad.maze;
/*
 * Maze.java
 *
 * For UVic CSC 115, Spring 2017.
 *
 * Used to read in maze data from plain-text files, store the maze in
 * a some useful representation that permits querying the state of the
 * maze.
 *
 * This class makes use of MazeLocation -- itself just a way of
 * encapsulating together a (row, column) pair that can reference some
 * position in the maze. Note that some of the methods in Maze.java 
 * take a MazeLocation as a parameter and return a MazeLocation as a
 * result, while some other Maze methods accept as parameters a pair
 * of ints (i.e., row, column).
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.quad.core.GameContainer;
import com.quad.core.Renderer;
import com.quad.core.fx.ShadowType;

public class Maze {
    private MazeLocation entry;
    private MazeLocation exit;
    private boolean[][] map;
    
    //map
    private int numRows;
    private int numCols;
    
    // drawing
 	private int rowOffset;
 	private int colOffset;
 	private int numRowsToDraw;
 	private int numColsToDraw;
 	private int tileSize;

    /*
     * Read in the contents of a plaintext file storing information
     * about a two-dimensional maze. The format of the file is
     * given in the Assignment #3 description.
     */
    public Maze(GameContainer gc, String filepath) {
    	
        try {
            File infile = new File(filepath);
            Scanner instream = new Scanner(infile);

            numRows = instream.nextInt();
            numCols = instream.nextInt();
            
            //drawing
        	tileSize = (gc.getWidth()/numCols);
        	numRowsToDraw = gc.getHeight() / tileSize;
    		numColsToDraw = gc.getWidth() / tileSize;
            
            map = new boolean[numRows][numCols];
    
            entry = new MazeLocation(instream.nextInt(),
                instream.nextInt()
            );
            exit = new MazeLocation(instream.nextInt(),
                instream.nextInt()
            ); 

            instream.nextLine();

            for (int i = 0; i < numRows; i++) {
                String line = instream.nextLine();
                for (int j = 0; j < numCols; j++) {
                    map[i][j] = line.charAt(j) == '*';
                }
            }
        }
        catch (IOException ioe) {
            System.err.println("Something went awry when reading"
                + " maze file.");
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Something went awry when constructing"
                + " the Maze object.");
            System.exit(1);
        }
    }
    
    public void render(GameContainer gc, Renderer r) {
    	for(int row = 0; row < rowOffset + numRowsToDraw; row++) {
    		
			if(row >= numRows) break;
			
			for(int col = colOffset; col < colOffset + numColsToDraw; col++) {
				
				if(col >= numCols) break;
				
				if(map[row][col]) {
					r.drawFillRect((col*tileSize), 1+row*tileSize, tileSize-2, tileSize-2, 0xe52f0b, ShadowType.NONE);
				}
				else {
					//check if start
					if(getEntry().equals(new MazeLocation(row, col))) {
						r.drawRect((col*tileSize), 1+row*tileSize, tileSize-2, tileSize-2, 0x06e248, ShadowType.NONE);
					}
					else if(getExit().equals(new MazeLocation(row, col))) {
						r.drawRect((col*tileSize), 1+row*tileSize, tileSize-2, tileSize-2, 0x0817dd, ShadowType.NONE);
					}
					else
					r.drawRect((col*tileSize), 1+row*tileSize, tileSize-2, tileSize-2, 0xffffff, ShadowType.NONE);
				}
				
			}
			
		}
    }


    /*
     * Purpose:
     *  return the MazeLocation (i.e., (row, column) pair)
     *  corresponding to the entry location of the maze.
     */

    public MazeLocation getEntry() {
        return entry;
    }


    /*
     * Purpose:
     *  return the MazeLocation (i.e., (row, column) pair)
     *  corresponding to the exit location of the maze.
     */
    public MazeLocation getExit() {
        return exit;
    }


    /*
     * Purpose:
     *   return the "height" of the 2D maze (i.e., the number of rows
     *   in the textual representation of the maze).
     */
    public int getNumRows() {
        return map.length;
    }


    /*
     * Purpose:
     *   return the "width" of the 2D maze (i.e., the number of
     *   columns in the textual representation of the maze).
     */
    public int getNumCols() {
        return map[0].length;
    }


    /*
     * Purpose:
     *   return false if there is ** no wall ** at the row and column
     *   provided as parameters; returns true otherwise. Note that the
     *   method treats out-of-bound rows and columns as being
     *   locations that "have walls".
     */
    public boolean isWall(int row, int col) {
        if (row < 0 || map.length <= row) {
            return true;
        }

        if (col < 0 || map[0].length <= col) {
            return true;
        }

        return map[row][col];
    }


    /*
     * Purpose:
     *   return the string representation of the maze itself, where
     *   a (row,column) spot without a wall is represented by space,
     *   and a (row,column) spot with a wall represented by an
     *   asterisk ('*'). The entry/exit locations are implied by
     *   spaces shown on the outermost walls, that is, the string
     *   representation does not just echo the information that
     *   appears in the plain-text maze file.
     */
    public String toString() {
        String result = "";

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                result += (map[i][j] ? "*" : " ");
            }
            result += "\n";
        }

        return result;
    }
    
    public int getTileSize() {
    	return tileSize;
    }
}
