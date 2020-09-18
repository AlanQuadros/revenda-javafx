package revenda.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Resale {
    private static Resale instance;
    public ObservableList<Vehicle> vehicles;

    private Resale() {
        this.vehicles = FXCollections.observableArrayList();
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

    public Vehicle findVehicleByPlate(String plate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle;
            }
        }

        return null;
    }

    public boolean editVehicle(String plate, Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlate().equalsIgnoreCase(plate)) {
                vehicles.remove(i);
                vehicles.add(i, vehicle);
                return true;
            }
        }
        return false;
    }
}
