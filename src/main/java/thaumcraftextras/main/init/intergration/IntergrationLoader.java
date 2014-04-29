package thaumcraftextras.main.init.intergration;

import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class IntergrationLoader {
	public static void init(FMLPreInitializationEvent event)
	{
		if(Loader.isModLoaded("NotEnoughItems")){
			NotEnoughItems.init(event);
				printMessage("Not Enough Items");}
		
		if(Loader.isModLoaded("ComputerCraft")){
			ComputerCraft.init();
			printMessage("ComputerCraft");}
		
		if(Loader.isModLoaded("ForgeMultipart")){
			ForgeMultipart.init();
			printMessage("Forge Multipart");}
	}
	
	public static void printMessage(String mod){
		System.out.println("[" + ThaumcraftExtras.alias + "]" + "You have " + mod + " installed, adding intergration");
	}
}
