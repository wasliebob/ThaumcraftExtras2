package thaumcraftextras.main.init;

import net.minecraftforge.common.MinecraftForge;
import thaumcraftextras.handlers.events.BlockBreak;
import thaumcraftextras.handlers.events.ExchangeFocusHUD;
import thaumcraftextras.handlers.events.PlayerDamageEntity;
import thaumcraftextras.handlers.events.PlayerItemDropped;
import thaumcraftextras.handlers.events.PlayerRender;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class TCEEvents {

	public static void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new PlayerDamageEntity());
        MinecraftForge.EVENT_BUS.register(new PlayerItemDropped());
        MinecraftForge.EVENT_BUS.register(new BlockBreak());
        MinecraftForge.EVENT_BUS.register(new PlayerRender());

        if(event.getSide() == Side.CLIENT){
        	MinecraftForge.EVENT_BUS.register(new ExchangeFocusHUD());
        }
	}
}