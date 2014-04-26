package thaumcraftextras.proxies;

import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrelWindow;
import thaumcraftextras.blocks.tiles.TileEntityMagicBattery;
import thaumcraftextras.proxies.client.renders.TileEntityBatteryRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityEssentiaBarrelRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityEssentiaBarrelWindowRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void render()
    {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssentiaBarrel.class, new TileEntityEssentiaBarrelRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssentiaBarrelWindow.class, new TileEntityEssentiaBarrelWindowRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagicBattery.class, new TileEntityBatteryRenderer());
    }
	
	@Override
    public void registerParticles()
	{	
    }
}