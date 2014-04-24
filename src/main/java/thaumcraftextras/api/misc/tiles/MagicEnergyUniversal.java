package thaumcraftextras.api.misc.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraftextras.api.interfaces.IMagicEnergy;
import thaumcraftextras.api.interfaces.IMagicEnergyReceiver;
import thaumcraftextras.blocks.tiles.TileEntityMagicBattery;

public class MagicEnergyUniversal extends MagicEnergyBase implements IMagicEnergy{
	public boolean isSending = false;
	
	public void setSending(boolean value){
		isSending = value;
	}
	
	@Override
	public int getEnergy() {
		return 0;
	}

	@Override
	public int getMaxEnergy() {
		return 0;
	}

	@Override
	public void setEnergy(int energy) {		
	}

	@Override
	public void increaseEnergy(int energy) {
		
	}

	@Override
	public int getMaxTransfer() {
		return 0;
	}

	@Override
	public void decreaseEnergy(int energy) {		
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