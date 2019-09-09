package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.ClientDao;
import model.entites.Client;
import serviceordem.Main;

public class ClientListController implements Initializable{
   
   ClientDao client = null;
    
    @FXML
   private TableView<Client> tableViewClient;
   
   @FXML
   private TableColumn<Client,String> tableColumnName;
   
   @FXML
   private TableColumn<Client,Integer> tableColumnCpf;
   
   @FXML
   private TableColumn<Client,String> tableColumnTelefone;
   
   @FXML
   private TableColumn<Client,String> tableColumnEndereco;
   
   @FXML
   private TableColumn<Client,String> tableColumnEmail;
   
   @FXML
   private Button btAdd;
   
   private ObservableList<Client> obsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNode();
    }
   
    public void initializeNode(){
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tableColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    
        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewClient.prefHeightProperty().bind(stage.heightProperty());
    }
    
    public void updateTableView() {
        List<Client> list = client.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewClient.setItems(obsList);
    }
   
   
   
   
}
