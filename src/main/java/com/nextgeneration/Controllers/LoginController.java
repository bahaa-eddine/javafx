package com.nextgeneration.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nextgeneration.Services.AdminService;
import com.nextgeneration.Utils.Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class LoginController {
    @FXML
    private Label messageErrorLogin;
	@FXML
	private TextField userText;
	@FXML
	private TextField passwordText;
	@FXML
	private Button loginButton;
	@Autowired
	private AdminService adminService;
	
	@FXML
	void login(ActionEvent event) throws IOException {
		String user = userText.getText();
		String password = passwordText.getText();
		// Authentication
		if(adminService.authenticate(user, password)){
			goToMainScreen(event);
		}else {
	    	messageErrorLogin.setText("Invalid user");
		}
	}

	public void goToMainScreen(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource(Files.MAIN));
		Scene home_page_scene = new Scene(home_page_parent,600,400);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.setResizable(false);
		app_stage.show();
	}
	
    @FXML
    void initialize() {
    	messageErrorLogin.setText("");
    }

}