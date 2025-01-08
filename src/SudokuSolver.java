import java.util.*;

/**
 * A program to solve a Sudoku puzzle using a backtracking algorithm.
 * <p>
 * The program takes a 9x9 Sudoku puzzle as input, where empty cells are represented by 0,
 * and attempts to solve it. If a solution exists, it displays the solved board; otherwise,
 * it reports that the puzzle is unsolvable.
 * </p>
 */
public class SudokuSolver {

    /**
     * The size of the Sudoku grid (9x9).
     */
    private static final int GRID_SIZE = 9;

    /**
     * Main method to run the Sudoku solver.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[GRID_SIZE][GRID_SIZE];

        System.out.println("Enter the Sudoku puzzle row by row, number by number, using 0 for empty cells:");
        for (int row = 0; row < GRID_SIZE; row++) {
            System.out.print("Enter row " + (row + 1) + " (one number at a time): ");
            for (int col = 0; col < GRID_SIZE; col++) {
                board[row][col] = scanner.nextInt();
            }
        }

        System.out.println("Initial Sudoku board:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            printBoard(board);
        } else {
            System.out.println("Unsolvable Sudoku puzzle.");
        }
    }

    /**
     * Solves the Sudoku puzzle using backtracking.
     *
     * @param board the 9x9 Sudoku grid to be solved.
     * @return {@code true} if the puzzle is solvable; {@code false} otherwise.
     */
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Puzzle solved
    }

    /**
     * Checks whether placing a number at a specific position is valid according to Sudoku rules.
     *
     * @param board  the 9x9 Sudoku grid.
     * @param number the number to be placed.
     * @param row    the row index of the cell.
     * @param col    the column index of the cell.
     * @return {@code true} if the placement is valid; {@code false} otherwise.
     */
    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        // Check row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // Check the small grid
        int subgridRowStart = (row / 3) * 3;
        int subgridColStart = (col / 3) * 3;

        for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
            for (int j = subgridColStart; j < subgridColStart + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Prints the Sudoku board to the console.
     *
     * @param board the 9x9 Sudoku grid to be printed.
     */
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
