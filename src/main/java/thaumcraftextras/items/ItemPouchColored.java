package thaumcraftextras.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import thaumcraft.common.items.wands.ItemFocusPouch;
import thaumcraftextras.helpers.DyeHelper;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPouchColored extends ItemFocusPouch{
	public ItemPouchColored()
	{
		setCreativeTab(TCETabs.tabMain);
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "item" + "." + "pouch_focus");
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
    @Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		return DyeHelper.getColorCode(stack.getItemDamage());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
			list.add(EnumChatFormatting.GRAY + DyeHelper.getColorName(stack.getItemDamage()));
	}

    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for(int i = 0; i < 15; i++)
        	list.add(new ItemStack(this, 1, i));
    }
}