package thaumcraftextras.api.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IScepterAction {
	public void onClick(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side);
	public int getCost();
}