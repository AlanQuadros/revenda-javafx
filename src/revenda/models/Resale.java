package revenda.models;

import java.util.ArrayList;

public class Resale {
    private static Resale instance;
    private final ArrayList<Vehicle> vehicles;

    private Resale() {
        this.vehicles = new ArrayList<>();
    }

    public static synchronized Resale getInstance() {
        if (instance == null) {
            instance = new Resale();
        }
        return instance;
    }

    public boolean addVehicle(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return vehicles.remove(vehicle);
    }

    public ArrayList<Vehicle> listVehicles() {
        return this.vehicles;
    }

    public Vehicle findVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle;
            }
        }

        return null;
    }
}
