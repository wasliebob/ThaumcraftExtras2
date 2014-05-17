package thaumcraftextras.blocks.tiles;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraftextras.api.core.recipes.DarkInfuserRecipeManager;
import thaumcraftextras.main.ThaumcraftExtras;
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
					if((DarkInfuserRecipeManager.darkInfusion.containsKey(item.getEntityItem().getItem()))){
						doStuff(item, DarkInfuserRecipeManager.darkInfusion.get(item.getEntityItem().getItem()));
					}else{
					if((item.getEntityItem().getItem() == ConfigItems.itemResource && item.getEntityItem().getItemDamage() == 2))
						doStuff(item, new ItemStack(TCEItems.darkThaumium));
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
		
		if(item != null){
			ThaumcraftExtras.proxy.spawnSprinkle((float)item.posX, (float)item.posY, (float)item.posZ, 0);
		}
		
		if(time == 0){
			dropItem(worldObj, xCoord, yCoord, zCoord, output);
			
			if(item.getEntityItem().stackSize > 1)
				item.getEntityItem().stackSize--;
			else if(item.getEntityItem().stackSize == 1)
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