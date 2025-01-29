// Author: Tytus Felbor
// CPSC225 - Intermediate Programming Project 2

package projTwoPackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class projectTwoMain extends Application {

	public void start(Stage s) {
		
		RootScene root = new RootScene();
		
		s.setScene(root);
		s.setTitle("CS225 Stock Tracker App");
		s.show();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch();	
		
	}

}
