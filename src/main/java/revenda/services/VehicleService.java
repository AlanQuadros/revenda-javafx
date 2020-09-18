package revenda.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import revenda.models.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VehicleService {
    Database database;

    public ObservableList<Vehicle> listVehicles() throws SQLException {
        database = new Database();

        try (
                Statement stmnt = database.connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from vehicle")
        ) {
            ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setPlate(rs.getString("plate"));
                vehicle.setValue(rs.getDouble("value"));

                vehicles.add(vehicle);
            }

            database.shutdown();

            return vehicles;
        }
    }
}
