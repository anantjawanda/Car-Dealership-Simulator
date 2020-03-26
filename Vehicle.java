/** 
	Anant Jawanda	500894314
	The Vehicle class contains instance variables that describe details of a vehicle.
	It has details that can be obtained using the get methods, and changed using the set methods.
*/
public class Vehicle {

	private String mfr;
	private String color;
	private int numWheels;
	private int power;
	public static final int ELECTRIC_MOTOR = 0;
	public static final int GAS_ENGINE = 1;

	/*
		Constructs a vehicle with an empty manufacturer
	*/
	public Vehicle(){
		this.mfr = "";
	}

	/** 
		Constructor that initializes the instance variables
		@param mfr (manufacturer name)
		@param color (color of vehicle)
		@param power (type of engine, later set to String)
		@param numWheels (number of wheels)
	*/
	public Vehicle(String mfr, String color, int power, int numWheels){
		this.mfr = mfr;
		this.color = color;
		this.power = power;
		this.numWheels = numWheels;
	}

	/*
		Get and Set methods for Mfr
	*/
	public String getMfr(){
		return mfr;
	}

	public void setMfr(String mfr){
		this.mfr = mfr;
	}

	/*
		Get and Set methods for color
	*/
	public String getColor(){
		return color;	
	}

	public void setColor(String color){
		this.color = color;
	}

	/*
		Get and Set methods for power
		sets to type of engine type according to number inputted
	*/
	public String getPower(){
		if (this.power == 1){
			return "GAS_ENGINE";
		} else {
			return "ELECTRIC_MOTOR";
		}
	}

	public void setPower(int power){
		this.power = power;
	}

	/*
		Get and Set methods for numWheels
	*/
	public int getNumWheels(){
		return numWheels;
	}

	public void setNumWheels(int numWheels){
		this.numWheels = numWheels;
	}

	/*
		Compares two vehicles objects for equality based on mfr, power, and numWheels
	*/
	public boolean equals(Object other){			
		Vehicle otherVehicle = (Vehicle) other;		
		return this.equals(otherVehicle.getMfr()) && this.equals(otherVehicle.getPower()) && this.equals(otherVehicle.getNumWheels());
	}	

	/*
		returns a string containing the Vehicle manufacturer and color seperated by spaces
	*/
	public String display(){
		return getMfr() + " " + getColor();
	}

}