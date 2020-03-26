import java.util.*;
import java.util.Scanner;

/** 
	Anant Jawanda 500894314
	Is the 'tester' class where it contains the main method and is user interaction component of the program.
	Creates a carDealership object where all the Car objects will be added to. Then, according to the user input,
	a corresponding method will execute.
*/

public class CarDealershipSimulator 
{
  public static void main(String[] args)
  {

		CarDealership dealer1 = new CarDealership();
		ArrayList<Car> carArray = new ArrayList<Car>();
		ArrayList<Car> boughtCars = new ArrayList<Car>();

		carArray.add(new Car("Toyota", "blue", 1, 4, "SEDAN", 500, 9.5, false, 25000));
		carArray.add(new Car("Honda", "red", 1, 4, "SPORTS", 450, 9.2, false, 30000));
		carArray.add(new Car("Kia", "white", 1, 4, "MINIVAN", 550, 9.7,false, 20000));
		carArray.add(new Car("BMW", "black", 1, 4, "SEDAN", 600, 9.6, true, 55000));
		carArray.add(new ElectricCar("Tesla", "red", 0, 4, "SEDAN", 425, 9.1, true, 85000, 30, "Lithium"));
		carArray.add(new Car("Chevy", "red", 1, 4, "MINIVAN", 475, 9.25, false, 40000));
		carArray.add(new ElectricCar("ChevyVolt", "green", 0, 4, "SEDAN", 375, 8.9, true, 37000, 45, "Lithium"));
		carArray.add(new Car("Bentley", "black", 1, 4, "SEDAN", 575, 9.8, false, 150000));
		carArray.add(new ElectricCar("NissanLeaf", "green", 0, 4, "SEDAN", 325, 8.8, true, 32000, 55, "Lithium"));

		Scanner scanner = new Scanner(System.in);
		String input = "";
		Scanner commandLine = null;
		String nextWord = "";
		while (scanner.hasNext()){	
			try {	
			input = scanner.nextLine();
			commandLine = new Scanner(input);
			nextWord = commandLine.next();
			}  catch (NoSuchElementException noElement) {
			}
			if (nextWord.equalsIgnoreCase("L")) {								//calls the CarDealership classes, displayInventory() method
				dealer1.displayInventory();
			} else if (nextWord.equalsIgnoreCase("Q")) {				//quits out of the program
				System.exit(0);
			} else if (nextWord.equalsIgnoreCase("BUY")) {			//user inputs an index alongside the command and calls CarDealership buyCar method()
				boughtCars.clear();
				while (commandLine.hasNextInt()){										//makes sure that an index is inputted after "BUY", then adds it to bought index and removes
					int boughtIndex = commandLine.nextInt();
					boughtCars.add(dealer1.buyCar(boughtIndex));}
			} else if (nextWord.equalsIgnoreCase("RET")) {			//returns the last car that was bought, using boughtIndex
					dealer1.addCars(boughtCars);
					boughtCars.clear(); 		//or removeALl()
			} else if (nextWord.equalsIgnoreCase("ADD")) {			//calls the addsCars() method and adds car to dealer arraylist
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










