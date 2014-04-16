package thaumcraftextras.main;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {


	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		addon_wands = config.get(Configuration.CATEGORY_GENERAL, "addon_wands", true).getBoolean(addon_wands);
		
		xpLevel = config.get(Configuration.CATEGORY_GENERAL, "xpLevel", 5).getInt();
	    
		config.save();
	}
	public static int xpLevel;
	public static boolean addon_wands;
}
