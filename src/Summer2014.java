import java.io.ByteArrayInputStream;
import java.io.IOException;


public class Summer2014 {

	public static void main(String[] args) throws IOException, SudokuFormatException {
		// ���̓ǂݍ���
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
