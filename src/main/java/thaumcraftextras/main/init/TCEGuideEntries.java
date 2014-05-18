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
		TCEGuide.addCategory(tcenergy);
		TCEGuide.addCategory(battery);
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

		title = tcenergy;
		s = new String[]{
				"TCE2 adds a whole ",
				"new energy system",
				"called Thaumic Energy."};
		page = new TCEPageText(title, s, null, null, null, 1);
		TCEGuide.addTextEntry(title, page);

		title = battery;
		s = new String[]{
				"The Thaumic Energy ",
				"Battery is one of the ",
				"core blocks of TCE2's ",
				"energy system.",
				"The battery allows you ",
				"to store energy and ",
				"take it with you."};
		page = new TCEPageText(title, s, TCERecipes.recipeMagicBattery, null, null, 2);
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
	public static String tcenergy = "Thaumic Energy";
	public static String battery = "Thaumic Energy Battery";
	public static String aarecipes = "Advanced Altar Recipes";
	public static String crecipes = "Elemental Clasher";

	public static AdvancedAltarRecipe getRecipe(Item input){
		return AdvancedAltarRecipeManager.advancedAltar.get(input);
	}
}