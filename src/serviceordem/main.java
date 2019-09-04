
package serviceordem;

import db.DB;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entites.Client;

public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClientDao clientDao = DaoFactory.creatingSellerDao();
        List<Client> list =null;
        Client client = new Client(321 ,"Bit","Rua jose adalmo",35151111,"Bitcoin@hotmail.com");

        
        System.out.println("TEST");
        
        launch(args);
        
            
    }
    
}
