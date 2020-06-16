import java.util.Queue;
import java.util.LinkedList; 

/**
 * Shortest Path between two points in a grid / matrix.
 */
public class DungeonMaster {
	/**
	 * You are trapped in a 3D dungeon and need to find the quickest way out! 
	 * The dungeon is composed of unit cubes which may or may not be filled with rock.
	 * It takes one minute to move one unit north, south, east, west, up or down.
	 * You cannot move diagonally and the maze is surrounded by solid rock on all sides.
	 * https://open.kattis.com/problems/dungeon
	 */

	private static class Cell {
		int row;
		int column;
		int count = 0;
		Cell(int row, int column, int count) {
			this.row = row;
			this.column = column;
			this.count = count;
		}
	}

	public static int getShortestPathCount (char[][] matrix) {
		Queue<Cell> q = new LinkedList<Cell>();
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];

		if (matrix == null || matrix.length == 0) {
			return 0;
		}

		/** 
		 * Check the presence of 'S' and 'E' points.
		 * Also 'S' and 'E' should be near the edge of the matrix (if it is maze).
		 */
		if (!validateStartEndPoints(matrix)) {
			System.out.println("yippee..");
			return 0;
		}

		// Do a BFS to traverse the  matrix.
		// Insert the first cell ('S' cell into the queue).
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 'S') {
					q.offer(new Cell(i, j, 0));
					break;
				}
			}
			if (q.size() != 0) {
				break;
			}
		}

		/**
		 * 1. Pop out 'S' from the Queue.
		 * 2. Add all the four adjacent cells to the queue, increasing the count by 1.
		 * 3. Pop out each element from the queue and Repeat.
		 */
		while (!q.isEmpty()) {
			Cell c = q.poll();
			int row = c.row;
			int col = c.column;
			int[] rowArr = {row - 1, row, row, row + 1};
			int[] colArr = {col, col - 1, col + 1, col};
			for (int i = 0; i < rowArr.length; i++) {
				if (rowArr[i] >=0 && colArr[i] >=0 
					&& rowArr[i] < rowSize && colArr[i] < colSize) {
					if (matrix[rowArr[i]][colArr[i]] == 'E') {
						return c.count + 1;
					} else if (matrix[rowArr[i]][colArr[i]] == '.'
						&& visited[rowArr[i]][colArr[i]] == false) {
						q.offer(new Cell(rowArr[i], colArr[i], c.count + 1));
						visited[rowArr[i]][colArr[i]] = true;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Validate the presence of existence of 'S' and 'E'.
	 * Make sure 'S' and 'E' are near the sides of the maze.
	 */
	private static boolean validateStartEndPoints(char[][] matrix) {
		boolean isStartPresent = false;
		boolean isEndPresent  = false;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 'S' || matrix[i][j] == 's') {
					if ((i == 0 || (i + 1) == matrix.length)  
						|| j == 0 || (j + 1) == matrix[0].length) {
						isStartPresent = true;
					}
				}
				if (matrix[i][j] == 'E' || matrix[i][j] == 'e') {
					if ((i == 0 || (i + 1) == matrix.length)  
						|| j == 0 || (j + 1) == matrix[0].length) {
						isEndPresent = true;
					}
				}
			}
		}
		return isStartPresent && isEndPresent;
	}

	public static void main (String[] args) {
		// The Entry point is 'S'.
		// The Exit point is 'E'.
		char[][] matrix = {
			{'.', 'S', '.', '.', '.'},
			{'.', '.', '#', '#', '.'},
			{'.', '#', '#', '.', '.'},
			{'#', '#', '#', 'E', '#'}
		};
		System.out.println(getShortestPathCount(matrix));
	}
}


Examples
/**
 *
 * 'S' and 'E' cannot be connected.
 *		char[][] matrix = {
 *			{'.', 'S', '.', '.', '.'},
 *			{'.', '.', '#', '#', '.'},
 *			{'.', '#', '#', '.', '.'},
 *			{'#', 'E', '#', '.', '#'}
 *		};
 *
 * 'E' should be near the edges.
 *		char[][] matrix = {
 *			{'.', 'S', '.', '.', '.'},
 *			{'.', '.', '#', '#', '.'},
 *			{'.', '#', 'E', '.', '.'},
 *			{'#', '#', '#', '.', '#'}
 *		};
 *
 * 'S' should be near the edges.
 *		char[][] matrix = {
 *			{'.', '#', '.', '.', '.'},
 *			{'.', '.', 'S', '#', '.'},
 *			{'.', '#', '#', '.', '.'},
 *			{'#', '#', '#', 'E', '#'}
 *		};
 *
 * 'E' not present
 *		char[][] matrix = {
 *			{'.', 'S', '.', '.', '.'},
 *			{'.', '.', '#', '#', '.'},
 *			{'.', '#', '#', '.', '.'},
 *			{'#', '#', '#', '.', '#'}
 *		};
 *
 * 'S' not present
 *		char[][] matrix = {
 *			{'.', '#', '.', '.', '.'},
 *			{'.', '.', '#', '#', '.'},
 *			{'.', '#', '#', '.', '.'},
 *			{'#', 'E', '#', '.', '#'}
 *		};
 *
 */