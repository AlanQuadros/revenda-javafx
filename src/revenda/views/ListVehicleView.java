package revenda.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import revenda.controllers.ListVehicleController;
import revenda.models.Vehicle;

public class ListVehicleView {
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

    private final ListVehicleController listVehicleController = ListVehicleController.getInstance();

    @FXML
    public void initialize() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        tableVehicle.setPlaceholder(new Label("Nenhum veículo cadastrado."));
        tableVehicle.setItems(listVehicleController.columnData);
    }
}
