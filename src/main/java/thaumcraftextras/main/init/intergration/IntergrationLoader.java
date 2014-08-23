package thaumcraftextras.main.init.intergration;

import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class IntergrationLoader {
	public static void init(FMLPreInitializationEvent event)
	{
		if(Loader.isModLoaded("ComputerCraft")){
			ComputerCraft.init();
			printMessage("ComputerCraft");}
	}
	
	public static void printMessage(String mod){
		System.out.println("[" + ThaumcraftExtras.alias + "]" + "You have " + mod + " installed, adding intergration");
	}
}
