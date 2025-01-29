// Author: Tytus Felbor

package projTwoPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RootScene extends Scene {

	// UI components
	private BorderPane root = null;
	private Pane center;
	private TextField queryText = new TextField();
	private Label symbol = new Label();
	private Label price = new Label();
	private VBox leftPanel = new VBox();
	protected Button track;
	protected Button quit;
	private Label savedSymbol;
	private Label savedPrice;
	private int row = -1;
	// 2D array to store stock data:
  	// Column 0: stock symbol
  	// Column 1: last recorded price
  	// Column 2: updated price for comparison
  	private String[][] saved = new String[5][3];

  	// Updates prices for all tracked stocks and updates UI with color-coded changes
	public void updatePrice() {
		
		int row = 0;
		int newPriceColumn = 2;
		int oldPriceColumn = 1;
		int symbolColumn = 0;
		int priceLabelIndex = 4;
		String key = "cfr4dppr01qhg1ura7e0cfr4dppr01qhg1ura7eg";
		
		try {

      			// Clear existing display
			leftPanel.getChildren().clear();

      			// Update each tracked stock
			while (row < 5 && saved[row][symbolColumn] != null) {
        
        			// Fetch current price using Finnhub API
				URL url = new URL("https://finnhub.io/api/v1/quote?symbol=" + saved[row][0] + "&token="+key);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String json = br.readLine();
				JSONObject j = new JSONObject(json);

        			// Store new price and compare with old price
			  	saved [row][newPriceColumn] = j.get("c") +"";
			  	double newPrice = Double.parseDouble(saved[row][newPriceColumn]);
			  	double oldPrice = Double.parseDouble(saved[row][oldPriceColumn]);
			  	Label temp = new Label(saved[row][0]+": "+saved[row][newPriceColumn]);

        			// Color code price changes (green for increase, red for decrease)
			  	if (oldPrice < newPrice) {
				
					temp.setStyle("-fx-text-fill:green");
			    		leftPanel.getChildren().add( temp);
			    		saved[row][oldPriceColumn] = saved[row][newPriceColumn];
				
				}
			
			  	else {
          				
					temp.setStyle("-fx-text-fill:red");
				  	leftPanel.getChildren().add( temp);
				  	saved[row][oldPriceColumn] = saved[row][newPriceColumn];
				
				}
			
			  	row++;
			  	priceLabelIndex = priceLabelIndex + 2;
			
			}
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}

  	// Constructor - Initializes the UI and sets up event handlers
	public RootScene() {
		
		super(new BorderPane(), 600, 400);
		root = (BorderPane)super.getRoot();

    		// Set up the top panel with search functionality
		HBox topPanel = new HBox();
		topPanel.setAlignment(Pos.CENTER);
		topPanel.getChildren().add(queryText);
		Button find = new Button("Find");
		find.setOnAction(event -> {
			
		getQuote(queryText.getText());	
		
		});
		
		topPanel.getChildren().add(find);
		root.setTop(topPanel);

		// Set up center panel with stock display
		center = new Pane();
		center.getChildren().add(symbol);
		center.getChildren().add(price);
		symbol.setLayoutX(100);
		symbol.setLayoutY(100);
		symbol.setFont(new Font(40));
		price.setLayoutX(100);
		price.setLayoutY(150);
		price.setFont(new Font(40));
		price.setAlignment(Pos.CENTER_RIGHT);
		symbol.setAlignment(Pos.CENTER_LEFT);
		root.setCenter(center);

		// Set up bottom panel with track/quit buttons
		HBox bottom = new HBox();
		Button track = new Button("Track");
		track.setOnAction(event -> {
			
			save();
			
		});
		
		Button quit = new Button("Quit");
		quit.setOnAction(event -> {
		
			try {
				
				FileWriter fw = new FileWriter("saved.txt");
				PrintWriter pw = new PrintWriter(fw);
				
			for (int m = 0; m < 5; m++) {
				
				if (saved[m][0] != null) {
					
				pw.print(saved[m][0] + ",");
				pw.println(saved[m][1]);
				
					}
				}
			
			pw.flush();
			pw.close();
			
			}
			catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			Platform.exit();
			
		});
		
		bottom.getChildren().add(track);
		bottom.getChildren().add(quit);
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(50);;
		root.setBottom(bottom);
		root.setLeft(leftPanel);

		// Load saved stocks and update prices
		loadQuote();
		updatePrice();

	}

	// Fetches current stock quote from Finnhub API	
	public void getQuote (String s) {
    
		// API authentication key 
		String key = "cfr4dppr01qhg1ura7e0cfr4dppr01qhg1ura7eg";
			
		try {

			// Make API request and parse response
			URL url = new URL("https://finnhub.io/api/v1/quote?symbol=" + s + "&token="+key);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String json = br.readLine();
			JSONObject j = new JSONObject(json);
			
      			// Update UI with the new price
      			price.setText(j.get("c")+"");
			symbol.setText(s);
		
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}

	// Saves curently displayed stock to tracking list
	public void save() {
		
		savedSymbol = new Label();
		savedPrice = new Label();
		savedSymbol.setText(symbol.getText());
		savedPrice.setText(price.getText());

		// Add to UI and storage
		savedSymbol.setFont(new Font(20));
		savedPrice.setFont(new Font(20));
		leftPanel.getChildren().add(savedSymbol);
		leftPanel.getChildren().add(savedPrice);
		row++;
		saved[row][0] = savedSymbol.getText();
		saved[row][1] = savedPrice.getText();
		
	}
	
	// Loads previously saved stocks from file
	public void loadQuote() {

		// Set up header labels
		Label l1 = new Label("Saved Stock");
		Label l2 = new Label("Price");
		l1.setLayoutX(5);
		l2.setLayoutX(125);
		l1.setFont(new Font(10));
		l2.setFont(new Font(10));
		leftPanel.getChildren().add(l1);
		leftPanel.getChildren().add(l2);
				
		try {

			// Read saved stocks from the file
			FileReader fr = new FileReader("saved.txt");
			BufferedReader br = new BufferedReader(fr);
			String line =null;
			int ct = 0;

			// Process each saved stock & add it to UI
		  	while ((line = br.readLine()) != null) {
				
				String[] values = line.split(",");
			  	row++;
				saved [ct][0] = values[0];
				saved [ct][1] = values[1];
				Label tempSym = new Label();
				tempSym.setText(values[0]);
				tempSym.setFont(new Font(20));		
				leftPanel.getChildren().add(tempSym);
				Label tempPr = new Label();
				tempPr.setText(values[1]);
				tempPr.setFont(new Font(20));
				leftPanel.getChildren().add(tempPr);
				ct++;
	                
			}

	        	br.close();
		
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		
		}	
	}
}
