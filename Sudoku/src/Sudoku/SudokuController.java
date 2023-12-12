package Sudoku;

import javax.swing.*;
import java.awt.*;

/**
 * En controller-klass för att hantera användargränssnittet för Sudoku-applikationen.
 */
public class SudokuController {

    /**
     * Skapar en instans av SudokuController.
     * @param s SudokuModel som används för att lösa och hantera sudoku-brädet.
     */
    public SudokuController(SudokuModel s) {
        SwingUtilities.invokeLater(() -> createWindow(s, "Sudoku", 500, 500));
    }

    /**
     * Skapar huvudfönstret för Sudoku-applikationen.
     * @param s SudokuModel som används för att lösa och hantera sudoku-brädet.
     * @param title Titeln på fönstret.
     * @param width Bredden på fönstret.
     * @param height Höjden på fönstret.
     */
    private static void createWindow(SudokuModel s, String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        
        // Användargränsnitt för sudoku-modellen
        SudokuField sudokuField = new SudokuField();

        // Implementering av knapparna Solve och Clear
        JPanel buttonSection = new JPanel();
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");
        
        // Solve-knappens aktionslyssnare
        solveButton.addActionListener(e -> {
            s.setBoard(sudokuField.getBoard());
            if (s.testFile()) {
                if (!s.solve()) {
                    JOptionPane.showMessageDialog(null, "Det finns ingen lösning!");
                    s.clear();
                } else {
                    sudokuField.setBoard(s);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Detta sudoku går inte att lösa!");
            }
        });

        // Clear-knappens aktionslyssnare
        clearButton.addActionListener(e -> {
            s.clear();
            sudokuField.setBoard(s);
        });

        // Lägg till komponenter i knappanelen
        buttonSection.add(solveButton);
        buttonSection.add(clearButton);

        // Lägg till komponenter i huvudinnehållet
        pane.add(sudokuField, BorderLayout.NORTH);
        pane.add(buttonSection, BorderLayout.CENTER);

        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
