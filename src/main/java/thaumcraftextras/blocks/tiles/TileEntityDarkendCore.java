package thaumcraftextras.blocks.tiles;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraftextras.api.core.TCEApi;
import thaumcraftextras.main.init.TCEItems;
import wasliecore.helpers.MathHelper;

public class TileEntityDarkendCore extends TileEntity{

	public TileEntityDarkendCore()
	{
		time = MathHelper.secondToTick(10);	
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote){
			double range = 15.0D;
			
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - range, yCoord - range, zCoord - range, xCoord + range, yCoord + range, zCoord + range);
			List<Entity> entities = worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
			
			for(Entity entity : entities){
				double xPos = entity.posX;
				double yPos = entity.posY;
				double zPos = entity.posZ;
			
				float distance = (float) ((xCoord - xPos) * (xCoord - xPos) + (yCoord - yPos) * (yCoord - yPos) + (zCoord - zPos) * (zCoord - zPos));
		
				if(distance < 15.0 && entity instanceof EntityItem){
					EntityItem item = (EntityItem)entity;
					if((item.getEntityItem().getItem() == ConfigItems.itemResource && item.getEntityItem().getItemDamage() == 2))
						doStuff(item, new ItemStack(TCEItems.darkThaumium));
					if((item.getEntityItem().getItem() == Items.coal))
						doStuff(item, new ItemStack(TCEItems.ignisFuel));
					if((item.getEntityItem().getItem() == TCEItems.essenceMagic))
						doStuff(item, new ItemStack(TCEItems.essenceDark));
					if((item.getEntityItem().getItem() == ConfigItems.itemShard))
						doStuff(item, new ItemStack(TCEItems.darkShard));
					if((TCEApi.darkInfusion.containsKey(item.getEntityItem().getItem()))){
						doStuff(item, TCEApi.darkInfusion.get(item.getEntityItem().getItem()));
					}
				}
			}
		}
	}
	public int time;
	
	public void doStuff(EntityItem item, ItemStack output)
	{
		if(time != 0)
			time--;
		
		if(item != null && Minecraft.getMinecraft().renderViewEntity != null){
			Thaumcraft.proxy.sparkle((float) item.posX, (float) item.posY, (float) item.posZ, 1.0F, 0, 1.0F);
		}
		if(time == 0){
			dropItem(worldObj, xCoord, yCoord, zCoord, output);
			item.setDead();
			time = MathHelper.secondToTick(10);
		}
	}
	
    public static void dropItem(World world, int x, int y, int z, ItemStack itemstack)
    {
        float f = 0.7F;
        double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, itemstack);
        world.spawnEntityInWorld(entityitem);
    }
    
	public IEntitySelector getSelector(){
		return new IEntitySelector(){
			@Override
			public boolean isEntityApplicable(Entity entity) {
				return entity instanceof EntityItem;}};}
}