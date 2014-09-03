import java.io.*;


public class SudokuWriter {
	
	public SudokuWriter() {
	}
	
	public void write(OutputStream os, Sudoku sudoku) throws IOException {
		for (int y = 0; y < 9; ++y) {
			for (int x = 0; x < 9; ++x) {
				os.write(String.valueOf(sudoku.get(x, y)).charAt(0));
			}
			os.write(CharCode.NL.getBytes());
		}
	}

}
