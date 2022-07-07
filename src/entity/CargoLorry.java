package entity;

public class CargoLorry extends Vehicle{

    public CargoLorry() {
    }

    public CargoLorry(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String parkedTime, String leaveTime, int slot) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers, parkedTime, leaveTime, slot);
    }

    public CargoLorry(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers, String driverName, String leaveTime) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers, driverName, leaveTime);
    }

    public CargoLorry(String vehicleNumber, String vehicleType, int maxWeight, int noOfPassengers) {
        super(vehicleNumber, vehicleType, maxWeight, noOfPassengers);
    }

}
