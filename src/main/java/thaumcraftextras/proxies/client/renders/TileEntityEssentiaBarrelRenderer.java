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
				Tessellator t = Tessellator.instance;
				double d1 = 0.0001D;

				GL11.glPushMatrix();
				GL11.glTranslated(x, y, z);

				t.startDrawingQuads();	              
				vertex(barrel, t, d1);
				t.draw();
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				GL11.glTranslated(x, y, z + 1.0D);
				GL11.glRotated(90D, 0D, 1D, 0D);
				
				t.startDrawingQuads();
				vertex(barrel, t, d1);
				t.draw();
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				GL11.glTranslated(x + 1.0D, y, z + 1.0D);
				GL11.glRotated(180D, 0D, 1D, 0D);
				
				t.startDrawingQuads();
				vertex(barrel, t, d1);
				t.draw();
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				GL11.glTranslated(x + 1.0D, y, z);
				GL11.glRotated(270D, 0D, 1D, 0D);

				t.startDrawingQuads();
				vertex(barrel, t, d1);
				t.draw();
				GL11.glPopMatrix();
			}
		}
	}
	
	public void vertex(TileEntityEssentiaBarrel barrel, Tessellator t, double d1)
	{
//		Color color = new Color(barrel.getAspect().getColor());
		t.setColorOpaque_I(barrel.getAspect().getColor());
//		tessellator.setColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		t.addVertexWithUV(0, 0 + 1.0D, 0 - d1, 1.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 1.0D, 0 - d1, 0.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 0.0D, 0 - d1, 0.0D, 1.0D);
		t.addVertexWithUV(0, 0 + 0.05D, 0 - d1, 1.0D, 1.0D);
	}
	
	public void vertex2(TileEntityEssentiaBarrel barrel, Tessellator t, double d1)
	{
//		Color color = new Color(barrel.getAspect().getColor());
		t.setColorOpaque_I(barrel.getAspect().getColor());
//		tessellator.setColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		t.addVertexWithUV(0, 0 + 1.0D, 0 - d1, 1.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 1.0D, 0 - d1, 0.0D, 0.0D);
		t.addVertexWithUV(0 + 1.0D, 0 + 0.0D, 0 - d1, 0.0D, 1.0D);
		t.addVertexWithUV(0, 0 + 0.05D, 0 - d1, 1.0D, 1.0D);
	}		
	public ResourceLocation getAspectIcon(Aspect asp)
	{
		return asp.getImage();
	}
}