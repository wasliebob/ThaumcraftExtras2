package thaumcraftextras.blocks.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDarkendAltarSpecial extends TileEntity{

	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote){
			if(Minecraft.getMinecraft().renderViewEntity != null){
				
			}
		}
	}
}