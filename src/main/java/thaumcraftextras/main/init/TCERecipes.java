package thaumcraftextras.main.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import thaumcraftextras.main.Config;
import thaumcraftextras.main.init.addons.TCEWands;
import thaumcraftextras.main.init.addons.wands.helpers.WandCreationHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCERecipes {

	public static void init()
	{
		initVanillaRecipes();
		initThaumcraftRecipes();
		initBarrelRecipes();
		initCrystalRecipes();
		initDarkInfusionRecipes();
		
		if(Config.addon_wands)
			initWandRecipes();
	}
	
	public static void initVanillaRecipes()
	{
		recipeAltar = GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.darkendAltar), new Object[]{
			"XY",
			"YX",
			'X', Items.iron_ingot,
			'Y', new ItemStack(ConfigBlocks.blockCosmeticSolid)});
		
		recipeCore = GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.darkendCore), new Object[]{
			"XY",
			"YX",
			'X', Blocks.iron_block,
			'Y', new ItemStack(ConfigBlocks.blockCosmeticSolid)});
		
		recipeMatrix = GameRegistry.addShapedRecipe(new ItemStack(TCEBlocks.darkendMatrix), new Object[]{
			"XY",
			"YX",
			'X', Items.diamond,
			'Y', new ItemStack(ConfigBlocks.blockCosmeticSolid)});
		
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
		
		GameRegistry.addShapelessRecipe(new ItemStack(TCEItems.darkThaumiumNugget, 1, 9), new ItemStack(TCEItems.darkThaumium));
	}
	public static ShapedArcaneRecipe barrel_essentia;
	
	public static void initThaumcraftRecipes()
	{		
		AspectList aspect = new AspectList();
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 2);
		recipeMagicEssence = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.tce, new ItemStack(TCEItems.essenceMagic), aspect, new Object[]{
			" X ",
			"XIX",
			" X ",
			'X', new ItemStack(Items.gold_nugget) ,
			'I', new ItemStack(ConfigItems.itemShard, 1, 4)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 2);
		recipeDarkThaumium = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.ingot_darkthaumium, new ItemStack(TCEItems.darkThaumium), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(TCEItems.essenceMagic) ,
			'I', new ItemStack(Items.diamond),
			'Y', new ItemStack(ConfigItems.itemResource, 1, 2)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.AIR, 10);
		aspect.add(Aspect.ORDER, 10);
		recipeFocusClean = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_clean, new ItemStack(TCEItems.focusClean), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(Items.quartz) ,
			'Y', new ItemStack(ConfigItems.itemShard),
			'I', new ItemStack(Items.bone)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 10);
		aspect.add(Aspect.ORDER, 10);
		recipeFocusReturn = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_return, new ItemStack(TCEItems.focusReturn), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(Items.quartz) ,
			'Y', new ItemStack(ConfigItems.itemShard, 1, 1),
			'I', new ItemStack(Items.ender_pearl)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.AIR, 10);
		aspect.add(Aspect.ORDER, 10);
		recipeFocusTrampoline = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_trampoline, new ItemStack(TCEItems.focusTrampoline), aspect, new Object[]{
			"YXY",
			"XIX",
			"YXY",
			'X', new ItemStack(Items.quartz) ,
			'Y', new ItemStack(ConfigItems.itemShard, 1, 4),
			'I', new ItemStack(Items.slime_ball)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.FIRE, 20);
		aspect.add(Aspect.ORDER, 10);
		recipeFireChestplate = ThaumcraftApi.addInfusionCraftingRecipe(TCEEntries.chestplate_fire, new ItemStack(TCEItems.fireChestplate), 2, aspect, new ItemStack(ConfigItems.itemChestThaumium), new ItemStack[]{
			new ItemStack(ConfigItems.itemSwordThaumium), new ItemStack(ConfigItems.itemShard, 2), new ItemStack(Items.flint_and_steel)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 10);
		recipeEnergyHelmet = ThaumcraftApi.addInfusionCraftingRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.energyHelmet), 2, aspect, new ItemStack(ConfigItems.itemGoggles), new ItemStack[]{
			new ItemStack(TCEItems.crystal_1), new ItemStack(ConfigItems.itemShard, 2), new ItemStack(TCEItems.reader)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 10);
		recipeScanner = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.reader, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(ConfigItems.itemResource, 1, 2),
			'I', new ItemStack(Items.iron_ingot),
			'Y', new ItemStack(Blocks.glass_pane)});
		aspect.aspects.clear();

		
		aspect.add(Aspect.ORDER, 10);
		recipeMagicBattery = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.magic_energy, new ItemStack(TCEBlocks.battery, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(Blocks.iron_block)});
		aspect.aspects.clear();

		aspect.add(Aspect.ORDER, 10);
		recipeMagicGenerator = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.magic_energy, new ItemStack(TCEBlocks.generator, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEBlocks.battery)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 10);
		recipeMagicCrystalCharger = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.magic_energy, new ItemStack(TCEBlocks.crystalCharger, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceDark),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEItems.crystal_1, 1, TCEItems.crystal_1.getMaxDamage())});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ORDER, 100);
		aspect.add(Aspect.ENTROPY, 100);
		recipeMagicWandCharger = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.magic_energy, new ItemStack(TCEBlocks.wandCharger, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceDark),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEItems.crystal_6)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 25);
		aspect.add(Aspect.AIR, 25);
		recipeFocusShocker = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.focus_shocker, new ItemStack(TCEItems.focusTessela, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEItems.ignisFuel)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 25);
		aspect.add(Aspect.AIR, 25);
		recipeShocker = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.shocker, new ItemStack(TCEBlocks.shocker, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(TCEBlocks.blockIgnis)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.ENTROPY, 25);
		aspect.add(Aspect.AIR, 25);
		recipeContainment = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.containment, new ItemStack(TCEBlocks.noMove, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 1, 2),
			'Y', new ItemStack(ConfigBlocks.blockCosmeticOpaque)});
		aspect.aspects.clear();
	}
	public static ShapedArcaneRecipe recipeMagicEssence;
	public static ShapedArcaneRecipe recipeDarkThaumium;
	
	public static ShapedArcaneRecipe recipeFocusClean;
	public static ShapedArcaneRecipe recipeFocusReturn;
	public static ShapedArcaneRecipe recipeFocusShocker;
	public static ShapedArcaneRecipe recipeFocusTrampoline;

	public static ShapedArcaneRecipe recipeMagicBattery;
	public static ShapedArcaneRecipe recipeMagicGenerator;
	public static ShapedArcaneRecipe recipeMagicCrystalCharger;
	public static ShapedArcaneRecipe recipeMagicWandCharger;

	public static ShapedArcaneRecipe recipeShocker;
	public static ShapedArcaneRecipe recipeContainment;
	
	public static ShapedArcaneRecipe recipeScanner;	
	public static InfusionRecipe recipeFireChestplate;
	public static InfusionRecipe recipeEnergyHelmet;

	public static void initCrystalRecipes()
	{
		recipeMagicCrystalT1 = getCrystalRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.crystal_1), new ItemStack(Blocks.redstone_block), new ItemStack(Items.iron_ingot), new ItemStack(Items.gold_ingot), 1);
		recipeMagicCrystalT2 = getCrystalRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.crystal_2), new ItemStack(TCEItems.crystal_1), new ItemStack(Items.iron_ingot), new ItemStack(Items.gold_ingot), 2);
		recipeMagicCrystalT3 = getCrystalRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.crystal_3), new ItemStack(TCEItems.crystal_2), new ItemStack(Items.iron_ingot), new ItemStack(Items.gold_ingot), 3);
		recipeMagicCrystalT4 = getCrystalRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.crystal_4), new ItemStack(TCEItems.crystal_3), new ItemStack(Items.iron_ingot), new ItemStack(Items.gold_ingot), 4);
		recipeMagicCrystalT5 = getCrystalRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.crystal_5), new ItemStack(TCEItems.crystal_4), new ItemStack(Items.iron_ingot), new ItemStack(Items.gold_ingot), 5);
		recipeMagicCrystalT6 = getCrystalRecipe(TCEEntries.magic_energy, new ItemStack(TCEItems.crystal_6), new ItemStack(TCEItems.crystal_5), new ItemStack(Items.iron_ingot), new ItemStack(Items.gold_ingot), 6);

	}
	public static ShapedArcaneRecipe recipeMagicCrystalT1;
	public static ShapedArcaneRecipe recipeMagicCrystalT2;
	public static ShapedArcaneRecipe recipeMagicCrystalT3;
	public static ShapedArcaneRecipe recipeMagicCrystalT4;
	public static ShapedArcaneRecipe recipeMagicCrystalT5;
	public static ShapedArcaneRecipe recipeMagicCrystalT6;

	public static void initWandRecipes()
	{
		recipe_rod_iron = WandCreationHelper.registerRecipe(TCEEntries.rod_iron, new ItemStack(TCEWands.item_rod_iron), new ItemStack(Items.iron_ingot), new ItemStack(Items.stick), 1);
		recipe_rod_gold = WandCreationHelper.registerRecipe(TCEEntries.rod_gold, new ItemStack(TCEWands.item_rod_gold), new ItemStack(Items.gold_ingot), new ItemStack(TCEWands.item_rod_iron), 2);
		recipe_rod_diamond = WandCreationHelper.registerRecipe(TCEEntries.rod_diamond, new ItemStack(TCEWands.item_rod_diamond), new ItemStack(Items.diamond), new ItemStack(TCEWands.item_rod_gold), 3);
		recipe_rod_emerald = WandCreationHelper.registerRecipe(TCEEntries.rod_emerald, new ItemStack(TCEWands.item_rod_emerald), new ItemStack(Items.emerald), new ItemStack(TCEWands.item_rod_diamond), 4);
		
		recipe_rod_devil = WandCreationHelper.registerRecipe(TCEEntries.rod_devil, new ItemStack(TCEWands.item_rod_devil), new ItemStack(TCEItems.ignisFuel), new ItemStack(TCEWands.item_rod_diamond), 20);
		recipe_rod_god = WandCreationHelper.registerRecipe(TCEEntries.rod_god, new ItemStack(TCEWands.item_rod_god), new ItemStack(TCEItems.essenceLight), new ItemStack(TCEWands.item_rod_devil), 30);

		recipe_cap_darkThaumium = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.cap_darkThaumium, new ItemStack(TCEWands.item_cap_darkThaumium), new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.ORDER, 10), new Object[]{
			"XXX",
			"X X",
			'X', TCEItems.darkThaumiumNugget});
		
		recipe_rod_darkSilverwood = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.rod_darkSilverwood, new ItemStack(TCEWands.item_rod_darkSilverwood), new AspectList().add(Aspect.ENTROPY, 500).add(Aspect.ORDER, 500), new Object[]{
			" YX",
			"YIY",
			"XY ",
			'I', TCEWands.item_rod_god,
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
	
	public static void initDarkInfusionRecipes()
	{
		TCEApi.addDarkInfusionRecipe(TCEItems.essenceDark, new ItemStack(TCEItems.essenceLight));
		TCEApi.addDarkInfusionRecipe(Items.coal, new ItemStack(TCEItems.ignisFuel));
		
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
}
