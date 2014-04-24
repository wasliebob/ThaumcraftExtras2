package thaumcraftextras.proxies.client.renders;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.helpers.RenderingHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderTesseract implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		Tessellator t = Tessellator.instance;
		
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glRotated(180, 0, 1, 0);
		GL11.glPopMatrix();
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return RenderingHelper.render_tesseract;
	}
}