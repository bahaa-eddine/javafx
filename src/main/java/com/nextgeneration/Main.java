package com.nextgeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nextgeneration.Utils.Files;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class Main extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent root;

	private static final String TITLE = "Doctor";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		springContext = SpringApplication.run(Main.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Files.LOGIN));
		fxmlLoader.setControllerFactory(springContext::getBean);
		root = fxmlLoader.load();
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(new Scene(root, 300, 300));
		primaryStage.setResizable(false);
		primaryStage.show();
    }
	
    @Override
    public void stop() throws Exception {
        springContext.stop();
    }
}
