package thaumcraftextras.proxies;

import net.minecraft.world.World;

public class CommonProxy {
	
	public void load()
	{
        registerRenderInformation();
        registerParticles();
	}
		
    public void registerRenderInformation(){}
    
    public World getClientWorld()
    {
        return null;
    }
    
    public void registerParticles(){}
}