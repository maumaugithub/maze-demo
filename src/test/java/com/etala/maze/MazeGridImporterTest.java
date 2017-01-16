package com.etala.maze;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class MazeGridImporterTest {
	private MazeGridImporter importer;
	static Logger logger = Logger.getLogger(MazeGridImporterTest.class);

	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		
		this.importer = new MazeGridImporter();
		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void readMazeGridReadyMadePatternTest() throws IOException {
		BufferedReader buffer = importer.readMazeGridReadyMadePattern();
		assertNotNull(buffer);
		buffer.close();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void startPointExistsTest() throws IOException {
		Point start = importer.getStartPoint();
		assertNotNull(start);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void finishPointExistsTest() throws IOException {
		Point end = importer.getFinishPoint();
		assertNotNull(end);
	}

	@SuppressWarnings("static-access")
	@Test
	public void parsePatternIntoBaseArrayTest() throws IOException {
		int n = importer.getMazeSize();
		boolean[][] actual = importer.parsePatternIntoBaseArray();
		assertNotNull(actual);
//		importer.getMazeSize();
//		boolean[][] expected = new boolean[n+2][n+2];
//		int x = 0; 
//		int y = 0;
//		expected[x][y] = true;
//		logger.info(actual[x][y]);
//		assertEquals(expected[x][y], actual[x][y]);
	}

}
