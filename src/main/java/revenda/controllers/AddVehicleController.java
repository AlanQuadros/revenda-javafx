package revenda.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import revenda.models.Vehicle;
import revenda.services.VehicleService;

public class AddVehicleController {
    @FXML
    private GridPane grid;
    @FXML
    private TextField tfBrand;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfColor;
    @FXML
    private TextField tfPlate;
    @FXML
    private TextField tfValue;

    private final VehicleService vehicleService = VehicleService.getInstance();

    @FXML
    public void initialize() {
        grid.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));
    }

    @FXML
    public void onClickSave() {
        Alert alert;

        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setBrand(tfBrand.getText());
            vehicle.setModel(tfModel.getText());
            vehicle.setColor(tfColor.getText());
            vehicle.setPlate(tfPlate.getText());
            vehicle.setValue(Double.parseDouble(tfValue.getText()));

            boolean success = vehicleService.addVehicle(vehicle);

            if (success) {
                clearTextFields();

                alert = new Alert(
                        Alert.AlertType.INFORMATION,
                        "Veículo cadastrado com sucesso.",
                        ButtonType.OK
                );
                alert.setHeaderText("Cadastro");
                alert.showAndWait();
            }
        } catch (Exception e) {
            alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Ocorreu um erro ao cadastrar o veículo.",
                    ButtonType.OK
            );
            alert.setHeaderText("Erro");
            alert.showAndWait();
        }
    }

    @FXML
    public void onClickClear() {
        clearTextFields();
    }

    public void clearTextFields() {
        tfBrand.setText("");
        tfModel.setText("");
        tfColor.setText("");
        tfPlate.setText("");
        tfValue.setText("");
    }
}
