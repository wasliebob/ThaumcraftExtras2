package thaumcraftextras.api.misc.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

import org.lwjgl.input.Keyboard;

import thaumcraftextras.api.interfaces.IMagicEnergyContainer;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MagicEnergyCrystal extends Item implements IMagicEnergyContainer{

	public MagicEnergyCrystal(int maxEnergy, int crystalColor, int tier) {
		super();
		setMaxStackSize(1);
		
		maxMCKE = maxEnergy;
		color = crystalColor;
		setMaxDamage(maxEnergy);
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "crystal" + "." + tier);
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	int maxMCKE;
	int color;
	IIcon primary;
	IIcon secondary;

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int renderPass) {
		if(renderPass > 0) {
			return this.primary;
		}
		return this.secondary;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getRenderPasses(int meta) {
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{		
		if(pass > 0) 
		{
			return color;
		}
			return 0;
	}
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
		this.primary = ir.registerIcon("thaumcraftextras:crystal_magic_1");
		this.secondary = ir.registerIcon("thaumcraftextras:crystal_magic_2");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id)
	{		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
				list.add(EnumChatFormatting.RED + "Energy Stored: " + EnumChatFormatting.GRAY + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
			}else{
				list.add(EnumChatFormatting.GREEN + "Press " + "Shift " + "for more info.");
			}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		list.add(new ItemStack(item, 1, maxMCKE));      
		list.add(new ItemStack(item, 1, 0));      
	}

	@Override
	public void reduceEnergy(ItemStack container, int amount) {
		if(container.getItemDamage() - amount >= 0)
			container.setItemDamage(container.getItemDamage() - amount);
	}

	@Override
	public void increaseEnergy(ItemStack container, int amount) {
		if(container.getItemDamage() + amount <= container.getMaxDamage())
			container.setItemDamage(container.getItemDamage() + amount);
	}

	@Override
	public void setEnergy(ItemStack container, int amount) {
		container.setItemDamage(amount);
	}

	@Override
	public int getEnergy(ItemStack container) {
		return container.getItemDamage();
	}
	
	@Override
	public int getMaxEnergy(ItemStack container){
		return container.getMaxDamage();
	}
}