package thaumcraftextras.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileJarFillable;

public class TileEntityEssentiaBarrel extends TileJarFillable{
	
	public TileEntityEssentiaBarrel()
	{
		super();
	}
	public int mod = 1;
	public int prevMod = 0;
	
	public int getMod(){return mod;}
	public void setMod(int modifier){mod = modifier;}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(mod != prevMod){
				this.maxAmount = 64*mod;
				prevMod = mod;
			}
		}
		if ((!this.worldObj.isRemote) && (++this.count % 5 == 0) && (this.amount < this.maxAmount)) {
			fillJars();
		}
	}
	int count = 0;
	
	  void fillJars()
	  {
	    TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.UP);
	    if (te != null)
	    {
	      IEssentiaTransport ic = (IEssentiaTransport)te;
	      if (!ic.canOutputTo(ForgeDirection.DOWN)) {
	        return;
	      }
	      Aspect ta = null;
	      if (this.aspectFilter != null) {
	        ta = this.aspectFilter;
	      } else if ((this.aspect != null) && (this.amount > 0)) {
	        ta = this.aspect;
	      } else if ((ic.getEssentiaAmount(ForgeDirection.DOWN) > 0) && 
	        (ic.getSuctionAmount(ForgeDirection.DOWN) < getSuctionAmount(ForgeDirection.UP)) && (getSuctionAmount(ForgeDirection.UP) >= ic.getMinimumSuction())) {
	        ta = ic.getEssentiaType(ForgeDirection.DOWN);
	      }
	      if ((ta != null) && (ic.getSuctionAmount(ForgeDirection.DOWN) < getSuctionAmount(ForgeDirection.UP))) {
	        addToContainer(ta, ic.takeEssentia(ta, 1, ForgeDirection.DOWN));
	      }
	    }
	  }
	@Override
	public void readCustomNBT(NBTTagCompound tag)
	{
		this.mod = tag.getInteger("Modifier");
		this.maxAmount = tag.getInteger("maxAmount");
		this.aspect = Aspect.getAspect(tag.getString("Aspect"));
	    this.aspectFilter = Aspect.getAspect(tag.getString("AspectFilter"));
	    this.amount = tag.getShort("Amount");
	    this.facing = tag.getByte("facing");
	}
	  
	@Override
	public void writeCustomNBT(NBTTagCompound tag)
	{
		if (this.aspect != null) {
			tag.setString("Aspect", this.aspect.getTag());
	    }
	    if (this.aspectFilter != null) {
	      tag.setString("AspectFilter", this.aspectFilter.getTag());
	    }
	    tag.setInteger("maxAmount", 64*this.getMod());
	    tag.setInteger("Modifier", this.getMod());
	    tag.setShort("Amount", (short)this.amount);
	    tag.setByte("facing", (byte)this.facing);
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