package thaumcraftextras.main.init.intergration;

import thaumcraftextras.main.init.intergration.NEI.recipes.DarkInfuserRecipes;
import codechicken.nei.api.API;
import cpw.mods.fml.common.Loader;

public class NotEnoughItems{
	public static void init()
	{
		if(Loader.isModLoaded("NotEnoughItems")){
			API.registerRecipeHandler(new DarkInfuserRecipes());
			API.registerUsageHandler(new DarkInfuserRecipes());
		}
	}
}
