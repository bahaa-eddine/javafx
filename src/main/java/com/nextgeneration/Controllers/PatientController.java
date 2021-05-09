package com.nextgeneration.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.nextgeneration.Entites.Patient;
import com.nextgeneration.Services.PatientService;
import com.nextgeneration.Utils.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

@Component
public class PatientController {
	
	private Patient patient;
	private ObservableList<Patient> data;


	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private PatientService patientService;
	
    @FXML
    private TextField firstnameInput;

    @FXML
    private TextField lastnameInput;

    @FXML
    private TextField cinInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField phoneInput;
    
    @FXML
    private TableView<Patient> table;

    @FXML
    private TableColumn<Patient, String> firstnameColumn;

    @FXML
    private TableColumn<Patient, String> lastnameColumn;

    @FXML
    private TableColumn<Patient, String> cinColumn;

    @FXML
    private TableColumn<Patient, String> emailColumn;

    @FXML
    private TableColumn<Patient, String> phoneColumn;
	
    @FXML
    void back(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Files.MAIN));
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		Parent home_page_parent = fxmlLoader.load();
		Scene home_page_scene = new Scene(home_page_parent, 600,400);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.setResizable(false);
		app_stage.show();
    }
    
    @FXML
    void initialize() {
		data = FXCollections.observableArrayList();
		table.getItems().clear();
    	if(patientService.count() != 0) {
    		patientService.getAll().forEach(patientData -> {
    			patient = new Patient();
    			patient.setCIN(patientData.getCIN());
    			patient.setEmail(patientData.getEmail());
    			patient.setFirstname(patientData.getFirstname());
    			patient.setLastname(patientData.getLastname());
    			patient.setPhone(patientData.getPhone());
				data.add(patient);
    		});
			firstnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstname"));
			lastnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastname"));
			cinColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("CIN"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
			phoneColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("phone"));
			table.setItems(data);
    	}
    }
    
    @FXML
    void save(ActionEvent event) {
    	patient = new Patient();
    	patient.setCIN(cinInput.getText());
    	patient.setEmail(emailInput.getText());
    	patient.setFirstname(firstnameInput.getText());
    	patient.setLastname(lastnameInput.getText());
    	patient.setPhone(phoneInput.getText());
    	patientService.create(patient);
    	
    	data.add(patient);
    	firstnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstname"));
		lastnameColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastname"));
		cinColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("CIN"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("email"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("phone"));
		table.setItems(data);
		
    	cinInput.setText("");
    	emailInput.setText("");
    	firstnameInput.setText("");
    	lastnameInput.setText("");
    	phoneInput.setText("");
    }

}
