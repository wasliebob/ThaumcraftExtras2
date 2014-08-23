package thaumcraftextras.api.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** Requires Thaumcraft Extras 2: Tools to be enabled */
public interface IScepterAction {
	public void onClick(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side);
	public int getCost();
}