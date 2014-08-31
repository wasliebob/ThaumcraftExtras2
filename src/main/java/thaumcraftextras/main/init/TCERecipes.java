package thaumcraftextras.main.init;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraftextras.api.core.TCEApi;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;
import thaumcraftextras.main.Config;
import thaumcraftextras.main.init.addons.TCETools;
import thaumcraftextras.main.init.addons.wands.helpers.WandCreationHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCERecipes {

	public static void init()
	{
		initVanillaRecipes();
		initThaumcraftRecipes();
		initInfuse();
		initBarrelRecipes();
		initDarkInfusionRecipes();
		initAdvancedAltarRecipes();
		initClasherRecipes();
		if(Config.addon_tools)
			initToolsRecipes();
	}
	
	public static void initVanillaRecipes()
	{
		recipeAltar = GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.darkendAltar), new Object[]{
			"XY",
			"YX",
			'X', Items.iron_ingot,
			'Y', new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1)});
		
		recipeCore = GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.darkendCore), new Object[]{
			"XY",
			"YX",
			'X', Blocks.iron_block,
			'Y', new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1)});
		
		recipeMatrix = GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.darkendMatrix), new Object[]{
			"XY",
			"YX",
			'X', Items.diamond,
			'Y', new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1)});
		
		GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.window_barrel_essentia), new Object[]{
			"XXX",
			"XYX",
			"XXX",
			'X', Blocks.glass,
			'Y', TCEItems.essenceMagic});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TCEBlocks.plank_darkmagic, 4, 0), new ItemStack(TCEBlocks.log_darkmagic));
		
		for(int i = 0; i < 16; i++)
			GameRegistry.addShapedRecipe(new ItemStack(TCEItems.pouch_color, 1, i), new Object[]{
			"XY",
			'X', new ItemStack(ConfigItems.itemFocusPouch),
			'Y', new ItemStack(Items.dye, 1, i)});
		
		for(int i = 0; i < 16; i++)
			GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.blockLight, 1, i), new Object[]{
			"XY",
			'X', new ItemStack(TCEBlocks.blockLight),
			'Y', new ItemStack(Items.dye, 1, i)});
	}
	public static IRecipe recipeAltar;
	public static IRecipe recipeCore;
	public static IRecipe recipeMatrix;

	public static void initBarrelRecipes()
	{
		barrel_essentia = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.essentia_barrel, new ItemStack(TCEBlocks.barrel_essentia), new AspectList().add(Aspect.ORDER, 5), new Object[]{
			"YZY",
			"ZXZ",
			"YZY",
			'Z', Items.iron_ingot,
			'Y', Blocks.log,
			'X', new ItemStack(ConfigBlocks.blockJar)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TCEItems.darkThaumiumNugget, 9, 0), new ItemStack(TCEItems.darkThaumium));
	}
	public static ShapedArcaneRecipe barrel_essentia;
	
	public static void initThaumcraftRecipes()
	{		
		AspectList aspect;
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ORDER), 2);
		recipeMagicEssence = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.tce, new ItemStack(TCEItems.essenceMagic), aspect, new Object[]{
			" X ",
			"XIX",
			" X ",
			'X', new ItemStack(Items.gold_nugget) ,
			'I', new ItemStack(ConfigItems.itemShard, 1, 4)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ENTROPY), 2);
		recipeDarkThaumium = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.ingot_darkthaumium, new ItemStack(TCEItems.darkThaumium), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
 			'X', new ItemStack(TCEItems.essenceMagic) ,
			'I', new ItemStack(Items.diamond),
			'Y', new ItemStack(ConfigItems.itemResource, 1, 2)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.AIR), 10);
		aspect.add(check(Aspect.ORDER), 10);
		recipeFocusClean = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_clean, new ItemStack(TCEItems.focusClean), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(Items.quartz) ,
			'Y', new ItemStack(ConfigItems.itemShard),
			'I', new ItemStack(Items.bone)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ENTROPY), 10);
		aspect.add(check(Aspect.ORDER), 10);
		recipeFocusReturn = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_return, new ItemStack(TCEItems.focusReturn), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(Items.quartz) ,
			'Y', new ItemStack(ConfigItems.itemShard, 1, 1),
			'I', new ItemStack(Items.ender_pearl)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.AIR), 10);
		aspect.add(check(Aspect.ORDER), 10);
		recipeFocusTrampoline = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_trampoline, new ItemStack(TCEItems.focusTrampoline), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(Items.quartz) ,
			'Y', new ItemStack(ConfigItems.itemShard, 1, 4),
			'I', new ItemStack(Items.slime_ball)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ENTROPY), 10);
		aspect.add(check(Aspect.ORDER), 10);
		recipeFocusPotion = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_potion, new ItemStack(TCEItems.focusPotion), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(Items.quartz) ,
			'Y', new ItemStack(ConfigItems.itemShard, 1, 3),
			'I', new ItemStack(Items.golden_apple)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ENTROPY), 25);
		aspect.add(check(Aspect.AIR), 25);
		recipeFocusShocker = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_shocker, new ItemStack(TCEItems.focusTessela, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEItems.ignisFuel)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.AIR), 35);
		recipeFocusPechTrade = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_pechTrade, new ItemStack(TCEItems.focusPechTrade, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceLight),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEItems.darkShard)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ORDER), 50);
		recipeFocusExchange = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_exchange, new ItemStack(TCEItems.focusExchange, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"ZIZ",
			'X', new ItemStack(TCEItems.essenceLight),
			'Z', new ItemStack(TCEItems.essenceDark),
			'I', new ItemStack(ConfigItems.itemShard, 1, 4),
			'Y', new ItemStack(ConfigItems.itemFocusTrade)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ENTROPY), 25);
		aspect.add(check(Aspect.AIR), 25);
		recipeShocker = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.shocker, new ItemStack(TCEBlocks.shocker, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEBlocks.blockIgnis)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.ENTROPY), 25);
		aspect.add(check(Aspect.AIR), 25);
		recipeContainment = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.containment, new ItemStack(TCEBlocks.noMove, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(ConfigBlocks.blockCosmeticOpaque)});
		aspect = new AspectList();
		
		aspect.add(check(Aspect.AIR), 2);
		aspect.add(check(Aspect.FIRE), 2);
		recipeBlockLight = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.tce, new ItemStack(TCEBlocks.blockLight, 1, 0), aspect, new Object[]{
			"XI",
			'X', new ItemStack(Blocks.glowstone),
			'I', new ItemStack(Blocks.glass)});
		aspect = new AspectList();		
		
		aspect.add(check(Aspect.ORDER), 10);
		aspect.add(check(Aspect.ENTROPY), 10);
		recipeAdvancedAltar = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.altar_advanced, new ItemStack(TCEBlocks.altar_advanced), aspect, new Object[]{
			"XYX",
			"YZY",
			"XYX",
			'Y', Blocks.glass_pane,
			'Z', TCEItems.essenceMagic,
			'X', Blocks.obsidian});
		aspect = new AspectList();	
		
		aspect.add(check(Aspect.ORDER), 15);
		aspect.add(check(Aspect.ENTROPY), 15);
		recipeClasher = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.altar_advanced, new ItemStack(TCEBlocks.clasher), aspect, new Object[]{
			"XYX",
			"YZY",
			"XYX",
			'Y', TCEItems.essenceDark,
			'Z', Items.diamond,
			'X', TCEItems.essenceLight});
		aspect = new AspectList();	
		
		aspect.add(check(Aspect.ENTROPY), 50);
		recipeAmuletGhost = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.amulet_ghost, new ItemStack(TCEItems.amulet_ghost), aspect, new Object[]{
			"XYX",
			"YZY",
			"XYX",
			'Y', Blocks.glass_pane,
			'X', TCEItems.essenceDark,
			'Z', Items.nether_star});
		aspect = new AspectList();	
		}
	public static ShapedArcaneRecipe recipeMagicEssence;
	public static ShapedArcaneRecipe recipeDarkThaumium;
	
	public static ShapedArcaneRecipe recipeFocusClean;
	public static ShapedArcaneRecipe recipeFocusReturn;
	public static ShapedArcaneRecipe recipeFocusShocker;
	public static ShapedArcaneRecipe recipeFocusTrampoline;
	public static ShapedArcaneRecipe recipeFocusPechTrade;
	public static ShapedArcaneRecipe recipeFocusPotion;
	public static ShapedArcaneRecipe recipeFocusExchange;

	public static ShapedArcaneRecipe recipeMagicBattery;
	public static ShapedArcaneRecipe recipeMagicGenerator;
	public static ShapedArcaneRecipe recipeMagicCrystalCharger;
	public static ShapedArcaneRecipe recipeMagicWandCharger;
	public static ShapedArcaneRecipe recipeBlockLight;

	public static ShapedArcaneRecipe recipeShocker;
	public static ShapedArcaneRecipe recipeContainment;
	public static ShapedArcaneRecipe recipeAdvancedAltar;
	public static ShapedArcaneRecipe recipeClasher;

	public static ShapedArcaneRecipe recipeScanner;	

	public static ShapedArcaneRecipe recipeAmuletGhost;	

	public static void initInfuse()
	{
		AspectList aspect;
		aspect = new AspectList();
		
		aspect.add(Aspect.FIRE, 20);
		aspect.add(Aspect.ORDER, 10);
		recipeFireChestplate = ThaumcraftApi.addInfusionCraftingRecipe(TCEEntries.chestplate_fire, new ItemStack(TCEItems.fireChestplate), 2, aspect, new ItemStack(ConfigItems.itemChestThaumium), new ItemStack[]{
			new ItemStack(ConfigItems.itemSwordThaumium), new ItemStack(ConfigItems.itemShard, 2), new ItemStack(Items.flint_and_steel)});
		aspect = new AspectList();

	}
	public static InfusionRecipe recipeFireChestplate;

	public static void initToolsRecipes()
	{
		recipe_rod_iron = WandCreationHelper.registerRecipe(TCEEntries.rod_iron, new ItemStack(TCETools.item_rod_iron), new ItemStack(Items.iron_ingot), new ItemStack(Items.stick), 1);
		recipe_rod_gold = WandCreationHelper.registerRecipe(TCEEntries.rod_gold, new ItemStack(TCETools.item_rod_gold), new ItemStack(Items.gold_ingot), new ItemStack(TCETools.item_rod_iron), 2);
		recipe_rod_diamond = WandCreationHelper.registerRecipe(TCEEntries.rod_diamond, new ItemStack(TCETools.item_rod_diamond), new ItemStack(Items.diamond), new ItemStack(TCETools.item_rod_gold), 3);
		recipe_rod_emerald = WandCreationHelper.registerRecipe(TCEEntries.rod_emerald, new ItemStack(TCETools.item_rod_emerald), new ItemStack(Items.emerald), new ItemStack(TCETools.item_rod_diamond), 4);
		
		recipe_rod_devil = WandCreationHelper.registerRecipe(TCEEntries.rod_devil, new ItemStack(TCETools.item_rod_devil), new ItemStack(TCEItems.ignisFuel), new ItemStack(TCETools.item_rod_diamond), 20);
		recipe_rod_god = WandCreationHelper.registerRecipe(TCEEntries.rod_god, new ItemStack(TCETools.item_rod_god), new ItemStack(TCEItems.essenceLight), new ItemStack(TCETools.item_rod_devil), 30);

		recipe_cap_darkThaumium = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.cap_darkThaumium, new ItemStack(TCETools.item_cap_darkThaumium), new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.ORDER, 10), new Object[]{
			"XXX",
			"X X",
			'X', TCEItems.darkThaumiumNugget});
		
		recipe_rod_darkSilverwood = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.rod_darkSilverwood, new ItemStack(TCETools.item_rod_darkSilverwood), new AspectList().add(Aspect.ENTROPY, 500).add(Aspect.ORDER, 500), new Object[]{
			" YX",
			"YIY",
			"XY ",
			'I', TCETools.item_rod_god,
			'X', Items.nether_star,
			'Y', TCEItems.essenceDark});
	}
	public static ShapedArcaneRecipe recipe_rod_iron;
	public static ShapedArcaneRecipe recipe_rod_gold;
	public static ShapedArcaneRecipe recipe_rod_diamond;
	public static ShapedArcaneRecipe recipe_rod_emerald;
	
	public static ShapedArcaneRecipe recipe_rod_devil;
	public static ShapedArcaneRecipe recipe_rod_god;
	public static ShapedArcaneRecipe recipe_rod_darkSilverwood;

	public static ShapedArcaneRecipe recipe_cap_darkThaumium;
	
	public static void initDarkInfusionRecipes(){
		TCEApi.addDarkInfusionRecipe(TCEItems.essenceMagic, new ItemStack(TCEItems.essenceDark));
		TCEApi.addDarkInfusionRecipe(ConfigItems.itemShard, new ItemStack(TCEItems.darkShard));
		TCEApi.addDarkInfusionRecipe(Item.getItemFromBlock(ConfigBlocks.blockMagicalLog), new ItemStack(TCEBlocks.log_darkmagic));

		if(Config.dark_exchange){
			TCEApi.addDarkInfusionRecipe(Items.diamond, new ItemStack(Items.gold_ingot, 3, 0));
			TCEApi.addDarkInfusionRecipe(Items.gold_ingot, new ItemStack(Items.iron_ingot, 5, 0));
			TCEApi.addDarkInfusionRecipe(Items.emerald, new ItemStack(Items.diamond, 2, 0));
			
			/** Tools */
			TCEApi.addDarkInfusionRecipe(Items.iron_axe, new ItemStack(Items.iron_ingot, 3, 0));
			TCEApi.addDarkInfusionRecipe(Items.iron_pickaxe, new ItemStack(Items.iron_ingot, 3, 0));
			TCEApi.addDarkInfusionRecipe(Items.iron_shovel, new ItemStack(Items.iron_ingot, 1, 0));
			TCEApi.addDarkInfusionRecipe(Items.iron_hoe, new ItemStack(Items.iron_ingot, 2, 0));
			TCEApi.addDarkInfusionRecipe(Items.iron_sword, new ItemStack(Items.iron_ingot, 2, 0));

			
			TCEApi.addDarkInfusionRecipe(Items.golden_axe, new ItemStack(Items.gold_ingot, 3, 0));
			TCEApi.addDarkInfusionRecipe(Items.golden_pickaxe, new ItemStack(Items.gold_ingot, 3, 0));
			TCEApi.addDarkInfusionRecipe(Items.golden_shovel, new ItemStack(Items.gold_ingot, 1, 0));
			TCEApi.addDarkInfusionRecipe(Items.golden_hoe, new ItemStack(Items.gold_ingot, 2, 0));
			TCEApi.addDarkInfusionRecipe(Items.golden_sword, new ItemStack(Items.gold_ingot, 2, 0));

			
			TCEApi.addDarkInfusionRecipe(Items.diamond_axe, new ItemStack(Items.diamond, 3, 0));
			TCEApi.addDarkInfusionRecipe(Items.diamond_pickaxe, new ItemStack(Items.diamond, 3, 0));
			TCEApi.addDarkInfusionRecipe(Items.diamond_shovel, new ItemStack(Items.diamond, 1, 0));
			TCEApi.addDarkInfusionRecipe(Items.diamond_hoe, new ItemStack(Items.diamond, 2, 0));
			TCEApi.addDarkInfusionRecipe(Items.diamond_sword, new ItemStack(Items.diamond, 2, 0));

			TCEApi.addDarkInfusionRecipe(Items.iron_helmet, new ItemStack(Items.iron_ingot, 5, 0));
			TCEApi.addDarkInfusionRecipe(Items.iron_chestplate, new ItemStack(Items.iron_ingot, 8, 0));
			TCEApi.addDarkInfusionRecipe(Items.iron_leggings, new ItemStack(Items.iron_ingot, 7, 0));
			TCEApi.addDarkInfusionRecipe(Items.iron_boots, new ItemStack(Items.iron_ingot, 4, 0));

			TCEApi.addDarkInfusionRecipe(Items.golden_helmet, new ItemStack(Items.gold_ingot, 5, 0));
			TCEApi.addDarkInfusionRecipe(Items.golden_chestplate, new ItemStack(Items.gold_ingot, 8, 0));
			TCEApi.addDarkInfusionRecipe(Items.golden_leggings, new ItemStack(Items.gold_ingot, 7, 0));
			TCEApi.addDarkInfusionRecipe(Items.golden_boots, new ItemStack(Items.gold_ingot, 4, 0));

			TCEApi.addDarkInfusionRecipe(Items.diamond_helmet, new ItemStack(Items.diamond, 5, 0));
			TCEApi.addDarkInfusionRecipe(Items.diamond_chestplate, new ItemStack(Items.diamond, 8, 0));
			TCEApi.addDarkInfusionRecipe(Items.diamond_leggings, new ItemStack(Items.diamond, 7, 0));
			TCEApi.addDarkInfusionRecipe(Items.diamond_boots, new ItemStack(Items.diamond, 4, 0));
		}
	}
	
	public static void initAdvancedAltarRecipes()
	{
		AdvancedAltarRecipe recipe;
		Item input;
		
		input = Item.getItemFromBlock(Blocks.cobblestone);
		recipe = new AdvancedAltarRecipe(new ItemStack(input), new ItemStack(Blocks.obsidian), Aspect.ENTROPY, 5);
		TCEApi.addAdvancedAltarRecipe(input, recipe); 
		
		input = TCEItems.essenceMagic;
		recipe = new AdvancedAltarRecipe(new ItemStack(TCEItems.essenceMagic), new ItemStack(TCEItems.essenceLight), Aspect.LIGHT, 5);
		TCEApi.addAdvancedAltarRecipe(TCEItems.essenceMagic, recipe); 
		
		input = Items.coal;
		recipe = new AdvancedAltarRecipe(new ItemStack(input), new ItemStack(TCEItems.ignisFuel), Aspect.FIRE, 2);
		TCEApi.addAdvancedAltarRecipe(input, recipe); 

		input = Item.getItemFromBlock(Blocks.coal_block);
		recipe = new AdvancedAltarRecipe(new ItemStack(input), new ItemStack(TCEBlocks.blockIgnis), Aspect.FIRE, 5);
		TCEApi.addAdvancedAltarRecipe(input, recipe);
	}
	
	public static void initClasherRecipes()
	{		
		TCEApi.addClasherRecipe(Items.lava_bucket, Items.water_bucket, new ItemStack(Blocks.obsidian));
		TCEApi.addClasherRecipe(TCEItems.essenceDark, TCEItems.essenceLight, new ItemStack(TCEItems.essenceMagic));

	}
	
	public static ShapedArcaneRecipe getCrystalRecipe(String entry, ItemStack result, ItemStack core, ItemStack inside, ItemStack outside, int tier)
	{
		int base = 5;
		int amount = base*tier;
		ShapedArcaneRecipe recipe;
		AspectList aspectlist = new AspectList().add(Aspect.ENTROPY, amount).add(Aspect.ORDER, amount);
		recipe = ThaumcraftApi.addArcaneCraftingRecipe(entry, new ItemStack(result.getItem(), 1, result.getItemDamage()), aspectlist, new Object[]{
			"XYX",
			"YIY",
			"XYX",
			'X', outside,
			'Y', inside,
			'I', core});
		return recipe;
	}
	
	public static Aspect check(Aspect asp){
		if(Aspect.getPrimalAspects().contains(asp)){
			return asp;
		}else{
			System.out.println("[TCE2] Stupid DerpyWaslieException/WaslieDerp detected, your game will now crash, report back to the author and yell at him for letting this happen)");
			Minecraft.getMinecraft().crashed(new CrashReport("[TCE2] Stupid DerpyWaslieException/WaslieDerp detected, your game will now crash, report back to the author and yell at him for letting this happen this happen", new Throwable()));
			return Aspect.FIRE;
		}
	}
}