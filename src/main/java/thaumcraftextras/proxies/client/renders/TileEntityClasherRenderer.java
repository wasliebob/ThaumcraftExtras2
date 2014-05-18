package thaumcraftextras.proxies.client.renders;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.blocks.tiles.TileEntityClasher;
import thaumcraftextras.proxies.client.models.ModelClasher;

public class TileEntityClasherRenderer extends TileEntitySpecialRenderer{
	public TileEntityClasherRenderer(){
		this.speed = 1.0F;
		this.yRotation = 0.1F;
		this.col = 255;
	}
	float speed;
	float yRotation;
	int col;
	ModelClasher model = new ModelClasher();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8) {
			renderBlockModel(x, y, z);
		if(tile != null && tile instanceof TileEntityClasher && ((TileEntityClasher)tile).getSizeInventory() > 0){
			TileEntityClasher te = (TileEntityClasher)tile;
			renderCircle(te, x, y, z, var8);
			if(te.getStackInSlot(0) != null){
				renderItem(te, x, y, z, var8);
			}
		}
	}
	
	public void renderBlockModel(double x, double y, double z)
	{
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		this.bindTexture(new ResourceLocation("thaumcraft:textures/blocks/wardedstone.png"));
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);

		GL11.glScaled(1, 1d, 1);
		GL11.glRotatef(180, 1, 0, 0);
		model.render();	
		GL11.glPopMatrix();
	}
	
	public void renderCircle(TileEntityClasher te, double x, double y, double z, float f)
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraftextras:textures/gui/circle.png"));
		double d1 = 0.0001D;
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 1, z + 0.5D);
		GL11.glRotated(90D, 1D, 0D, 0D);
//		GL11.glScaled(1.5D, 1.5D, 1.5D);
		
		yRotation += f*speed;
        if (yRotation <= 360) {
        	yRotation -= 360;
        }
        GL11.glRotatef(yRotation, 0F, 0F, 1F);

		t.startDrawingQuads();	              
		vertex2(te, t, d1);
		t.draw();
		GL11.glPopMatrix();
	}
	
	public void vertex2(TileEntityClasher te, Tessellator t, double d1)
	{
		if(col - 1 >= 0)
			col = col - 1;
		else
			col = 255;
			
		Color c = new Color(col, col, col);
		t.setColorOpaque_I(c.getRGB());
		t.addVertexWithUV(-0.75D, 0.75D, 0, 1, 0);
		t.addVertexWithUV(0.75D, 0.75D, 0, 0, 0);
		t.addVertexWithUV(0.75D, -0.75D, 0, 0, 1);
		t.addVertexWithUV(-0.75D, -0.75D, 0, 1, 1);
		
	}
	
	public void vertex(TileEntityClasher te, Tessellator t, double d1)
	{
		t.setColorOpaque_I(Color.cyan.getRGB());
		t.addVertexWithUV(0, 0 + 1.0D, 0 - d1, 1.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 1.0D, 0 - d1, 0.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 0.0D, 0 - d1, 0.0D, 1.0D);
		t.addVertexWithUV(0, 0 + 0.05D, 0 - d1, 1.0D, 1.0D);
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