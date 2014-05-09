package thaumcraftextras.blocks.tiles;

import java.awt.Color;

import net.minecraft.tileentity.TileEntity;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCEBlocks;

public class TileEntityDarkendAltar extends TileEntity{

	@Override
	public void updateEntity()
	{		
		if(!worldObj.isRemote){
			float bX, bY, bZ;
			
			TileEntity des = null;
			if(worldObj.getBlock(xCoord -1, yCoord +3, zCoord -1) == TCEBlocks.darkendMatrix)
				des = worldObj.getTileEntity(xCoord -1, yCoord +3, zCoord -1);
			else if(worldObj.getBlock(xCoord -1, yCoord +3, zCoord +1) == TCEBlocks.darkendMatrix)
				des = worldObj.getTileEntity(xCoord -1, yCoord +3, zCoord +1);
			else if(worldObj.getBlock(xCoord +1, yCoord +3, zCoord -1) == TCEBlocks.darkendMatrix)
				des = worldObj.getTileEntity(xCoord +1, yCoord +3, zCoord -1);
			else if(worldObj.getBlock(xCoord +1, yCoord +3, zCoord +1) == TCEBlocks.darkendMatrix)
				des = worldObj.getTileEntity(xCoord +1, yCoord +3, zCoord +1);
			
			if(des != null){
				bX = des.xCoord + 0.5F;
				bY = des.yCoord + 0.5F;
				bZ = des.zCoord + 0.5F;
					if(des != null && worldObj != null && bX != 0 && bY != 0 && bZ != 0 && xCoord != 0 && yCoord != 0 && zCoord != 0 && canUpdate() == true){
						if(worldObj.isDaytime())
							ThaumcraftExtras.proxy.spawnTrail(worldObj,(double)xCoord + 0.5F, (double)yCoord, (double)zCoord + 0.5F, bX, bY, bZ, Color.red.getRGB());
						else
							ThaumcraftExtras.proxy.spawnTrail(worldObj,(double)xCoord + 0.5F, (double)yCoord, (double)zCoord + 0.5F, bX, bY, bZ, Color.green.getRGB());
				}
			}
		}
	}
}