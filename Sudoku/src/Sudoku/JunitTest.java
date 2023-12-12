package Sudoku;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JunitTest {
	private SudokuModel board;

	@BeforeEach
	void setUp() throws Exception {
		this.board = new SudokuModel();
	}

	@AfterEach
	void tearDown() throws Exception {
		board = null;
	}

	@Test
	void testEmpty() {
		int[][] matrix = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		board.setBoard(matrix);
		assertEquals(true, board.solve(), "Solve was not true for empty board");
	}

	@Test
	void testFigure1() {
		int[][] figure1 = {
				{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
				{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
				{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		board.setBoard(figure1);
		assertEquals(true, board.solve(), "Solve was not true for figure 1");
	}

	@Test
	void testUnsolvable() {
		int[][] figure2 = {
				{ 0, 9, 8, 0, 0, 9, 0, 6, 2 }, // 2 nior i samma rad
				{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
				{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
				{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		board.setBoard(figure2);
		assertEquals( false,board.solve(), "Solve was not false for unsolvable board, same row");

		int[][] figure3 = {
				{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
				{ 0, 5, 0, 0, 0, 0, 6, 0, 5 }, // 2 femmor i samma kolumn
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
				{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		board.setBoard(figure3);
		assertEquals(
				false,
				board.solve(),
				"Solve was not false for unsolvable board, same column"
				);

		int[][] figure4 = {
				{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
				{ 0, 8, 0, 0, 0, 0, 0, 0, 5 }, // 2 åttor samma region
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
				{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
				{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		board.setBoard(figure4);
		assertEquals(false,board.solve(),"Solve was not false for unsolvable board, same box");
	}

	@Test
	void testLegal() {
		int[][] matrix = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		board.setBoard(matrix);
		board.set(0, 0, 1);
		assertEquals(false,board.isLegal(1, 2, 1), "Legal gave wrong value for illegal inputs");
		assertEquals(true,board.isLegal(1, 3, 3),"Legal gave wrong value for legal inputs");
	}

	@Test
	void testGetMatrix() {
		int[][] figure1 = {
				{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
				{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
				{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		board.setBoard(figure1);

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				assertEquals(
						figure1[row][col],
						board.getBoard()[row][col],
						"Wrong return for getMatrix"
						);
			}
		}
	}

	@Test
	void testClear() {
		int[][] figure1 = {
				{ 0, 5, 8, 0, 0, 9, 0, 5, 2 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
				{ 0, 0, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
				{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
				{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 },
		};
		board.setBoard(figure1);
		assertFalse(board.solve());
		board.clear();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				assertEquals( 0, board.getBoard()[row][col], "Wrong return for getMatrix");
			}
		}
	}

}
