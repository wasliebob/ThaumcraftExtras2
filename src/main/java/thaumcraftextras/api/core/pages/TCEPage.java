package thaumcraftextras.api.core.pages;

import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;

public class TCEPage {
	/**
	 * @param title
	 * Title of the page
	 * @param text
	 * Text on the page
	 * @param color
	 * Color of the text
	 */
	public TCEPage(String title, String[] text, int color, String image, AdvancedAltarRecipe[] recipe, int width, int height){
		this.title = title;
		this.text = text;
		this.color = color;
		this.image = image;
		this.width = width;
		this.height = height;
		this.recipe = recipe;
	}
	public String title;
	public String[] text;
	public int color;
	public String image;
	public AdvancedAltarRecipe[] recipe;
	public int width;
	public int height;
}