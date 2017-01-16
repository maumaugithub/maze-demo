package com.etala.maze;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.etala.maze.gui.MazeDrawer;

public class MazeGrid {
	
	static Logger logger = Logger.getLogger(MazeGrid.class);
	
	private int         n;
	private boolean[][] north;
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] mazePath;
	private Point       startPoint;
	private Point       finishPoint;
	private boolean     quit = false;
	private Random      random = new Random(2000000);
	
	public MazeGrid(int n) {
		this.n = n;
		initialiseGrid();
		generateMazePattern();
		setupMazeDrawer(n);
	}

	private void initialiseGrid() {

		mazePath = new boolean[n + 2][n + 2];
	
		for (int x = 0; x < n + 2; x++) {
			mazePath[x][0] = true;
			mazePath[x][n + 1] = true;
		}
		for (int y = 0; y < n + 2; y++) {
			mazePath[0][y] = true;
			mazePath[n + 1][y] = true;
		}

		north  = new boolean[n + 2][n + 2];
		east   = new boolean[n + 2][n + 2];
		south  = new boolean[n + 2][n + 2];
		west   = new boolean[n + 2][n + 2];
		
		for (int x = 0; x < n + 2; x++) {
			for (int y = 0; y < n + 2; y++) {
				north[x][y]  = true ;
				east[x][y]   = true ;
				south[x][y]  = true ;
				west[x][y]   = true ;
			}
		}
	}

	private void generatePattern(int x, int y) {

		mazePath[x][y] = true;

		while (!mazePath[x][y + 1] || !mazePath[x + 1][y] || !mazePath[x][y - 1] || !mazePath[x - 1][y]) {

			while (true) {
				double r = random.nextInt(4);
				if    (r == 0 && !mazePath[x][y + 1]) {
					   north[x][y] = false;
					   south[x][y + 1] = false;
					generatePattern(x, y + 1);
					break;
				} else if (r == 1 && !mazePath[x + 1][y]) {
					east[x][y] = false;
					west[x + 1][y] = false;
					generatePattern(x + 1, y);
					break;
				} else if (r == 2 && !mazePath[x][y - 1]) {
					south[x][y] = false;
					north[x][y - 1] = false;
					generatePattern(x, y - 1);
					break;
				} else if (r == 3 && !mazePath[x - 1][y]) {
					west[x][y] = false;
					east[x - 1][y] = false;
					generatePattern(x - 1, y);
					break;
				}
			}
		}
	}

	protected void generateMazePattern() {
		
		generatePattern(1, 1);

		for (int i = 0; i < n; i++) {
			int x = 1 + random.nextInt(n - 1);
			int y = 1 + random.nextInt(n - 1);
			north[x][y] = south[x][y + 1] = false;
		}

		for (int i = 0; i < 10; i++) {
			int x = n / 2 + random.nextInt(n / 2);
			int y = n / 2 + random.nextInt(n / 2);
			east[x][y] = west[x + 1][y] = true;
		}

	}

	private void solveMazeWithPoint(int x, int y) {
		
		if (x == 0 || y == 0 || x == n + 1 || y == n + 1)
			return;
		if (quit || mazePath[x][y])
			return;
		mazePath[x][y] = true;

		drawSolutionPoint(x, y);
	
		if (!north[x][y])
			solveMazeWithPoint(x, y + 1);
		if (!east[x][y])
			solveMazeWithPoint(x + 1, y);
		if (!south[x][y])
			solveMazeWithPoint(x, y - 1);
		if (!west[x][y])
			solveMazeWithPoint(x - 1, y);
		
		if (x == finishPoint.x && y == finishPoint.y) {
			quit = true; 	
		};
		
	}

	public void solveMaze() {
		for (int x = 1; x <= n; x++)
			for (int y = 1; y <= n; y++)
				mazePath[x][y] = false;
		quit = false;
		solveMazeWithPoint(startPoint.x, startPoint.y);
		}
	

	private void setupMazeDrawer(int n) {
		MazeDrawer.setXscale(0, n + 2);
		MazeDrawer.setYscale(0, n + 2);
	}

	private void drawSolutionPoint(int x, int y) {
		MazeDrawer.setPenColor(Color.BLUE);
		MazeDrawer.filledCircle(x + 0.5, y + 0.5, 0.25);
		MazeDrawer.show();
		MazeDrawer.pause(30);
	}

	public void drawMaze() {
		drawOpeningPoint(Color.RED, startPoint);
		drawOpeningPoint(Color.GREEN, finishPoint);

		MazeDrawer.setPenColor(Color.BLACK);
		for (int x = 1; x <= n; x++) {
			for (int y = 1; y <= n; y++) {
				if (south[x][y])
					MazeDrawer.line(x, y, x + 1, y);
				if (north[x][y])
					MazeDrawer.line(x, y + 1, x + 1, y + 1);
				if (west[x][y])
					MazeDrawer.line(x, y, x, y + 1);
				if (east[x][y])
					MazeDrawer.line(x + 1, y, x + 1, y + 1);
			}
		}
		MazeDrawer.show();
		MazeDrawer.pause(1000);
	}

	private void drawOpeningPoint(Color color, Point point) {
		MazeDrawer.setPenColor(color);
		MazeDrawer.filledCircle(point.getX() + 0.5, point.getY() + 0.5, 0.375);
	}

	public boolean[][] getNorth() {
		return north;
	}

	public boolean[][] getEast() {
		return east;
	}

	public boolean[][] getSouth() {
		return south;
	}

	public boolean[][] getWest() {
		return west;
	}

	public boolean[][] getVisited() {
		return mazePath;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public void setFinishPoint(Point finishPoint) {
		this.finishPoint = finishPoint;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public Point getFinishPoint() {
		return finishPoint;
	}

	public int getGridSize() {
		return n;
	}

}
