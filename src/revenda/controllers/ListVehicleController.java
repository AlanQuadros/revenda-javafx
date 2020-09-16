package revenda.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import revenda.models.Resale;
import revenda.models.Vehicle;
import revenda.views.ListVehicleView;

public class ListVehicleController {
    Resale resale = Resale.getInstance();
    public ObservableList<Vehicle> columnData;

    public ListVehicleController() {
        this.columnData = FXCollections.observableArrayList(resale.listVehicles());

//        this.columnData.add(new Vehicle("a", "a", "a", "a", 22));

//        this.columnData.addListener(new ListChangeListener<Vehicle>() {
//            @Override
//            public void onChanged(Change<? extends Vehicle> c) {
//                System.out.println(c);
//            }
//        });
    }

    public void addNewRow(Vehicle vehicle) {
//        ListVehicleView.tableVehicle.getItems().clear();
        this.columnData.add(vehicle);
        ListVehicleView.tableVehicle.getItems().forEach(System.out::println);
        ListVehicleView.tableVehicle.setItems(this.columnData);
    }
}
