package thaumcraftextras.main.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import thaumcraftextras.api.core.TCEGuide;
import thaumcraftextras.api.core.pages.TCEPageText;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipeManager;

public class TCEGuideEntries {
	public static void init()
	{
		initIndex();
		initPages();
	}
	
	public static void initIndex()
	{
		TCEGuide.addCategory(tce);
		TCEGuide.addCategory(aarecipes);
		TCEGuide.addCategory(crecipes);
	}
	
	public static void initPages()
	{
		String title;
		TCEPageText page;
		String[] s;
		
		s = new String[]{
				"Welcome to the world",
				"of Thaumcraft Extras 2", 
				"TCE2 has a lot of new",
				"features, ", 
				"But also returns ",
				"previous once."};
		title = tce;
		page = new TCEPageText(title, s, null, null, null, 1);
		TCEGuide.addTextEntry(title, page);

		AdvancedAltarRecipe[] aaRecipe;
		title = aarecipes;
		aaRecipe = new AdvancedAltarRecipe[]{
				getRecipe(TCEItems.essenceMagic),
				getRecipe(Item.getItemFromBlock(Blocks.cobblestone)),
				getRecipe(Items.coal),
				getRecipe(Item.getItemFromBlock(Blocks.coal_block))
		};
		page = new TCEPageText(title, null, null, aaRecipe, null, 1);
		TCEGuide.addTextEntry(title, page);
		
		Item[] cRecipe;
		title = crecipes;
		cRecipe = new Item[]{
				Items.lava_bucket,
				TCEItems.essenceDark
		};
		page = new TCEPageText(title, null, null, null, cRecipe, 1);
		TCEGuide.addTextEntry(title, page);
	}
	public static String tce = "Thaumcraft Extras";
	public static String aarecipes = "Advanced Altar Recipes";
	public static String crecipes = "Elemental Clasher";

	public static AdvancedAltarRecipe getRecipe(Item input){
		return AdvancedAltarRecipeManager.advancedAltar.get(input);
	}
}