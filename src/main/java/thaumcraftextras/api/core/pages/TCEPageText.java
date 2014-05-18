package thaumcraftextras.api.core.pages;

import net.minecraft.item.Item;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;

public class TCEPageText extends TCEPageBase{

	public TCEPageText(String title, String[] page1, ShapedArcaneRecipe recipe, AdvancedAltarRecipe[] aaRecipe, Item[] cRecipe, int size) {
		super(title);
		this.title = title;
		this.page1 = page1;
		this.size = size;
		this.recipe = recipe;
		this.aaRecipe = aaRecipe;
		this.cRecipe = cRecipe;
	}
	public String[] page1;
	public int size;
	public ShapedArcaneRecipe recipe;
	public AdvancedAltarRecipe[] aaRecipe;
	public Item[] cRecipe;
}