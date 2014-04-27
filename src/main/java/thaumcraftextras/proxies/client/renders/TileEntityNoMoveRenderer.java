package thaumcraftextras.proxies.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.proxies.client.models.ModelNoMove;

public class TileEntityNoMoveRenderer extends TileEntitySpecialRenderer{
	ModelNoMove model = new ModelNoMove();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8) {
		GL11.glPushMatrix();
		this.bindTexture(new ResourceLocation("thaumcraft:textures/blocks/arcaneearbottom.png"));
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 1, 0, 0);
		model.render();
		GL11.glPopMatrix();
	}
}