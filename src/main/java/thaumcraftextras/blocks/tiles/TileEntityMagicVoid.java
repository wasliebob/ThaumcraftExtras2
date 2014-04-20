package thaumcraftextras.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import thaumcraftextras.api.misc.classes.MagicEnergy;
import thaumcraftextras.api.misc.tiles.MagicEnergyReceiver;

public class TileEntityMagicVoid extends MagicEnergyReceiver{
	
	public TileEntityMagicVoid()
	{
		storage = new MagicEnergy(10000, 20);
	}
	public MagicEnergy storage;
    int energy;
    public static final String ENERGY = "ENERGY_MAGIC";
    
    @Override
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
    		setEnergy(0);
    	}
    }
    
    public boolean hasEnoughEnergy(){
    		return false;
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
}