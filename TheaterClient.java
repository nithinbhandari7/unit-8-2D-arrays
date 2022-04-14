import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class TheaterClient {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("##.00");
		boolean repeat = true;
		boolean invalid = false;
		int option = 0;
		Theater theater = new Theater("seats.txt");
		while(repeat == true) {
			if(invalid == false) {
				System.out.println("These are the options presented: \n (1) Purchase Tickets \n (2) Display the total dollar value of all tickets sold \n (3) Display the total number of tickets sold \n (4) Display the number of seats available in each row \n (5) Display the number of seats available in the entire auditorium \n (6) Display the current seating chart \n (7) Exit the system");
				System.out.print("Please select an option by entering the number that is associated with the option: ");
				option = sc.nextInt();
			}
			if(option == 1) {
				purchaseTickets(theater);
			} else if(option == 2) {
				System.out.println("$" + df.format(theater.getTotalSold()));
			} else if(option == 3) {
				System.out.println(theater.getNumSeatsTaken());
			} else if(option == 4) {
				seatsAvailableInEachRow(theater);
			} else if(option == 5) {
				System.out.println(450 - theater.getNumSeatsTaken());
			} else if(option == 6) {
				System.out.println(theater);
			} else if(option == 7) {
				repeat = false;
			} else {
				while(option < 1 || option > 7) {
					System.out.print("Please enter a valid option: ");
					option = sc.nextInt();
					invalid = true;
				}
			}
		}
		System.out.println("Thank You!");
		sc.close();
	}
	
	private static boolean isColumn(String str) {
		boolean isColumn = false;
		String[] seatCols = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d"};
		for(int i = 0; i < seatCols.length; i++) {
			if(seatCols[i].equals(str)) {
				isColumn = true;
			}
		}
		return isColumn;
	}
	
	private static int indexOfColumn(String str) {
		int index = 0;
		String[] seatCols = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d"};
		for(int i = 0; i < seatCols.length; i++) {
			if(seatCols[i].equals(str)) {
				index = i;
			}
		}
		return index;
	}
	
	private static void purchaseTickets(Theater theater) {
		Scanner sc = new Scanner(System.in);
		boolean wantToContinue = true;
		int rowNum;
		String colLetter = "";
		while(wantToContinue) {
			System.out.print("Please enter the row number: ");
			rowNum = sc.nextInt();
			if(rowNum == -1) {
				wantToContinue = false;
				return;
			} else if(rowNum > 15 || rowNum < 1) {
				while(rowNum > 15 || rowNum < 1) {
					System.out.print("That row is out of bounds, please choose another row: ");
					rowNum = sc.nextInt();
				}
			} else if(theater.getNumSeatsTakenInRow(rowNum - 1) == 30) {
				while(theater.getNumSeatsTakenInRow(rowNum - 1) == 30) {
					System.out.print("That row has been completely taken, please choose another row: ");
					rowNum = sc.nextInt();
				}
			}
			if(wantToContinue == true) {
				System.out.print("Please enter the column letter: ");
				colLetter = sc.next();
				if(!(isColumn(colLetter))) {
					while(!(isColumn(colLetter))) {
						System.out.print("Please enter a valid column letter: ");
						colLetter = sc.next();
					}
				} else if(theater.isTaken(rowNum - 1, indexOfColumn(colLetter))) {
					System.out.println("Sorry, that seat is taken please choose another seat.");
				}
			}
			theater.buySeat(rowNum - 1, indexOfColumn(colLetter));
		}
		System.out.println("The total value of all the tickets sold is $" + theater.getTotalSold());
		sc.close();
	}
	
	private static void seatsAvailableInEachRow(Theater theater) {
		int[] available = new int[15];
		for(int i = 0; i < available.length; i++) {
			available[i] = 30 - theater.getNumSeatsTakenInRow(i);
		}
		System.out.println(Arrays.toString(available));
	}
}
