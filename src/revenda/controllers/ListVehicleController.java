package revenda.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import revenda.models.Resale;
import revenda.models.Vehicle;

public class ListVehicleController {
    private static ListVehicleController instance;
    Resale resale = Resale.getInstance();
    public ObservableList<Vehicle> columnData;

    private ListVehicleController() {
        this.columnData = FXCollections.observableArrayList(resale.listVehicles());
    }

    public static synchronized ListVehicleController getInstance() {
        if (instance == null) {
            instance = new ListVehicleController();
        }
        return instance;
    }

    public void addNewRow(Vehicle vehicle) {
        this.columnData.add(vehicle);
    }
}
