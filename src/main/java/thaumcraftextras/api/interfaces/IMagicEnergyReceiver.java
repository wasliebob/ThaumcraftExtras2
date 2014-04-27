package thaumcraftextras.api.interfaces;

public interface IMagicEnergyReceiver {
	/** 
	 * @return energy amount
	 */
	public int getEnergy();
	
	/** 
	 * @returns max energy
	 */
	public int getMaxEnergy();
	
	/**
	 * @param energy
	 * energy to set to
	 */
	public void setEnergy(int energy);
	
	/**
	 * @param energy
	 * energy to add
	 */
	public void increaseEnergy(int energy);
	
	/**
	 * @param energy
	 * energy to remove
	 */
	public void decreaseEnergy(int energy);
	
	/**
	 * @return max input/output
	 */
	public int getMaxTransfer();
	
    /** 
     * @return has enough energy
     */
	public boolean hasEnoughEnergy();
    
    /**
     * @return can receive energy
     */
    public boolean shouldReceive();
}
