package thaumcraftextras.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEssentiaBarrelWindow extends TileEntity{
	@Override
	public void updateEntity()
	{
		if(mblock != true){
			if(worldObj.getTileEntity(xCoord, yCoord -1, zCoord) != null && worldObj.getTileEntity(xCoord, yCoord -1, zCoord) instanceof TileEntityEssentiaBarrel){
				mblock = true;
				worldObj.markBlockForUpdate(xCoord, yCoord -1, zCoord);
			}else{
				mblock = false;
			}
		}
	}
	public boolean mblock;
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
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