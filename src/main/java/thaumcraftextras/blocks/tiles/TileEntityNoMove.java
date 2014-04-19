package thaumcraftextras.blocks.tiles;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.Thaumcraft;

public class TileEntityNoMove extends TileEntity{

	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity()
	{
			double range = 15.0D;
			
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range, xCoord + range, yCoord + range, zCoord + range);
			List<Entity> entities = worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
			
			for(Entity entity : entities){
				double xPos = entity.posX;
				double yPos = entity.posY;
				double zPos = entity.posZ;
			
				float distance = (float) ((xCoord - xPos) * (xCoord - xPos) + (yCoord - yPos) * (yCoord - yPos) + (zCoord - zPos) * (zCoord - zPos));
		
				if(Minecraft.getMinecraft().renderViewEntity != null && distance < 15 && entity instanceof EntityLiving && !(entity instanceof EntityPlayer)){
					EntityLiving living = (EntityLiving)entity;
					if(Minecraft.getMinecraft().renderViewEntity != null)
					{
						living.motionX = 0.0f;
						living.motionY = 0.0f;
						living.motionZ = 0.0f;
						Thaumcraft.proxy.sourceStreamFX(worldObj, (float)xPos, (float)yPos, (float)zPos, (float)xCoord, (float)yCoord, (float)zCoord, 0);
					}
				}
			}
	}
	
	public IEntitySelector getSelector(){
		return new IEntitySelector(){
			@Override
			public boolean isEntityApplicable(Entity entity) {
				return entity instanceof EntityLiving;}};}
}