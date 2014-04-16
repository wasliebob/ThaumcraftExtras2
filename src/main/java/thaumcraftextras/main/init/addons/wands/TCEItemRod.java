package thaumcraftextras.main.init.addons.wands;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCEItemRod extends Item{

	public TCEItemRod(String itemName, int visStorage, String[] description) {
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "rod" + "." + itemName.toLowerCase());
		setCreativeTab(TCETabs.tabWands);
		name = itemName;
		des = description;
		storage = visStorage;
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;
	int storage;
	String[] des;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id)
	{		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
				list.add(EnumChatFormatting.RED + "Vis Storage: " + EnumChatFormatting.GRAY + storage);
			if(des != null){
				for(int i = 0; i < des.length; i++){
					list.add(EnumChatFormatting.GRAY + des[i]);}}
			}else{
				list.add(EnumChatFormatting.GREEN + "Press " + "Shift " + "for more info.");
			}
	}
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "rod_" + name);
	}
}