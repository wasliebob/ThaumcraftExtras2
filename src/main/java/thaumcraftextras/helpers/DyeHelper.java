package thaumcraftextras.helpers;

import java.awt.Color;

public class DyeHelper {
	/**
	* A 0 = Black
	* B 1 = Red
	* C 2 = Green
	* D 3 = Brown
	* E 4 = Blue
	* F 5 = Purple
	* G 6 = Cyan
	* H 7 = Light Gray
	* I 8 = Gray
	* J 9 = Pink
	* K 10 = Lime
	* L 11 = Yellow
	* M 12 = Light Blue
	* N 13 = Magenta
	* O 14 = Orange
	* P 15 = White
	*/

	    /** new Color(red, green, blue) */
	    static int black = new Color(0, 0, 0).getRGB();
	    static int red = new Color(255, 0, 0).getRGB();
	    static int green = new Color(0, 255, 20).getRGB();
	    static int brown = new Color(139, 69, 19).getRGB();
	    static int blue = new Color(0, 0, 255).getRGB();
	    static int purple = new Color(148, 0, 211).getRGB();
	    static int cyan = Color.cyan.getRGB();
	    static int lgray = Color.lightGray.getRGB();
	    static int gray = Color.gray.getRGB();
	    static int pink = new Color(255, 105, 180).getRGB();
	    static int lime = new Color(124, 252, 0).getRGB();
	    static int yellow = Color.yellow.getRGB();
	    static int lblue = new Color(127, 255, 212).getRGB();
	    static int magenta = Color.magenta.getRGB();
	    static int orange = Color.orange.getRGB();
	    static int white = Color.white.getRGB();
	    
	    
	public static int getColorCode(int value)
	{
	switch(value)
	{
	     case 0: return black;
	     case 1: return red;
	     case 2: return green;
	     case 3: return brown;
	     case 4: return blue;
	     case 5: return purple;
	     case 6: return cyan;
	     case 7: return lgray;
	     case 8: return gray;
	     case 9: return pink;
	     case 10: return lime;
	     case 11: return yellow;
	     case 12: return lblue;
	     case 13: return magenta;
	     case 14: return orange;
	     case 15: return white;
	     default: return black;
	}
	}
}
