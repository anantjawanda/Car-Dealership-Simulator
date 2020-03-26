/** 
	Anant Jawanda	500894314
	The Car class adds more instance variables onto the extended Vehicle class.
	It implements the Comparable<Car> interface to sort Car objects by price.
*/
public class Car extends Vehicle implements Comparable<Car> {

	private String model;
	private int maxRange;
	private double safetyRating;
	private boolean AWD;
	private double price;
	private final String SEDAN = "SEDAN";
	private final String SUV = "SUV";
	private final String SPORTS = "SPORTS";
	private final String MINIVAN = "MINIVAN";


	/** 
		Constructor that initializes the instance variables, and inherits the variables from the class Vehicle as well
		@param model (type of Vehicle)
		@param maxRange (max fuel range of car)
		@param safetyRating (safety rating of car)
		@param AWD (All wheel drive boolean)
		@param price (price of car)
	*/
	public Car(){
		this.model = "";
	}

	public Car(int power2, String mfr2, String color2, int numWheels2, String model, int maxRange, double safetyRating, boolean AWD, double price) {
		super(power2, mfr2, color2, numWheels2);
		this.model = model;
		this.maxRange = maxRange;
		this.safetyRating = safetyRating;
		this.AWD = AWD;
		this.price = price;
	}

	/*
		returns a string containing the variables from Vehicle, and the new variables from this Car class
	*/
	public String display(){
		String first = super.display();
		return first + " " + model + " " + price + "$" + " SF: " + safetyRating + " " + " RNG: " + maxRange;
	}

	public int displayVIN(){
		int vinNum = super.displayVIN();
		return vinNum;
	}
	/*
		Compares two Car objects for equality by first checking if inherited variables are equal by calling the superclass equals method.
		Then checks equality based on model and AWD
	*/
	public boolean equals(Object other) {
		Car otherCar = (Car) other;
		boolean equals1 = super.equals(otherCar);
		if (equals1) {
			return this.model.equals(otherCar.model) && this.AWD == otherCar.getAWD();
		} else {
			return equals1;
		}
	}

	/*
		Get methods for instance variables
	*/
	public double getPrice(){
		return price;
	}

	public int getRange(){
		return maxRange;
	}

	public double getSafety(){
		return safetyRating;
	}

	public boolean getAWD(){
		return AWD;
	}

	/** 
		Method that incorporates the comparable interface and compares prices of Car objects
		@return 1 if this Car object comes first, -1 if it comes second and 0 if they are the same
	*/
	public int compareTo(Car other){                    
		if (this.price > other.price) return 1;
		if (this.price < other.price) return -1;
		return 0;
	}

}