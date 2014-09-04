import java.util.*;


public class SudokuSolverResultBuilder {
	
	public SudokuSolverResultBuilder(Sudoku problem) {
		this.problem = problem;
	}
	
	public void addAnswer(Sudoku answer) {
		if (!answers.contains(answer)) {
			answers.add(answer.clone());
		}
	}
	
	public SudokuSolverResult build() {
		return new SudokuSolverResult(problem, answers);
	}
	
	private Sudoku problem;
	private List<Sudoku> answers = new ArrayList<Sudoku>();

}
