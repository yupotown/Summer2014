import java.io.*;


public class SudokuReader {
	
	public SudokuReader() {
	}
	
	public Sudoku read(InputStream is) throws IOException, SudokuFormatException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		Sudoku res = new Sudoku();
		for (int y = 0; y < 9; ++y) {
			String line = reader.readLine();
			for (int x = 0; x < 9; ++x) {
				try {
					int n = Character.getNumericValue(line.charAt(x));
					if (n < 0 || n >= 10) {
						throw new SudokuFormatException();
					}
					res.set(x, y, n);
				}
				catch (IndexOutOfBoundsException e) {
					throw new SudokuFormatException();
				}
			}
		}
		return res;
	}

}
