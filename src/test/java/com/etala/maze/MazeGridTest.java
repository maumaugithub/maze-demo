package com.etala.maze;

import static org.junit.Assert.*;

import java.awt.Point;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.junit.Before;
import org.junit.Test;

public class MazeGridTest {
	private MazeGrid maze;
	static Logger logger = Logger.getLogger(MazeGridTest.class);
	private int mazeSize;
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		this.mazeSize = 13;
		this.maze = new MazeGrid(mazeSize);
		
		Point startPoint = new Point(1, mazeSize);
		Point finishPoint = new Point(mazeSize, 1);
		maze.setStartPoint(startPoint);
		maze.setFinishPoint(finishPoint);
		
	}

	@Test
	public void mazePatternAllArraysAreRightSizeTest() {
		maze.generateMazePattern();
		boolean[][] northValues = maze.getNorth();
		boolean[][] southValues = maze.getSouth();
		boolean[][] westValues = maze.getWest();
		boolean[][] eastValues = maze.getEast();
		assertEquals(mazeSize + 2, northValues.length);
		assertEquals(mazeSize + 2, southValues.length);
		assertEquals(mazeSize + 2, westValues.length);
		assertEquals(mazeSize + 2, eastValues.length);
	}

	@Test
	public void mazePatternGetStartPointWhenCalculatedTest() {
		maze.generateMazePattern();
		Point expectedStart = new Point(1, mazeSize);
		assertEquals("Start Point is Calculated on Main", expectedStart, maze.getStartPoint());
	}

	@Test
	public void mazePatternGetFinishPointWhenCalculatedTest() {
		maze.generateMazePattern();
		Point expectedFinish = new Point(mazeSize, 1);
		assertEquals("Finish Point is Calculated on Main", expectedFinish, maze.getFinishPoint());
	}
}
