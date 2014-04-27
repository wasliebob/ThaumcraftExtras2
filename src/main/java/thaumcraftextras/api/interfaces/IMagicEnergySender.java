package thaumcraftextras.api.interfaces;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IMagicEnergySender {
	/** 
	 * @return energy amount
	 */
	public int getEnergy();
	
	/** 
	 * @returns max energy
	 */
	public int getMaxEnergy();
	
	/**
	 * @param energy
	 * energy to set to
	 */
	public void setEnergy(int energy);
	
	/**
	 * @param energy
	 * energy to add
	 */
	public void increaseEnergy(int energy);
	
	/**
	 * @param energy
	 * energy to remove
	 */
	public void decreaseEnergy(int energy);
	
	/**
	 * @return max input/output
	 */
	public int getMaxTransfer();

	/**
	 * @param world
	 * the world
	 * @param x
	 * x coord of the block 
	 * @param y
	 * y coord of the block
	 * @param z
	 * z coord of the block
	 * @return TileEntity which can receive energy
	 */
	public TileEntity checkForBlock(World world, int x, int y, int z);
}