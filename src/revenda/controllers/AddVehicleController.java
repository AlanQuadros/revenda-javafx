package revenda.controllers;

import revenda.models.Resale;
import revenda.models.Vehicle;

public class AddVehicleController {
    private final Resale resale = Resale.getInstance();
    private final ListVehicleController listVehicleController = ListVehicleController.getInstance();

    public boolean saveVehicle(String brand, String model, String color, String plate, double value) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setPlate(plate);
        vehicle.setValue(value);

        listVehicleController.addNewRow(vehicle);

        return resale.addVehicle(vehicle);
    }

    public void clear() {
        listVehicleController.columnData.forEach(System.out::println);
    }
}
