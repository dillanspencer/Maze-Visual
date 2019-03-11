package com.quad.state;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.quad.core.GameContainer;
import com.quad.core.Renderer;
import com.quad.core.components.State;
import com.quad.core.fx.ShadowType;
import com.quad.maze.Maze;
import com.quad.maze.MazeLocation;
import com.quad.maze.MazeSolver;
import com.quad.stack.StackNode;
import com.quad.stack.StackRefBased;

public class MazeTest extends State{
	
	//maze
	private Maze maze;
	private MazeSolver solver;
	
	//animation attributes
	private int tick;
	private int count;
	private int speed;
	
	//renderer
	private boolean renderPath;
	
	//file
	private String fileName;

	@Override
	public void init(GameContainer gc) {
		
		System.out.println("State Enter: MazeTest");
		System.out.println("Click screen and press Enter to start!");
		
		fileName = getFileName();
		maze = new Maze(gc, "tests/maze" + fileName + ".txt");
		
		solver = new MazeSolver();
		solver.findPath(maze);
		
		tick = 0;
		count = 0;
		
		//change speed here
		speed = 10;
		
		renderPath = false;
	}

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyPressed(KeyEvent.VK_ENTER)) renderPath = true;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		maze.render(gc, r);
		
		if(renderPath) renderPath(r);
		
	}

	@Override
	public void dipose() {
		// TODO Auto-generated method stub
		
	}
	
	//gets input of filename from user
	private String getFileName() {
		String s = "";
		s = JOptionPane.showInputDialog("Enter Maze Number: (0-10) ex// 01 or 09");
		
		//loop until user enters right info
		while(!s.equals("00") && !s.equals("01") && !s.equals("02") && !s.equals("03") && !s.equals("04") && !s.equals("05") 
				&& !s.equals("06") && !s.equals("07") && !s.equals("08") && !s.equals("09") && !s.equals("10")) {
			JOptionPane.showMessageDialog(null, "Enter number in correct format");
			s = JOptionPane.showInputDialog("Enter Maze Number: (0-10) ex// 01 or 09");
		}
		
		return s;
	}
	
	private void renderPath(Renderer r) {
		StackRefBased<MazeLocation> stack = solver.getLocations();
		StackRefBased<MazeLocation> visit = solver.getLocationsVisited();
		
		stack = stack.reverseStack();
		visit = visit.reverseStack();
		
		int col = 0;
		int row = 0;
		
		//draw entry point
		r.drawFillRect(1+(maze.getEntry().getCol() * maze.getTileSize()), 2+maze.getEntry().getRow()*maze.getTileSize(), maze.getTileSize()-3, maze.getTileSize()-3, 0x0f02ff, ShadowType.NONE);
				
		tick++;		
      
		//base case
		if(count >= stack.size()) {
			count = stack.size();
		}
		
		//loop through all of locations
		for(int i = 0; i < count; i++) {
		    if (stack.head != null) {
	        	col = stack.get(i).getCol();
	        	row = stack.get(i).getRow();
	        	
	        	if(intersects(visit, stack.get(i))) {
	        		r.drawFillRect(1+(col*maze.getTileSize()), 2+row*maze.getTileSize(), maze.getTileSize()-3, maze.getTileSize()-3, 0x0f02ff, ShadowType.NONE);
	        	}
	        	else r.drawFillRect(1+(col*maze.getTileSize()), 2+row*maze.getTileSize(), maze.getTileSize()-3, maze.getTileSize()-3, 0xffffff, ShadowType.NONE);

		     }
		}
		
		//speed of solver
		if(tick >= speed) {
			count++;
			tick = 0;
		}
		
	}
	
	//checks if a location is withing a stack
	private boolean intersects(StackRefBased<MazeLocation> first, MazeLocation other) {
		StackNode<MazeLocation> tempNode = null;
    	
   	 for(tempNode = first.head; tempNode != null; tempNode = tempNode.next) {
           if(tempNode.getValue().equals(other)) return true;
        }
   	 return false;
	}
	
}
