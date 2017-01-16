package com.etala.maze;

import java.awt.Point;
import java.io.IOException;
import java.util.Random;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.etala.maze.gui.MazeDrawer;

public class MazeDemo {
	static Logger logger = Logger.getLogger(ExplorerPosition.class);
		
	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		int mazeSize 		= MazeGridImporter.getMazeSize();
		MazeGrid maze 		= new MazeGrid(mazeSize);
		Point startPoint	= new Point(1, mazeSize);
		Point finishPoint 	= new Point(mazeSize - 3, 1);
		maze.setStartPoint(startPoint);
		maze.setFinishPoint(finishPoint);
        MazeDrawer.enableDoubleBuffering();
        maze.drawMaze();    		
        
        explorerDemoPath(maze);

	}

	private static void explorerDemoPath(MazeGrid maze) {
		ExplorerPosition explorer = new ExplorerPosition(maze);
        explorer.setMazeGrid(maze);
        explorer.generateExplorerMap();
        
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.showPosition();
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.showPosition();
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY()); 
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.showPosition();
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.WEST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());   
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.showPosition();
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.showPosition();
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.WEST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());   
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        explorer.move(ExplorerPosition.Direction.EAST);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());   
        explorer.showPosition();
        explorer.move(ExplorerPosition.Direction.SOUTH);
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        
	}

}
