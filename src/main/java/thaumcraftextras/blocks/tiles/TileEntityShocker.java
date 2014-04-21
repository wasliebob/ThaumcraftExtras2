package thaumcraftextras.blocks.tiles;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import thaumcraft.client.fx.FXLightningBolt;
import wasliecore.helpers.MathHelper;

public class TileEntityShocker extends TileEntity{

	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity()
	{				
			double range = 8.0D;

			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range, xCoord + range, yCoord + range, zCoord + range);
			List<Entity> entities = worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
			
			for(Entity entity : entities){
				double xPos = entity.posX;
				double yPos = entity.posY;
				double zPos = entity.posZ;
			
				float distance = (float) ((xCoord - xPos) * (xCoord - xPos) + (yCoord - yPos) * (yCoord - yPos) + (zCoord - zPos) * (zCoord - zPos));
		
				if(distance < 8 && entity instanceof EntityLiving && !(entity instanceof EntityPlayer)){
					EntityLiving living = (EntityLiving)entity;
					performAction(living, xPos, yPos, zPos);
				
//					Thaumcraft.proxy.sourceStreamFX(worldObj, (float)xPos, (float)yPos, (float)zPos, (float)xCoord, (float)yCoord, (float)zCoord, 0);
				}
			}
		}
	public int mode = 0;
	public int maxMode = 2;
	public void performAction(EntityLiving living, double xPos, double yPos, double zPos)
	{
		if(mode == 0){
			living.setHealth(living.getHealth() - 0.2F);
			living.performHurtAnimation();

			if(living.getHealth() - 0.2F == 0)
				living.onDeath(DamageSource.generic);
			
			FXLightningBolt light = new FXLightningBolt(worldObj, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, xPos, yPos + 0.5, zPos, worldObj.getSeed(), 1);
			light.defaultFractal();
			light.setType(2);
			light.setWidth(0.125F);
			light.finalizeBolt();
		}else if(mode == 1){
			living.setHealth(living.getHealth() - 0.2F);
			if(living.getHealth() - 0.2F == 0)
				living.onDeath(DamageSource.inFire);
			
				living.setFire(2);
				FXLightningBolt light = new FXLightningBolt(worldObj, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, xPos, yPos + 0.5, zPos, worldObj.getSeed(), 1);
				light.defaultFractal();
				light.setType(3);
				light.setWidth(0.125F);
				light.finalizeBolt();
//				living.onDeath(DamageSource.inFire)
		}else if(mode == 2){
			living.motionY = 2.0F;
			living.hurtResistantTime = MathHelper.secondToTick(10);
		}
	}
	
	public IEntitySelector getSelector(){
		return new IEntitySelector(){
			@Override
			public boolean isEntityApplicable(Entity entity) {
				return entity instanceof EntityLiving;}};}
}