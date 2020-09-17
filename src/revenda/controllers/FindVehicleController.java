package revenda.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import revenda.models.Resale;
import revenda.models.Vehicle;

public class FindVehicleController {
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
    @FXML
    private TextField tfFind;

    Resale resale = Resale.getInstance();

    @FXML
    public void initialize() {
        grid.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));
    }

    @FXML
    public void onFindClick() {
        clearTextFields();
        Alert alert;

        if (tfFind.getText().length() <= 0) {
            alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Digite uma placa",
                    ButtonType.OK
            );
            alert.setHeaderText("Erro");
            alert.showAndWait();

            return;
        }

        try {
            Vehicle vehicle = resale.findVehicleByPlate(tfFind.getText());
            tfBrand.setText(vehicle.getBrand());
            tfModel.setText(vehicle.getModel());
            tfColor.setText(vehicle.getColor());
            tfPlate.setText(vehicle.getPlate());
            tfValue.setText(String.valueOf(vehicle.getValue()));
        } catch (Exception e) {
            alert = new Alert(
                    Alert.AlertType.INFORMATION,
                    "Veículo com a placa " + tfFind.getText() + " não encontrado.",
                    ButtonType.OK
            );
            alert.setHeaderText("Não encontrado");
            alert.showAndWait();
        }
    }

    public void clearTextFields() {
        tfBrand.setText("");
        tfModel.setText("");
        tfColor.setText("");
        tfPlate.setText("");
        tfValue.setText("");
    }
}
