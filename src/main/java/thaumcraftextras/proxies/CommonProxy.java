package thaumcraftextras.proxies;

import net.minecraft.world.World;

public class CommonProxy {
	
	public void load()
	{
        registerRenderInformation();
        registerParticles();
		registerKeyBindings();
	}
		
    public void registerRenderInformation(){}
    
    public World getClientWorld()
    {
        return null;
    }
    
    public void registerParticles(){}
    
    public void registerKeyBindings(){}
}