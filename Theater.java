import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Theater {
	private String[][] theater = new String[15][30];
	private double[] rowPrices = new double[15];
	
	public Theater(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));
		for(int i = 0; i < theater.length; i++) {
			for(int j = 0; j < theater[i].length; j++) {
				theater[i][j] = "#";
			}
		}
		
		for(int i = 0; i < rowPrices.length; i++) {
			rowPrices[i] = scan.nextDouble();
		}
	}
	
	public String toString() {
		String str = "";
		str += "	A B C D E F G H I J K L M N O P Q R S T U V W X Y Z a b c d" + "\n";
		for(int i = 0; i < theater.length; i++) {
			str += (i + 1) + "	";
			for(int j = 0; j < theater[i].length; j++) {
				str += theater[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}
	
	public void buySeat (int r, int c) {
		theater[r][c] = "*";
	}
	
	public int getNumSeatsTaken() {
		int num = 0;
		for(int i = 0; i < theater.length; i++) {
			for(int j = 0; j < theater[i].length; j++) {
				if(theater[i][j].equals("*")) {
					num += 1;
				}
			}
		}
		return num;
	}
	
	public int getNumSeatsTakenInRow(int r) {
		int num = 0;
		for(int j = 0; j < theater[r].length; j++) {
			if(theater[r][j].equals("*")) {
				num += 1;
			}
		}
		return num;
	}
	
	public boolean isTaken(int r, int c) {
		if(theater[r][c].equals("*")) {
			return true;
		} else {
			return false;
		}
	}
	
	public double getTotalSold() {
		double sum = 0;
		for(int i = 0; i < theater.length; i++) {
			sum += (double)getNumSeatsTakenInRow(i) * rowPrices[i];
		}
		return sum;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Theater t = new Theater("seats.txt");
		System.out.println(t);
		t.buySeat(0, 0);
		t.buySeat(14, 29);
		System.out.println(t);
		System.out.println(t.getNumSeatsTaken());
		System.out.println(t.getTotalSold());
		System.out.println(t.getNumSeatsTakenInRow(0));
		System.out.println(t.getNumSeatsTakenInRow(1));
	}
}
