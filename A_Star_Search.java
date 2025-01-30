// Author: Tytus Felbor
// CPSC271 : Robotics
// Project 3 - Robot & Maze

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a cell in the grid
class Cell {

	int parent_i, parent_j; // Coordinates of the parent cell
	double f, g, h; // Values used in the A* algorithm

	// Constructor to initialize the cell
	Cell() {
	
		this.parent_i = 0;
		this.parent_j = 0;
		this.f = 0;
		this.g = 0;
		this.h = 0;
	}
}

// Implementation of the A* search algorithm
public class A_Star_Search {

	// Constants defining the grid dimensions
	private static final int ROW = 7;
	private static final int COL = 7;

	// List to store the path coordinates
	private static ArrayList<int[]> moves = new ArrayList<int[]>();

	// Array to store directions for each move
	private static String[] directions;

	// Main method to execute the A* algorithm
	public static void main(String[] args) {
		// Description of the grid
		int[][] grid = {
				{0, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 0, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0}
		};

		// Source and destination coordinates
		int[] src = {0, 3};
		int[] dest = {4, 5};

		// Perform A* search
		aStarSearch(grid, src, dest);

		// Initialize array to store directions
		directions = new String[moves.size()];

		System.out.println("List of moves: ");
		// Calculate and print directions for each move
		for (int i = 0; i < moves.size() - 1; i++) {
			int[] current = moves.get(i);
			int[] next = moves.get(i + 1);
			String direction = getDirection(current, next);
			directions[i] = direction;
			System.out.println("Move " + direction.toUpperCase() + ": (" + current[0] + ", " + current[1] + ") ==> (" + next[0] + ", " + next[1] + ")");
		}
	}

	private static final String[] DIRECTIONS = {"forward", "right", "back", "left"};

	private static int currentDirectionIndex = 0; // 0: forward, 1: right, 2: back, 3: left

	private static void turnLeft() {
		currentDirectionIndex = (currentDirectionIndex + 3) % 4; // Turning left means decrementing by 1
	}

	private static void turnRight() {
		currentDirectionIndex = (currentDirectionIndex + 1) % 4; // Turning right means incrementing by 1
	}

	private static String getDirection(int[] current, int[] next) {
	
		int currRow = current[0];
		int currCol = current[1];
		int nextRow = next[0];
		int nextCol = next[1];

		int rowDifference = nextRow - currRow;
		int colDifference = nextCol - currCol;

		// Calculate the required turning direction
		int targetDirectionIndex;
		if (rowDifference != 0) {
			targetDirectionIndex = rowDifference > 0 ? 0 : 2;
		} else if (colDifference != 0) {
			targetDirectionIndex = colDifference > 0 ? 1 : 3;
		} else {
			return "stay"; // No movement
		}

		StringBuilder directions = new StringBuilder();

		// Calculate the number of turns needed to reach the target direction
		int turnCount = (targetDirectionIndex - currentDirectionIndex + 4) % 4;
		if (turnCount == 1) {
			directions.append("left&");
			turnRight();
		} else if (turnCount == 3) {
			directions.append("right&");
			turnLeft();
		}

		// Add forward movement
		directions.append("forward");

		return directions.toString();
	}

	// Method to check if a cell is valid (within grid bounds)
	private static boolean isValid(int row, int col) {
		return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
	}

	// Method to check if a cell is unblocked (not blocked by an obstacle)
	private static boolean isUnBlocked(int[][] grid, int row, int col) {
		return grid[row][col] == 1;
	}

	// Method to check if a cell is the destination
	private static boolean isDestination(int row, int col, int[] dest) {
		return row == dest[0] && col == dest[1];
	}

	// Method to calculate the heuristic value (distance to destination)
	private static double calculateHValue(int row, int col, int[] dest) {
		return Math.sqrt((row - dest[0]) * (row - dest[0]) + (col - dest[1]) * (col - dest[1]));
	}

	// Method to trace the path from destination back to source
	private static void tracePath(Cell[][] cellDetails, int[] dest) {
		System.out.print("The Path is: ");
		int row = dest[0];
		int col = dest[1];

		Map<int[], Boolean> path = new LinkedHashMap<>();

		// Traverse through the parent cells until reaching the source
		while (!(cellDetails[row][col].parent_i == row && cellDetails[row][col].parent_j == col)) {
			path.put(new int[]{row, col}, true);
			int temp_row = cellDetails[row][col].parent_i;
			int temp_col = cellDetails[row][col].parent_j;
			row = temp_row;
			col = temp_col;
		}

		// Add source cell to path
		path.put(new int[]{row, col}, true);
		List<int[]> pathList = new ArrayList<>(path.keySet());
		Collections.reverse(pathList);

		// Store path coordinates and print path
		pathList.forEach(p -> {
			moves.add(p);
			System.out.print("-> (" + p[0] + ", " + (p[1]) + ") ");
		});
		System.out.println("\n\n---------A*-Algorithm-Finished!--------------------\n");
	}

	private static void aStarSearch(int[][] grid, int[] src, int[] dest) {
		if (!isValid(src[0], src[1]) || !isValid(dest[0], dest[1])) {
			System.out.println("Source or destination is invalid");
			return;
		}

		if (!isUnBlocked(grid, src[0], src[1]) || !isUnBlocked(grid, dest[0], dest[1])) {
			System.out.println("Source or the destination is blocked");
			return;
		}

		if (isDestination(src[0], src[1], dest)) {
			System.out.println("We are already at the destination");
			return;
		}

		System.out.println("---------A*-Algorithm-Processing...----------------\n");

		boolean[][] closedList = new boolean[ROW][COL];
		Cell[][] cellDetails = new Cell[ROW][COL];

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				cellDetails[i][j] = new Cell();
				cellDetails[i][j].f = Double.POSITIVE_INFINITY;
				cellDetails[i][j].g = Double.POSITIVE_INFINITY;
				cellDetails[i][j].h = Double.POSITIVE_INFINITY;
				cellDetails[i][j].parent_i = -1;
				cellDetails[i][j].parent_j = -1;
			}
		}

		int i = src[0], j = src[1];
		cellDetails[i][j].f = 0;
		cellDetails[i][j].g = 0;
		cellDetails[i][j].h = 0;
		cellDetails[i][j].parent_i = i;
		cellDetails[i][j].parent_j = j;

		Map<Double, int[]> openList = new HashMap<>();
		openList.put(0.0, new int[]{i, j});

		boolean foundDest = false;

		while (!openList.isEmpty()) {
			Map.Entry<Double, int[]> p = openList.entrySet().iterator().next();
			openList.remove(p.getKey());

			i = p.getValue()[0];
			j = p.getValue()[1];
			closedList[i][j] = true;

			double gNew, hNew, fNew;

			// Right
			if (isValid(i, j + 1)) {
				if (isDestination(i, j + 1, dest)) {
					cellDetails[i][j + 1].parent_i = i;
					cellDetails[i][j + 1].parent_j = j;
					System.out.println("The destination cell is found");
					tracePath(cellDetails, dest);
					foundDest = true;
					return;
				} else if (!closedList[i][j + 1] && isUnBlocked(grid, i, j + 1)) {
					gNew = cellDetails[i][j].g + 1;
					hNew = calculateHValue(i, j + 1, dest);
					fNew = gNew + hNew;

					if (cellDetails[i][j + 1].f == Double.POSITIVE_INFINITY || cellDetails[i][j + 1].f > fNew) {
						openList.put(fNew, new int[]{i, j + 1});

						cellDetails[i][j + 1].f = fNew;
						cellDetails[i][j + 1].g = gNew;
						cellDetails[i][j + 1].h = hNew;
						cellDetails[i][j + 1].parent_i = i;
						cellDetails[i][j + 1].parent_j = j;
					}
				}
			}

			// Left
			if (isValid(i, j - 1)) {
				if (isDestination(i, j - 1, dest)) {
					cellDetails[i][j - 1].parent_i = i;
					cellDetails[i][j - 1].parent_j = j;
					System.out.println("The destination cell is found");
					tracePath(cellDetails, dest);
					foundDest = true;
					return;
				} else if (!closedList[i][j - 1] && isUnBlocked(grid, i, j - 1)) {
					gNew = cellDetails[i][j].g + 1;
					hNew = calculateHValue(i, j - 1, dest);
					fNew = gNew + hNew;

					if (cellDetails[i][j - 1].f == Double.POSITIVE_INFINITY || cellDetails[i][j - 1].f > fNew) {
						openList.put(fNew, new int[]{i, j - 1});

						cellDetails[i][j - 1].f = fNew;
						cellDetails[i][j - 1].g = gNew;
						cellDetails[i][j - 1].h = hNew;
						cellDetails[i][j - 1].parent_i = i;
						cellDetails[i][j - 1].parent_j = j;
					}
				}
			}

			// Forward
			if (isValid(i - 1, j)) {
				if (isDestination(i - 1, j, dest)) {
					cellDetails[i - 1][j].parent_i = i;
					cellDetails[i - 1][j].parent_j = j;
					System.out.println("The destination cell is found");
					tracePath(cellDetails, dest);
					foundDest = true;
					return;
				} else if (!closedList[i - 1][j] && isUnBlocked(grid, i - 1, j)) {
					gNew = cellDetails[i][j].g + 1;
					hNew = calculateHValue(i - 1, j, dest);
					fNew = gNew + hNew;

					if (cellDetails[i - 1][j].f == Double.POSITIVE_INFINITY || cellDetails[i - 1][j].f > fNew) {
						openList.put(fNew, new int[]{i - 1, j});

						cellDetails[i - 1][j].f = fNew;
						cellDetails[i - 1][j].g = gNew;
						cellDetails[i - 1][j].h = hNew;
						cellDetails[i - 1][j].parent_i = i;
						cellDetails[i - 1][j].parent_j = j;
					}
				}
			}

			// Back
			if (isValid(i + 1, j)) {
				if (isDestination(i + 1, j, dest)) {
					cellDetails[i + 1][j].parent_i = i;
					cellDetails[i + 1][j].parent_j = j;
					System.out.println("The destination cell is found");
					tracePath(cellDetails, dest);
					foundDest = true;
					return;
				} else if (!closedList[i + 1][j] && isUnBlocked(grid, i + 1, j)) {
					gNew = cellDetails[i][j].g + 1;
					hNew = calculateHValue(i + 1, j, dest);
					fNew = gNew + hNew;

					if (cellDetails[i + 1][j].f == Double.POSITIVE_INFINITY || cellDetails[i + 1][j].f > fNew) {
						openList.put(fNew, new int[]{i + 1, j});

						cellDetails[i + 1][j].f = fNew;
						cellDetails[i + 1][j].g = gNew;
						cellDetails[i + 1][j].h = hNew;
						cellDetails[i + 1][j].parent_i = i;
						cellDetails[i + 1][j].parent_j = j;
					}
				}
			}
		}

		if (!foundDest)
			System.out.println("Failed to find the destination cell");

	}
}
