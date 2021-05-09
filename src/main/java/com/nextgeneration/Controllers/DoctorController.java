package com.nextgeneration.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.nextgeneration.Entites.Doctor;
import com.nextgeneration.Entites.Speciality;
import com.nextgeneration.Services.DoctorService;
import com.nextgeneration.Utils.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

@Component
public class DoctorController {
	
	private Doctor doctor;
	private ObservableList<Doctor> data;

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private DoctorService doctorService;
	
	@FXML
    private TableView<Doctor> table;
    
    @FXML
    private TableColumn<Doctor, String> firstnameColumn;

    @FXML
    private TableColumn<Doctor, String> lastnameColumn;

    @FXML
    private TableColumn<Doctor, String> cinColumn;

    @FXML
    private TableColumn<Doctor, String> emailColumn;

    @FXML
    private TableColumn<Doctor, String> phoneColumn;

    @FXML
    private TableColumn<Doctor, String> specialityColumn;

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
    private ChoiceBox<Speciality> specialityInput;
	
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
    	specialityInput.getItems().setAll(Speciality.values());
    	data = FXCollections.observableArrayList();
		table.getItems().clear();
    	if(doctorService.count() != 0) {
    		doctorService.getAll().forEach(doctorData -> {
    			doctor = new Doctor();
    			doctor.setCIN(doctorData.getCIN());
    			doctor.setEmail(doctorData.getEmail());
    			doctor.setFirstname(doctorData.getFirstname());
    			doctor.setLastname(doctorData.getLastname());
    			doctor.setPhone(doctorData.getPhone());
    			doctor.setSpeciality(doctorData.getSpeciality());
				data.add(doctor);
    		});
			firstnameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("firstname"));
			lastnameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lastname"));
			cinColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("CIN"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("email"));
			phoneColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phone"));
			specialityColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("speciality"));
			table.setItems(data);
    	}
    }

    @FXML
    void save(ActionEvent event) {
    	doctor = new Doctor();
    	doctor.setCIN(cinInput.getText());
    	doctor.setEmail(emailInput.getText());
    	doctor.setFirstname(firstnameInput.getText());
    	doctor.setLastname(lastnameInput.getText());
    	doctor.setPhone(phoneInput.getText());
    	doctor.setSpeciality(specialityInput.getValue());
    	doctorService.create(doctor);
    	
    	data.add(doctor);
    	firstnameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("firstname"));
		lastnameColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("lastname"));
		cinColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("CIN"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("email"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phone"));
		specialityColumn.setCellValueFactory(new PropertyValueFactory<Doctor, String>("speciality"));
		table.setItems(data);
		
    	cinInput.setText("");
    	emailInput.setText("");
    	firstnameInput.setText("");
    	lastnameInput.setText("");
    	phoneInput.setText("");
    	specialityInput.setValue(null);
    }

}
