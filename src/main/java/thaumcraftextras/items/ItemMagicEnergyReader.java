package thaumcraftextras.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemMagicEnergyReader extends Item{

	public ItemMagicEnergyReader(String itemName, String iconName) {
		
		setMaxStackSize(1);
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "item" + "." + itemName.toLowerCase());
		setCreativeTab(TCETabs.tabMain);
		name = itemName;
		icon = iconName;
		init();
	}
	String name;
	String icon;
	
	public void init()
	{
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + icon);
	}
}