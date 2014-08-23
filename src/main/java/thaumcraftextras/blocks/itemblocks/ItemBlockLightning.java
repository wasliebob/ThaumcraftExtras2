package thaumcraftextras.blocks.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import thaumcraftextras.helpers.DyeHelper;

public class ItemBlockLightning extends ItemBlock
{
	public ItemBlockLightning(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}
       
    public int getMetadata(int meta){
    	return meta;
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id)
	{		
			list.add(EnumChatFormatting.GRAY + DyeHelper.getColorName(stack.getItemDamage()));	
	}
}