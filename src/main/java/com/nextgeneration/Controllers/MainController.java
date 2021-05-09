package com.nextgeneration.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.nextgeneration.Entites.TableViewItem;
import com.nextgeneration.Services.CalendarService;
import com.nextgeneration.Utils.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

@Component
public class MainController {
	Integer iteration;
	@Autowired
	private CalendarService calendarService;
	@Autowired
	private ApplicationContext applicationContext;

	private LocalDate date;
	private ObservableList<TableViewItem> data;
	private TableViewItem tableItemView;
	@FXML
	private Label messageError;
	@FXML
	private TableView<TableViewItem> table;
    @FXML
    private Label count;
	@FXML
	private TableColumn<TableViewItem, String> patient;

	@FXML
	private TableColumn<TableViewItem, String> doctor;

	@FXML
	private DatePicker timeInput;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void logout(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Files.LOGIN));
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		Parent home_page_parent = fxmlLoader.load();
		Scene home_page_scene = new Scene(home_page_parent, 300, 300);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.setResizable(false);
		app_stage.show();
	}

	@FXML
	void initialize() {
		data = FXCollections.observableArrayList();
		messageError.setText("");
		date = getCurrentTimeStamp();
		timeInput.setValue(date);
		table.getItems().clear();
		iteration = 0;
		if (calendarService.getCalendarByDate(convertToDate(date)).size() != 0) {
			messageError.setText("");
			calendarService.getCalendarByDate(convertToDate(date)).get(0).getAppointments().forEach(appointment -> {
				tableItemView = new TableViewItem();
				tableItemView.setDoctorName(appointment.getDoctor().getFirstname());
				tableItemView.setPatientName(appointment.getPatient().getFirstname());
				data.add(tableItemView);
				iteration++;
			});

			doctor.setCellValueFactory(new PropertyValueFactory<TableViewItem, String>("doctorName"));
			patient.setCellValueFactory(new PropertyValueFactory<TableViewItem, String>("patientName"));
			count.setText("Count: "+iteration.toString());
			table.setItems(data);
		} else {
			messageError.setText("No data Exist");
			count.setText("");
		}
	}

	@FXML
	void addAppoitment(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Files.APPOINTMENT));
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
	void addDoctor(ActionEvent event)  throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Files.DOCTOR));
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
	void addPatient(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Files.PATIENT));
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		Parent home_page_parent = fxmlLoader.load();
		Scene home_page_scene = new Scene(home_page_parent, 600,400);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.setResizable(false);
		app_stage.show();
	}

	public LocalDate getCurrentTimeStamp() {
		return LocalDateTime.now().toLocalDate();
	}

	public Date convertToDate(LocalDate dateToConvert) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
	}

	@FXML
	void changeDate(ActionEvent event) {
		System.out.println(timeInput.getValue());
		date = timeInput.getValue();
		timeInput.setValue(date);
		table.getItems().clear();
		iteration = 0;
		if (calendarService.getCalendarByDate(convertToDate(date)).size() != 0) {
			messageError.setText("");
			calendarService.getCalendarByDate(convertToDate(date)).get(0).getAppointments().forEach(appointment -> {
				tableItemView = new TableViewItem();
				tableItemView.setDoctorName(appointment.getDoctor().getFirstname());
				tableItemView.setPatientName(appointment.getPatient().getFirstname());
				data.add(tableItemView);
				iteration++;
			});
			count.setText("Count: "+iteration.toString());
			doctor.setCellValueFactory(new PropertyValueFactory<TableViewItem, String>("doctorName"));
			patient.setCellValueFactory(new PropertyValueFactory<TableViewItem, String>("patientName"));
			table.setItems(data);
		} else {
			messageError.setText("No data Exist");
			count.setText("");
		}

	}
}