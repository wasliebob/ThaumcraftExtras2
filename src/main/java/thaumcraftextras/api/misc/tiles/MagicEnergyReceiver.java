package thaumcraftextras.api.misc.tiles;

import thaumcraftextras.api.interfaces.IMagicEnergyReceiver;

public class MagicEnergyReceiver extends MagicEnergyBase implements IMagicEnergyReceiver{

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
	
    public boolean hasEnoughEnergy(){
    	return false;
    }
	
	public boolean shouldReceive(){
		return false;
	}
}