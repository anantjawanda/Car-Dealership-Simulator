import java.util.*;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/** 
	Anant Jawanda	500894314
	The CarDealership class is where objects of type Car are added to a Car type Arraylist.
    Methods to sort, filter, add and remove Car objects are found in this class.
*/

public class CarDealership {

	ArrayList<Car> cars;
    ArrayList<Car> allCars;
    Car carTest = new Car();
    Car returnedCar = new Car();
    public AccountingSystem accounting = new AccountingSystem();
    SalesTeam salesT = new SalesTeam();
    Random rand = new Random();
    Transaction returnTransaction = new Transaction();
    Transaction buyTransaction = new Transaction();
    private int dateBought, monthBought, weekdayBought;
    private int year = 2019;
    private String salesP;
	private int boughtCarIndex;
	private boolean filterElectric, filterAWD, filterPrice, filterClear;
	private double minPrice, maxPrice;
    private int boughtCarIndex2;

    /*
        Constructor method that creates an empty arrayList of Car objects and sets it to the 'cars' instance variable
    */
	public CarDealership(){
        cars = new ArrayList<Car>();
        allCars = new ArrayList<Car>();
		boughtCarIndex = 0;
        boughtCarIndex2 = 0;	
        this.filterElectric = false;
        this.filterAWD = false;
        this.filterPrice = false;
        this.filterClear = false;									
	}

    /**
		method that adds a Car object to the current arraylist of cars
		@param newCars (a reference array list of car objects)
	*/
	public void addCars(ArrayList<Car> newCars) {
		for (int i=0; i<newCars.size(); i++){
            cars.add(newCars.get(i));
            allCars.add(newCars.get(i));
        }
	}

    /**
		method that removes cars from the arraylist and creates a transaction object
		@param VIN (vin # related to the vehicle user wants to buy)
	*/
    public String buyCar(int VIN){
        String output = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YYYY");
        Calendar calendar = Calendar.getInstance();
        if (cars.size() > 0) {
            for (int i=0;i<cars.size();i++){
                if (cars.get(i).displayVIN() == VIN){       //checks if vin number matches
                    carTest = cars.get(i);
                    cars.remove(i);         //removes the car object from arraylist

                    salesP = salesT.returnSalesPerson();        //returns random salesperson
                    monthBought = rand.nextInt((11-0) + 1);
                    dateBought = rand.nextInt((30-0) + 1);  
                    calendar = new GregorianCalendar(year, monthBought, dateBought);        //creates new calendar object
                    accounting.add(calendar, carTest, salesP, "BUY", carTest.getPrice());       
                    buyTransaction = new Transaction(accounting.getRandomID(), calendar, carTest, salesP, "BUY", carTest.getPrice());       //new transaction is created with type "BUY"
                    output = buyTransaction.display() + "\n";
                }
            }
        }
        System.out.println(output);
        return output;
    }

    /**
		method that adds last bought car to arraylist
		@param transaction (transaction # related to the vehicle user wants to return)
	*/
    public void returnCar(int transaction){
        if (buyTransaction.getID() == transaction){
            returnedCar = buyTransaction.transactionCar();
            String output2 = "";
            if (cars.size() > 0) {
            
                for (int i=0;i<allCars.size();i++){
                    if (allCars.get(i) == returnedCar){
                        cars.add(returnedCar);
                        carTest = allCars.get(i);       //checks the returning car with the whole list of cars (not including bought ones)
                    }
                } 
                    int returnDay = rand.nextInt((31-dateBought) + 1) + dateBought;     //return date is same month, but later date
                    int returnMonth = monthBought;
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd YYYY");
                    Calendar returnCalendar = new GregorianCalendar(year, returnMonth, returnDay);
                    accounting.add(returnCalendar, returnedCar, salesP, "RET", returnedCar.getPrice());
                    returnTransaction = new Transaction(transaction, returnCalendar, carTest, salesP, "RET", returnedCar.getPrice());  //new transaction is created with type "RET"
                    output2 = returnTransaction.display() + "\n";
                    System.out.println(output2);
            }
        } 
    }

    //returns bought transaction object
    public Transaction returnBoughtTransaction() {
        return buyTransaction;
    }

    //returns return transaction object
    public Transaction returnReturnedTransaction(){
        return returnTransaction;
    }

    //returns salesteam members
    public String returnSalesTeam(){
        return salesT.displayAll();
    }

    //returns salesteam object
    public SalesTeam returnTeam(){
        return salesT;
    }

    /**
		prints the information about the cars in the array list, according to the filters set.
        Depending on which filter is set, or if all set, then only the objects that apply will print
	*/
	public void displayInventory(){
		boolean displayIt = true;
         
        for (int i=0; i<cars.size(); i++) {
            displayIt = true;
            if (filterPrice) {
                if (! (cars.get(i).getPrice() <= maxPrice) && (cars.get(i).getPrice() >= minPrice)) {
                    displayIt = false;}}
            if (filterElectric) {
                if (!(cars.get(i).getPower().equals("ELECTRIC_MOTOR"))){
                    displayIt = false;}} 
            if (filterAWD) {
                if (!cars.get(i).getAWD()) {
                    displayIt = false;}} 
            if (displayIt == true) {
				System.out.println(cars.get(i).display());
            } 
        }
    }


    /*
        Filter methods that set the filter booleans to true when called
    */
	public void filterByElectric() {
		this.filterElectric = true;
	}

	public void filterByAWD(){
		this.filterAWD = true;
	}

	public void filterByPrice(double minPrice, double maxPrice){
		this.filterPrice = true;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

    /*
        clears all the filters by resetting the filter booleans back to false
    */
	public void filtersClear(){
		this.filterElectric = false;
		this.filterAWD = false;
		this.filterPrice = false;
	}

    /*
        Inner comparator clases that implement the Comparator interfaces. Since safetyRating/range
        do not implement comparable, comparator allows to sort based on different keys
    */
    class safetyComparator implements Comparator<Car> {             
        public int compare(Car a, Car b){                    
            if (a.getSafety() < b.getSafety()) return 1;
            if (a.getSafety() > b.getSafety()) return -1;
            return 0;
        }
    }

    class rangeComparator implements Comparator<Car> {
        public int compare(Car a, Car b){
            if (a.getRange() < b.getRange()) return 1;
            if (a.getRange() > b.getRange()) return -1;
            return 0;
        }
    }

    /*
        sorts arraylist based on the maxRange, using the rangeComparator)
    */
    public void sortByMaxRange() {
        Collections.sort(cars, new rangeComparator());
	}

    /*
        sorts arraylist based on price, no need to use comparators
    */
	public void sortByPrice(){
		Collections.sort(cars);
    }

    /*
        sorts arraylist based on the safetyRatings, using the safetyCompartor)
    */
	public void sortBySafetyRating() {
        Collections.sort(cars, new safetyComparator());
	}






}   