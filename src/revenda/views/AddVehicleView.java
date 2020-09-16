package revenda.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import revenda.controllers.AddVehicleController;

public class AddVehicleView {
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

    private final AddVehicleController addVehicleController = new AddVehicleController();

    @FXML
    public void initialize() {
        grid.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));

        tfBrand.setText("Voyage");
        tfModel.setText("Volkswagem");
        tfColor.setText("Prata");
        tfPlate.setText("AYK-9097");
        tfValue.setText("32000");
    }

    @FXML
    public void onClickSave() {
        Alert alert;

        try {
            boolean success = addVehicleController.saveVehicle(
                    tfBrand.getText(),
                    tfModel.getText(),
                    tfColor.getText(),
                    tfPlate.getText(),
                    Double.parseDouble(tfValue.getText())
            );

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
