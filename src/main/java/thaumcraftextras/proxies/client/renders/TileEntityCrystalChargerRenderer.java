package thaumcraftextras.proxies.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.api.interfaces.IMagicEnergyContainerItem;
import thaumcraftextras.blocks.tiles.TileEntityMagicCrystalCharger;
import thaumcraftextras.proxies.client.models.ModelMagicCrystalCharger;

public class TileEntityCrystalChargerRenderer extends TileEntitySpecialRenderer{
	ModelMagicCrystalCharger model = new ModelMagicCrystalCharger();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8) {
		renderBlockModel(x, y, z);
		if(tile != null && tile instanceof TileEntityMagicCrystalCharger){
			TileEntityMagicCrystalCharger te = (TileEntityMagicCrystalCharger)tile;
			if(te.getStackInSlot(0) != null && te.getStackInSlot(0).getItem() instanceof IMagicEnergyContainerItem){
				renderItem(te, x, y, z, var8);
			}
		}
	}
	
	public void renderItem(TileEntityMagicCrystalCharger te, double x, double y, double z, float f)
	{
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
		EntityItem entityitem = null;
 		GL11.glPushMatrix();
 		
 		if (te.getStackInSlot(0).getItem() instanceof ItemBlock){
 	 		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.4F, (float)z + 0.5F);
 			GL11.glScalef(1.0F, 1.0F, 1.0F);
 		} else {
 	 		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.3F, (float)z + 0.5F);
 			GL11.glScalef(1.0F, 1.0F, 1.0F);
 		}
 		
 		ItemStack ist = te.getStackInSlot(0);
 		ist.stackSize = 1;
 		entityitem = new EntityItem(te.getWorldObj(), 0.0D, 0.0D, 0.0D, ist);
 		entityitem.hoverStart = 0.0F;
 		
 		/** Ghost Items */
// 		if (altar.getStackInSlot(0).stackSize != 0) {
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
	
	public void renderBlockModel(double x, double y, double z)
	{
		GL11.glPushMatrix();
		this.bindTexture(new ResourceLocation("thaumcraft:textures/blocks/arcane_stone.png"));
		GL11.glTranslatef((float)x + 0.5F, (float)y + 2.2F, (float)z + 0.5F);
		GL11.glScaled(1, 1.5d, 1);
		GL11.glRotatef(180, 1, 0, 0);
		model.render();	
		GL11.glPopMatrix();
	}
}