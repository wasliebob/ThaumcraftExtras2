package thaumcraftextras.blocks.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import thaumcraftextras.helpers.LocalizationHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockMagicEnergyBattery extends ItemBlock{

	public ItemBlockMagicEnergyBattery(Block block) {
		super(block);
		text = new String[]{LocalizationHelper.getLocalization("thaumcraftextras.tooltip.magic_battery" , 0)};
	}
	String[] text;
		
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) 
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			if(text.length > 0){
				for(int i = 0;i < text.length; i++)
					list.add(EnumChatFormatting.GRAY + text[i]);
			}
		}else{
			list.add(EnumChatFormatting.GREEN + "Press " + "Shift " + "for info.");
		}
	}
}