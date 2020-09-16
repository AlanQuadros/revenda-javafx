package revenda.controllers;

import revenda.models.Resale;
import revenda.models.Vehicle;

public class FindVehicleController {
    Resale resale = Resale.getInstance();

    public Vehicle find(String plate) {
        return resale.findVehicleByPlate(plate);
    }
}
