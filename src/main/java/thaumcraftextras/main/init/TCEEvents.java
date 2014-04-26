package thaumcraftextras.main.init;

import net.minecraftforge.common.MinecraftForge;
import thaumcraftextras.handlers.events.PlayerDamageEntity;
import thaumcraftextras.handlers.events.PlayerItemDropped;
import thaumcraftextras.handlers.events.PlayerRenderTick;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class TCEEvents {

	public static void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new PlayerDamageEntity());
        MinecraftForge.EVENT_BUS.register(new PlayerItemDropped());
        
        if(event.getSide() == Side.CLIENT){
        	MinecraftForge.EVENT_BUS.register(new PlayerRenderTick());}
	}
}
