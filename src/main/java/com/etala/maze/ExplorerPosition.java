/**
 * 
 */
package com.etala.maze;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.etala.maze.gui.MazeDrawer;

@SuppressWarnings("unused")
public class ExplorerPosition {
	
	    static Logger       logger = Logger.getLogger(ExplorerPosition.class);
		
	    public enum         Direction{NORTH,SOUTH,EAST,WEST}
		private int         mazeSize;
		private Point       position;
		private Point       finish;
		private boolean     stopExploring = false;
		private MazeGrid    maze;
		private boolean[][] visited;
		private Map<Point, boolean[][]> mazeMap;
		private MazeGridImporter    readyMaze;
		
		public ExplorerPosition(MazeGrid maze)
	    {
			 this.maze     		= maze;
	         this.position 		= maze.getStartPoint();
	         this.finish 		= maze.getFinishPoint();
	         this.mazeSize 		= maze.getGridSize();
	         this.visited  		= maze.getVisited();
	         this.mazeMap 		= new HashMap<>();
	    }
		
		public ExplorerPosition(MazeGridImporter readyMaze)
	    {
			 this.readyMaze     = readyMaze;
	         this.position 		= readyMaze.getStartPoint();
	         this.finish 		= readyMaze.getFinishPoint();
	         try {
				this.mazeSize 		= MazeGridImporter.getMazeSize();
			} catch (IOException e) {
				e.printStackTrace();
			}
	         this.visited  		= readyMaze.getVisited();
	         this.mazeMap 		= new HashMap<>();
	    }


		public Point move(Direction direction) {
			boolean[][] geo = getAllNearbyPointsToExplorerPosition(position.x, position.y, 1);
		    									
			switch(direction) {
		    case NORTH:	
		    	if (geo[position.x][position.y + 1]){ 
		    		moveExplorerPositionToPoint(position.x, (position.y + 1));
			    }
		        break;
		    case SOUTH:
		    	logger.info("south pattern? " + geo[position.x][position.y - 1]);
		    	if (geo[position.x][position.y - 1]){ 
		    		moveExplorerPositionToPoint(position.x, (position.y - 1));
		    	}
		        break;
		    case EAST:
		    	logger.info("east pattern? " + geo[position.x + 1][position.y]);
		    	if (geo[position.x + 1][position.y]) {
		    		moveExplorerPositionToPoint((position.x + 1), position.y);
		    	}
		        break;
		    case WEST:
			   logger.info("west pattern? " + geo[position.x - 1][position.y]);
		    	if (geo[position.x - 1][position.y]){
		    		moveExplorerPositionToPoint((position.x - 1), (position.y));
		    	}
		        break;
			default:
				break;
			}
			drawPositionMove(Color.RED, position.x, position.y);
			return position;
		}

		private void moveExplorerPositionToPoint(int x, int y) {
			if (!isDirectionABorder(x, y)){
				position.move(x, y);
				visited[x][y] = true;
			}
		}
		
		protected boolean[][] getAllNearbyPointsToExplorerPosition(int x, int y, int distance) {
			boolean[][] compass = new boolean[mazeSize+2][mazeSize+2];
			boolean[][] north   = maze.getNorth(); 
			boolean[][] south   = maze.getSouth();
			boolean[][] east    = maze.getEast();
			boolean[][] west    = maze.getWest();
		    
			if (distance == 0) return compass;
			
			for (int i = 1; i < distance+ 1; i++) {
				compass[x][y + i]   = (!north[x][y])?  true: false;
				compass[x + i][y]   = (!east[x][y])?  true: false;
				compass[x][y - i]   = (!south[x][y])?  true: false;
		        compass[x - i][y]   = (!west[x][y])?  true: false;
			}
			
			return compass;
		}
		
		/**
		 * Generates mazeMap a HashMap were you can lookup a coordinate Point and retrieve an array with its 4 directions mapped
		 */
		protected void generateExplorerMap() {
			
			for (int x = 1; x <= mazeSize; x++)
				for (int y = 1; y <= mazeSize; y++)
					mazeMap.put(new Point(x, y), getAllNearbyPointsToExplorerPosition(x, y, 1));
		
		}
		
		public void showPosition() {
	        stopExploring = false;
	        drawPositionGeo(position.x, position.y);
	    }
		 
		private void drawPositionGeo(int x, int y) {
			if (x == 0 || y == 0 || x == mazeSize+1 || y == mazeSize+1) return;
	        if (stopExploring) return;
	        drawPositionMove(Color.YELLOW, x, y);
		}

		private void drawPositionMove(Color color, int x, int y) {
			MazeDrawer.setPenColor(color);
	        MazeDrawer.filledCircle(x + 0.5, y + 0.5, 0.3);
	        MazeDrawer.show();
	        MazeDrawer.pause(30);
		}
		
		protected Boolean isDirectionABorder(int x, int y) {
	        if (x == 0 || y == 0 || x == mazeSize+1 || y == mazeSize+1) return true;
	        return false;
		}
		
		public Point getPosition() {
			return position;
		}

		public void setPosition(Point position) {
			this.position = position;
		}

		public void setMazeGrid(MazeGrid maze) {
			this.maze = maze;
		}

		public Map<Point, boolean[][]> getMazeMap() {
			return mazeMap;
		}

}
