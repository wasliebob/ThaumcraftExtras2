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
		text = new String[]{"1"};
		research = new ResearchHelper(magic_battery, TCEEntries.TCE, aspect, 3, -3, 2, new ItemStack(TCEBlocks.battery)).setParents(ingot_darkthaumium).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeMagicBattery));
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
		aspect.add(Aspect.ELDRITCH, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(focus_clean, TCEEntries.TCE, aspect, -1, -2, 2, new ItemStack(TCEItems.focusClean)).setParents(tce).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipeFocusClean));
		aspect.aspects.clear();
		
	}
	public static String tce = "Thaumcraft Extras"; 
	public static String ingot_darkthaumium = "Dark Thaumium"; 
	public static String focus_return = "Wand Focus: Return";
	public static String focus_clean = "Wand Focus: Clean";
	public static String magic_battery = "Magical Battery";
	public static String chestplate_fire = "Chestplate of Flames";
	
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
		research = new ResearchHelper(TCEEntries.rod_god, TCEEntries.TCEWANDS, aspect, 1, 4, 2, new ItemStack(TCEWands.item_rod_god)).setParents(TCEEntries.rod_god).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_rod_god));
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 5);
		text = new String[]{"1"};
		research = new ResearchHelper(TCEEntries.cap_darkThaumium, TCEEntries.TCEWANDS, aspect, -1, -1, 2, new ItemStack(TCEWands.item_cap_darkThaumium)).setParents(TCEEntries.rod_iron).setSecondary().registerResearchItem();
		research.setPages(new ResearchPage(text[0]), new ResearchPage(TCERecipes.recipe_cap_darkthaumium));
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
         ResearchCategories.registerCategory("TCE", new ResourceLocation(ThaumcraftExtras.modName.toLowerCase() + ":" + "/textures/items/essence_magic.png"), background);
       
         if(Config.addon_wands)
        	 ResearchCategories.registerCategory("TCE_WANDS", new ResourceLocation(ThaumcraftExtras.modName.toLowerCase() + ":" + "/textures/items/rod_darksilverwood.png"), background);
	}
	public static String TCE = "TCE";
	public static String TCEWANDS = "TCE_WANDS";
}
