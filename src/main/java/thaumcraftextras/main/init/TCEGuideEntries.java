package thaumcraftextras.main.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import thaumcraftextras.api.core.TCEGuide;
import thaumcraftextras.api.core.pages.TCEPage;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipeManager;

public class TCEGuideEntries {
	public static void init()
	{
		initTextPages();
	}
	
	public static void initTextPages()
	{
		int n = 1;
		TCEPage page;
		
		page = new TCEPage("Control", new String[]{
			"Next Page: D",
			"Previous Page: S",},
			0x0033FF, null, null, null, 0, 0);
		TCEGuide.addPage(n, page);
		n++;
		
		page = new TCEPage("Thaumcraft Extras 2", new String[]{
				"Welcome to the world",
				"of Thaumcraft Extras 2", 
				"TCE2 has a lot of new",
				"features, ", 
				"But also returns ",
				"previous once."},
				0x0033FF, null, null, null, 0, 0);
		TCEGuide.addPage(n, page);
		n++;
		
		page = new TCEPage("Thaumic Energy", new String[]{
				"TCE2 adds a whole ",
				"new energy system",
				"called Thaumic Energy."},
				0x0033FF, null, null, null, 0, 0);
		TCEGuide.addPage(n, page);
		n++;
		
		page = new TCEPage("Thaumic Energy Battery", new String[]{
				"The Thaumic Energy ",
				"Batter is one of the ",
				"core blocks of TCE2's ",
				"energy system.",
				"The battery allows you ",
				"to store energy and ",
				"take it with you."},
				0x0033FF, null, null, null, 0, 0);
		TCEGuide.addPage(n, page);
		n++;
		
		AdvancedAltarRecipe[] recipe;
		recipe = new AdvancedAltarRecipe[]{
				getRecipe(TCEItems.essenceMagic),
				getRecipe(Item.getItemFromBlock(Blocks.cobblestone)),
				getRecipe(Items.coal),
				getRecipe(Item.getItemFromBlock(Blocks.coal_block))
		};
		page = new TCEPage("Advanced Altar Recipes",
				null,
				0x0033FF, null, recipe, null, 16, 16);
		TCEGuide.addPage(n, page);
		n++;
		
		Item[] rec;
		rec = new Item[]{
				Items.lava_bucket,
				TCEItems.essenceDark
		};
		page = new TCEPage("Clasher Recipes", 
				null,
				0x0033FF, null, null, rec, 16, 16);
		TCEGuide.addPage(n, page);
		n++;
		
	}
	
	public static AdvancedAltarRecipe getRecipe(Item input){
		return AdvancedAltarRecipeManager.advancedAltar.get(input);
	}
}