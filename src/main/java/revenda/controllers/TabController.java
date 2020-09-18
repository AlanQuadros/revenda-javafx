package revenda.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class TabController {
    @FXML
    private Tab addTab;
    @FXML
    private Tab listTab;
    @FXML
    private Tab findTab;

    @FXML
    public void initialize() throws Exception {
        addTab.setContent(FXMLLoader.load(getClass().getResource("/views/add_vehicle.fxml")));
        listTab.setContent(FXMLLoader.load(getClass().getResource("/views/list_vehicle.fxml")));
        findTab.setContent(FXMLLoader.load(getClass().getResource("/views/find_vehicle.fxml")));
    }
}
