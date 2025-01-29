/* Author: Tytus Felbor
CPSC225 - Intermediate Programming Project 2
Main entry point for the Stock Tracker application */

package projTwoPackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class projectTwoMain extends Application {
	
    	// Initialize the JavaFX application and set up the primary stage
	public void start(Stage s) {

		// Create the main root scene containing the application UI
		RootScene root = new RootScene();

		// Configure and display the primary stage
		s.setScene(root);
		s.setTitle("CS225 Stock Tracker App");
		s.show();
		
	}

	// Application entry point
	public static void main(String[] args) {

		launch();	
		
	}
}
