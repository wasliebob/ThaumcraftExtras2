package thaumcraftextras.proxies.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.blocks.tiles.TileEntityAdvancedAltar;
import thaumcraftextras.helpers.RenderingHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderAdvancedAltar implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer) {
		
		GL11.glPushMatrix();
		GL11.glScalef(1, 0.6f, 1);

		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityAdvancedAltar(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glPopMatrix();
	}

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
		return RenderingHelper.render_altar;
	}
}