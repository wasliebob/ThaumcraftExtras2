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
		AspectList aspect = new AspectList();
		
		text = new String[]{"1"};
		research = new ResearchHelper(tce, TCEEntries.TCE, aspect, 0, 0, 2, new ItemStack(TCEItems.essenceMagic)).setRound().setAutoUnlock().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeMagicEssence));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 2);
		aspect.add(Aspect.METAL, 2);
		text = new String[]{"1"};
		research = new ResearchHelper(ingot_darkthaumium, TCEEntries.TCE, aspect, 1, -2, 2, new ItemStack(TCEItems.darkThaumium)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeDarkThaumium));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.ENERGY, 5);
		text = new String[]{"1", "2", "3" , "4"};
		research = new ResearchHelper(magic_energy, TCEEntries.TCE, aspect, 1, -4, 2, new ItemStack(TCEBlocks.battery)).setParents(ingot_darkthaumium).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(text[1]), new ResearchPage(text[2]), new ResearchPage(text[3]), new ResearchPage(TCERecipes.recipeMagicBattery),  new ResearchPage(TCERecipes.recipeMagicGenerator),  new ResearchPage(TCERecipes.recipeMagicCrystalCharger), new ResearchPage(TCERecipes.recipeMagicWandCharger), new ResearchPage(TCERecipes.recipeMagicCrystalT1), new ResearchPage(TCERecipes.recipeMagicCrystalT2), new ResearchPage(TCERecipes.recipeMagicCrystalT3), new ResearchPage(TCERecipes.recipeMagicCrystalT4), new ResearchPage(TCERecipes.recipeMagicCrystalT5), new ResearchPage(TCERecipes.recipeMagicCrystalT6), new ResearchPage(TCERecipes.recipeScanner), new ResearchPage(TCERecipes.recipeEnergyHelmet));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.FIRE, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(chestplate_fire, TCEEntries.TCE, aspect, -1, 2, 2, new ItemStack(TCEItems.fireChestplate)).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFireChestplate));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.TOOL, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_clean, TCEEntries.TCE, aspect, -1, -2, 2, new ItemStack(TCEItems.focusClean)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusClean));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.ELDRITCH, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_return, TCEEntries.TCE, aspect, -2, -2, 2, new ItemStack(TCEItems.focusReturn)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusReturn));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_trampoline, TCEEntries.TCE, aspect, -3, -2, 2, new ItemStack(TCEItems.focusTrampoline)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusTrampoline));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(shocker, TCEEntries.TCE, aspect, 5, -3, 2, new ItemStack(TCEBlocks.shocker)).setParents(TCEEntries.tce).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeShocker));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_shocker, TCEEntries.TCE, aspect, 6, -4, 2, new ItemStack(TCEItems.focusTessela)).setParents(TCEEntries.shocker).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusShocker));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(containment, TCEEntries.TCE, aspect, 4, -4, 2, new ItemStack(TCEBlocks.noMove)).setParents(TCEEntries.tce).registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeContainment));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.AIR, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(essentia_barrel, TCEEntries.TCE, aspect, 3, -4, 2, new ItemStack(TCEBlocks.barrel_1)).setParents(TCEEntries.tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.barrel_1),  new ResearchPage(TCERecipes.barrel_2),  new ResearchPage(TCERecipes.barrel_3),  new ResearchPage(TCERecipes.barrel_4),  new ResearchPage(TCERecipes.barrel_5),  new ResearchPage(TCERecipes.barrel_6));
		aspect.aspects.clear();
	}
	public static String tce = "Thaumcraft Extras"; 
	public static String ingot_darkthaumium = "Dark Thaumium"; 
	public static String focus_return = "Wand Focus: Return";
	public static String focus_clean = "Wand Focus: Clean";
	public static String focus_shocker = "Wand Focus: Shock";
	public static String focus_trampoline = "Wand Focus: Trampoline";

	public static String magic_energy = "Magic Energy";
	public static String chestplate_fire = "Chestplate of Flames";
	
	public static String dark_infusion = "Dark Infusion";
	public static String containment = "Containment";
	public static String shocker = "Shocker";
	public static String essentia_barrel = "Essentia Barrels";
	
	public static void initWandEntries()
	{
		ResearchItem research;
		String[] text;
		AspectList aspect = new AspectList();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_iron, TCEEntries.TCEWANDS, aspect, 0, 0, 2, new ItemStack(TCEWands.item_rod_iron)).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_iron));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_gold, TCEEntries.TCEWANDS, aspect, 1, 1, 2, new ItemStack(TCEWands.item_rod_gold)).setParents(TCEEntries.rod_iron).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_gold));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_diamond, TCEEntries.TCEWANDS, aspect, 2, 2, 2, new ItemStack(TCEWands.item_rod_diamond)).setParents(TCEEntries.rod_gold).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_diamond));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 5);
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_emerald, TCEEntries.TCEWANDS, aspect, 3, 3, 2, new ItemStack(TCEWands.item_rod_emerald)).setParents(TCEEntries.rod_diamond).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_emerald));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 10);
		aspect.add(Aspect.ENTROPY, 10);
		aspect.add(Aspect.FIRE, 10);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_devil, TCEEntries.TCEWANDS, aspect, 1, 3, 2, new ItemStack(TCEWands.item_rod_devil)).setParents(TCEEntries.rod_diamond).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_devil));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 15);
		aspect.add(Aspect.ENTROPY, 15);
		aspect.add(Aspect.AIR, 15);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_god, TCEEntries.TCEWANDS, aspect, 1, 4, 2, new ItemStack(TCEWands.item_rod_god)).setParents(TCEEntries.rod_devil).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_god));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 25);
		aspect.add(Aspect.ENTROPY, 25);
		aspect.add(Aspect.AIR, 25);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.rod_darkSilverwood, TCEEntries.TCEWANDS, aspect, 1, 5, 2, new ItemStack(TCEWands.item_rod_darkSilverwood)).setParents(TCEEntries.rod_god).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_darkSilverwood));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.cap_darkThaumium, TCEEntries.TCEWANDS, aspect, -1, -1, 2, new ItemStack(TCEWands.item_cap_darkThaumium)).setParents(TCEEntries.rod_iron).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_cap_darkThaumium));
		aspect.aspects.clear();
		
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
