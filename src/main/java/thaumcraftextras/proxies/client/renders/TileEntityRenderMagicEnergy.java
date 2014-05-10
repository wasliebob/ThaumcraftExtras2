package thaumcraftextras.proxies.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.items.ItemEnergyHelmet;
import thaumcraftextras.items.ItemMagicEnergyReader;

public class TileEntityRenderMagicEnergy extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8) {
		renderOnBlock(tile, x, y, z);
	}
	
	public void renderOnBlock(TileEntity te, double x, double y, double z){
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ItemStack held = player.getHeldItem();
		if((held != null && held.getItem() instanceof ItemMagicEnergyReader) || (player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() instanceof ItemEnergyHelmet)){
			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.4D, y + 1.4D, z + 0.6D);
        
        	GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        
        	GL11.glScalef(-0.03F, -0.03F, -0.03F);
        	String text = "HELLO";
        	int sw = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
        
        	GL11.glDepthMask(false);
        	GL11.glDisable(2896);
        	Minecraft.getMinecraft().fontRenderer.drawString(text, -sw / 2, 0, 16777215);
//    	Minecraft.getMinecraft().fontRenderer.drawString(text, -sw / 2, 0, 16777215);
        	GL11.glEnable(2896);
        	GL11.glDepthMask(true);
        
			GL11.glPopMatrix();
		}
	}
}