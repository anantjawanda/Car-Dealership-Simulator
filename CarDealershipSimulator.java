import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

/** 
	Anant Jawanda 500894314
	Is the 'tester' class where it contains the main method and is user interaction component of the program.
	Creates a carDealership object where all the Car objects will be added to. Then, according to the user input,
	a corresponding method will execute, otherwise exceptions will be thrown.
*/

public class CarDealershipSimulator 
{
  public static void main(String[] args) throws FileNotFoundException
{
		CarDealership dealer1 = new CarDealership();
		ArrayList<Car> carArray = new ArrayList<Car>();
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		int transactionNum = 0;
		
		File file = new File("cars.txt");
		Scanner readFile = new Scanner(file);

		/* the scanner reads from the txt file and according to their engine type (gas/electric), it will add the according
		information to the corresponding car type. Since it is reading from a txt, it must parse the integers/booleans*/
		while (readFile.hasNextLine()){
			Scanner line = new Scanner(readFile.nextLine());
			if (Integer.parseInt(line.next()) == 1){
				carArray.add(new Car(1, line.next(), line.next(), Integer.parseInt(line.next()) ,line.next(),
				Integer.parseInt(line.next()), Double.parseDouble(line.next()), Boolean.parseBoolean(line.next()), Double.parseDouble(line.next())));
			} else { 
				carArray.add(new ElectricCar(0, line.next(), line.next(), Integer.parseInt(line.next()), line.next(),
					Integer.parseInt(line.next()), Double.parseDouble(line.next()), Boolean.parseBoolean(line.next()),
					Double.parseDouble(line.next()), Integer.parseInt(line.next()), "Lithium"));
			}
		} 
		
		int VIN;
		Scanner scanner = new Scanner(System.in);
		String input = "";
		Scanner commandLine = null;
		String nextWord = "";
		while (scanner.hasNext()){	
			try {	
			input = scanner.nextLine();
			commandLine = new Scanner(input);
			nextWord = commandLine.next();
			} catch (NoSuchElementException noElement) {
				System.out.println("Try again");
			}
			if (nextWord.equalsIgnoreCase("L")) {								//calls the CarDealership classes, displayInventory() method
				dealer1.displayInventory();
			} else if (nextWord.equalsIgnoreCase("Q")) {				//quits out of the program
				System.exit(0);

			} else if (nextWord.equalsIgnoreCase("SALES")) {
				try {
					String result ="";
					if (commandLine.hasNextInt()) {
						int nextInteger = commandLine.nextInt();			//if sales is followed by a #, then the sales for that month will appear
						for (Transaction t: transactionList){
							if (nextInteger == t.getMonth()){
								System.out.println(t.display());}
							else result = "No transactions during this month";}
						System.out.println(result);
					} else if (commandLine.hasNext()) {
					String str = commandLine.next();
						if (str.equalsIgnoreCase("TEAM")){						//displays the sales team
							System.out.println(dealer1.returnSalesTeam());
						} else if (str.equalsIgnoreCase("TOPSP")){				//displays the sales person who sold the cars
							int d,j,f,g,trav;
							d=j=f=g=trav=0;
							for (Transaction tr: transactionList){
								if (tr.getType().equals("BUY")){						//checks the frequencies of each salesperson in the transactions
								if (tr.getSP().equalsIgnoreCase("Drake")) d++;
								else if (tr.getSP().equalsIgnoreCase("Jorja")) j++;
								else if (tr.getSP().equalsIgnoreCase("Frank")) f++;
								else if (tr.getSP().equalsIgnoreCase("Gambino")) g++;
								else if (tr.getSP().equalsIgnoreCase("Travis")) trav++;}}
								if (d>j && d>f && d>g && d>trav) System.out.println("Top SP: Drake " + d + "\n");
								else if (j>d && j>f && j>g && j>trav) System.out.println("Top SP: Jorja " + j + "\n");
								else if (f>d && f>j && f>g && f>trav) System.out.println("Top SP: Frank " + f + "\n");
								else if (g>d && g>j && g>f && g>trav) System.out.println("Top SP: Gambino " + g + "\n");
								else if (trav>d && trav>j && trav>f && trav>g) System.out.println("Top SP: Travis " + trav + "\n");
								else System.out.println("More than one sales person has sold same amount of cars!");
								
						} else if (str.equalsIgnoreCase("STATS")) {							//displays all the stats of the program
							double sum = 0;
							int totalCarsBought = 0;
							int totalCarsReturned = 0;
							int jan, feb, mar, apr, may, jun, jul, aug, sept, oct, nov, dec;
							jan = feb = mar = apr = may = jun = jul = aug = sept = oct = nov = dec = 0;
							ArrayList<Integer> list = new ArrayList<Integer>();
							String bestMonth = "";
							for (Transaction t: transactionList){
								if (t.getType().equals("BUY")){			//month incrementor to keep track of best month, put in arraylist and using maxCollections function
									totalCarsBought++;
									sum += t.getSalesPrice();
									if (t.getMonth() == 0) jan++;
									else if (t.getMonth() == 1) feb++;
									else if (t.getMonth() == 2) mar++;
									else if (t.getMonth() == 3) apr++;
									else if (t.getMonth() == 4) may++;
									else if (t.getMonth() == 5) jun++;
									else if (t.getMonth() == 6) jul++;
									else if (t.getMonth() == 7) aug++;
									else if (t.getMonth() == 8) sept++;
									else if (t.getMonth() == 9) oct++;
									else if (t.getMonth() == 10) nov++;
									else if (t.getMonth() == 11) dec++;}

								else if (t.getType().equals("RET")){
									totalCarsReturned++;
								}
										
								list.add(jan);
								list.add(feb);
								list.add(mar);
								list.add(apr);
								list.add(may);
								list.add(jun);
								list.add(jul);
								list.add(aug);
								list.add(sept);
								list.add(oct);
								list.add(nov);
								list.add(dec);

							int highestMonth = Collections.max(list);
							bestMonth = t.returnMonth(highestMonth);}
							int averageSales = (int) sum / 12;
							System.out.println("Total sales: " + sum + " Total sold: " + totalCarsBought +
							" Average Sales: " + averageSales + " Total Returned: " + totalCarsReturned +
							" Best Month: " + bestMonth);}
					} else {
							for (Transaction t: transactionList){			//displays all the transactions
								System.out.println(t.display());}}
				} catch (NullPointerException a){
				}

			} else if (nextWord.equalsIgnoreCase("BUY")){					//buys the car/removes it from dealership
				try { 
					VIN = commandLine.nextInt();
					dealer1.buyCar(VIN);		
					transactionList.add(dealer1.returnBoughtTransaction());		//adds the transactions to the transaction list
				} catch (NoSuchElementException c) {
					System.out.println("No VIN was inputted!");
				}
			} else if (nextWord.equalsIgnoreCase("RET")){						//returns the most last bought car
					transactionNum = dealer1.returnBoughtTransaction().getID();		//obtains transaction id from last boughtCar 
					dealer1.returnCar(transactionNum);
					transactionList.add(dealer1.returnReturnedTransaction());		//adds the transactions to the transaction list
			}
			 else if (nextWord.equalsIgnoreCase("ADD")) {			//calls the addsCars() method and adds car to dealer arraylist
				dealer1.addCars(carArray);
			} else if (nextWord.equalsIgnoreCase("SPR")) {			//sorts by price, using metohd from CarDealership
				dealer1.sortByPrice();
			} else if (nextWord.equalsIgnoreCase("SSR")) {			//sorts by safetyRating, using metohd from CarDealership
				dealer1.sortBySafetyRating();
			} else if (nextWord.equalsIgnoreCase("SMR")) {			//sorts by maxRange, using metohd from CarDealership
				dealer1.sortByMaxRange();
			} else if (nextWord.equalsIgnoreCase("FPR")) {			//includes car objects from the range inputted, using filter method from CarDealership
				double minPrice = commandLine.nextInt();
				double maxPrice = commandLine.nextInt();
				dealer1.filterByPrice(minPrice, maxPrice);
			} else if (nextWord.equalsIgnoreCase("FEL")) {			//includes car objects to only electric cars, using filter method from CarDealership
				dealer1.filterByElectric();
			} else if (nextWord.equalsIgnoreCase("FAW")) {			//includes car objects that are only AWD, using filter method from CarDealership
				dealer1.filterByAWD();
			} else if (nextWord.equalsIgnoreCase("FCL")) {			//clears all the filters applied, using filter clear method from CarDealership
				dealer1.filtersClear();
			} else {
				System.out.println("Invalid, input again");
			}
			
		}   
    }
}










