package thaumcraftextras.api.interfaces;

import net.minecraft.item.ItemStack;

public interface IMagicEnergyContainerItem{
	/**
	 * @param container
	 * energy container
	 * @param amount
	 * energy amount
	 */
	public void reduceEnergy(ItemStack container, int amount);
	
	/**
	 * @param container
	 * energy container
	 * @param amount
	 * energy amount
	 */
	public void increaseEnergy(ItemStack container, int amount);
	
	/**
	 * @param container
	 * energy container
	 * @param amount
	 * energy amount
	 */
	public void setEnergy(ItemStack container, int amount);
	
	/**
	 * @param container
	 * energy container
	 * @return energy stored
	 */
	public int getEnergy(ItemStack container);
	
	/**
	 * @param container
	 * energy container
	 * @return max capacity
	 */
	public int getMaxEnergy(ItemStack container);
}