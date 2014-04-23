package thaumcraftextras.main.init.intergration;

import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.Loader;

public class IntergrationLoader {
	public static void init()
	{
		if(Loader.isModLoaded("NotEnoughItems")){
			NotEnoughItems.init();
				printMessage("Not Enough Items");
		}
	}
	
	public static void printMessage(String mod){
		System.out.println("[" + ThaumcraftExtras.alias + "]" + "You have " + mod + " installed, adding intergration");
	}
}
