import java.util.*;


public class SudokuSolverResult {
	
	public SudokuSolverResult(Sudoku problem, List<Sudoku> answers) {
		this.problem = problem;
		this.answers = answers;
	}
	
	public boolean hasAnswer() {
		return !answers.isEmpty();
	}
	
	public int getAnswersCount() {
		return answers.size();
	}
	
	public Sudoku getProblem() {
		return problem;
	}
	
	public Sudoku getAnswer(int index) throws IndexOutOfBoundsException {
		return answers.get(index);
	}
	
	private Sudoku problem;
	private List<Sudoku> answers;

}
