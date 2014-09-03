import java.util.Arrays;


public class Sudoku {

	public Sudoku() {
	}
	
	/**
	 * 1つのマスの値を取得する。
	 * @param x マスのX座標(0 <= x <= 8)
	 * @param y マスのY座標(0 <= y <= 8)
	 * @return (x, y) のマスの値(空白は0)
	 * @throws IllegalArgumentException
	 */
	public int get(int x, int y) throws IllegalArgumentException {
		if (x < 0 || y < 0 || x >= 9 || y >= 9) {
			throw new IllegalArgumentException();
		}
		return cells[x][y];
	}
	
	/**
	 * 1つのマスの値を設定する。
	 * @param x マスのX座標(0 <= x <= 8)
	 * @param y マスのY座標(0 <= y <= 8)
	 * @param value (x, y) のマスに設定する値(0 <= value <= 9, 空白は0)
	 * @throws IllegalArgumentException
	 */
	public void set(int x, int y, int value) throws IllegalArgumentException {
		if (x < 0 || y < 0 || x >= 9 || y >= 9) {
			throw new IllegalArgumentException();
		}
		if (value < 0 || value >= 10) {
			throw new IllegalArgumentException();
		}
		cells[x][y] = value;
	}
	
	/**
	 * 現在のマス全体の状態が数独のルールに沿っているか否かを取得する。
	 * すなわち、同じ行、列またはブロック内に(0以外の)同じ数字が2つ以上入ってる場合は false を返す。
	 * @return マス全体の状態が数独のルールに沿っているか
	 */
	public boolean isCorrect() {
		// 同じ行に同じ数字が2つ以上入っていたら false
		for (int y = 0; y < 9; ++y) {
			boolean[] contains = new boolean[9];
			for (int x = 0; x < 9; ++x) {
				if (cells[x][y] == 0) {
					continue;
				}
				if (contains[cells[x][y] - 1]) {
					return false;
				}
				contains[cells[x][y] - 1] = true;
			}
		}
		
		// 同じ列に同じ数字が2つ以上入っていたら false
		for (int x = 0; x < 9; ++x) {
			boolean[] contains = new boolean[9];
			for (int y = 0; y < 9; ++y) {
				if (cells[x][y] == 0) {
					continue;
				}
				if (contains[cells[x][y] - 1]) {
					return false;
				}
				contains[cells[x][y] - 1] = true;
			}
		}

		// 同じブロック内に同じ数字が2つ以上入っていたら false
		for (int block = 0; block < 9; ++block) {
			boolean[] contains = new boolean[9];
			for (int i = 0; i < 9; ++i) {
				int x = (block % 3) * 3 + i % 3;
				int y = (block / 3) * 3 + i / 3;
				if (cells[x][y] == 0) {
					continue;
				}
				if (contains[cells[x][y] - 1]) {
					return false;
				}
				contains[cells[x][y] - 1] = true;
			}
		}
		
		return true;
	}
	
	/**
	 * マスがすべて(0以外の)数字で埋まっているか否かを取得する。
	 * @return マスがすべて数字で埋まっているか
	 */
	public boolean isFilled() {
		for (int y = 0; y < 9; ++y) {
			for (int x = 0; x < 9; ++x) {
				if (cells[x][y] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int res = 0;
		for (int i = 0; i < 9; ++i) {
			res += cells[i][i];
		}
		return res;
	}
	
	@Override
	public boolean equals(Object rhs) {
		if (rhs == null) {
			return false;
		}
		if (this.getClass() != rhs.getClass()) {
			return false;
		}
		Sudoku sudoku = (Sudoku)rhs;
		return Arrays.deepEquals(this.cells, sudoku.cells);
	}
	
	@Override
	public String toString() {
		String res = "";
		for (int y = 0; y < 9; ++y) {
			if (y == 3 || y == 6) {
				res += "---+---+---" + CharCode.NL;
			}
			for (int x = 0; x < 9; ++x) {
				if (x == 3 || x == 6) {
					res += "|";
				}
				if (cells[x][y] == 0) {
					res += CharCode.SPACE;
				} else {
					res += cells[x][y];
				}
			}
			res += CharCode.NL;
		}
		return res;
	}
	
	private int[][] cells = new int[9][9];
	
}
