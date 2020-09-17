package revenda.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import revenda.models.Resale;
import revenda.models.Vehicle;

import java.io.IOException;

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

    Resale resale = Resale.getInstance();

    @FXML
    public void initialize() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableVehicle.setPlaceholder(new Label("Nenhum veículo cadastrado."));
        tableVehicle.setItems(resale.columnData);

        tableVehicle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>() {
            @Override
            public void changed(ObservableValue<? extends Vehicle> observable, Vehicle oldValue, Vehicle newValue) {
//                System.out.println(observable.getValue());

                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/revenda/views/edit_vehicle.fxml"));
                    loader.load();

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
        });
    }
}
