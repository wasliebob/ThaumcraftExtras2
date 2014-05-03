package thaumcraftextras.api.core;

public class TCEMagicEnergy {
	/**
	 * @param amountEssentia
	 * How much Essentia
	 * @return
	 */
	public static int convertEssentiaToMCE(int amountEssentia)
	{
		if(amountEssentia > 0)
			return (amountEssentia*essentia);
		else
			System.out.println("[TCE2] " +  " A mod is trying to multiply with zero, returning 1");
		return 1;
	}
	public static int essentia = 10;
	
	/**
	 * NOT YET IMPLEMENTED
	 * @param amountShards
	 * How much XP Shards
	 * @return
	 */
	public static int convertXPShardToMCE(int amountShards)
	{
		if(amountShards > 0)
			return (amountShards*xpShard);
		else
			System.out.println("[TCE2] " +  " A mod is trying to multiply with zero, returning 1");
		return 1;
	}
	public static int xpShard = 10; /** Not yet implemented */
}
