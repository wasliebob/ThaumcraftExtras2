package thaumcraftextras.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraftextras.api.misc.classes.TileEntityMagicBattery;
import thaumcraftextras.blocks.tiles.TileEntityAdvancedAltar;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrelWindow;
import thaumcraftextras.blocks.tiles.TileEntityMagicCrystalCharger;
import thaumcraftextras.blocks.tiles.TileEntityNoMove;
import thaumcraftextras.blocks.tiles.TileEntityShocker;
import thaumcraftextras.proxies.client.renders.RenderAdvancedAltar;
import thaumcraftextras.proxies.client.renders.RenderCrystalCharger;
import thaumcraftextras.proxies.client.renders.RenderNoMove;
import thaumcraftextras.proxies.client.renders.RenderTesserract;
import thaumcraftextras.proxies.client.renders.TileEntityAdvancedAltarRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityBatteryRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityCrystalChargerRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityEssentiaBarrelRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityEssentiaBarrelWindowRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityNoMoveRenderer;
import thaumcraftextras.proxies.client.renders.TileEntityTesserractRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void render()
    {
		RenderingRegistry.registerBlockHandler(new RenderTesserract());
		RenderingRegistry.registerBlockHandler(new RenderNoMove());
		RenderingRegistry.registerBlockHandler(new RenderCrystalCharger());
		RenderingRegistry.registerBlockHandler(new RenderAdvancedAltar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShocker.class, new TileEntityTesserractRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssentiaBarrel.class, new TileEntityEssentiaBarrelRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssentiaBarrelWindow.class, new TileEntityEssentiaBarrelWindowRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagicBattery.class, new TileEntityBatteryRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoMove.class, new TileEntityNoMoveRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagicCrystalCharger.class, new TileEntityCrystalChargerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdvancedAltar.class, new TileEntityAdvancedAltarRenderer());
    }
	
	@Override
    public void registerParticles(){}
	
	@Override
	public void spawnBurst(World worldObj, double xCoord, double yCoord, double zCoord){
		if(Minecraft.getMinecraft().renderViewEntity != null)
			Thaumcraft.proxy.burst(worldObj, xCoord, yCoord, zCoord, 2);
	}
	
	@Override
	public void spawnTrail(World worldObj, double xCoord, double yCoord, double zCoord, float mX, float mY, float mZ, int color){
		if(Minecraft.getMinecraft().renderViewEntity != null)
			Thaumcraft.proxy.sourceStreamFX(worldObj, xCoord, yCoord, zCoord, mX, mY, mZ, color);
	}
	
	@Override
	public void spawnSprinkle(double x, double y, double z){
		if(Minecraft.getMinecraft().renderViewEntity != null)
			Thaumcraft.proxy.sparkle((float) x, (float) y, (float) z, 1.0F, 0, 1.0F);
	}
	
	@Override
	public void spawnSound(World worldObj, int xCoord, int yCoord, int zCoord, String id){
		worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "thaumcraft:craftstart", 0.25F, 1.0F);
	}
}