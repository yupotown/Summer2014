import java.io.ByteArrayInputStream;
import java.io.IOException;


public class Summer2014 {

	public static void main(String[] args) throws IOException, SudokuFormatException {
		// 問題の読み込み
		Sudoku problem = new SudokuReader().read(new ByteArrayInputStream((
				"009300000" + CharCode.NL
				+ "007009000" + CharCode.NL
				+ "000000810" + CharCode.NL
				+ "080000000" + CharCode.NL
				+ "610020000" + CharCode.NL
				+ "000000503" + CharCode.NL
				+ "000900000" + CharCode.NL
				+ "005007004" + CharCode.NL
				+ "020000060"
				).getBytes()));
		
		// 問題を表示
		System.out.println(problem);
		
		SudokuSolver solver = new SudokuSolver();
		
		// 時間計測開始
		long startTime = System.currentTimeMillis();
		
		// 問題を解く
		SudokuSolverResult result = solver.solve(problem);
		
		// 時間計測終了
		long endTime = System.currentTimeMillis();
		
		if (result.hasAnswer()) {
			// 解があれば1つ出力
			System.out.println(result.getAnswer(0));
			System.out.println("Number of answers: " + result.getAnswersCount());
		} else {
			// 解なし
			System.out.println("There's no answer.");
		}
		
		// 計測した時間を出力
		System.out.println((endTime - startTime) + "ms");
	}

}
