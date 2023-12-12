package Sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * SudokuField representerar en grafisk användargränssnittskomponent för att visa och redigera ett 9x9 Sudoku-bräda.
 * Klassen nyttjar en privat inre klass kallad NumField för att skapa rutorna för varje nummer.
 */
public class SudokuField extends JPanel {
	/** Random shit som man var tvungen att lägga till för att få bort varningar */
	private static final long serialVersionUID = 1L;

	/** 2D-Array för att lagra NumField-instanser som representerar Sudoku-brädet. */
	private NumField[][] board;

	/**
	 * Konstruerar en SudokuField med ett 9x9 rutnät av NumField-instanser.
	 * Sätter standardvärden för Sudoku-brädet.
	 */
	public SudokuField() {
		setLayout(new GridLayout(9, 9));
		this.board = new NumField[9][9];

		// Initialisera Sudoku-brädet med standardvärden & färg för region.
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int regionRow = i / 3;
				int regionCol = j / 3;
				if ((regionRow + regionCol) % 2 == 0) {
					board[i][j] = new NumField(0,Color.RED);
				} else {
					board[i][j] = new NumField(0,Color.WHITE);
				}	 
				add(board[i][j]);
			}
		}
	}

	/**
	 * Sätter värdena för Sudoku-brädet baserat på den angivna SudokuModel.
	 * @param s SudokuModel som representerar den aktuella statusen för Sudoku-brädet.
	 */
	public void setBoard(SudokuModel s) {
		// Initialize the 9x9 matrix of NumFields
		int[][] temp = s.getBoard();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//Använder värden från den givna SudokuModellen
				board[i][j].set(temp[i][j]);
			}
		}
	}


	/**
	 * Hämtar den aktuella statusen för Sudoku-brädet som en 2D-array av heltal.
	 * @return Den aktuella Sudoku-brädet representerad som en 2D-array av heltal.
	 */
	public int[][]  getBoard() {
		int[][] temp = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				temp[i][j] = board[i][j].get();
			}
		}
		return temp;
	}


	/**
	 * Privat inre klass NumField representerar varje enskild cell i Sudoku-rutnätet.
	 */
	private class NumField extends JTextField {
		/**Honest to god har ingen aning vad serialVersionUID gör */
		private static final long serialVersionUID = 1L;

		/** Det numeriska värdet av NumField. */
		private int n = 0;

		/**
		 * Konstruerar ett NumField objekt med det angivna initialvärdet och bakgrundsfärgen.
		 * @param n Det initiala numeriska värdet av NumField.
		 * @param c Bakgrundsfärgen av NumField.
		 */
		public NumField(int n, Color c) {
			super();
			if(n != 0) {
				this.n = n;
				setText(Integer.toString(n));
			}
			setBackground(c);
			setPreferredSize(new Dimension(30, 30));
			setHorizontalAlignment(JTextField.CENTER);

			// Lägg till en dokumentlyssnare för att upptäcka ändringar i textfältet
			getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					updateValue();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					updateValue();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					updateValue();
				}
			});
		}

		/**
		 * Uppdaterar det numeriska värdet av NumField baserat på användarinput.
		 */
		private void updateValue() {
			try {
				int temp = Integer.parseInt(getText());
				if(temp >= 1 && temp <= 9) {
					this.n = temp;
				} else {
					// Hantera fallet när inmatningen inte är i det giltiga intervallet (1-9)
					JOptionPane.showMessageDialog(this, "I sudoku används endast siffror mellan 1 - 9");
					setText(""); // Reset the text field
					this.n = 0;
				}

			} catch (NumberFormatException ex) {
				// Hantera fallet när inmatningen inte är ett numersiktvärde
				if (!getText().isEmpty()) {
					JOptionPane.showMessageDialog(SudokuField.this, "I sudoku används endast siffror mellan 1 - 9");
					this.n = 0;
				}
				else this.n = 0;
			} 
		}

		/**
		 * Sätter det numeriska värdet för NumField och uppdaterar texten i fältet.
		 * Om det angivna värdet är 0 kommer textfältet att återställas.
		 * @param n Det numeriska värdet som ska sättas för NumField.
		 */
		public void set(int n) {
			if(n == 0) {
				setText("");
			} else {
				setText(Integer.toString(n));
			}
			this.n = n;
		}


		/**
		 * Hämtar det numeriska värdet av NumField.
		 * @return Det numeriska värdet av NumField.
		 */
		public int get() {
			return n;
		}

	}
}
