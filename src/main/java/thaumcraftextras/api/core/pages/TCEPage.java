package thaumcraftextras.api.core.pages;

import net.minecraft.item.Item;
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
	public TCEPage(String title, String[] text, int color, String image, AdvancedAltarRecipe[] recipe, Item[] input, int width, int height){
		this.title = title;
		this.text = text;
		this.color = color;
		this.image = image;
		this.width = width;
		this.height = height;
		this.recipe = recipe;
		this.input = input;
	}
	public String title;
	public String[] text;
	public int color;
	public String image;
	public AdvancedAltarRecipe[] recipe;
	public Item[] input;
	public int width;
	public int height;
}