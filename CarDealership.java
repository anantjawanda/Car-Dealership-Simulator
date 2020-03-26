import java.util.*;
/** 
	Anant Jawanda	500894314
	The CarDealership class is where objects of type Car are added to a Car type Arraylist.
    Methods to sort, filter, add and remove Car objects are found in this class.
*/

public class CarDealership {

	private ArrayList<Car> cars;	    
	private int boughtCarIndex;
	private boolean filterElectric, filterAWD, filterPrice, filterClear;
	private double minPrice, maxPrice;

    /*
        Constructor method that creates an empty arrayList of Car objects and sets it to the 'cars' instance variable
    */
	public CarDealership(){
        cars = new ArrayList<Car>();
		boughtCarIndex = 0; 		
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
        }
	}

    /**
		method that removes a Car object at the index inputted
		@param index (index parameter that is a reference to where from the list the car object should be removed from)
	*/
	public Car buyCar(int index) {
		if (index < cars.size() && index >= 0) {
			boughtCarIndex = index;
            Car indexHolder = cars.get(index);
			cars.remove(index);
            return indexHolder;
		} else {
			return null;
		}
	}

    /**
		method that adds back the Car object that was last bought(removed) 
		@param index (index parameter that is a reference to where from the list the car object should be removed from)
	*/
	public void returnCar(Car car){
		if (car != null) {
			cars.add(car);
		}
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
				System.out.println(i + " " + cars.get(i).display());
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