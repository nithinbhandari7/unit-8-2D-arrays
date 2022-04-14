import java.util.Arrays;

/*
In main:
1. Declare and instantiate on a single line a two-dimensional array called intArr that holds int
values and has 5 rows and 8 columns.
2. Fill in each cell of the intArr array from the previous question with the result of multiplying
that cell's column index by its row index

Programming Examples
/Example 1:  Find the sum of all elements in a matrix (2-d array) mat
/Example 2: Add 10 to each element in row n of a matrix mat
/Example 3: Write a method that returns a int which is equal to the sum of the 
	major left to right diagonal of a matrix
	precondition: matrix is a square matrix
/Example 4: Write a method that returns an array which holds the values of the 
	sum of each row of a matrix
/Example 5: Write a method that returns an array which holds the values of the sum 
	of each column in a matrix
/Example 6: write a function that returns the sum of two matrices of the same size
/Example 7: write a function that returns the sum of a specified (by parameter) row of a 2d array
/Example 8: write a function that returns the sum of a specified (by parameter) column of a 2d array
/Example 9: swap all elements in the Ath and Bth row of a 2d array
/Example 10: Given a 2-d array, re-order the rows such that the row with the highest row sum is
the first row
Example 11: Write a function called largestColumnFirst(). This function takes a two-dimensional
array of integers, finds the column with the largest sum, and switches it with the first
column in the matrix
Example 12: write the function transpose(). This function takes a two-d array of
integers, representing a square n Ã— n matrix, and swaps the row and column of each
element (i.e. reflects the contents over the main diagonal).

For example: 
6 7 8 0	  6 3 1 2
3 2 4 5   7 2 5 0
1 5 8 2   8 4 8 9
2 0 9 3   0 5 2 3
 */
public class Notes {
	public static void main(String[] args) {
		int[][] intArr = new int[5][8];
		intArr[1][3] = 100;
		
		for(int i = 0; i < intArr.length; i++) {//intArr.length is the number of rows
			for(int j = 0; j < intArr[i].length; j++) {
				System.out.print(intArr[i][j] + "\t");
			}
			System.out.println();
		}
		
		for(int[] row : intArr) {
			for(int entry : row) {
				System.out.print(entry + "\t");
			}
			System.out.println();
		}
		
		int[][] square = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		
		System.out.println(Arrays.toString(getRowSums(square)));
		System.out.println(Arrays.toString(getColumnSums(square)));
		System.out.println(getRowSum(square, 1));
		System.out.println(getColumnSum(square, 1));
		//swapRows(square, 0, 1);
		print2D(square);
		System.out.println();
		transpose(square);
		print2D(square);
	}
	
	public static void print2D(int[][] intArr) {
		for(int i = 0; i < intArr.length; i++) {
			for(int j = 0; j < intArr[i].length; j++) {
				System.out.print(intArr[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static int sum2D(int[][] intArr) {
		int sum = 0;
		for(int i = 0; i < intArr.length; i++) {
			for(int j = 0; j < intArr[i].length; j++) {
				sum += intArr[i][j];
			}
		}
		return sum;
	}
	
	public static void add10ToRow(int[][] intArr, int r) {
		for(int i = 0; i < intArr[r].length; i++) {
			intArr[r][i] += 10;
		}
	}
	
	public static int diagonal(int[][] intArr) {
		int sum  = 0;
		for(int i = 0; i < intArr.length; i++) {
			sum += intArr[i][i];
		}
		return sum;
	}
	
	public static int[][] sum2ds(int[][] intArr1, int[][] intArr2){
		int[][] sum = new int[intArr1.length][intArr1[0].length];
		for(int i = 0; i < intArr1.length; i++) {
			for(int j = 0; j < intArr1[i].length; j++) {
				sum[i][j] = intArr1[i][j] + intArr2[i][j];
			}
		}
		return sum;
	}
	
	public static int[] getRowSums(int[][] intArr) {
		int[] sums = new int[intArr.length];
		for(int i = 0; i < intArr.length; i++) {
			for(int j = 0; j < intArr[i].length; j++) {
				sums[i] += intArr[i][j];
			}
		}
		return sums;
	}
	
	public static int[] getColumnSums(int[][] intArr) {
		int[] sums = new int[intArr[0].length];
		for(int i = 0; i < intArr.length; i++) {
			for(int j = 0; j < intArr[i].length; j++) {
				sums[j] += intArr[i][j];
			}
		}
		return sums;
	}
	
	public static int getRowSum(int[][] intArr, int row) {
		int sum = 0;
		for(int j = 0; j < intArr[row].length; j++) {
			sum += intArr[row][j];
		}
		return sum;
	}
	
	public static int getColumnSum(int[][] intArr, int column) {
		int sum = 0;
		for(int i = 0; i < intArr.length; i++) {
			sum += intArr[i][column];
		}
		return sum;
	}
	
	public static void swapRows(int[][] intArr, int a, int b) {
		int[] rowA = new int[intArr[0].length];
		int[] rowB = new int[intArr[0].length];
		int[] temp = new int[intArr[0].length];
		rowA = intArr[a];
		rowB = intArr[b];
		temp = rowA;
		rowA = rowB;
		rowB = temp;
	}
	
	public static void swapCols(int[][] ar, int c, int d) {
		for(int i = 0; i < ar.length; i++) {
			int temp = ar[i][c];
			ar[i][c] = ar[i][d];
			ar[i][d] = temp;
		}
	}
	
	public static int findIndexOfMax(int[] ar) {
		int maxIndex = 0;
		for(int i = 0; i < ar.length; i++) {
			if(ar[i] < ar[maxIndex]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public static void largestRowFirst(int[][] ar) {
		int maxIndex = findIndexOfMax(getRowSums(ar));
		swapRows(ar, 0, maxIndex);
	}
	
	public static void transpose(int[][] ar) {
		int[][] newAr = new int[ar.length][ar[0].length];
		for(int i = 0; i < ar.length; i++) {
			for(int j = 0; j < ar[0].length; j++) {
				newAr[i][j] = ar[i][j];
			}
		}
		
		for(int i = 0; i < ar.length; i++) {
			for(int j = 0; j < ar[0].length; j++) {
				ar[i][j] = newAr[j][i];
			}
		}
	}
}