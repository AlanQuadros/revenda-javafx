package revenda.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class TabView {
    @FXML
    private Tab addTab;
    @FXML
    private Tab listTab;
    @FXML
    private Tab findTab;

    @FXML
    public void initialize() throws Exception {
        addTab.setContent(FXMLLoader.load(getClass().getResource("/resources/add_vehicle.fxml")));
        listTab.setContent(FXMLLoader.load(getClass().getResource("/resources/list_vehicle.fxml")));
        findTab.setContent(FXMLLoader.load(getClass().getResource("/resources/find_vehicle.fxml")));
    }
}
