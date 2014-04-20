package thaumcraftextras.blocks.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileEntityLavaGen extends TileEntity implements IAspectContainer, IEssentiaTransport, IFluidHandler {

	public TileEntityLavaGen()
	{
		list.add(Aspect.FIRE, am);
	}
	AspectList list = new AspectList();
	Aspect fire = Aspect.FIRE;
	int am = 10;
	FluidTank tank = new FluidTank(FluidRegistry.LAVA, 0, 10000);
	
	@Override
	public void updateEntity()
	{
		if(list.getAmount(fire) >= am){
			generateLava();
		}
	}
	
	public void generateLava()
	{
//		takeEssentia(fire, am, ForgeDirection.UNKNOWN);
		this.fill(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, drawFromTube()), canDrain(null, FluidRegistry.LAVA));
	}
	
	public int drawFromTube()
    {
    	 ForgeDirection orientation = getOrientation();
         TileEntity tile = ThaumcraftApiHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, orientation);

         if (tile != null) {
                 IEssentiaTransport ic = (IEssentiaTransport) tile;

                 if (!ic.canOutputTo(orientation.getOpposite()))
                         return 0;

                 for(Aspect aspect : list.aspects.keySet())
                		if(ic.getSuctionType(orientation.getOpposite()) == aspect && ic.getSuctionAmount(orientation.getOpposite()) < getSuctionAmount(orientation) && ic.takeEssentia(aspect, 1, orientation) == 1)
                			return list.aspects.get(aspect);
                 return 0;
         }
         return 0;
    }
    
    ForgeDirection getOrientation() 
    {
        return ForgeDirection.UP;
    }
	
    @Override
    public AspectList getAspects() {
		return new AspectList().add(fire, list.getAmount(fire));
    }

    @Override
    public void setAspects(AspectList paramAspectList) { }

    @Override
    public boolean doesContainerAccept(Aspect paramAspect) 
    {
            return false;
    }

    @Override
    public int addToContainer(Aspect paramAspect, int paramInt) {
            return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect paramAspect, int paramInt) {
            return false;
    }

    @Override
    public boolean takeFromContainer(AspectList paramAspectList) {
            return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect paramAspect, int paramInt) {
            return false;
    }

    @Override
    public boolean doesContainerContain(AspectList paramAspectList) {
            return false;
    }

    @Override
    public int containerContains(Aspect paramAspect) {
            return 0;
    }

    @Override
    public boolean isConnectable(ForgeDirection paramForgeDirection) {
            return paramForgeDirection == getOrientation();
    }

    @Override
    public boolean canInputFrom(ForgeDirection paramForgeDirection) {
            return false;
    }

    @Override
    public boolean canOutputTo(ForgeDirection paramForgeDirection) {
            return isConnectable(paramForgeDirection);
    }

    
    @Override
    public int getMinimumSuction() {
            return 0;
    }

    @Override
    public boolean renderExtendedTube() {
            return false;
    }


	@Override
	public int getEssentiaAmount(ForgeDirection arg0) {
		return 0;
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection dir) {
		return null;
	}

	@Override
	public int getSuctionAmount(ForgeDirection arg0) {
		return arg0 == getOrientation() ? 128 : 0;
	}

	@Override
	public Aspect getSuctionType(ForgeDirection arg0) {
		return arg0 == getOrientation() ? fire : null;
	}

	@Override
	public void setSuction(Aspect arg0, int arg1) {		
	}

	
//	@Override
//	public int addEssentia(Aspect asp, int amount, ForgeDirection arg2) {
//		if(asp == Aspect.FIRE){
//			list.add(asp, amount);
//			return list.getAmount(asp);
//		}
//		return list.getAmount(asp);
//	}
//
//	@Override
//	public boolean canInputFrom(ForgeDirection dir) {
//		return true;
//	}
//
//	@Override
//	public boolean canOutputTo(ForgeDirection arg0) {
//		return false;
//	}
//
//	@Override
//	public int getEssentiaAmount(ForgeDirection arg0) {
//		return list.getAmount(list.getAspects()[0]);
//	}
//
//	@Override
//	public Aspect getEssentiaType(ForgeDirection arg0) {
//		return list.getAspects()[0];
//	}
//
//	@Override
//	public int getMinimumSuction() {
//		return 0;
//	}
//
//	@Override
//	public int getSuctionAmount(ForgeDirection arg0) {
//		return arg0 == getOrientation() ? 128 : 0;
//	}
//
//	@Override
//	public Aspect getSuctionType(ForgeDirection arg0) {
//		return arg0 == getOrientation() ? fire : null;
//	}
//
//	@Override
//	public boolean isConnectable(ForgeDirection arg0) {
//		return true;
//	}
//
//	@Override
//	public boolean renderExtendedTube() {
//		return false;
//	}
//
//	@Override
//	public void setSuction(Aspect arg0, int arg1) {
//		
//	}
//
//	@Override
//	public int takeEssentia(Aspect asp, int amount, ForgeDirection arg2) {
//		list.reduce(asp, amount);
//		return list.getAmount(list.getAspects()[0]);
//	}
//
//	@Override
//	public int addToContainer(Aspect asp, int amount) {
//		list.add(asp, amount);
//		return list.getAmount(list.getAspects()[0]);
//	}
//
//	@Override
//	public int containerContains(Aspect arg0) {
//		return 0;
//	}
//
//	@Override
//	public boolean doesContainerAccept(Aspect asp) {
//		return asp == fire;
//	}
//
//	@Override
//	@Deprecated
//	public boolean doesContainerContain(AspectList arg0) {
//		return false;
//	}
//
//	@Override
//	public boolean doesContainerContainAmount(Aspect asp, int amount) {
//		return list.getAmount(list.getAspects()[0]) >= amount;
//	}
//
//	@Override
//	public AspectList getAspects() {
//		return list;
//	}
//
//	@Override
//	public void setAspects(AspectList arg0) {	
//	}
//
//	@Override
//	@Deprecated
//	public boolean takeFromContainer(AspectList arg0) {
//		return false;
//	}
//
//	@Override
//	public boolean takeFromContainer(Aspect arg0, int arg1) {
//		return false;
//	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{tank.getInfo()};
	}

	@Override
	public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int takeEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
}