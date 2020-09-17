package revenda.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import revenda.models.Vehicle;

public class EditVehicleController {
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
    public void initialize() {
        grid.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));
    }

    public void populateFields(Vehicle vehicle) {
        tfBrand.setText(vehicle.getBrand());
        tfModel.setText(vehicle.getModel());
        tfColor.setText(vehicle.getColor());
        tfPlate.setText(vehicle.getPlate());
        tfValue.setText(String.valueOf(vehicle.getValue()));
    }

    @FXML
    public void onClickSave() {
        System.out.println("salvar");
    }
}
