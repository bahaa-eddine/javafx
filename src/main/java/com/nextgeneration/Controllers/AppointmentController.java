package com.nextgeneration.Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.nextgeneration.Entites.Appointment;
import com.nextgeneration.Entites.Calendar;
import com.nextgeneration.Entites.Doctor;
import com.nextgeneration.Entites.Patient;
import com.nextgeneration.Services.AppointmentService;
import com.nextgeneration.Services.CalendarService;
import com.nextgeneration.Services.DoctorService;
import com.nextgeneration.Services.PatientService;
import com.nextgeneration.Utils.Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@Component
public class AppointmentController {

	private Appointment appointment;
	private Calendar calendar;
	private LocalDate date;
	private List<Appointment> appointments;

	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private ApplicationContext applicationContext;

	@FXML
	private ChoiceBox<Doctor> doctorInput;

	@FXML
	private ChoiceBox<Patient> patientInput;

	@FXML
	private DatePicker dayInput;

	@FXML
	private Label messageError;

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Files.MAIN));
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		Parent home_page_parent = fxmlLoader.load();
		Scene home_page_scene = new Scene(home_page_parent, 600, 400);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.setResizable(false);
		app_stage.show();
	}

	@FXML
	void initialize() {

		date = getCurrentTimeStamp();
		dayInput.setValue(date);
		doctorService.getAll().forEach(doctor -> {
			doctorInput.getItems().add(doctor);
		});
		patientService.getAll().forEach(patient -> {
			patientInput.getItems().add(patient);
		});
		messageError.setText("");
	}

	@FXML
	void save(ActionEvent event) {
		appointment = new Appointment();
		appointment.setDoctor(doctorInput.getValue());
		appointment.setPatient(patientInput.getValue());
		appointmentService.create(appointment);
		calendar = new Calendar();
		if (calendar.getAppointments() == null) {
			appointments = new ArrayList<Appointment>();
			appointments.add(appointment);
			calendar.setAppointments(appointments);
		} else {
			calendar.getAppointments().add(appointment);
		}
		calendar.setDate(convertToDate(dayInput.getValue()));
		System.out.println(calendar);
		calendarService.create(calendar);
		messageError.setText("Created Successfully!");
		doctorInput.setValue(null);
		patientInput.setValue(null);
	}

	public Date convertToDate(LocalDate date) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
	}

	public LocalDate getCurrentTimeStamp() {
		return LocalDateTime.now().toLocalDate();
	}

}
