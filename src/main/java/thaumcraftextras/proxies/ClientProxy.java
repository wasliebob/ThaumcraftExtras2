package thaumcraftextras.proxies;

import net.minecraft.world.World;
import thaumcraftextras.blocks.tiles.TileEntityMagicWandCharger;
import thaumcraftextras.proxies.client.renders.TileEntityWandChargerRenderer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void load()
	{
		render();
		registerParticles();
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
}