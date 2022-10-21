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
	 * 外部呼叫函式, 印出結果
	 */
	public void printPuzzleResult() {
		for(int i=0; i<width; i++) {
			// 初始化list內容為false
			List<List<Boolean>> blockUse = new ArrayList<List<Boolean>>();
			for(int j=0; j<width; j++) {
				List<Boolean> row = new ArrayList<Boolean>();
				for(int k=0; k<width; k++)
					row.add(false);	
				blockUse.add(row);
			}
			
			// 找出puzzle組合
			findPuzzleResult(0, i, blockUse);		
		}
		
		// 沒有為空, 否則印出
		if(puzzle.size()==0)
			System.out.println("no result");
		else
			printPuzzle();	
	}
	
	/*
	 * 找出puzzle結果
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
		
		// 左上
		for(int c=1; row-c>=0 && col-c>=0; c++)
			tmpUse.get(row-c).set(col-c, true);
		
		// 右下
		for(int c=1; row+c<width && col+c<width; c++)
			tmpUse.get(row+c).set(col+c, true);
		
		// 左下
		for(int c=1; row+c<width && col-c>=0; c++)
			tmpUse.get(row+c).set(col-c, true);
		
		// 右上
		for(int c=1; row-c>=0 && col+c<width; c++)
			tmpUse.get(row-c).set(col+c, true);
		
		// 橫
		for(int c=1; col-c >= 0; c++)
			tmpUse.get(row).set(col-c, true);
		for(int c=1; col+c < width; c++)
			tmpUse.get(row).set(col+c, true);
		
		// 縱
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
	 * 印出Puzzle結果
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
