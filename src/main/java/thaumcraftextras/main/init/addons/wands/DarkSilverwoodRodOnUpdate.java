package thaumcraftextras.main.init.addons.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.api.interfaces.IMagicEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DarkSilverwoodRodOnUpdate implements IWandRodOnUpdate {

	@SideOnly(Side.CLIENT)
	@Override
	public void onUpdate(ItemStack stack, EntityPlayer player) {
		if(stack != null && player != null){
			if(stack.getItem() instanceof ItemWandCasting && hasBattery(player, 1, 1)){
				ItemWandCasting wand = (ItemWandCasting)stack.getItem();
				for(Aspect asp : Aspect.getPrimalAspects()){
					if(wand.getVis(stack, asp) < wand.getMaxVis(stack)){
						doCharge(stack, getBattery(player), wand, asp, 1, 1, hasBattery(player, 1, 1));
					}
				}
				
			}
		}
	}
	
	public void doCharge(ItemStack stack, ItemStack battery, ItemWandCasting wand, Aspect asp, int charge, int mod, boolean doCharge)
	{
		if(doCharge){
			if(battery.getItemDamage() + 1 <= battery.getMaxDamage()){
				battery.setItemDamage(battery.getItemDamage() + (charge*mod));
				wand.addVis(stack, asp, charge, true);
			}
		}
	}

	public ItemStack getBattery(EntityPlayer player)
	{
		for(int i = 0; i < player.inventory.getSizeInventory(); i++)
		{
			if(player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof IMagicEnergyContainerItem && player.inventory.getStackInSlot(i).getItemDamage() + 1 <= player.inventory.getStackInSlot(i).getMaxDamage()){
				return player.inventory.getStackInSlot(i);
			}
		}
			return null;
	}
	
	public boolean hasBattery(EntityPlayer player, int cost, int mod)
	{
		for(int i = 0; i < player.inventory.getSizeInventory(); i++)
		{
			if(player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof IMagicEnergyContainerItem && player.inventory.getStackInSlot(i).getItemDamage() + 1 <= player.inventory.getStackInSlot(i).getMaxDamage()){
				return true;
			}
		}
			return false;
	}
}
