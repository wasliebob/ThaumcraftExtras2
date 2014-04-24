package thaumcraftextras.main.init.intergration;

import thaumcraftextras.main.init.intergration.computercraft.PeripheralHandler;
import cpw.mods.fml.common.Loader;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

public class ComputerCraft {
	public static void init()
	{
		if(Loader.isModLoaded("ComputerCraft")){
			IPeripheralProvider handler = new PeripheralHandler();
			ComputerCraftAPI.registerPeripheralProvider(handler);

		}
	}
}
