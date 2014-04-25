package thaumcraftextras.proxies.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.blocks.tiles.TileEntityMagicBattery;

public class TileEntityBatteryRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8){
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.theWorld;
		
		Block bl = world.getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
		
		if(bl != null && tile != null && tile instanceof TileEntityMagicBattery){
			TileEntityMagicBattery te = (TileEntityMagicBattery)tile;
			if(te != null){
                OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
                GL11.glEnable(GL11.GL_TEXTURE_2D);

				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraftextras:textures/gui/ring.png"));
				Tessellator t = Tessellator.instance;
				double d1 = 0.0001D;

				GL11.glPushMatrix();
				
				GL11.glTranslated(x, y, z);
				GL11.glScaled(x, y, z -1);
//				GL11.glRotated(90, 1, 0, 0);
				
				t.startDrawingQuads();	       
				vertex(te, t, d1);
				t.draw();
				GL11.glPopMatrix();
			}
		}
	}
	
	public void vertex(TileEntityMagicBattery te, Tessellator t, double d1)
	{
//		Color color = new Color(barrel.getAspect().getColor());
		t.setColorOpaque_I(te.getColor());
//		tessellator.setColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		t.addVertexWithUV(0, 0 + 1.0D, 0 - d1, 1.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 1.0D, 0 - d1, 0.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 0.0D, 0 - d1, 0.0D, 1.0D);
		t.addVertexWithUV(0, 0 + 0.05D, 0 - d1, 1.0D, 1.0D);
	}
}