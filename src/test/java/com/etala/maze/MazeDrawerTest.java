package com.etala.maze;

import java.awt.Color;

import com.etala.maze.gui.MazeDrawer;

public class MazeDrawerTest {

	 public static void main(String[] args) {

		    MazeDrawer.square(.2, .8, .1);
	        MazeDrawer.filledSquare(.8, .8, .2);
	        MazeDrawer.circle(.8, .2, .2);

	        MazeDrawer.setPenColor(Color.RED);
	        MazeDrawer.setPenRadius(.02);
	        MazeDrawer.arc(.8, .2, .1, 200, 45);

	        MazeDrawer.setPenRadius();
	        MazeDrawer.setPenColor(Color.BLUE);
	        double[] x = { .1, .2, .3, .2 };
	        double[] y = { .2, .3, .2, .1 };
	        MazeDrawer.filledPolygon(x, y);

	        MazeDrawer.setPenColor(Color.BLACK);
	        MazeDrawer.text(0.2, 0.5, "black text");
	        MazeDrawer.setPenColor(Color.WHITE);
	        MazeDrawer.text(0.8, 0.8, "white text");
	        
	   }

}
