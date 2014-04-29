package thaumcraftextras.api.core.pages;

public class TCEPage {
	/**
	 * @param title
	 * Title of the page
	 * @param text
	 * Text on the page
	 * @param color
	 * Color of the text
	 */
	public TCEPage(String title, String[] text, int color){
		this.title = title;
		this.text = text;
		this.color = color;
	}
	public String title;
	public String[] text;
	public int color;
}