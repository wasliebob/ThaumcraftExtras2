package thaumcraftextras.items.baubles;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.registry.GameRegistry;

public class AmuletGhost extends Item implements IBauble{

	public AmuletGhost(String itemName, String iconName) {
		
		setMaxStackSize(1);
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "item" + "." + itemName.toLowerCase());
		setCreativeTab(TCETabs.tabMain);
		name = itemName;
		icon = iconName;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;
	String icon;
	float mod = 1.1F;
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + icon);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase base) {
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase base) {
		EntityPlayer player = (EntityPlayer)base;
		player.capabilities.allowFlying = true;
		player.capabilities.disableDamage = true;
		player.capabilities.setFlySpeed(player.capabilities.getFlySpeed()*mod);
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase base) {
		EntityPlayer player = (EntityPlayer)base;
		player.capabilities.allowFlying = false;
		player.capabilities.disableDamage = false;
		player.capabilities.setFlySpeed(player.capabilities.getFlySpeed()/mod);
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase base) {
		return base instanceof EntityPlayer;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase base) {
		return base instanceof EntityPlayer;
	}
}