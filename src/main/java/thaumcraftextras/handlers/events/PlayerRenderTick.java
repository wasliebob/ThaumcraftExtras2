package thaumcraftextras.handlers.events;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import thaumcraft.api.aspects.Aspect;
import thaumcraftextras.api.misc.tiles.MagicEnergyBase;
import thaumcraftextras.api.misc.tiles.MagicEnergySender;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.helpers.IconHelper;
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
		if(Minecraft.getMinecraft().thePlayer != null && event.type == ElementType.HOTBAR && Minecraft.getMinecraft().currentScreen == null){
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = Minecraft.getMinecraft().thePlayer ;
			ItemStack held = player.getHeldItem();
			if(held != null && held.getItem() instanceof ItemMagicEnergyReader){
				renderIt(event, player, mc);
			}else if (player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem() instanceof ItemEnergyHelmet){
				renderIt(event, player, mc);
			}
		}
	}
	
	public void renderIt(RenderGameOverlayEvent event, EntityPlayer player, Minecraft mc)
	{
		if(mc.renderViewEntity != null){
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
						stringLengthY = height - 100;    
						stringLengthX = (width - font.getStringWidth(s)) / 2;
						font.drawString(s, stringLengthX, stringLengthY, ColorHelper.getColorCodeFromColor(Color.red));
						
						s = "Input/Output: " + magic.getMaxTransfer();
						stringLengthY = height - 90;    
						stringLengthX = (width - font.getStringWidth(s)) / 2;
						font.drawString(s, stringLengthX, stringLengthY, ColorHelper.getColorCodeFromColor(Color.red));
						
						if(magic instanceof ISidedInventory && ((ISidedInventory)magic).getSizeInventory() > 0 && ((ISidedInventory)magic).getStackInSlot(0) != null){
							s = "Content: " + ((ISidedInventory)magic).getStackInSlot(0).getDisplayName();
							stringLengthY = height - 80;
							stringLengthX = (width - font.getStringWidth(s)) / 2;
							font.drawString(s, stringLengthX, stringLengthY, ColorHelper.getColorCodeFromColor(Color.red));
						}
						
						if(magic instanceof MagicEnergySender){
							s = "Color: " + ((MagicEnergySender)magic).getColor();
							stringLengthY = height - 80;
							stringLengthX = (width - font.getStringWidth(s)) / 2;
							font.drawString(s, stringLengthX, stringLengthY, ((MagicEnergySender)magic).getColor());
						}
						
						Block nblock = player.worldObj.getBlock(blockX, blockY, blockZ);
						RenderItem ri = new RenderItem();
						FontRenderer fr = mc.fontRenderer;
						int x = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 40;
						int y = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 30;
						ri.renderItemIntoGUI(fr, mc.renderEngine, new ItemStack(nblock), x, y);
					
						int xX = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 32;
						int yY = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 38;
						
						Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraft:textures/misc/home.png"));
						IconHelper.drawIcon(xX, yY, 32, 32, this.zLevel);
					}
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glPopMatrix();
				}else if(tile instanceof TileEntityEssentiaBarrel){
					TileEntityEssentiaBarrel barrel = (TileEntityEssentiaBarrel)tile;
					ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
					int width = res.getScaledWidth();
					int height = res.getScaledHeight();
					FontRenderer font = mc.fontRenderer;
					int stringLengthX = (width - font.getStringWidth("Energy Stored: 00")) / 2;
					int stringLengthY = height - 70;    
					GL11.glPushMatrix();
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					
					if(barrel != null && barrel.getAspect() != null){
						String s;
						
						s = "Essentia: " + barrel.amount + "/" + (64*barrel.mod);
						stringLengthY = height - 80;
						stringLengthX = (width - font.getStringWidth(s)) / 2;
						font.drawString(s, stringLengthX, stringLengthY, barrel.getAspect().getColor());
						
						int x = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 40;
						int y = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 30;
						
						Minecraft.getMinecraft().getTextureManager().bindTexture(getAspectIcon(barrel.getAspect()));
						IconHelper.drawAspect(x, y, 16, 16, this.zLevel,barrel.getAspect());
						
						int xX = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 32;
						int yY = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 38;
						
						Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraft:textures/misc/home.png"));
						IconHelper.drawIcon(xX, yY, 32, 32, this.zLevel);
					}
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glPopMatrix();
				}
			}
			}
		}
	}
	
	public ResourceLocation getAspectIcon(Aspect asp)
	{
		return asp.getImage();
	}
}