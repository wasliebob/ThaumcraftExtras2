package thaumcraftextras.proxies;

import net.minecraft.world.World;
import thaumcraft.client.fx.bolt.FXLightningBoltCommon;

public class CommonProxy {
	
	public void load()
	{
        registerParticles();
        render();
	}
    public void registerParticles(){}
	public void render() {}
	public void spawnBurst(World worldObj, double xCoord, double yCoord, double zCoord){}
	public void spawnTrail(World worldObj, double xCoord, double yCoord, double zCoord, float mX, float mY, float mZ, int color){}
	public void spawnSound(World worldObj, int xCoord, int yCoord, int zCoord, String id){}
	public void spawnSprinkle(float posX, float posY, float posZ, int color){}
	public void spawnWisp(World worldObj, double x, double y, double z, int size, int type, boolean shrink, int gravity){}
	
	public FXLightningBoltCommon createLightning(World world, double x1, double y1, double z1, double x2, double y2, double z2, long seed, int duration, float multi, int speed){
		return null;
	}
	
	public boolean renderView(){
		return false;
	}
}