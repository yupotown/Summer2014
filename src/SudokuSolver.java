import java.util.*;


public class SudokuSolver {

	public SudokuSolver() {
	}
	
	public SudokuSolverResult solve(Sudoku problem) {
		SudokuSolverResultBuilder builder = new SudokuSolverResultBuilder(problem);
		
		_State state = new _State();
		state.sudoku = problem.clone();
		for (int x = 0; x < 9; ++x) {
			for (int y = 0; y < 9; ++y) {
				for (int n = 0; n < 9; ++n) {
					state.cand[x][y][n] = true;
				}
			}
		}
		
		return _solve(builder, state).build();
	}
	
	private SudokuSolverResultBuilder _solve(SudokuSolverResultBuilder builder, _State state) {
		if (!state.sudoku.isCorrect()) {
			return builder;
		}
		
		_State next = state.clone();
		
		// ���肤�鐔�����i��
		for (int x = 0; x < 9; ++x) {
			for (int y = 0; y < 9; ++y) {
				if (next.sudoku.get(x, y) != 0) {
					for (int n = 0; n < 9; ++n) {
						next.cand[x][y][n] = false;
					}
					next.cand[x][y][next.sudoku.get(x, y) - 1] = true;
					continue;
				}
				for (int n = 0; n < 9; ++n) {
					if (!next.cand[x][y][n]) {
						continue;
					}
					int blockX = (x / 3) * 3, blockY = (y / 3) * 3;
					int offsetX = x % 3, offsetY = y % 3;
					for (int i = 0; i < 9; ++i) {
						if (
							(i != x && next.sudoku.get(i, y) == n+1)
							|| (i != y && next.sudoku.get(x, i) == n+1)
							|| (i != offsetY * 3 + offsetX && next.sudoku.get(blockX + i % 3, blockY + i / 3) == n+1)
						) {
							next.cand[x][y][n] = false;
							break;
						}
					}
				}
			}
		}
		
		// �e�}�X�ɓ��鐔�����
		// ����������������Ȃ��Ƃ��낪1�ł�����Ή��Ȃ�
		// ���肤�鐔����1�����̂Ƃ���͊m��
		List<List<List<Integer>>> nums = new ArrayList<List<List<Integer>>>();
		for (int x = 0; x < 9; ++x) {
			List<List<Integer>> row = new ArrayList<List<Integer>>();
			for (int y = 0; y < 9; ++y) {
				List<Integer> cell = new ArrayList<Integer>();
				for (int n = 0; n < 9; ++n) {
					if (next.cand[x][y][n]) {
						cell.add(n + 1);
					}
				}
				if (cell.isEmpty()) {
					return builder;
				}
				if (cell.size() == 1) {
					next.sudoku.set(x,  y, cell.get(0));
				}
				row.add(cell);
			}
			nums.add(row);
		}
		
		// ���鐔���ɂ��āA���ꂪ1�̍s�A��܂��̓u���b�N���ɓ������ꏊ��1�ӏ������Ȃ���Ίm��
		for (int n = 0; n < 9; ++n) {
			for_i: for (int i = 0; i < 9; ++i) {
				int rowX = 0, rowY = 0, rowCnt = 0;
				int colX = 0, colY = 0, colCnt = 0;
				int blockX = 0, blockY = 0, blockCnt = 0;
				for (int j = 0; j < 9; ++j) {
					{
						// i ���
						int x = i, y = j;
						if (next.sudoku.get(x, y) == n+1) {
							continue for_i;
						}
						if (next.cand[x][y][n]) {
							++rowCnt;
							rowX = x;
							rowY = y;
						}
					}
					{
						// i �s��
						int x = j, y = i;
						if (next.sudoku.get(x,  y) == n+1) {
							continue for_i;
						}
						if (next.cand[x][y][n]) {
							++colCnt;
							colX = x;
							colY = y;
						}
					}
					{
						// i �u���b�N
						int x = (i % 3) * 3 + j % 3, y = (i / 3) * 3 + j / 3;
						if (next.sudoku.get(x, y) == n+1) {
							continue for_i;
						}
						if (next.cand[x][y][n]) {
							++blockCnt;
							blockX = x;
							blockY = y;
						}
					}
				}
				if (rowCnt == 1) {
					next.sudoku.set(rowX, rowY, n+1);
				}
				if (colCnt == 1) {
					next.sudoku.set(colX, colY, n+1);
				}
				if (blockCnt == 1) {
					next.sudoku.set(blockX, blockY, n+1);
				}
			}
		}
		
		// ���ׂẴ}�X�����܂��Ă���΂��ꂪ����
		if (next.sudoku.isFilled()) {
			builder.addAnswer(next.sudoku);
		} else {
			// ���܂��Ă��Ȃ��}�X������΁A�����ɓ��鐔���̌�₪2�ȏ゠��
			// ���ꂼ������肵�ĉ���������
			List<Integer> cell = null;
			int cellX = 0, cellY = 0;
			for_x: for (int x = 0; x < 9; ++x) {
				List<List<Integer>> row = nums.get(x);
				for (int y = 0; y < 9; ++y) {
					List<Integer> temp = row.get(y);
					if (temp.size() >= 2) {
						cell = temp;
						cellX = x;
						cellY = y;
						break for_x;
					}
				}
			}
			
			for (Integer n : cell) {
				next.sudoku.set(cellX, cellY, n.intValue());
				this._solve(builder, next);
			}
		}
		
		return builder;
	}
	
	private class _State implements Cloneable {
		public Sudoku sudoku = new Sudoku();
		
		// cand[x][y][n] : �}�X (x, y) �ɐ��� n+1 �����肤�邩 
		public boolean[][][] cand = new boolean[9][9][9];
		
		public _State clone() {
			_State res = new _State();
			
			res.sudoku = this.sudoku.clone();
			for (int x = 0; x < 9; ++x) {
				for (int y = 0; y < 9; ++y) {
					for (int n = 0; n < 9; ++n) {
						res.cand[x][y][n] = this.cand[x][y][n];
					}
				}
			}
			return res;
		}
		
	}
	
}
