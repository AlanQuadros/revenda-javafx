package revenda.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import revenda.models.Vehicle;
import revenda.services.VehicleService;

import java.util.Optional;

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
    private Button btnSave;
    @FXML
    private Button btnRemove;

    private final VehicleService vehicleService = VehicleService.getInstance();

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
        Alert alert;
        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setBrand(tfBrand.getText());
            vehicle.setModel(tfModel.getText());
            vehicle.setColor(tfColor.getText());
            vehicle.setPlate(tfPlate.getText());
            vehicle.setValue(Double.parseDouble(tfValue.getText()));

            boolean success = vehicleService.editVehicle(tfPlate.getText(), vehicle);

            if (success) {
                alert = new Alert(
                        Alert.AlertType.INFORMATION,
                        "Veículo alterado com sucesso.",
                        ButtonType.OK
                );
                alert.setHeaderText("Editar");
                alert.showAndWait();
            }

            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            alert = new Alert(
                    Alert.AlertType.ERROR,
                    "Ocorreu um erro ao editar o veículo.",
                    ButtonType.OK
            );
            alert.setHeaderText("Erro");
            alert.showAndWait();
        }
    }

    @FXML
    public void onClickRemove() {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja remover este veículo?",
                ButtonType.CANCEL,
                ButtonType.OK
        );
        alert.setHeaderText("Remover veículo");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean success = vehicleService.removeVehicle(tfPlate.getText());
                Stage stage = (Stage) btnRemove.getScene().getWindow();
                stage.close();

                if (success) {
                    alert = new Alert(
                            Alert.AlertType.INFORMATION,
                            "Veículo removido com sucesso.",
                            ButtonType.OK
                    );
                    alert.setHeaderText("Remover");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                alert = new Alert(
                        Alert.AlertType.ERROR,
                        "Ocorreu um erro ao remover o veículo.",
                        ButtonType.OK
                );
                alert.setHeaderText("Erro");
                alert.showAndWait();
            }
        }
    }
}
