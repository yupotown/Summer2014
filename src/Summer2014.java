import java.io.*;


public class Summer2014 {

	public static void main(String[] args) throws IOException, SudokuFormatException {
		// ���̓ǂݍ���
		Sudoku problem;
		if (args.length == 0) {
			problem = new SudokuReader().read(System.in);
		} else {
			problem = new SudokuReader().read(new FileInputStream(args[0]));
		}
		
		// ����\��
		System.out.println(problem);
		
		SudokuSolver solver = new SudokuSolver();
		
		// ���Ԍv���J�n
		long startTime = System.currentTimeMillis();
		
		// ��������
		SudokuSolverResult result = solver.solve(problem);
		
		// ���Ԍv���I��
		long endTime = System.currentTimeMillis();
		
		if (result.hasAnswer()) {
			// ���������1�o��
			System.out.println(result.getAnswer(0));
			System.out.println("Number of answers: " + result.getAnswersCount());
		} else {
			// ���Ȃ�
			System.out.println("There's no answer.");
		}
		
		// �v���������Ԃ��o��
		System.out.println((endTime - startTime) + "ms");
	}

}
