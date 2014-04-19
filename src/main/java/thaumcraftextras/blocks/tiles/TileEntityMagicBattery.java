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
import thaumcraftextras.api.misc.classes.MagicEnergy;
import thaumcraftextras.api.misc.tiles.MagicEnergyTile;

public class TileEntityMagicBattery extends MagicEnergyTile{
	
	public TileEntityMagicBattery()
	{
		storage = new MagicEnergy(1000, 10, true);
	}
	public MagicEnergy storage;
    public static Map<Aspect, Integer> map = new HashMap<Aspect, Integer>();
    int energy;
    public static final String ENERGY = "ENERGY_MAGIC";
    
    @Override
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
        		TileEntity tile = checkForBlock(worldObj, xCoord, yCoord, zCoord);
    			if(tile != null){
    				if(tile instanceof MagicEnergyTile){
    					MagicEnergyTile to = (MagicEnergyTile)tile;
    					if(to.canReceive()){
    						to.increaseEnergy(calcEnergy(to));
    						worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    						if(Minecraft.getMinecraft().renderViewEntity != null){
    							Thaumcraft.proxy.sourceStreamFX(worldObj,(double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D ,(float)to.xCoord + 0.5F, (float)to.yCoord + 0.5F, (float)to.zCoord + 0.5F, 0);}
    					}
    				}
    			}
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
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void increaseEnergy(int energy) {
		storage.addEnergy(energy);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void decreaseEnergy(int energy) {
		storage.removeEnergy(energy);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public int getMaxTransfer(){
		return storage.getMaxTransfer();
	}
	
	@Override
	public boolean canReceive(){
		return storage.canReceive();
	}

	public int calcEnergy(MagicEnergyTile to)
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
		}else{
			return 0;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger(ENERGY, getEnergy());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		if(nbt.getInteger(ENERGY) != 0)
			setEnergy(nbt.getInteger(ENERGY));
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
			if(world.getTileEntity(x + i, y, z) != null && world.getTileEntity(x + i, y, z) instanceof MagicEnergyTile && ((MagicEnergyTile)world.getTileEntity(x + i, y, z)).canReceive() && !(world.getTileEntity(x + i, y, z) instanceof TileEntityMagicBattery))
				return world.getTileEntity(x + i, y, z);
			
			else if(world.getTileEntity(x - i, y, z) != null && world.getTileEntity(x - i, y, z) instanceof MagicEnergyTile && ((MagicEnergyTile)world.getTileEntity(x - i, y, z)).canReceive() && !(world.getTileEntity(x - i, y, z) instanceof TileEntityMagicBattery))
				return world.getTileEntity(x - i, y, z);
			
			else if(world.getTileEntity(x, y, z - i) != null && world.getTileEntity(x, y, z -i) instanceof MagicEnergyTile && ((MagicEnergyTile)world.getTileEntity(x, y, z - i)).canReceive() && !(world.getTileEntity(x, y, z - i) instanceof TileEntityMagicBattery))
				return world.getTileEntity(x, y, z - i);
			
			else if(world.getTileEntity(x, y, z + i) != null && world.getTileEntity(x, y, z +i) instanceof MagicEnergyTile && ((MagicEnergyTile)world.getTileEntity(x, y, z + i)).canReceive() && !(world.getTileEntity(x, y, z + i) instanceof TileEntityMagicBattery))
				return world.getTileEntity(x, y, z + i);
		}
		
		return null;
	}
}