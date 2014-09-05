import java.io.*;


public class Summer2014 {

	public static void main(String[] args) throws IOException, SudokuFormatException {
		// 問題の読み込み
		Sudoku problem;
		if (args.length == 0) {
			problem = new SudokuReader().read(System.in);
		} else {
			problem = new SudokuReader().read(new FileInputStream(args[0]));
		}
		
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
