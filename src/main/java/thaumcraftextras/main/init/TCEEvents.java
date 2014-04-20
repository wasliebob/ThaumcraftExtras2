package thaumcraftextras.main.init;

import net.minecraftforge.common.MinecraftForge;
import thaumcraftextras.handlers.events.PlayerDamageEntity;
import thaumcraftextras.handlers.events.PlayerItemDropped;
import thaumcraftextras.handlers.events.PlayerRenderTick;

public class TCEEvents {

	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new PlayerDamageEntity());
        MinecraftForge.EVENT_BUS.register(new PlayerItemDropped());
        MinecraftForge.EVENT_BUS.register(new PlayerRenderTick());
	}
}
