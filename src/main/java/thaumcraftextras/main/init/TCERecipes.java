package thaumcraftextras.main.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.common.config.ConfigItems;
import thaumcraftextras.api.core.TCEApi;
import thaumcraftextras.main.init.addons.TCEWands;
import thaumcraftextras.main.init.addons.wands.helpers.WandCreationHelper;

public class TCERecipes {

	public static void init()
	{
		initVanillaRecipes();
		initThaumcraftRecipes();
		initWandRecipes();
		initDarkInfusionRecipes();
	}
	
	public static void initVanillaRecipes()
	{
		
	}
	
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
			'I', new ItemStack(Items.ender_pearl)});
		aspect.aspects.clear();
		
		aspect.add(Aspect.FIRE, 20);
		aspect.add(Aspect.ORDER, 10);
		recipeFireChestplate = ThaumcraftApi.addInfusionCraftingRecipe(TCEEntries.chestplate_fire, new ItemStack(TCEItems.fireChestplate), 2, aspect, new ItemStack(ConfigItems.itemChestThaumium), new ItemStack[]{
			new ItemStack(ConfigItems.itemSwordThaumium), new ItemStack(ConfigItems.itemShard, 2), new ItemStack(Items.flint_and_steel)});
		aspect.aspects.clear();
		
		
		aspect.add(Aspect.ORDER, 10);
		recipeMagicBattery = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.magic_battery, new ItemStack(TCEBlocks.battery, 1, 0), aspect, new Object[]{
			"XIX",
			"IYI",
			"XIX",
			'X', new ItemStack(TCEItems.essenceMagic),
			'I', new ItemStack(ConfigItems.itemResource, 2),
			'Y', new ItemStack(Blocks.iron_block)});
		aspect.aspects.clear();

	}
	public static ShapedArcaneRecipe recipeMagicEssence;
	public static ShapedArcaneRecipe recipeDarkThaumium;
	public static ShapedArcaneRecipe recipeFocusClean;
	public static ShapedArcaneRecipe recipeFocusReturn; /** Todo */
	public static ShapedArcaneRecipe recipeMagicBattery;
	public static InfusionRecipe recipeFireChestplate;
	
	public static void initWandRecipes()
	{
		recipe_rod_iron = WandCreationHelper.registerRecipe(TCEEntries.rod_iron, new ItemStack(TCEWands.item_rod_iron), new ItemStack(Items.iron_ingot), new ItemStack(Items.stick), 1);
		recipe_rod_gold = WandCreationHelper.registerRecipe(TCEEntries.rod_gold, new ItemStack(TCEWands.item_rod_gold), new ItemStack(Items.gold_ingot), new ItemStack(TCEWands.item_rod_iron), 2);
		recipe_rod_diamond = WandCreationHelper.registerRecipe(TCEEntries.rod_diamond, new ItemStack(TCEWands.item_rod_diamond), new ItemStack(Items.diamond), new ItemStack(TCEWands.item_rod_gold), 3);
		recipe_rod_emerald = WandCreationHelper.registerRecipe(TCEEntries.rod_emerald, new ItemStack(TCEWands.item_rod_emerald), new ItemStack(Items.emerald), new ItemStack(TCEWands.item_rod_diamond), 4);
		
		recipe_rod_devil = WandCreationHelper.registerRecipe(TCEEntries.rod_devil, new ItemStack(TCEWands.item_rod_devil), new ItemStack(TCEItems.ignisFuel), new ItemStack(TCEWands.item_rod_diamond), 20);
		recipe_rod_god = WandCreationHelper.registerRecipe(TCEEntries.rod_god, new ItemStack(TCEWands.item_rod_god), new ItemStack(TCEItems.essenceLight), new ItemStack(TCEWands.item_rod_devil), 30);

		recipe_cap_darkthaumium = ThaumcraftApi.addArcaneCraftingRecipe(TCEEntries.cap_darkThaumium, new ItemStack(TCEWands.item_cap_darkThaumium), new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.ORDER, 10), new Object[]{
			"XXX",
			"X X",
			'X', TCEItems.darkThaumiumNugget});
	}
	public static ShapedArcaneRecipe recipe_rod_iron;
	public static ShapedArcaneRecipe recipe_rod_gold;
	public static ShapedArcaneRecipe recipe_rod_diamond;
	public static ShapedArcaneRecipe recipe_rod_emerald;
	
	public static ShapedArcaneRecipe recipe_rod_devil;
	public static ShapedArcaneRecipe recipe_rod_god;
	
	public static ShapedArcaneRecipe recipe_cap_darkthaumium;
	
	public static void initDarkInfusionRecipes()
	{
		TCEApi.addDarkInfusionRecipe(TCEItems.xpShard, new ItemStack(TCEItems.darkShard));
	}
}
