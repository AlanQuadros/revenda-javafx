package revenda.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import revenda.models.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VehicleService {
    private static VehicleService instance;
    Database database;
    public ObservableList<Vehicle> vehicles;

    private VehicleService() {
        vehicles = FXCollections.observableArrayList();
    }

    public static synchronized VehicleService getInstance() {
        if (instance == null) {
            instance = new VehicleService();
        }
        return instance;
    }

    public boolean addVehicle(Vehicle vehicle) throws SQLException {
        database = new Database();

        try (PreparedStatement statement =
                     database.connection.prepareStatement("INSERT INTO vehicle (brand, model, color, plate, value) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, vehicle.getBrand());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getColor());
            statement.setString(4, vehicle.getPlate());
            statement.setDouble(5, vehicle.getValue());

            statement.executeUpdate();
            vehicles.add(vehicle);
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }

    public ObservableList<Vehicle> listVehicles() throws SQLException {
        database = new Database();

        try (
                Statement statement = database.connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from vehicle")
        ) {
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
