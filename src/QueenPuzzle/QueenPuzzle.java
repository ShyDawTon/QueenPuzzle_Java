package QueenPuzzle;
import java.util.*;
import java.util.stream.Collectors;

public class QueenPuzzle {
	private int width;
	private List<List<List<Boolean>>> puzzle;
	
	public QueenPuzzle() {
		this(8);
	}
	
	public QueenPuzzle(int width) {
		this.width = width;
		puzzle = new ArrayList<List<List<Boolean>>>();
	}
	
	/*
	 * �~���I�s�禡, �L�X���G
	 */
	public void printPuzzleResult() {
		for(int i=0; i<width; i++) {
			// ��l��list���e��false
			List<List<Boolean>> blockUse = new ArrayList<List<Boolean>>();
			for(int j=0; j<width; j++) {
				List<Boolean> row = new ArrayList<Boolean>();
				for(int k=0; k<width; k++)
					row.add(false);	
				blockUse.add(row);
			}
			
			// ��Xpuzzle�զX
			findPuzzleResult(0, i, blockUse);		
		}
		
		// �S������, �_�h�L�X
		if(puzzle.size()==0)
			System.out.println("no result");
		else
			printPuzzle();	
	}
	
	/*
	 * ��Xpuzzle���G
	 */
	private void findPuzzleResult(int row, int col, List<List<Boolean>> blockUse) {		
		// list copy
		List<List<Boolean>> tmpUse = new ArrayList<List<Boolean>>();
		for(int i=0; i<width; i++) {
			List<Boolean> rd = new ArrayList<Boolean>();
			for(int j=0; j<width; j++)
				rd.add(blockUse.get(i).get(j));	
			tmpUse.add(rd);
		}
		
		// ���W
		for(int c=1; row-c>=0 && col-c>=0; c++)
			tmpUse.get(row-c).set(col-c, true);
		
		// �k�U
		for(int c=1; row+c<width && col+c<width; c++)
			tmpUse.get(row+c).set(col+c, true);
		
		// ���U
		for(int c=1; row+c<width && col-c>=0; c++)
			tmpUse.get(row+c).set(col-c, true);
		
		// �k�W
		for(int c=1; row-c>=0 && col+c<width; c++)
			tmpUse.get(row-c).set(col+c, true);
		
		// ��
		for(int c=1; col-c >= 0; c++)
			tmpUse.get(row).set(col-c, true);
		for(int c=1; col+c < width; c++)
			tmpUse.get(row).set(col+c, true);
		
		// �a
		for(int c=1; row-c >= 0; c++)
			tmpUse.get(row-c).set(col, true);
		for(int c=1; row+c < width; c++)
			tmpUse.get(row+c).set(col, true);
		
		if(row==width-1) {
			puzzle.add(tmpUse);
			return;
		}
		
		//printtest(row, tmpUse);
		
		for(int j=0; j<width; j++)
			if(!tmpUse.get(row+1).get(j))
				findPuzzleResult(row+1, j, tmpUse);		
	}
	
	/*
	 * �L�XPuzzle���G
	 */
	private void printPuzzle() {
		for(List<List<Boolean>> p : puzzle) {
			for(int i=0; i<width; i++) {
				for(int j=0; j<width; j++)
					if(p.get(i).get(j))
						System.out.print('.');
					else
						System.out.print('Q');
				System.out.println();
			}
			System.out.println();
		}
	}
}
