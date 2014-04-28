package thaumcraftextras.api.core.pages;

public class TCEPage {
	/**
	 * @param title
	 * Title of the page
	 * @param text
	 * Text on the page
	 * @param color
	 * Color of the text (bugged as of 28-4-2014)
	 */
	public TCEPage(String title, String[] text, int color){
		this.title = title;
		this.text = text;
	}
	public String title;
	public String[] text;
	public int color;
}