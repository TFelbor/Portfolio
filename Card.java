// Author: Tytus Felbor

package unoPackage;

public class Card {
	
	public static final int zero = 9;
	public static final int draw2 = 10;
	public static final int skip = 12;
	public static final int wild = -1;
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int YELLOW = 3;
	public static final int BLACK = 4;
	int color;
	int value;
	
	protected boolean check() {
		
		return false;
		
	}
}
