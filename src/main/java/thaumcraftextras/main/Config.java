package thaumcraftextras.main;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {


	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		addon_tools = config.get(Configuration.CATEGORY_GENERAL, "addon_tools", false).getBoolean(addon_tools);
		dark_exchange = config.get(Configuration.CATEGORY_GENERAL, "dark_exchange", true).getBoolean(dark_exchange);

		xpLevel = config.get(Configuration.CATEGORY_GENERAL, "xpLevel", 5).getInt();
	    
		config.save();
	}
	public static int xpLevel;
	public static boolean addon_tools;
	public static boolean dark_exchange;
}
