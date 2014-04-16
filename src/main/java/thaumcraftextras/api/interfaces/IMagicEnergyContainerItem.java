package thaumcraftextras.api.interfaces;

import net.minecraft.item.ItemStack;

public interface IMagicEnergyContainerItem{
	public void reduceEnergy(ItemStack container, int amount);
	public void increaseEnergy(ItemStack container, int amount);
	public void setEnergy(ItemStack container, int amount);
	public int getEnergy(ItemStack container);
	public int getMaxEnergy(ItemStack container);
}
