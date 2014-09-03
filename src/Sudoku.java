import java.util.Arrays;


public class Sudoku {

	public Sudoku() {
	}
	
	/**
	 * 1�̃}�X�̒l���擾����B
	 * @param x �}�X��X���W(0 <= x <= 8)
	 * @param y �}�X��Y���W(0 <= y <= 8)
	 * @return (x, y) �̃}�X�̒l(�󔒂�0)
	 * @throws IllegalArgumentException
	 */
	public int get(int x, int y) throws IllegalArgumentException {
		if (x < 0 || y < 0 || x >= 9 || y >= 9) {
			throw new IllegalArgumentException();
		}
		return cells[x][y];
	}
	
	/**
	 * 1�̃}�X�̒l��ݒ肷��B
	 * @param x �}�X��X���W(0 <= x <= 8)
	 * @param y �}�X��Y���W(0 <= y <= 8)
	 * @param value (x, y) �̃}�X�ɐݒ肷��l(0 <= value <= 9, �󔒂�0)
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
	 * ���݂̃}�X�S�̂̏�Ԃ����Ƃ̃��[���ɉ����Ă��邩�ۂ����擾����B
	 * ���Ȃ킿�A�����s�A��܂��̓u���b�N����(0�ȊO��)����������2�ȏ�����Ă�ꍇ�� false ��Ԃ��B
	 * @return �}�X�S�̂̏�Ԃ����Ƃ̃��[���ɉ����Ă��邩
	 */
	public boolean isCorrect() {
		// �����s�ɓ���������2�ȏ�����Ă����� false
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
		
		// ������ɓ���������2�ȏ�����Ă����� false
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

		// �����u���b�N���ɓ���������2�ȏ�����Ă����� false
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
	 * �}�X�����ׂ�(0�ȊO��)�����Ŗ��܂��Ă��邩�ۂ����擾����B
	 * @return �}�X�����ׂĐ����Ŗ��܂��Ă��邩
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
