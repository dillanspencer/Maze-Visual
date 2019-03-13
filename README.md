# Maze Backtracking Visualization
> Maze solver using backtracking algorithm

The __Maze Backtracking Visualization__ is created using the __Quad Engine__

The program takes in a 2D array of map data and uses it to visualize the maze. This solver uses a __Stack__ based backtracking algorithm to solve the maze. For more information on __backtracking__ , see below.

## Maze Classes
* #### __Maze__
>  * Reads file and implements values into 2D array of data
>  * Holds locations of __Entry__ of maze and __Exit__ of maze
>  * Renders map data to the screen

* #### __Maze Location__
>  * Holds information of __Row__ and __Column__ of map
>  * Holds information of __isWall__

* #### __Maze Solver__
>  * Implements backtracking algorithm

## Backtracking Algorithm

Backtracking is a general algorithm for finding all solutions to some computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate as soon as it determines that the candidate cannot possibly be completed to a valid solution.

In this program I used a stack based backtracking algorithm where I keep track of visited locations on the maze and for every non visited location I check whether it is possible to move in one of the four directions. 

__Keep track of locations visited so far with a 2D-boolean array
Push the start location onto a new stack
While (the stack is not empty && stack top is not the finish location)
 Use the location at the top of the stack as the current location
 Mark the current location as visited
 If there is an unvisited location next to the current location
 Push the unvisited location onto stack, and continue the loop
 Else
 Pop the stack (i.e., remove current location as it is top of stack
If stack is empty
 There is no path from start to finish
Else
 Stack contains path from finish to end__
 <hr>
 <a href="https://imgflip.com/gif/2vz93d"><img src="https://i.imgflip.com/2vz93d.gif" title="made at imgflip.com"/></a>

