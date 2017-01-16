package com.etala.maze;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.chainsaw.Main;

public class MazeGridImporter {

	private static String LOCAL_FOLDER= "/src/main/resources/";
	private static int mazeSize = 0;
	private Point startPoint;
	private Point finishPoint;
	private boolean[][] north;
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] visited;
	
	public MazeGridImporter() {
		try {
			readMazeSize();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialiseGrid();
		this.startPoint = new Point(0, 0);
		this.finishPoint = new Point(mazeSize, mazeSize);
	}
	
	private void initialiseGrid() {
		north  = new boolean[mazeSize+1][mazeSize+1];
		east   = new boolean[mazeSize+1][mazeSize+1];
		south  = new boolean[mazeSize+1][mazeSize+1];
		west   = new boolean[mazeSize+1][mazeSize+1];
		
		for (int x = 0; x < mazeSize ; x++) {
			for (int y = 0; y < mazeSize ; y++) {
				north[x][y]  = true ;
				east[x][y]   = true ;
				south[x][y]  = true ;
				west[x][y]   = true ;
			}
		}
		
		visited = new boolean[mazeSize+1][mazeSize+1];
	
		for (int x = 0; x < mazeSize ; x++) {
			visited[x][0] = true;
			visited[x][mazeSize] = true;
			west[x][0] = false;
			east[x][mazeSize] = false;
		}
		for (int y = 0; y < mazeSize ; y++) {
			visited[0][y] = true;
			visited[mazeSize][y] = true;
			north[0][y] = false;
			south[mazeSize][y] = false;
		}
	}
	
	public boolean[][] parsePatternIntoBaseArray() throws IOException {
		BufferedReader mazeReader = readMazeGridReadyMadePattern();
		boolean[][] wall = new boolean [mazeSize][mazeSize];
		try {
			Stream<String> lines = mazeReader.lines();
			List<String> asList = lines.collect(Collectors.toList());
			System.out.println(asList.size());
			for (int i = 0; i < mazeSize; i++) {
				System.out.println("Line i=" +i );
				String linei = asList.get(i);
				System.out.println(":"+ linei);
				for (int j = 0; j < mazeSize; j++) {	
					char cj = linei.charAt(j);
					System.out.println(cj);
					wall[i][j]= StringUtils.equals(String.valueOf(cj) , "X")? true: false;
					System.out.println("x:"+ i + " y:" + j + " wall=" + wall[i][j]);
					if (wall[i][j] || !visited[i][j]){
						north[i][j] = false;
						south[i][j] = false;
						east[i][j] = false;
						west[i][j] = false;
					}
					System.out.println("north=" + north[i][j]);
					System.out.println("south=" + south[i][j]);
					System.out.println("east=" + east[i][j]);
					System.out.println("west=" + west[i][j]);
					if (StringUtils.equals(String.valueOf(cj) , "S")) setStartPoint(new Point(i, j));
					if (StringUtils.equals(String.valueOf(cj) , "F")){
						setFinishPoint(new Point(i, j));
						visited[i][j] = false;
					}
				}
			} 
		} finally {
			mazeReader.close();
		}
		return wall;
	}
	
	
	protected static BufferedReader readMazeGridReadyMadePattern() throws FileNotFoundException {
		Path currentRelativePath = Paths.get("");
		String currentPath = currentRelativePath.toAbsolutePath().toString();
		FileInputStream fis = new FileInputStream(currentPath+ LOCAL_FOLDER + "Maze1.txt");
		BufferedReader mazeReader = new BufferedReader(
				new InputStreamReader( fis) 
				);
//		String pattern = mazeReader.lines().collect(Collectors.joining("/n"));
		
		return mazeReader;
	}
	
	protected Boolean isDirectionABorder(int x, int y) {
        if (x == 0 || y == 0 || x == mazeSize || y == mazeSize) return true;
        return false;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getFinishPoint() {
		return finishPoint;
	}

	public void setFinishPoint(Point finishPoint) {
		this.finishPoint = finishPoint;
	}

	public static int getMazeSize() throws IOException {
		return mazeSize;
	}
	
	public static int readMazeSize() throws IOException {
		BufferedReader mazeReader = readMazeGridReadyMadePattern();
		try {
			setMazeSize(mazeReader.readLine().length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		mazeReader.close();
		System.out.println("Maze Size is: " + mazeSize);
		return mazeSize;
	}
	
	public static void setMazeSize(int mazeSize) {
		MazeGridImporter.mazeSize = mazeSize;
	}
	
	public boolean[][] getVisited() {
		initialiseGrid();
		return visited;
	}

}
