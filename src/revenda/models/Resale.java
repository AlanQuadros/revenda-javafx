package revenda.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Resale {
    private static Resale instance;
    private final ArrayList<Vehicle> vehicles;
    public ObservableList<Vehicle> columnData;

    private Resale() {
        this.vehicles = new ArrayList<>();
        this.columnData = FXCollections.observableArrayList(vehicles);
    }

    public static synchronized Resale getInstance() {
        if (instance == null) {
            instance = new Resale();
        }
        return instance;
    }

    public boolean addVehicle(Vehicle vehicle) {
        columnData.add(vehicle);
        return vehicles.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        columnData.remove(vehicle);
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
