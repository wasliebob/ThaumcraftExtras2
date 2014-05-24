package thaumcraftextras.proxies.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.helpers.RenderingHelper;
import thaumcraftextras.proxies.client.models.ModelClasher;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderClasher implements ISimpleBlockRenderingHandler{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks rb) {
		
		  OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPushMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraft:textures/blocks/wardedstone.png"));
			GL11.glTranslated(1, 2, 1);
			GL11.glScalef(1, 1F, 1);
			GL11.glRotatef(180, 1, 0, 0);
			model.render();	
			GL11.glPopMatrix();
//		GL11.glPushMatrix();
//		GL11.glScalef(1, 0.6f, 1);
//
//		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityClasher(), 0.0D, 0.0D, 0.0D, 0.0F);
//		GL11.glPopMatrix();
	}
	ModelClasher model = new ModelClasher();

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderingHelper.render_clasher;
	}
}