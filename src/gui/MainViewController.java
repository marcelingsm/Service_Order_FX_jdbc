package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import serviceordem.Main;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemClientes;

    @FXML
    private MenuItem menuItemOrdens;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemClientAction() {
        loadView("/gui/ClientList.fxml", (ClientListController controller) -> {
            controller.updateTableView();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();
            
            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) (((ScrollPane) mainScene.getRoot()).getContent());
            
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());
            T controller = loader.getController();
            initializingAction.accept(controller);
            
        } catch (IOException e) {
            //Alerts.showAlert("IO Excepition", null, "Erro loading view", Alert.AlertType.ERROR);
        }
    }

}
