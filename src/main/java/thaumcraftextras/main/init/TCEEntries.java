package thaumcraftextras.main.init;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraftextras.helpers.ResearchHelper;
import thaumcraftextras.main.Config;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.addons.TCEWands;

public class TCEEntries {

	public static void init()
	{
		initBookTab();
		initBookEntries();
		
		if(Config.addon_wands)
			initWandEntries();
	}
	
	public static void initBookEntries()
	{
		ResearchItem research;
		String[] text;
		AspectList aspect;
		aspect = new AspectList();
		
		text = new String[]{"1"};
		research = new ResearchHelper(tce, TCEEntries.TCE, aspect, 0, 0, 2, new ItemStack(TCEItems.essenceMagic)).setRound().setAutoUnlock().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeMagicEssence), new ResearchPage(TCERecipes.recipeBlockLight));
		aspect = new AspectList();
		
		aspect.add(Aspect.ENTROPY, 2);
		aspect.add(Aspect.METAL, 2);
		text = new String[]{"1"};
		research = new ResearchHelper(ingot_darkthaumium, TCEEntries.TCE, aspect, 1, -2, 2, new ItemStack(TCEItems.darkThaumium)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeDarkThaumium));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.ENERGY, 5);
		text = new String[]{"1", "2", "3" , "4"};
		research = new ResearchHelper(magic_energy, TCEEntries.TCE, aspect, 1, -4, 2, new ItemStack(TCEBlocks.battery)).setParents(ingot_darkthaumium).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(text[1]), new ResearchPage(text[2]), new ResearchPage(text[3]), new ResearchPage(TCERecipes.recipeMagicBattery),  new ResearchPage(TCERecipes.recipeMagicGenerator),  new ResearchPage(TCERecipes.recipeMagicCrystalCharger), new ResearchPage(TCERecipes.recipeMagicWandCharger), new ResearchPage(TCERecipes.recipeMagicCrystalT1), new ResearchPage(TCERecipes.recipeMagicCrystalT2), new ResearchPage(TCERecipes.recipeMagicCrystalT3), new ResearchPage(TCERecipes.recipeMagicCrystalT4), new ResearchPage(TCERecipes.recipeMagicCrystalT5), new ResearchPage(TCERecipes.recipeMagicCrystalT6), new ResearchPage(TCERecipes.recipeScanner), new ResearchPage(TCERecipes.recipeEnergyHelmet));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.FIRE, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(chestplate_fire, TCEEntries.TCE, aspect, -1, 2, 2, new ItemStack(TCEItems.fireChestplate)).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFireChestplate));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.TOOL, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_clean, TCEEntries.TCE, aspect, -1, -2, 2, new ItemStack(TCEItems.focusClean)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusClean));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.ELDRITCH, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_return, TCEEntries.TCE, aspect, -2, -2, 2, new ItemStack(TCEItems.focusReturn)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusReturn));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_trampoline, TCEEntries.TCE, aspect, -3, -2, 2, new ItemStack(TCEItems.focusTrampoline)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusTrampoline));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 10);
		aspect.add(Aspect.AIR, 10);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_pechTrade, TCEEntries.TCE, aspect, -4, -2, 2, new ItemStack(TCEItems.focusPechTrade)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusPechTrade));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 10);
		aspect.add(Aspect.ENTROPY, 10);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_potion, TCEEntries.TCE, aspect, -5, -2, 2, new ItemStack(TCEItems.focusPotion)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusPotion));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 10);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_exchange, TCEEntries.TCE, aspect, -6, -2, 2, new ItemStack(TCEItems.focusExchange)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusExchange));
		aspect = new AspectList();
		
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(shocker, TCEEntries.TCE, aspect, 5, -3, 2, new ItemStack(TCEBlocks.shocker)).setParents(TCEEntries.tce).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeShocker));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_shocker, TCEEntries.TCE, aspect, 6, -4, 2, new ItemStack(TCEItems.focusTessela)).setParents(TCEEntries.shocker).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusShocker));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(containment, TCEEntries.TCE, aspect, 4, -4, 2, new ItemStack(TCEBlocks.noMove)).setParents(TCEEntries.tce).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeContainment));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(essentia_barrel, TCEEntries.TCE, aspect, 3, -4, 2, new ItemStack(TCEBlocks.barrel_essentia)).setParents(TCEEntries.tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.barrel_essentia));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 10);
		aspect.add(Aspect.AIR, 10);
		aspect.add(Aspect.ENTROPY, 10);
		text = new String[]{"1"};
		research = new ResearchHelper(altar_advanced, TCEEntries.TCE, aspect, 5, -2, 2, new ItemStack(TCEBlocks.altar_advanced)).setParents(TCEEntries.tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeAdvancedAltar));
		aspect = new AspectList();
	}
	public static String tce = "Thaumcraft_Extras"; 
	public static String ingot_darkthaumium = "Dark_Thaumium"; 
	public static String focus_return = "Wand_Focus:_Return";
	public static String focus_clean = "Wand_Focus:_Clean";
	public static String focus_shocker = "Wand_Focus:_Shock";
	public static String focus_trampoline = "Wand_Focus:_Trampoline";
	public static String focus_pechTrade = "Wand_Focus:_Pech_Trade";
	public static String focus_potion = "Wand_Focus:_Potion";
	public static String focus_exchange = "Wand_Focus:_Exchange";

	public static String magic_energy = "Magic_Energy";
	public static String chestplate_fire = "Chestplate_of_Flames";
	
	public static String dark_infusion = "Dark_Infusion";
	public static String containment = "Containment";
	public static String shocker = "Shocker";
	public static String essentia_barrel = "Essentia_Barrels";
	public static String altar_advanced = "Altar_Advanced";
	
	public static void initWandEntries()
	{
		ResearchItem research;
		String[] text;
		AspectList aspect;
		aspect = new AspectList();

		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_iron, TCEEntries.TCEWANDS, aspect, 0, 0, 2, new ItemStack(TCEWands.item_rod_iron)).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_iron));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_gold, TCEEntries.TCEWANDS, aspect, 1, 1, 2, new ItemStack(TCEWands.item_rod_gold)).setParents(TCEEntries.rod_iron).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_gold));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_diamond, TCEEntries.TCEWANDS, aspect, 2, 2, 2, new ItemStack(TCEWands.item_rod_diamond)).setParents(TCEEntries.rod_gold).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_diamond));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_emerald, TCEEntries.TCEWANDS, aspect, 3, 3, 2, new ItemStack(TCEWands.item_rod_emerald)).setParents(TCEEntries.rod_diamond).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_emerald));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 10);
		aspect.add(Aspect.ENTROPY, 10);
		aspect.add(Aspect.FIRE, 10);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_devil, TCEEntries.TCEWANDS, aspect, 1, 3, 2, new ItemStack(TCEWands.item_rod_devil)).setParents(TCEEntries.rod_diamond).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_devil));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 15);
		aspect.add(Aspect.ENTROPY, 15);
		aspect.add(Aspect.AIR, 15);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_god, TCEEntries.TCEWANDS, aspect, 1, 4, 2, new ItemStack(TCEWands.item_rod_god)).setParents(TCEEntries.rod_devil).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_god));
		aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 25);
		aspect.add(Aspect.ENTROPY, 25);
		aspect.add(Aspect.AIR, 25);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_darkSilverwood, TCEEntries.TCEWANDS, aspect, 1, 5, 2, new ItemStack(TCEWands.item_rod_darkSilverwood)).setParents(TCEEntries.rod_god).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_darkSilverwood));
		aspect = new AspectList();
		
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.cap_darkThaumium, TCEEntries.TCEWANDS, aspect, -1, -1, 2, new ItemStack(TCEWands.item_cap_darkThaumium)).setParents(TCEEntries.rod_iron).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_cap_darkThaumium));
		aspect = new AspectList();
		
	}
	public static String rod_iron = "ROD_IRON";
	public static String rod_gold = "ROD_GOLD";
	public static String rod_diamond = "ROD_DIAMOND";
	public static String rod_emerald = "ROD_EMERALD";
	
	public static String rod_devil = "ROD_DEVIL";
	public static String rod_god = "ROD_GOD";
	
	public static String rod_darkSilverwood = "ROD_DARKSILVERWOOD";
	
	public static String cap_darkThaumium = "CAP_DARKTHAUMIUM";
	
	public static void initBookTab()
	{
		 ResourceLocation background = new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png");
         ResearchCategories.registerCategory("TCE", new ResourceLocation(ThaumcraftExtras.modName.toLowerCase() + ":" + "textures/items/essence_magic.png"), background);
       
         if(Config.addon_wands)
        	 ResearchCategories.registerCategory("TCE_WANDS", new ResourceLocation(ThaumcraftExtras.modName.toLowerCase() + ":" + "textures/items/rod_darksilverwood.png"), background);
	}
	public static String TCE = "TCE";
	public static String TCEWANDS = "TCE_WANDS";
}
