package com.nextgeneration.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.nextgeneration.Utils.Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    void logout(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource(Files.LOGIN));
		Scene home_page_scene = new Scene(home_page_parent,300,400);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.setResizable(false);
		app_stage.show();
    }

    @FXML
    void initialize() {

    }
}
