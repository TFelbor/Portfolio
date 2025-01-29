// Author: Tytus Felbor

package unoPackage;

public abstract class CommonCard extends Card{

	
	protected CommonCard(int color, int number) {
		
		this.color = color;
		this.value = number;
		
	}


	protected boolean check(Table t) {
		
		if (t.onTop.color == this.color) {
			
			return true;
		}
			
		else {
			
			return false;
		}
	}
}
