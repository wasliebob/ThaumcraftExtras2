package thaumcraftextras.blocks.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import thaumcraftextras.api.misc.classes.TileEntityMagicBattery;
import thaumcraftextras.main.init.TCEBlocks;

public class ItemBlockMagicBattery extends ItemBlock
{
	public ItemBlockMagicBattery(Block block)
	{
		super(block);
	}
    
    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, 
            float hitX, float hitY, float hitZ, int metadata){
    	if(!world.setBlock(x, y, z, TCEBlocks.battery))
    		return false;
    	
    	if(world.getBlock(x, y, z) == TCEBlocks.battery){
    		if(world.getTileEntity(x, y, z) != null){
    			TileEntity tile = world.getTileEntity(x, y, z);
    			if(tile instanceof TileEntityMagicBattery){
    				TileEntityMagicBattery magic = (TileEntityMagicBattery)tile;
    				if(stack.hasTagCompound()){
    					magic.setEnergy(stack.stackTagCompound.getInteger("BATTERY_MCE"));
    				}
    			}
    		}
    	}
    	return true;
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id)
	{		
		if(stack.hasTagCompound())
			list.add(EnumChatFormatting.RED + "Energy Stored: " + EnumChatFormatting.GRAY + (stack.stackTagCompound.getInteger("BATTERY_MCE")));	
	}
}