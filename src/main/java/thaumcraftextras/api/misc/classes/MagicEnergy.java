package thaumcraftextras.api.misc.classes;

public class MagicEnergy {
	
	public MagicEnergy(int maxEnergy, int maxTransfer){
		maxE = maxEnergy;
		maxT = maxTransfer;
		energyStored = 0;
	}
	int maxE;
	int maxT;
	int energyStored;
	
	public void setEnergy(int energy)
	{
		energyStored = energy;
	}
	
	public void addEnergy(int energy)
	{
		energyStored = energyStored + energy;
	}
	
	public int getEnergy()
	{
		return energyStored;
	}
	
	public int getMaxEnergy()
	{
		return maxE;
	}
	
	public int getMaxTransfer()
	{
		return maxT;
	}
	
	public void removeEnergy(int energy) {
		energyStored = energyStored - energy;
	}
}
