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

    public VehicleService() {
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

    public Vehicle findVehicleByPlate(String plate) throws SQLException {
        database = new Database();

        try (PreparedStatement statement = database.connection.prepareStatement("SELECT * FROM vehicle where plate = ?")) {
            statement.setString(1, plate);
            ResultSet rs = statement.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setBrand(rs.getString("brand"));
                    vehicle.setModel(rs.getString("model"));
                    vehicle.setColor(rs.getString("color"));
                    vehicle.setPlate(rs.getString("plate"));
                    vehicle.setValue(rs.getDouble("value"));

                    return vehicle;
                }
            }
            return null;
        } catch (SQLException sqlException) {
            return null;
        }
    }

    public boolean editVehicle(String plate, Vehicle vehicle) throws SQLException {
        database = new Database();

        try (PreparedStatement statement = database.connection.prepareStatement("UPDATE vehicle SET brand = ?, model = ?, color = ?, value = ? where plate = ?")) {
            statement.setString(1, vehicle.getBrand());
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getColor());
            statement.setDouble(4, vehicle.getValue());
            statement.setString(5, plate);

            statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }

    public boolean removeVehicle(String plate) throws SQLException {
        database = new Database();

        try (PreparedStatement statement = database.connection.prepareStatement("DELETE FROM vehicle where plate = ?")) {
            statement.setString(1, plate);

            statement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            return false;
        }
    }
}
