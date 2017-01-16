package com.etala.maze;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class ExplorerPositionTest {
	static Logger logger = Logger.getLogger(ExplorerPositionTest.class);
	private ExplorerPosition explorer;
	private MazeGrid maze;
	private int mazeSize;

	@Before
	public void setUp() throws Exception {
		this.mazeSize = 15;
		this.maze = new MazeGrid(mazeSize);
		Point startPoint = new Point(1, mazeSize);
		Point finishPoint = new Point(mazeSize, 1);
		maze.setStartPoint(startPoint);
		maze.setFinishPoint(finishPoint);
		this.explorer  = new ExplorerPosition(maze);
		BasicConfigurator.configure();
	}

	@Test
	public void explorerActuallyMovesHappyPathTest() {
	        explorer.showPosition();
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        
	        explorer.move(ExplorerPosition.Direction.EAST);
	        assertEquals(2.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.SOUTH);
	        assertEquals(14.0, explorer.getPosition().getY(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.EAST);
	        assertEquals(3.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.SOUTH);
	        assertEquals(13.0, explorer.getPosition().getY(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.SOUTH);
	        assertEquals(12.0, explorer.getPosition().getY(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        
	        explorer.move(ExplorerPosition.Direction.EAST);
	        assertEquals(4.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.EAST);
	        assertEquals(5.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	    
	}
	
	@Test
	public void explorerMoveIsWrongChoiceTest() {
	        explorer.showPosition();
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        
	        explorer.move(ExplorerPosition.Direction.WEST);
	        assertEquals(1.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        
	        explorer.move(ExplorerPosition.Direction.NORTH);
	        assertEquals(mazeSize, explorer.getPosition().getY(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	}
	
	@Test
	public void explorerCannotMoveToBordersTest() {
		explorer.move(ExplorerPosition.Direction.WEST);
	    assertEquals(1.0, explorer.getPosition().getX(),0);
	    logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	    assertTrue(explorer.isDirectionABorder(0, mazeSize));
	    assertTrue(explorer.isDirectionABorder(1, mazeSize + 1));
	    assertTrue(explorer.isDirectionABorder(0, mazeSize + 1));
	    assertTrue(explorer.isDirectionABorder(mazeSize + 1, mazeSize + 1));
	}   

	@Test
	public void explorerDontCrossWallsTest() {
	        
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());

	        explorer.move(ExplorerPosition.Direction.EAST);
	        assertEquals(2.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.NORTH);
	        assertEquals(15.0, explorer.getPosition().getY(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.WEST);
	        assertEquals(1.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        explorer.move(ExplorerPosition.Direction.EAST);
	        assertEquals(2.0, explorer.getPosition().getX(),0);
	        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	        
	}
	
	@Test
	public void getAllNearbyPointsToExplorerPositionUpToDistanceOneStep() {
		Point current = explorer.getPosition();
        logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
        
        int distanceStepsLoaded = 1;
        boolean[][] expectedCompass = setupExpectedCompassStepSizeOne(current); 
	   
        boolean[][] compass = explorer.getAllNearbyPointsToExplorerPosition(explorer.getPosition().x, explorer.getPosition().y, distanceStepsLoaded );
	    
	    assertEquals(false, compass[0][15]);
	    assertEquals(true, compass[2][15]);
	    assertEquals(true, compass[1][14]);
	    assertEquals(false, compass[1][16]);
	    
	    assertArrayEquals(expectedCompass, compass);
	}
	
	@Test
	public void generateExplorerMapTest() {
		
		Point current = explorer.getPosition();
		logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
       // For each coordinate Point we get a compass (a double array with 4 locations)
		Map<Point, boolean[][]> expectedMap = setupExpectedMap(current);
                
	    explorer.generateExplorerMap();
	    assertArrayEquals(expectedMap.get(current), explorer.getMazeMap().get(current));
	    logger.info(explorer.getPosition().getX() + " , " + explorer.getPosition().getY());
	}

	private boolean[][] setupExpectedCompassStepSizeOne(Point current) {
		boolean[][] expectedGeo = new boolean[mazeSize+2][mazeSize+2];
		
		if ((current.x - 1 == 0) || (current.x == 0)) expectedGeo[current.x - 1][current.y] = false; //south
		if (current.y - 1 == 0 || current.y == 0) expectedGeo[current.x][current.y - 1] = false; 
		if (current.x + 1 == mazeSize+1 ) expectedGeo[current.x + 1][current.y] = false;  //east
		if (current.y + 1 == mazeSize+1) expectedGeo[current.x][current.y + 1] = false; 
				
        expectedGeo[current.x][current.y + 1] = false; 
        expectedGeo[current.x + 1][current.y] = true;  //east
        expectedGeo[current.x][current.y - 1] = true; //south
        expectedGeo[current.x - 1][current.y] = false; 
		
		return expectedGeo;
	}

	private Map<Point, boolean[][]> setupExpectedMap(Point current) {
		Map<Point, boolean[][]> expectedMap = new HashMap<>();
		boolean[][] expectedGeo = setupExpectedCompassStepSizeOne(current); 
		expectedMap.put(current, expectedGeo);
		return expectedMap;
	}
}
