package thaumcraftextras.proxies.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.blocks.tiles.TileEntityMagicWandCharger;

public class TileEntityWandChargerRenderer extends TileEntitySpecialRenderer {

//	@Override
//	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) 
//	{     
//		EntityItem item = null;
//		if(tileentity instanceof TileEntityMagicWandCharger){
//			TileEntityMagicWandCharger charger = ((TileEntityMagicWandCharger) tileentity);
//			if(charger.getStackInSlot(0) != null){
//					item = new EntityItem(charger.getWorldObj(), 0.0D, 0.0D, 0.0D, charger.getStackInSlot(0));
//
//					GL11.glPushMatrix();
//					GL11.glEnable(3042);
//					GL11.glTranslated(z + 0.5F, y + 1.5F, z + 0.5F);
//					GL11.glRotatef(180.0F, 0.0F, 0.0F, 0.0F);
//					GL11.glTranslated(-0.5F, -0.5F, -0.5F);
//					
//					RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
//					GL11.glDisable(3042);
//					GL11.glPopMatrix();
//			}
//		}
//	}
//	public static Minecraft mc = Minecraft.getMinecraft();
//	public static EntityPlayer player = mc.thePlayer;
//	public static World theWorld = mc.theWorld;
//	public static ForgeDirection dir;
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
	double d2, float f) {

		TileEntityMagicWandCharger charger = ((TileEntityMagicWandCharger) tileentity);
	         if (charger.getStackInSlot(0) != null) {
	        	 EntityItem entityitem = null;
	        		float ticks = Minecraft.getMinecraft().renderViewEntity.ticksExisted + f;
	        		GL11.glPushMatrix();
	        		float h = MathHelper.sin(ticks % 32767.0F / 16.0F) * 0.05F;
	        			GL11.glTranslatef((float)d0 + 0.5F, (float)d1 + 1.15F + h, (float)d2 + 0.5F);
	        			GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
	        		if (charger.getStackInSlot(0).getItem() instanceof ItemWandCasting){
	        			GL11.glScalef(2.0F, 2.0F, 2.0F);
	        		} else {
	        			GL11.glScalef(1.0F, 1.0F, 1.0F);
	        		}
	        		ItemStack ist = charger.getStackInSlot(0).copy();
	        		ist.stackSize = 1;
	        		entityitem = new EntityItem(charger.getWorldObj(), 0.0D, 0.0D, 0.0D, ist);
	        		entityitem.hoverStart = 0.0F;
	        		if (charger.getStackInSlot(0).stackSize == 0) {
	        			GL11.glEnable(3042);
	        			GL11.glBlendFunc(770, 1);
	        			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.85F);
	        		}
	        		RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
	        		if (!Minecraft.isFancyGraphicsEnabled())
	        		{
	        			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	        			RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
	        		}
	        		GL11.glDisable(3042);
	        		GL11.glPopMatrix();
	         }
	}
}