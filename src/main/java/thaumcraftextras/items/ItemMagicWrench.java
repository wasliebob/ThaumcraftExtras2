package thaumcraftextras.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemMagicWrench extends Item{

	public ItemMagicWrench(String itemName, String iconName) {
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