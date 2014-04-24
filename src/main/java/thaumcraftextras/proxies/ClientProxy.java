package thaumcraftextras.proxies;

import net.minecraft.world.World;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrelWindow;
import thaumcraftextras.handlers.events.TCEKeyHandler;
import thaumcraftextras.proxies.client.renders.RenderTesseract;
import thaumcraftextras.proxies.client.renders.TileEntityEssentiaBarrelRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityEssentiaBarrelWindowRenderer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssentiaBarrel.class, new TileEntityEssentiaBarrelRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssentiaBarrelWindow.class, new TileEntityEssentiaBarrelWindowRenderer());
		RenderingRegistry.registerBlockHandler(new RenderTesseract());
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