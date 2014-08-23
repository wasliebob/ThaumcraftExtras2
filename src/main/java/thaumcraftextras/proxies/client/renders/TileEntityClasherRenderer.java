package thaumcraftextras.proxies.client.renders;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.api.core.recipes.ClasherRecipeManager;
import thaumcraftextras.blocks.tiles.TileEntityClasher;
import thaumcraftextras.proxies.client.models.ModelClasher;

public class TileEntityClasherRenderer extends TileEntitySpecialRenderer{
	public TileEntityClasherRenderer(){
		this.col = 255;
	}

	int col;
	ModelClasher model = new ModelClasher();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8) {
			renderBlockModel(x, y, z);
		if(tile != null && tile instanceof TileEntityClasher){
			TileEntityClasher te = (TileEntityClasher)tile;
			renderCircle(te, x, y, z, var8);
			renderFloatingItems(te, x, y, z, var8);
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

		GL11.glScaled(1, 1, 1);
		GL11.glRotatef(180, 1, 0, 0);
		model.render();	
		GL11.glPopMatrix();
	}
	
	public void renderFloatingItems(TileEntityClasher te, double x, double y, double z, float f)
	{
	        if(te.getStackInSlot(0) != null){
				te.yRotationCube += f*te.speedCube;
		        if (te.yRotationCube <= 360) 
		        	te.yRotationCube -= 360;
		        
		        GL11.glPushMatrix();
		        GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		        GL11.glScaled(0.3D, 0.3D, 0.3D);
		        
		        GL11.glRotatef(te.yRotationCube, 0F, 1F, 0F);
		        GL11.glTranslated(x + 1, y + 1, z + 1);
		        drawItem(te, x, y, z, getIngredients(te.getStackInSlot(0), 0));
		        GL11.glPopMatrix();
		        
		        GL11.glPushMatrix();
		        GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		        GL11.glScaled(0.3D, 0.3D, 0.3D);
		        
		        GL11.glRotatef(te.yRotationCube, 0F, -1F, 0F);
		        GL11.glTranslated(x + 1, y + 1, z + 1);
		        drawItem(te, x, y, z, getIngredients(te.getStackInSlot(0), 1));
		        GL11.glPopMatrix();
        }
	}
	
	public ItemStack getIngredients(ItemStack stack, int id){
		Item output = stack.getItem();
		if(output != null && ClasherRecipeManager.ingredients.get(output) != null)
			return new ItemStack(ClasherRecipeManager.ingredients.get(output)[id]);
		return stack;
	}
	
	public void drawItem(TileEntityClasher te, double x, double y, double z, ItemStack stack)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(getLocation(stack));
		RenderBlocks rb = new RenderBlocks();
 		if(stack.getItem() instanceof ItemBlock){
 			rb.renderBlockAsItem(Block.getBlockFromItem(stack.getItem()), stack.getItemDamage(), 1F);
 		}else{
 	 		IIcon icon = stack.getItem().getIcon(stack, stack.getItem().getRenderPasses(stack.getItemDamage()));
 	 		if(icon != null)
 	 			ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 1/16);
 		}
	}
	
	public ResourceLocation getLocation(ItemStack stack){
		if(stack.getItem() instanceof ItemBlock)
			return TextureMap.locationBlocksTexture;
		else
			return TextureMap.locationItemsTexture;
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
		
		te.yRotation += f*te.speed;
        if (te.yRotation <= 360) 
        	te.yRotation -= 360;
        GL11.glRotatef(te.yRotation, 0F, 0F, 1F);

		t.startDrawingQuads();	              
		vertex2(te, t, d1);
		t.draw();
		GL11.glPopMatrix();
	}
	
	public void vertex2(TileEntityClasher te, Tessellator t, double d1)
	{
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