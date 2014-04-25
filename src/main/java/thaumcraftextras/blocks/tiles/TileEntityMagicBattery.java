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
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraftextras.api.interfaces.IMagicEnergyReceiver;
import thaumcraftextras.api.misc.classes.MagicEnergy;
import thaumcraftextras.api.misc.tiles.MagicEnergyReceiver;
import thaumcraftextras.api.misc.tiles.MagicEnergyUniversal;

public class TileEntityMagicBattery extends MagicEnergyUniversal{
	
	public TileEntityMagicBattery()
	{
		storage = new MagicEnergy(1000, 10);	
	}
	public MagicEnergy storage;
    public static Map<Aspect, Integer> map = new HashMap<Aspect, Integer>();
    int energy;
    public static final String ENERGY = "ENERGY_MAGIC";
    public static final String COLOR = "COLOR_MAGIC";

    @Override
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
        		TileEntity tile = checkForBlock(worldObj, xCoord, yCoord, zCoord);
    			if(tile != null){
    				if(tile instanceof MagicEnergyReceiver && !(tile instanceof TileEntityMagicBattery)){
    						MagicEnergyReceiver to = (MagicEnergyReceiver)tile;
    						if(!to.isDone){
    							this.setSending(true);
    							to.increaseEnergy(calcEnergy(to));
    							updateBlocks(to);
    						if(Minecraft.getMinecraft().renderViewEntity != null){
    							Thaumcraft.proxy.sourceStreamFX(worldObj,(double)xCoord + 0.5D, (double)yCoord, (double)zCoord + 0.5D ,(float)to.xCoord + 0.5F, (float)to.yCoord + 1.0F, (float)to.zCoord + 0.5F, this.getColor());}
    						}else{
    							this.setSending(false);
    							updateBlocks(to);
    					}
    				}else if(tile instanceof MagicEnergyUniversal && !(tile instanceof TileEntityMagicBattery)){
    					MagicEnergyUniversal to = (MagicEnergyUniversal)tile;
    					this.setSending(true);
    					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
						to.increaseEnergy(calcEnergy(to));
						updateBlocks(to);
						if(Minecraft.getMinecraft().renderViewEntity != null){
							Thaumcraft.proxy.sourceStreamFX(worldObj,(double)xCoord + 0.5D, (double)yCoord, (double)zCoord + 0.5D ,(float)to.xCoord + 0.5F, (float)to.yCoord + 1.0F, (float)to.zCoord + 0.5F, this.getColor());}
    				}
    			}else{
    			}
    	}
    }
    
    public void updateBlocks(MagicEnergyReceiver to){
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	worldObj.markBlockForUpdate(to.xCoord, to.yCoord, to.zCoord);
    }
    
    public void updateBlocks(MagicEnergyUniversal to){
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    	worldObj.markBlockForUpdate(to.xCoord, to.yCoord, to.zCoord);
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
	}

	@Override
	public void increaseEnergy(int energy) {
		storage.addEnergy(energy);
	}
	
	@Override
	public void decreaseEnergy(int energy) {
		storage.removeEnergy(energy);
	}
	
	@Override
	public int getMaxTransfer(){
		return storage.getMaxTransfer();
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
				return to.getMaxTransfer();
			}else{
				return 0;
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
	
	@Override
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
}