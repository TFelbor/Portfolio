// Author: Tytus Felbor
// CPSC225 - Intermediate Programming Project 1

package unoPackage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class mainClass extends Application {
	
	// The canvas on which the strings are drawn.
	private Canvas canvas;
	
	// Contains images of all of the cards.
	private Image cardImages;
	
	/* Cards are arranged in 5 rows and 13 columns.
	Each of the first four rows contains the cards from one suit, in numerical order.
 	The first four rows contain clubs, diamonds, hearts, and spades in that order.
  	The fifth row contains two jokers and a face-down card. */

	private Player human = new Player();
	private Player cpu = new Player();
	private Card humanMiddle = null;
	private Card cpuMiddle = null;

	public static void main(String[] args) {
		launch();
	}

	public void start( Stage stage ) {

		cardImages = new Image("cards.png");

		canvas = new Canvas(10*75+40, 110*5+150);
		draw();  // draw content of canvas the first time.
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
			game_played(canvas, event);
		});

		Pane root = new Pane(canvas);

		stage.setScene( new Scene(root, Color.BLACK) );
		stage.setTitle("Random Cards");
		stage.setResizable(false);

		stage.show();
	}

	private void game_played(Canvas canvas, MouseEvent event) {
		
		double x = event.getX();
		double y = event.getY();
		boolean played = false;
		
		for (int i = 0 ; i < human.getHand().size(); i++) {
			
			if (x > 20+75*(i%10) && x < 20+75*(i%10) + 75 
					&& y > 400+110*(i/10) && y <400+110*(i/10)+110) {
				
				Card c = human.getHand().get(i);
				humanMiddle = c;
				human.getHand().remove(i);
				played = true;
				break;
			}
		}
		
		if (x > 340 && x < 460 && y > 640 && y < 680) {
			
			human.draw();
			played = true;
		} 

		if (!played) return;

		if (cpu.getHand().size() > 0) {
			cpuMiddle = cpu.getHand().get(0);
			cpu.getHand().remove(0);
		}

		GraphicsContext g = canvas.getGraphicsContext2D();
		g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		draw();
	}

	private void draw() {

		GraphicsContext g = canvas.getGraphicsContext2D();

		//draw the draw button

		g.setTextAlign(TextAlignment.CENTER);
		g.setFill(Color.WHITE);
		g.setFont(new Font(40));
		g.fillText("DRAW", Math.round(canvas.getWidth()  / 2), 670);
		
		double sx,sy;  // top left corner of source rect for card in cardImages
		double dx,dy;  // top left corner of destination rect for card in the canvas

		for (int i = 0; i < human.getHand().size(); i++) {
			
			Card card = human.getHand().get(i);
			// System.out.println(card); // for testing
			sx = 411+card.value*73 ;
			sy = 120+card.color*115 ;
			dx = 20+75*(i%10);
			dy = 400+110*(i/10);
			
			if (card.color == card.BLACK) {
				
				sx = 411 ;
				sy = 120+card.color*115 ;
			}
			
			g.drawImage( cardImages, sx,sy,73,115, dx,dy,75, 110 );
		}

		for (int i = 0; i < cpu.getHand().size(); i++) {
			Card card = cpu.getHand().get(i);

			// System.out.println(card); // for testing

			sx = 5 ;
			sy = 14 ;
			dx = 20+75*(i%10);
			dy = 0+110*(i/10);
			
			if (card.color == card.BLACK) {
				sx = 411 ;
				sy = 120+card.color*115 ;
			}
			
			g.drawImage( cardImages, sx,sy,73,115, dx,dy,75, 110 );
		}

		if (humanMiddle != null) {
			
			sx = 411+humanMiddle.value*73 ;
			sy = 120+humanMiddle.color*115 ;
			dx = 20+75*4.5;
			dy = 280;
			
			if (humanMiddle.color == humanMiddle.BLACK) {
				
				sx = 411 ;
				sy = 120+humanMiddle.color*115 ;
				
			}
			
			g.setGlobalAlpha(0.7);
			g.drawImage( cardImages, sx,sy,73,115, dx,dy,75, 110 );
			g.setGlobalAlpha(1);
		}
		
		if (cpuMiddle != null) {
			
			sx = 411+cpuMiddle.value*73 ;
			sy = 120+cpuMiddle.color*115 ;
			dx = 20+75*4.5+20;
			dy = 230;
			
			if (cpuMiddle.color == cpuMiddle.BLACK) {
				
				sx = 411 ;
				sy = 120+cpuMiddle.color*115 ;
			}
			
			g.drawImage( cardImages, sx,sy,73,115, dx,dy,75, 110 );
		}
	} // end draw()
}
