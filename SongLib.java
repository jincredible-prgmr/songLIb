//Authors: Jin Kim

package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Controller;

public class SongLib extends Application {

	public void start(Stage primaryStage) 
	throws IOException {
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("/view/SongStage.fxml"));
		VBox rootscene = (VBox)loader.load();


		Controller Controller = loader.getController();
		Controller.start(primaryStage);

		Scene scene = new Scene(rootscene, 640, 400);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}

	public static void main(String[] args) {
		launch(args);
	}

}
