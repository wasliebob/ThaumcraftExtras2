package thaumcraftextras.blocks.tiles;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.Thaumcraft;
import thaumcraftextras.api.interfaces.IMagicEnergyReceiver;
import thaumcraftextras.api.misc.classes.MagicEnergy;
import thaumcraftextras.api.misc.tiles.MagicEnergyReceiver;
import thaumcraftextras.api.misc.tiles.MagicEnergySender;
import thaumcraftextras.api.misc.tiles.MagicEnergyUniversal;

public class TileEntityMagicGenerator extends MagicEnergySender implements IAspectContainer, IEssentiaTransport{
	
	public TileEntityMagicGenerator()
	{
		storage = new MagicEnergy(1000, 10);
		map.put(Aspect.MAGIC, 10);
		baseTime = 40;
		chargeTime = baseTime;
	}
	public MagicEnergy storage;
    public static Map<Aspect, Integer> map = new HashMap<Aspect, Integer>();
    public static Aspect asp = Aspect.MAGIC;
    int energy;
    public static final String ENERGY = "ENERGY_MAGIC";
    public static final String COLOR = "COLOR_MAGIC";
    int baseTime;
    int chargeTime;
    
    @Override
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
    		TileEntity tile = checkForBlock(worldObj, xCoord, yCoord, zCoord);
    		if(tile != null){
    			if(tile instanceof MagicEnergyUniversal){
					MagicEnergyUniversal to = (MagicEnergyUniversal)tile;
					if(getEnergy() == 0 && to.getEnergy() + calcEnergy(to) <= to.getMaxEnergy()){
						if(!to.isSending){
						to.increaseEnergy(calcEnergy(to));
						if(Minecraft.getMinecraft().renderViewEntity != null){
    						Thaumcraft.proxy.sourceStreamFX(worldObj,(double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D ,(float)to.xCoord + 0.5F, (float)to.yCoord + 0.5F, (float)to.zCoord + 0.5F, this.getColor());
						}
						
						updateBlocks(to);
						}
					}else if(to.getEnergy() + calcEnergy(to) <= to.getMaxEnergy()){
						if(!to.isSending){
						to.increaseEnergy(drawFromTube());
						updateBlocks(to);
						}
					}else if(getEnergy() + calcEnergyA() <= to.getMaxEnergy()){
						if(!to.isSending){
						to.increaseEnergy(drawFromTube());
						
						updateBlocks(to);
					}
					}
    			}else if(tile instanceof MagicEnergyReceiver){
    				MagicEnergyReceiver to = (MagicEnergyReceiver)tile;
					if(getEnergy() == 0 && to.getEnergy() + calcEnergy(to) <= to.getMaxEnergy()){
						to.increaseEnergy(calcEnergy(to));
						if(Minecraft.getMinecraft().renderViewEntity != null){
    						Thaumcraft.proxy.sourceStreamFX(worldObj,(double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D ,(float)to.xCoord + 0.5F, (float)to.yCoord + 0.5F, (float)to.zCoord + 0.5F, this.getColor());
						}
						updateBlocks(to);
					}else if(to.getEnergy() + calcEnergy(to) <= to.getMaxEnergy()){
						to.increaseEnergy(drawFromTube());
						updateBlocks(to);
					}else if(getEnergy() + calcEnergyA() <= to.getMaxEnergy()){
						to.increaseEnergy(drawFromTube());
						updateBlocks(to);
					}
    			}else{
    				
    			}
    		}else if(getEnergy() + drawFromTube() <= getMaxEnergy()){
    			increaseEnergy(drawFromTube());
//    			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    		}
    	}
    }

    
    public void updateBlocks(MagicEnergyReceiver to){
    	worldObj.markBlockForUpdate(to.xCoord, to.yCoord, to.zCoord);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    public void updateBlocks(MagicEnergyUniversal to){
		if(!to.isSending){
    	worldObj.markBlockForUpdate(to.xCoord, to.yCoord, to.zCoord);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
    }

	@Override
	public int getEnergy() {
		return storage.getEnergy();
	}

	@Override
	public int getMaxEnergy() {
		return storage.getMaxEnergy();
	}

	@Override
	public void setEnergy(int energy) {
		storage.setEnergy(energy);
		
		if(worldObj != null)
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void increaseEnergy(int energy) {		
		storage.addEnergy(energy);
//		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	 public int drawFromTube()
	    {
	    	 ForgeDirection orientation = getOrientation();
	         TileEntity tile = ThaumcraftApiHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, orientation);

	         if (tile != null) {
	        	 worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	                 IEssentiaTransport ic = (IEssentiaTransport) tile;

	                 if (!ic.canOutputTo(orientation.getOpposite()))
	                         return 0;

	                 for(Aspect aspect : map.keySet())
	                		if(ic.getSuctionType(orientation.getOpposite()) == aspect && ic.getSuctionAmount(orientation.getOpposite()) < getSuctionAmount(orientation) && ic.takeEssentia(aspect, 1, orientation) == 1)
	                			return map.get(aspect);
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
			return new AspectList().add(Aspect.MAGIC, getEnergy());
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
			return arg0 == getOrientation() ? asp : null;
		}

		@Override
		public void setSuction(Aspect arg0, int arg1) {		
		}

		@Override
		public int getMaxTransfer() {
			return storage.getMaxTransfer();
		}
		
		@Override
		public void decreaseEnergy(int energy) {
			storage.removeEnergy(energy);
		}
		
		public int calcEnergyA()
		{
			if(getEnergy() >= getMaxTransfer()){
				if(getMaxEnergy() - getEnergy() > getMaxTransfer()){
					return this.getMaxTransfer();
				}else if(this.getMaxEnergy() - this.getEnergy() < this.getMaxTransfer()){
					return this.getMaxEnergy() - this.getEnergy();
				}else if(this.getMaxEnergy() - this.getEnergy() == this.getMaxTransfer()){
					return this.getMaxTransfer();
				}else if(this.getEnergy() == this.getMaxEnergy()){
					return 0;
				}else{
					return 0;
				}
			}else if(this.getEnergy() + this.getMaxTransfer() <= this.getMaxEnergy()){
				return this.getMaxTransfer();
			}else{
				return 0;
			}
		}
		
		public int calcEnergy(MagicEnergyUniversal to)
		{
			if(getEnergy() >= to.getMaxTransfer()){
				if(to.getMaxEnergy() - to.getEnergy() > to.getMaxTransfer()){
					decreaseEnergy(to.getMaxTransfer());
					return to.getMaxTransfer();
				}else if(to.getMaxEnergy() - to.getEnergy() < to.getMaxTransfer()){
					decreaseEnergy(to.getMaxEnergy() - to.getEnergy());
					return to.getMaxEnergy() - to.getEnergy();
				}else if(to.getMaxEnergy() - to.getEnergy() == to.getMaxTransfer()){
					decreaseEnergy(to.getMaxEnergy() - to.getEnergy());
					return to.getMaxTransfer();
				}else if(to.getEnergy() == to.getMaxEnergy()){
					return 0;
				}else{
					return 0;
				}
			}else if(to.getEnergy() + to.getMaxTransfer() <= to.getMaxEnergy()){
				return to.getMaxTransfer();
			}else{
				return 0;
			}
		}
		
		public int calcEnergy(MagicEnergyReceiver to)
		{
			if(to.shouldReceive()){
			if(getEnergy() >= to.getMaxTransfer()){
				if(to.getMaxEnergy() - to.getEnergy() > to.getMaxTransfer()){
					decreaseEnergy(to.getMaxTransfer());
					return to.getMaxTransfer();
				}else if(to.getMaxEnergy() - to.getEnergy() < to.getMaxTransfer()){
					decreaseEnergy(to.getMaxEnergy() - to.getEnergy());
					return to.getMaxEnergy() - to.getEnergy();
				}else if(to.getMaxEnergy() - to.getEnergy() == to.getMaxTransfer()){
					decreaseEnergy(to.getMaxEnergy() - to.getEnergy());
					return to.getMaxTransfer();
				}else if(to.getEnergy() == to.getMaxEnergy()){
					return 0;
				}else{
					return 0;
				}
			}else{
				return to.getMaxTransfer();
			}
			}else{
				return 0;
			}
		}
		
		@Override
		public void writeToNBT(NBTTagCompound nbt)
		{
			super.writeToNBT(nbt);
			
			if(this.getEnergy() > 0)
				nbt.setInteger(ENERGY, this.getEnergy());
//			if(this.getColor() != 0)
				nbt.setInteger(COLOR, this.getColor());
		}
		
		@Override
		public void readFromNBT(NBTTagCompound nbt)
		{
			super.readFromNBT(nbt);
			setEnergy(nbt.getInteger(ENERGY));
			setColor(nbt.getInteger(COLOR));
		}
		
		@Override
		public Packet getDescriptionPacket() {
		    NBTTagCompound tagCompound = new NBTTagCompound();
		    writeToNBT(tagCompound);
		    
		    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, tagCompound);
		}
		
		@Override
		public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
			this.readFromNBT(packet.func_148857_g());
		}
		
		public TileEntity checkForBlock(World world, int x, int y, int z)
		{
			for(int i = 1; i < 16; i++){				
				if(world.getTileEntity(x + i, y, z) != null && world.getTileEntity(x + i, y, z) instanceof MagicEnergyUniversal || world.getTileEntity(x + i, y, z) instanceof IMagicEnergyReceiver && !(world.getTileEntity(x + i, y, z) instanceof TileEntityMagicBattery))
					return world.getTileEntity(x + i, y, z);
				
				else if(world.getTileEntity(x - i, y, z) != null && world.getTileEntity(x - i, y, z) instanceof MagicEnergyUniversal || world.getTileEntity(x - i, y, z) instanceof IMagicEnergyReceiver && !(world.getTileEntity(x - i, y, z) instanceof TileEntityMagicBattery))
					return world.getTileEntity(x - i, y, z);
				
				else if(world.getTileEntity(x, y, z - i) != null && world.getTileEntity(x, y, z -i) instanceof MagicEnergyUniversal || world.getTileEntity(x, y, z -i) instanceof IMagicEnergyReceiver && !(world.getTileEntity(x, y, z - i) instanceof TileEntityMagicBattery))
					return world.getTileEntity(x, y, z - i);
				
				else if(world.getTileEntity(x, y, z + i) != null && world.getTileEntity(x, y, z +i) instanceof MagicEnergyUniversal || world.getTileEntity(x, y, z +i) instanceof IMagicEnergyReceiver&& !(world.getTileEntity(x, y, z + i) instanceof TileEntityMagicBattery))
					return world.getTileEntity(x, y, z + i);
			}
			
			return null;
		}

		@Override
		public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
			return 0;
		}

		@Override
		public int takeEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
			return 0;
		}
}