package thaumcraftextras.items.foci.normal.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.projectile.EntityPrimalOrb;

public class ProjectilePechTrade extends EntityPrimalOrb{

	public ProjectilePechTrade(World world) {
		super(world);
	}
	
	public ProjectilePechTrade(World world, EntityLivingBase living) {
		super(world, living);
	}
	
	@Override
	public void onUpdate(){
		if (this.worldObj.isRemote){
			for (int a = 0; a < 3; a++){
				Thaumcraft.proxy.wispFX2(this.worldObj, this.posX + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posY + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posZ + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, 0.3F, 3, true, 0.02F);
				double x2 = (this.posX + this.prevPosX) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
				double y2 = (this.posY + this.prevPosY) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
				double z2 = (this.posZ + this.prevPosZ) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
				Thaumcraft.proxy.wispFX2(this.worldObj, x2, y2, z2, 0.3F, 2, true, 0.02F);
				Thaumcraft.proxy.sparkle((float)this.posX + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.1F, (float)this.posY + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.1F, (float)this.posZ + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.1F, 5);
			}
		}
		super.onUpdate();
	}

	@Override
	public void onImpact(MovingObjectPosition mop){
	    if(this.worldObj.isRemote){
	    	for (int a = 0; a < 6; a++) {
	    		for (int b = 0; b < 6; b++){
	    			float fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
	    			float fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
	    			float fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
	    			Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 10.0F, this.posY + fy * 10.0F, this.posZ + fz * 10.0F, 0.4F, b, true, 0.05F);
	    			this.setDead();
	    		}
	    	}
	}else if(!this.worldObj.isRemote){
    		if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mop.entityHit instanceof EntityPech){
    			EntityPech pech = (EntityPech)mop.entityHit;
    			pech.setAnger(0);
    			pech.setTamed(true);
    			pech.updateAITasks();
    			this.setDead();
    		}
		}
	}
}