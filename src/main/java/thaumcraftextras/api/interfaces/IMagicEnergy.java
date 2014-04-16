package thaumcraftextras.api.interfaces;

public interface IMagicEnergy{
	public int getEnergy();
	public int getMaxEnergy();
	public void setEnergy(int energy);
	public void increaseEnergy(int energy);
	public void decreaseEnergy(int energy);
	public int getMaxTransfer();
	public boolean canReceive();
}
