package thaumcraftextras.main.init;

import java.awt.Color;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import thaumcraft.api.aspects.Aspect;
import thaumcraftextras.api.core.TCEApi;
import thaumcraftextras.items.ItemFlamingChestplate;
import thaumcraftextras.items.ItemPouchColored;
import thaumcraftextras.items.ItemXPExtractor;
import thaumcraftextras.items.ItemXPShard;
import thaumcraftextras.items.TCEItem;
import thaumcraftextras.items.TCEItemShard;
import thaumcraftextras.items.baubles.AmuletAngel;
import thaumcraftextras.items.baubles.AmuletGhost;
import thaumcraftextras.items.foci.beam.FocusClean;
import thaumcraftextras.items.foci.beam.FocusTessela;
import thaumcraftextras.items.foci.beam.FocusTrampoline;
import thaumcraftextras.items.foci.normal.FocusExchange;
import thaumcraftextras.items.foci.normal.FocusPechTrade;
import thaumcraftextras.items.foci.normal.FocusPotionEffect;
import thaumcraftextras.items.foci.normal.FocusReturn;
import thaumcraftextras.items.guide.ItemGuide;
import thaumcraftextras.items.scepters.TCEItemScepter;
import thaumcraftextras.items.scepters.actions.ActionFire;
import thaumcraftextras.items.scepters.actions.ActionOrder;
import wasliecore.helpers.ColorHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCEItems {
	public static void init()
	{
		initItems();
		initFoci();
		initScepters();
		initArmor();
		initBaubles();
	}
	
	public static void initItems()
	{
		essenceMagic = new TCEItem("Essence of Magic", "essence_magic", "essenceMagic");
		essenceDark = new TCEItem("Essence of Darkness", "essence_dark", "essenceDark");
		essenceLight = new TCEItem("Essence of Light", "essence_light", "essenceLight");

		darkThaumium = new TCEItem("Dark Thaumium Ingot", "ingot_darkthaumium", "ingotDarkThaumium");
		darkThaumiumNugget = new TCEItem("Dark Thaumium Nugget", "nugget_darkthaumium", "nuggetDarkThaumium");
		ignisFuel = new TCEItem("Ignis Fuel", "fuel_ignis", "fuelIgnis");

		darkShard = new TCEItemShard("dark", ColorHelper.getColorCodeFromRGB(50, 50, 50));
		
		xpShard = new ItemXPShard("xp", ColorHelper.getColorCodeFromColor(Color.green));
		xpExtractor = new ItemXPExtractor("Experience Extractor");
				
		pouch_color = new ItemPouchColored();
		guide = new ItemGuide("Thaumic Energy Guide", "guide");
	}
	public static TCEItem essenceMagic;
	public static TCEItem essenceLight;
	public static TCEItem essenceDark;
	public static TCEItem darkThaumium;
	public static TCEItem darkThaumiumNugget;
	public static TCEItem ignisFuel;
	
	public static ItemPouchColored pouch_color;

	public static TCEItemShard darkShard;	
	public static ItemXPShard xpShard;
	public static ItemXPExtractor xpExtractor;
	public static ItemGuide guide;

	public static void initFoci()
	{
		focusClean = new FocusClean(ColorHelper.getColorCodeFromRGB(0, 0, 100), "Wand Focus: Clean");
		focusReturn = new FocusReturn(ColorHelper.getColorCodeFromRGB(50, 50, 0), "Wand Focus: Return");
		focusTessela = new FocusTessela(ColorHelper.getColorCodeFromRGB(0, 0, 200), "Wand Focus: Shock");
		focusTrampoline = new FocusTrampoline(ColorHelper.getColorCodeFromRGB(100, 0, 100), "Wand Focus: Trampoline");
		focusPechTrade = new FocusPechTrade(ColorHelper.getColorCodeFromRGB(50, 0, 100), "Wand Focus: Pech Trade");
		focusPotion = new FocusPotionEffect(ColorHelper.getColorCodeFromRGB(0, 100, 100), "Wand Focus: Potion");
		focusExchange = new FocusExchange(ColorHelper.getColorCodeFromRGB(0, 50, 0), "Wand Focus: Exchange");
	}
	public static FocusClean focusClean;
	public static FocusReturn focusReturn;
	public static FocusTessela focusTessela;
	public static FocusTrampoline focusTrampoline;
	public static FocusPechTrade focusPechTrade;
	public static FocusPotionEffect focusPotion;
	public static FocusExchange focusExchange;

	public static void initArmor()
	{
		fireChestplate = new ItemFlamingChestplate("Flaming Chestplate", "chestplate_flame", ArmorMaterial.DIAMOND, 2, 1, fireChestplateDamage, 7);
		GameRegistry.registerItem(fireChestplate, fireChestplate.getUnlocalizedName());
	}
	public static ItemFlamingChestplate fireChestplate;
	public static int fireChestplateDamage = 300;

	
	public static void initScepters(){
		initScepterAbilities();
		scepter = new TCEItemScepter("base");		
	}
	public static TCEItemScepter scepter;
	
	public static String use = "USE";
	
	public static void initScepterAbilities(){
		TCEApi.scepterAction.put(Aspect.ORDER, new ActionOrder());
		TCEApi.scepterAction.put(Aspect.FIRE, new ActionFire());
	}
	
	public static void initBaubles()
	{
		amulet_angel = new AmuletAngel("Amulet of The Angelic", "amulet_angel");
		amulet_ghost = new AmuletGhost("Amulet of The Invisible", "amulet_ghost");
	}
	public static AmuletAngel amulet_angel;
	public static AmuletGhost amulet_ghost;

}