/** 
	Anant Jawanda	500894314
	The ElectricCar class is a simple class that is similar to parent classes, 
	where it adds more instance variables onto the extended Car class.
*/
public class ElectricCar extends Car {

	private int rechargeTime;
	private String batteryType;
    
	/**
		Constructor that initializes the instance variables
		@param rechargeTime (the time it takes in minutes, to recharge the battery)
		@param batteryType (the type of battery the ElectricCar object has)
	*/
	public ElectricCar(String mfr3, String color3, int power3, int numWheels3, String model3, int maxRange3, double safetyRating3, boolean AWD3, double price3, int rechargeTime, String batteryType) {
		super(mfr3, color3, power3, numWheels3, model3, maxRange3, safetyRating3, AWD3, price3);
		this.rechargeTime = rechargeTime;
		this.batteryType = batteryType;
	}

	/*
		Get and set methods for the instance variables
	*/
	public int getRechargeTime(){
		return rechargeTime;
	}

	public void setRechargeTime(int rechargeTime){
		this.rechargeTime = rechargeTime;
	}

	public String getBatteryType() {
		return batteryType;
	}

	public void setBatteryType(String batteryType){
		this.batteryType = batteryType;
	}

	/*
		returns a string containing the variables from parent classes, and the new variables from this ElectricCar class to the end
	*/
	public String display(){
		String second = super.display();
		return second + " EL, BAT: " + batteryType + " RCH: " + rechargeTime;
	}

}