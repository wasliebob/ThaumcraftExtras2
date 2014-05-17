package thaumcraftextras.proxies.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.blocks.tiles.TileEntityClasher;

public class TileEntityClasherRenderer extends TileEntitySpecialRenderer{
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8) {
		
		if(tile != null && tile instanceof TileEntityClasher){
			TileEntityClasher te = (TileEntityClasher)tile;
			if(te.getStackInSlot(0) != null)
				renderItem(te, x, y, z, var8);
		}
	}
	
	public void renderItem(TileEntityClasher te, double x, double y, double z, float f)
	{
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
		EntityItem entityitem = null;
 		GL11.glPushMatrix();
 		
 		if (te.getStackInSlot(0).getItem() instanceof ItemBlock){
 	 		GL11.glTranslatef((float)x + 0.5F, (float)y + 1F, (float)z + 0.5F);
 			GL11.glScalef(1.0F, 1.0F, 1.0F);
 		} else {
 	 		GL11.glTranslatef((float)x + 0.5F, (float)y + 1F, (float)z + 0.5F);
 			GL11.glScalef(1.0F, 1.0F, 1.0F);
 		}
 		
 		ItemStack ist = te.getStackInSlot(0);
 		ist.stackSize = 1;
 		entityitem = new EntityItem(te.getWorldObj(), 0.0D, 0.0D, 0.0D, ist);
 		entityitem.hoverStart = 0.0F;
 		
// 		/** Ghost Items */
// 		if (te.getStackInSlot(0).stackSize != 0) {
// 			GL11.glEnable(3042);
// 			GL11.glBlendFunc(770, 1);
// 			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.85F);
// 		}
 		
 		RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
 		if (!Minecraft.isFancyGraphicsEnabled()){
 			RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
 		}
 		GL11.glDisable(3042);
 		GL11.glPopMatrix();
	}
}