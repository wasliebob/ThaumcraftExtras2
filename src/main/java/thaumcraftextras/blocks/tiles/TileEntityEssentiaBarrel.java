package thaumcraftextras.blocks.tiles;

import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.tiles.TileJarFillable;

public class TileEntityEssentiaBarrel extends TileJarFillable{
	
	public TileEntityEssentiaBarrel(int max)
	{
		this.maxAmount = max;
	}

	@Override
	public int getMinimumSuction()
	{
	    return super.getMinimumSuction() + 5;
	}
	
	@Override
	public int getSuctionAmount(ForgeDirection loc)
	{
	    return super.getSuctionAmount(loc) + 5;
	}
	
	@Override
	public boolean isConnectable(ForgeDirection face)
	{
		return face == ForgeDirection.UP || face == ForgeDirection.DOWN;
	}
	  
	@Override
	public boolean canInputFrom(ForgeDirection face)
	{
		return face == ForgeDirection.UP || face == ForgeDirection.DOWN;
	}
	  
	@Override
	public boolean canOutputTo(ForgeDirection face)
	{
		return face == ForgeDirection.UP || face == ForgeDirection.DOWN;
	}
}