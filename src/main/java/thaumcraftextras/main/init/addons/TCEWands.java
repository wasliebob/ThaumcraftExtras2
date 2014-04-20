package thaumcraftextras.main.init.addons;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandRod;
import thaumcraftextras.helpers.LocalizationHelper;
import thaumcraftextras.main.init.addons.wands.BlockWand;
import thaumcraftextras.main.init.addons.wands.DarkSilverwoodRodOnUpdate;
import thaumcraftextras.main.init.addons.wands.TCEItemCap;
import thaumcraftextras.main.init.addons.wands.TCEItemRod;
import thaumcraftextras.main.init.addons.wands.TCEWandCap;
import thaumcraftextras.main.init.addons.wands.UltimateRodOnUpdate;

public class TCEWands {
	public static void init()
	{
		initBlock();
		initItemRod();
		initWandRod();
		initItemCap();
		initWandCap();
	}

	public static void initBlock()
	{
		wands = new BlockWand("Debug Block");
	}
	public static BlockWand wands;
	
	public static void initItemRod()
	{
		item_rod_iron = new TCEItemRod("iron", 30, null);
		item_rod_gold = new TCEItemRod("gold", 40, null);
		item_rod_diamond = new TCEItemRod("diamond", 65, null);
		item_rod_emerald = new TCEItemRod("emerald", 75, null);
		
		item_rod_devil = new TCEItemRod("devil", 250, null);
		item_rod_god = new TCEItemRod("god", 500, null);

		item_rod_ultimate = new TCEItemRod("ultimate", 9999999, getInfo(new String[]{LocalizationHelper.getLocalization("thaumcraftextras.tooltip.wand_ultimate", 0), LocalizationHelper.getLocalization("thaumcraftextras.tooltip.wand_ultimate", 1)}));
		item_rod_darkSilverwood = new TCEItemRod("darksilverwood", 1000, getInfo(new String[]{LocalizationHelper.getLocalization("thaumcraftextras.tooltip.wand_darksilverwood", 0), LocalizationHelper.getLocalization("thaumcraftextras.tooltip.wand_darksilverwood", 1)}));
	}
	public static TCEItemRod item_rod_ultimate;
	public static TCEItemRod item_rod_iron;
	public static TCEItemRod item_rod_gold;
	public static TCEItemRod item_rod_diamond;
	public static TCEItemRod item_rod_emerald;
	
	public static TCEItemRod item_rod_devil;
	public static TCEItemRod item_rod_god;

	public static TCEItemRod item_rod_darkSilverwood;

	public static void initWandRod()
	{
        rod_ultimate = new WandRod("ULTIMATE", 9999999, new ItemStack(item_rod_ultimate), 999, new UltimateRodOnUpdate(), new ResourceLocation("thaumcraftextras","textures/models/wand_rod_ultimate.png"));
        rod_iron = new WandRod("IRON", 30, new ItemStack(item_rod_iron), 12, new ResourceLocation("thaumcraftextras","textures/models/wand_rod_iron.png"));
        rod_gold = new WandRod("GOLD", 40, new ItemStack(item_rod_gold), 14, new ResourceLocation("thaumcraftextras","textures/models/wand_rod_gold.png"));
        rod_diamond = new WandRod("DIAMOND", 65, new ItemStack(item_rod_diamond), 16, new ResourceLocation("thaumcraftextras","textures/models/wand_rod_diamond.png"));
        rod_emerald = new WandRod("EMERALD", 75, new ItemStack(item_rod_emerald), 18, new ResourceLocation("thaumcraftextras","textures/models/wand_rod_emerald.png"));
        
        rod_devil = new WandRod("DEVIL", 250, new ItemStack(item_rod_devil), 20, new ResourceLocation("thaumcraftextras","textures/models/wand_rod_devil.png"));
        rod_god = new WandRod("GOD", 500, new ItemStack(item_rod_god), 27, new ResourceLocation("thaumcraftextras","textures/models/wand_rod_god.png"));

        rod_darkSilverwood = new WandRod("DARKSILVERWOOD", 1000, new ItemStack(item_rod_darkSilverwood), 50, new DarkSilverwoodRodOnUpdate(), new ResourceLocation("thaumcraftextras","textures/models/wand_rod_darksilverwood.png"));
	}
	public static WandRod rod_ultimate;
	public static WandRod rod_iron;
	public static WandRod rod_gold;
	public static WandRod rod_diamond;
	public static WandRod rod_emerald;
	
	public static WandRod rod_devil;
	public static WandRod rod_god;

	public static WandRod rod_darkSilverwood;
	
	public static void initItemCap()
	{
		item_cap_darkThaumium = new TCEItemCap("darkthaumium");
	}
	public static TCEItemCap item_cap_darkThaumium;
	
	public static void initWandCap()
	{
		cap_darkThaumium = new TCEWandCap("DARKTHAUMIUM", 0.8F, new ItemStack(TCEWands.item_cap_darkThaumium), 10, new ResourceLocation("thaumcraftextras", "textures/models/wand_cap_darkthaumium.png"));
	}
	public static TCEWandCap cap_darkThaumium;
	
	public static String[] getInfo(String[] special)
	{
		
//		String[] info = new String[]{special[0], special[1]};
		String[] info = new String[special.length];
		
		for(int i = 0; i < special.length; i++)
		{
			info[i] = special[i];
		}
		
		return info;
	}
}
