package thaumcraftextras.api.interfaces;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IMagicEnergy{
	public int getEnergy();
	public int getMaxEnergy();
	public void setEnergy(int energy);
	public void increaseEnergy(int energy);
	public void decreaseEnergy(int energy);
	public int getMaxTransfer();
	public TileEntity checkForBlock(World world, int x, int y, int z);
	public void setColor(int color);
	public int getColor();
}
