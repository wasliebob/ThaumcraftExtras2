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
	
	/** 
	 * @param energy
	 * energy to set to
	 */
	public void setEnergy(int energy)
	{
		energyStored = energy;
	}
	
	/**
	 * @param energy
	 * energy to add
	 */
	public void addEnergy(int energy)
	{
		energyStored = energyStored + energy;
	}
	
	/**
	 * @return energy stored
	 */
	public int getEnergy()
	{
		return energyStored;
	}
	
	/**
	 * @return max energy capacity
	 */
	public int getMaxEnergy()
	{
		return maxE;
	}
	
	/**
	 * @return max input/output
	 */
	public int getMaxTransfer()
	{
		return maxT;
	}

	/**
	 * @param energy
	 * energy to remove
	 */
	public void removeEnergy(int energy) {
		energyStored = energyStored - energy;
	}
}