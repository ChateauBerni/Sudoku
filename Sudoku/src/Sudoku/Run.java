package Sudoku;

/**
 * Klass för att köra Sudoku-applikationen.
 */
public class Run {

    /**
     * Main-metod för att starta applikationen.
     * @param args Kommandoradsargument (används inte här).
     */
    public static void main(String[] args) {
        // Skapa en ny instans av SudokuModel
        SudokuModel sm = new SudokuModel();
        
        // Sätt brädet till en tom 9x9 matris
        sm.setBoard(new int[9][9]);

        // Skapa en instans av SudokuController och skicka med SudokuModel
        SudokuController sc = new SudokuController(sm);
    }
}
