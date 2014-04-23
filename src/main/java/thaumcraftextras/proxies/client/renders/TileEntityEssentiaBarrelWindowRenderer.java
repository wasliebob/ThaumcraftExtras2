package thaumcraftextras.proxies.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrelWindow;
import thaumcraftextras.main.init.TCEBlocks;

public class TileEntityEssentiaBarrelWindowRenderer extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8){
		Minecraft mc = Minecraft.getMinecraft();
		World world = mc.theWorld;

		Block window = world.getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
		
		if(window != null && tile != null && tile instanceof TileEntityEssentiaBarrelWindow){
			if(world.getTileEntity(tile.xCoord, tile.yCoord -1, tile.zCoord ) != null && world.getTileEntity(tile.xCoord, tile.yCoord -1, tile.zCoord) instanceof TileEntityEssentiaBarrel){
				TileEntityEssentiaBarrel te = (TileEntityEssentiaBarrel)world.getTileEntity(tile.xCoord, tile.yCoord -1, tile.zCoord);
				if(te.getAspect() != null){
	                IIcon icon = TCEBlocks.barrel_essentia.glow;
	                this.field_147501_a.field_147553_e.bindTexture(TextureMap.locationBlocksTexture);

	                OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
	                GL11.glDisable(GL11.GL_TEXTURE_2D);
	                OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	                GL11.glEnable(GL11.GL_TEXTURE_2D);
	                
					Tessellator tessellator = Tessellator.instance;
					GL11.glPushMatrix();
				    GL11.glDisable(2896);
					GL11.glTranslated(x, y, z);
					
					RenderBlocks renderBlocks = new RenderBlocks();
					
					double maxYY = 0.975D; 
					double maxY;
					double am = 64*te.mod;

					if(maxYY / (am/te.amount) <= maxYY)
						maxY = maxYY/(am/te.amount);
					else
						maxY = maxYY;
					
//					if(te.amount == am){
//						maxY = maxYY;
//					}else if(te.amount >= 0 && te.amount < am/8){
//						maxY = maxYY/8;
//					}else if(te.amount >= am/8 && te.amount < am/7){
//						maxY = maxYY/7;
//					}else if(te.amount >= am/7 && te.amount < am/7){
//						maxY = maxYY/6;
//					}else if(te.amount >= am/6 && te.amount < am/5){
//						maxY = maxYY/5;
//					}else if(te.amount >= am/5 && te.amount < am/4){
//						maxY = maxYY/4;
//					}else if(te.amount >= am/4 && te.amount < am/3){
//						maxY = maxYY/3;
//					}else if(te.amount >= am/3 && te.amount < am/2){
//						maxY = maxYY/2;
//					}else if(te.amount >= am/2 && te.amount < am/1){
//						maxY = maxYY/1;
//					}else if(te.amount == 0){
//						maxY = 0.0D;
//					}else{
//						maxY = 0.0D;
//					}
//					
					renderBlocks.setRenderBounds(0.025D, 0.025D, 0.025D, 0.975D, maxY, 0.975D);
					tessellator.startDrawingQuads();
					
					tessellator.setColorOpaque_I(te.getAspect().getColor());
				    int bright = 200;
				    if (te.getWorldObj() != null) {
				      bright = Math.max(200, window.getMixedBrightnessForBlock(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord));
				    }
					tessellator.setBrightness(bright);

					renderBlocks.renderFaceYNeg(window, 0D, 0.0D, 0.0D, icon);
					renderBlocks.renderFaceYPos(window, 0D, 0.0D, 0.0D, icon);
					renderBlocks.renderFaceZNeg(window, 0D, 0.0D, 0.0D, icon);
					renderBlocks.renderFaceZPos(window, 0D, 0.0D, 0.0D, icon);
					renderBlocks.renderFaceXNeg(window, 0D, 0.0D, 0.0D, icon);
					renderBlocks.renderFaceXPos(window, 0D, 0.0D, 0.0D, icon);
					
					
					tessellator.draw();

					GL11.glEnable(2896);
				    GL11.glColor3f(1.0F, 1.0F, 1.0F);
				    GL11.glPopMatrix();
				    
				}
			}
		}
	}
}