package thaumcraftextras.proxies.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.blocks.tiles.TileEntityAdvancedAltar;
import thaumcraftextras.proxies.client.models.ModelAdvancedAltar;

public class TileEntityAdvancedAltarRenderer extends TileEntitySpecialRenderer{
	ModelAdvancedAltar model = new ModelAdvancedAltar();
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float f) {
		TileEntityAdvancedAltar altar = (TileEntityAdvancedAltar)tile;
		renderBlockModel(x, y, z);

		if(altar.getStackInSlot(0) != null){
			renderItem(altar, x, y, z, f);
		}
	}
	
	public void renderItem(TileEntityAdvancedAltar altar, double x, double y, double z, float f)
	{
		System.out.println("IT GETS HERE");
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
		EntityItem entityitem = null;
 		float ticks = Minecraft.getMinecraft().renderViewEntity.ticksExisted + f;
 		GL11.glPushMatrix();
 		float h = MathHelper.sin(ticks % 32767.0F / 16.0F) * 0.05F;
 		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.15F + h, (float)z + 0.5F);
 		GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
 		
 		if (altar.getStackInSlot(0).getItem() instanceof ItemBlock){
 			GL11.glScalef(2.0F, 2.0F, 2.0F);
 		} else {
 			GL11.glScalef(1.0F, 1.0F, 1.0F);
 		}
 		
 		ItemStack ist = altar.getStackInSlot(0).copy();
 		ist.stackSize = 1;
 		entityitem = new EntityItem(altar.getWorldObj(), 0.0D, 0.0D, 0.0D, ist);
 		entityitem.hoverStart = 0.0F;
 		
 		if (altar.getStackInSlot(0).stackSize == 0) {
 			GL11.glEnable(3042);
 			GL11.glBlendFunc(770, 1);
 			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.85F);
 		}
 		
 		RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
 		if (!Minecraft.isFancyGraphicsEnabled()){
 			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
 			RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
 		}
 		GL11.glDisable(3042);
 		GL11.glPopMatrix();
	}
	
	public void renderBlockModel(double x, double y, double z)
	{
		GL11.glPushMatrix();
		this.bindTexture(new ResourceLocation("minecraft:textures/blocks/obsidian.png"));
		GL11.glTranslatef((float)x + 0.5F, (float)y + 2.3F, (float)z + 0.5F);
		GL11.glScaled(1, 1.5d, 1);
		GL11.glRotatef(180, 1, 0, 0);
		model.render();	
		GL11.glPopMatrix();
	}
}