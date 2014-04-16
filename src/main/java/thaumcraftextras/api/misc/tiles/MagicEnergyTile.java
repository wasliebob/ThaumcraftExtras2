package thaumcraftextras.api.misc.tiles;

import net.minecraft.tileentity.TileEntity;
import thaumcraftextras.api.interfaces.IMagicEnergy;

public class MagicEnergyTile extends TileEntity implements IMagicEnergy{

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
	public boolean canReceive(){
		return false;
	}
}
