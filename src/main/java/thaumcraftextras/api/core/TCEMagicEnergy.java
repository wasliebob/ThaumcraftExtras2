package thaumcraftextras.api.core;

public class TCEMagicEnergy {
	public static int convertEssentiaToMCE(int amountEssentia)
	{
		return (amountEssentia*essentia);
	}
	public static int essentia = 1;
	
	public static int convertXPShardToMCE(int amountShards)
	{
		return (amountShards*xpShard);
	}
	public static int xpShard = 10; /** Not yet implemented */
}
