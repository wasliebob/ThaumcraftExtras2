package thaumcraftextras.proxies.client.renders;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import thaumcraft.api.aspects.Aspect;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;

public class TileEntityEssentiaBarrelRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8){
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.theWorld;
		
		Block bl = world.getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
		
		if(bl != null && tile != null && tile instanceof TileEntityEssentiaBarrel){
			TileEntityEssentiaBarrel barrel = (TileEntityEssentiaBarrel)tile;
			
			if(barrel != null && barrel.getAspect() != null){
                OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
                GL11.glEnable(GL11.GL_TEXTURE_2D);

				Minecraft.getMinecraft().getTextureManager().bindTexture(getAspectIcon(barrel.getAspect()));
				Tessellator tessellator = Tessellator.instance;
				
				GL11.glPushMatrix();
				GL11.glTranslated(x, y, z);

				//				GL11.glRotated(90D, 1D, 0D, 0D);
				
				tessellator.startDrawingQuads();
				double d1 = 0.0001D;
				Color color = new Color(barrel.getAspect().getColor());
				tessellator.setColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	              
				/** South */
//				tessellator.addVertexWithUV((double)x, (double)y + 1.0D, (double)z - d1, 0.0D, 0.0D);
//				tessellator.addVertexWithUV((double)x + 1.0D, (double)y + 1.0D, (double)z - d1, 1.0D, 0.0D);
//				tessellator.addVertexWithUV((double)x + 1.0D, (double)y + 0.0D, (double)z - d1, 1.0D, 1.0D);
//				tessellator.addVertexWithUV((double)x, (double)y + 0.05D, (double)z - d1, 0.0D, 1.0D);


//				tessellator.addVertexWithUV(0, 0 + 1.0D, 0 - d1, 0.0D, 0.0D);
//				tessellator.addVertexWithUV(0 + 1.0D, 0 + 1.0D, 0 - d1, 1.0D, 0.0D);
//				tessellator.addVertexWithUV(0 + 1.0D, 0 + 0.0D, 0 - d1, 1.0D, 1.0D);
//				tessellator.addVertexWithUV(0, 0 + 0.05D, 0 - d1, 0.0D, 1.0D);

				tessellator.addVertexWithUV(0, 0 + 1.0D, 0 - d1, 1.0D, 0.0D);
				tessellator.addVertexWithUV(0 + 1.0D, 0 + 1.0D, 0 - d1, 0.0D, 0.0D);
				tessellator.addVertexWithUV(0 + 1.0D, 0 + 0.0D, 0 - d1, 0.0D, 1.0D);
				tessellator.addVertexWithUV(0, 0 + 0.05D, 0 - d1, 1.0D, 1.0D);
				
				tessellator.draw();
				
				GL11.glPopMatrix();

			}
		}
	}
		
	public ResourceLocation getAspectIcon(Aspect asp)
	{
		return asp.getImage();
	}
}