package revenda.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import revenda.models.Vehicle;
import revenda.services.VehicleService;

import java.io.IOException;
import java.sql.SQLException;

public class ListVehicleController {
    @FXML
    private TableColumn<Vehicle, String> brandColumn;
    @FXML
    private TableColumn<Vehicle, String> modelColumn;
    @FXML
    private TableColumn<Vehicle, String> colorColumn;
    @FXML
    private TableColumn<Vehicle, String> plateColumn;
    @FXML
    private TableColumn<Vehicle, Number> valueColumn;
    @FXML
    public TableView<Vehicle> tableVehicle = new TableView<>();

    private final VehicleService vehicleService = new VehicleService();

    @FXML
    public void initialize() throws SQLException {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableVehicle.setPlaceholder(new Label("Nenhum veículo cadastrado."));
        tableVehicle.setItems(vehicleService.listVehicles());

        tableVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                onSelectRow(observable.getValue())
        );
    }

    public void onSelectRow(Vehicle vehicle) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/edit_vehicle.fxml"));
            loader.load();

            EditVehicleController editVehicleController = loader.getController();
            editVehicleController.populateFields(vehicle);
            Parent editRoot = loader.getRoot();

            Stage stage = new Stage();
            stage.setScene(new Scene(editRoot));
            stage.setTitle("Editar veículo");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
