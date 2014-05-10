package thaumcraftextras.main.init.intergration;

import thaumcraftextras.main.init.intergration.NEI.recipes.AltarRecipes;
import thaumcraftextras.main.init.intergration.NEI.recipes.DarkInfuserRecipes;
import codechicken.nei.api.API;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class NotEnoughItems{
	public static void init(FMLPreInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT && Loader.isModLoaded("NotEnoughItems")){
			API.registerRecipeHandler(new DarkInfuserRecipes());
			API.registerUsageHandler(new DarkInfuserRecipes());
			
			API.registerRecipeHandler(new AltarRecipes());
			API.registerUsageHandler(new AltarRecipes());
		}
	}
}
