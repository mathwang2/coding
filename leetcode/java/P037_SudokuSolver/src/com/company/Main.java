package com.company;

public class Main {

    public static void main(String[] args) {

	    SudokuSolver solver = new SudokuSolver(SudokuExamples.s1);
	    int totSteps = solver.solve();
	    int[][] solution = solver.outputFilled();

	    System.out.printf("Tot steps: %d. Solutions %s!\n", totSteps, checkSudokuArray(solution)?"correct":"incorrect");

	    for (int x=0; x<9; ++x){
	        for (int y=0; y<9; ++y){
	            System.out.printf("%d", solution[x][y]);
            }
            System.out.printf("\n");
        }
    }

    public static boolean check1line(int[] line){
    	int[] num = new int[9];
    	for (int i = 0; i<line.length;++i){
    		num[line[i]-1]++;
		}



    	for (int i=0; i<num.length; ++i){
    		if (num[i]==0) return false;
		}

		return true;
	}

	public static boolean checkSudokuArray(int[][] s){
    	for (int x=0; x<9;++x){
    		int[] num = new int[9];
    		for (int y=0; y<9; ++y){
    			num[y]=s[x][y];
			}
			if(!check1line(num)) return false;
		}

		for (int x=0; x<9;++x){
			int[] num = new int[9];
			for (int y=0; y<9; ++y){
				num[y]=s[y][x];
			}
			if(!check1line(num)) return false;
		}

		for (int x=0; x<3;++x){
			for (int y=0; y<3; ++y){
				int[] num = new int[9];
				for (int x2=0; x2<3;++x2){
					for (int y2=0; y2<3;++y2){
						num[x2*3+y2]=s[x*3+x2][y*3+y2];
					}
				}
				if(!check1line(num)) return false;
			}
		}

		return true;
	}
}
