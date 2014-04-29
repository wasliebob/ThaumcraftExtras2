package thaumcraftextras.items.guide;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraftextras.helpers.GuiHelper;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemGuide extends Item{

	public ItemGuide(String itemName, String iconName) {
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "item" + "." + itemName.toLowerCase());
		setMaxStackSize(1);
		setCreativeTab(TCETabs.tabMain);
		
		name = itemName;
		icon = iconName;
		GameRegistry.registerItem(this, this.getUnlocalizedName());

	}
	String name;
	String icon;
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + icon);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		player.openGui(ThaumcraftExtras.instance, GuiHelper.gui_guide, world, 0, 0, 0);
		return stack;
	}
}