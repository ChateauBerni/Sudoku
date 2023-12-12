package Sudoku;

/**
 * En modellklass som implementerar SudokuSolver och hanterar logiken för Sudoku-brädet.
 */
public class SudokuModel implements SudokuSolver {
    int[][] board;

    /**
     * Sätter brädet med givna värden.
     * @param board Brädet som ska användas för att lösa Sudoku.
     */
    @Override
    public void setBoard(int[][] board) {
        this.board = board;
    }

    /**
     * Returnerar det aktuella brädet.
     * @return Brädet för Sudoku.
     */
    @Override
    public int[][] getBoard() {
        return board;
    }

    /**
     * Publik tilgång till solve metoden
     * @return True om en lösning hittades, annars false.
     */
    @Override
    public boolean solve() {
        return solve(0, 0);
    }

    /**
     * Försöker rekursivt lösa Sudoku-brädet.
     * @param r Radposition.
     * @param c Kolumnposition.
     * @return True om en lösning hittades, annars false.
     */
    private boolean solve(int r, int c) {
        // Kollar om det är en komplett lösning
        if (r == 9) {
            return true;
        }
        // Kollar om slutet av raden har nåtts
        if (c == 9) {
            return solve(r + 1, 0);
        }

        // Kollar om cellen är satt av användaren
        if (board[r][c] != 0) {
            isLegal(r, c, board[r][c]);
            return solve(r, c + 1);
        }

        // Loop för att försöka placera siffror
        for (int i = 1; i <= 9; i++) {
            if (isLegal(r, c, i)) {
                set(r, c, i);

                // Försök lösa rekursivt
                if (solve(r, c + 1)) {
                    return true;
                }

                // Backtracka
                set(r, c, 0);
            }
        }

        // Om ingen siffra kunde placeras, returnera false
        return false;
    }

    /**
     * Kontrollerar om det är lagligt att placera en siffra i en viss rad och kolumn.
     * @param row Radposition.
     * @param col Kolumnposition.
     * @param nbr Siffran att kontrollera.
     * @return True om det är lagligt, annars false.
     */
    @Override
    public boolean isLegal(int row, int col, int nbr) {
        // Kolla raden
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i] == nbr) {
                return false;
            }
        }

        // Kolla kolumnen
        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][col] == nbr) {
                return false;
            }
        }

        // Kolla regioner
        int regRow = 3 * (row / 3);
        int regCol = 3 * (col / 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i + regRow) != row && (j + regCol) != col && board[i + regRow][j + regCol] == nbr) {
                    return false;
                }
            }
        }

        return true;
    }
    
    /**
     * Testar om brädet är korrekt enligt spelets regler.
     * @return True om brädet är korrekt, annars false.
     */
    public boolean testFile() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    if (!isLegal(i, j, board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returnerar värdet i en viss rad och kolumn på brädet.
     * @param row Radposition.
     * @param col Kolumnposition.
     * @return Siffran på den givna positionen.
     */
    @Override
    public int get(int row, int col) {
        return board[row][col];
    }

    /**
     * Sätter värdet i en viss rad och kolumn på brädet.
     * @param row Radposition.
     * @param col Kolumnposition.
     * @param nbr Siffran att sätta.
     */
    @Override
    public void set(int row, int col, int nbr) {
        board[row][col] = nbr;
    }

    /**
     * Rensar brädet genom att skapa en ny referensmodell.
     */
    @Override
    public void clear() {
        this.board = new int[9][9];
    }
}
