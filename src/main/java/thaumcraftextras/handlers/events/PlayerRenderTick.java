package thaumcraftextras.handlers.events;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.api.misc.tiles.MagicEnergyBase;
import thaumcraftextras.items.ItemEnergyHelmet;
import thaumcraftextras.items.ItemMagicEnergyReader;
import wasliecore.helpers.ColorHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerRenderTick extends Gui{
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	  public void playerRender(RenderGameOverlayEvent event){
		if(Minecraft.getMinecraft().thePlayer != null){
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = Minecraft.getMinecraft().thePlayer ;
			if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemMagicEnergyReader){
				renderIt(event, player, mc);
			}else if (player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() instanceof ItemEnergyHelmet){
				renderIt(event, player, mc);
			}
		}
	}
	
	public void renderIt(RenderGameOverlayEvent event, EntityPlayer player, Minecraft mc)
	{
		if(mc.renderViewEntity != null){
//			MovingObjectPosition ray = mc.renderViewEntity.rayTrace(200, 1.0F);
			MovingObjectPosition ray = mc.objectMouseOver;
			if(ray != null){
			int blockX = ray.blockX;
			int blockY = ray.blockY;
			int blockZ = ray.blockZ;
			TileEntity tile = player.worldObj.getTileEntity(blockX, blockY, blockZ);
			if(tile != null){
				if(tile instanceof MagicEnergyBase){
					MagicEnergyBase magic = (MagicEnergyBase)tile;
					ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
					int width = res.getScaledWidth();
					int height = res.getScaledHeight();
					FontRenderer font = mc.fontRenderer;
					int stringLengthX = (width - font.getStringWidth("Energy Stored: 00")) / 2;
					int stringLengthY = height - 70;    
					GL11.glPushMatrix();
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					
					if(magic != null){
						String s;
						s = "Energy Stored: " + magic.getEnergy() + "/" + magic.getMaxEnergy();
						stringLengthY = height - 90;    
						stringLengthX = (width - font.getStringWidth(s)) / 2;
						font.drawString(s, stringLengthX, stringLengthY, ColorHelper.getColorCodeFromColor(Color.red));
						
						s = "Input/Output: " + magic.getMaxTransfer();
						stringLengthY = height - 80;    
						stringLengthX = (width - font.getStringWidth(s)) / 2;
						font.drawString(s, stringLengthX, stringLengthY, ColorHelper.getColorCodeFromColor(Color.red));
					}
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glPopMatrix();
				}
			}
			}
		}
	}
}