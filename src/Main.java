import java.util.Scanner;
import QueenPuzzle.QueenPuzzle;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int width = scanner.nextInt();
		QueenPuzzle qp = new QueenPuzzle(width);
		qp.printPuzzleResult();
	}
}
