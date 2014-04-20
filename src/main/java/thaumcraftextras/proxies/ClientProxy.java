package thaumcraftextras.proxies;

import net.minecraft.world.World;
import thaumcraftextras.blocks.tiles.TileEntityMagicWandCharger;
import thaumcraftextras.handlers.events.TCEKeyHandler;
import thaumcraftextras.proxies.client.renders.TileEntityWandChargerRenderer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

	@Override
	public void load()
	{
		render();
		registerParticles();
		registerKeyBindings();
	}
	
	public void render()
    {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagicWandCharger.class, new TileEntityWandChargerRenderer());
    }
	
	@Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }
	
	@Override
    public void registerParticles()
	{
		
    }
	
	@Override
	public void registerKeyBindings()
	{
		FMLCommonHandler.instance().bus().register(new TCEKeyHandler());
	}
}