package thaumcraftextras.handlers.events;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import thaumcraft.api.wands.IWandFocus;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.api.core.TCEApi;
import thaumcraftextras.helpers.IconHelper;
import thaumcraftextras.items.foci.normal.FocusExchange;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ExchangeFocusHUD extends Gui{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	  public void playerRender(RenderGameOverlayEvent.Post event){
		if(Minecraft.getMinecraft().thePlayer != null && event.type == ElementType.HOTBAR && Minecraft.getMinecraft().currentScreen == null){
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = Minecraft.getMinecraft().thePlayer ;
			ItemStack held = player.getHeldItem();
			if(held != null && held.getItem() instanceof ItemWandCasting && (IWandFocus)((ItemWandCasting)held.getItem()).getFocus(held) != null && (IWandFocus)((ItemWandCasting)held.getItem()).getFocus(held) instanceof FocusExchange && (FocusExchange)((ItemWandCasting)held.getItem()).getFocus(held) instanceof FocusExchange){
				renderExchangeBlock(event, player, mc);
			}
		}
	}

	public void renderExchangeBlock(RenderGameOverlayEvent event, EntityPlayer player, Minecraft mc){
		if(mc.renderViewEntity != null){
			MovingObjectPosition ray = mc.objectMouseOver;
			if(ray != null){
				int blockX = ray.blockX;
				int blockY = ray.blockY;
				int blockZ = ray.blockZ;
				Block block = player.worldObj.getBlock(blockX, blockY, blockZ);
				if(block != null && TCEApi.exchange.containsKey(block)){
					Block nblock = TCEApi.exchange.get(block);
					RenderItem ri = new RenderItem();
					FontRenderer fr = mc.fontRenderer;
					int x = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 40;
					int y = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 30;

					ri.renderItemIntoGUI(fr, mc.renderEngine, new ItemStack(nblock), x, y);
				
					int xX = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 32;
					int yY = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 38;
					
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraft:textures/misc/home.png"));
					IconHelper.drawIcon(xX, yY, 32, 32, this.zLevel);                           
				}else if(block != null && TCEApi.exchangeMeta.containsKey(block)){
					RenderItem ri = new RenderItem();
					FontRenderer fr = mc.fontRenderer;
					int x = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 40;
					int y = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 30;
					int meta = getMeta(player, player.worldObj, blockX, blockY, blockZ, block);
					ri.renderItemIntoGUI(fr, mc.renderEngine, new ItemStack(block, 1, meta), x, y);
					
					int xX = event.resolution.getScaledWidth()/2 + event.resolution.getScaledWidth()/3 + 32;
					int yY = event.resolution.getScaledHeight()/2 - event.resolution.getScaledHeight()/3 - 38;
					
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraft:textures/misc/home.png"));
					IconHelper.drawIcon(xX, yY, 32, 32, this.zLevel);                           
				}
			}
		}
	}
		
	public int getMeta(EntityPlayer player, World world, int x, int y, int z, Block block)
	{
		if(!player.isSneaking()){
			if(world.getBlockMetadata(x, y, z) < TCEApi.exchangeMeta.get(block)){
				int meta = world.getBlockMetadata(x, y, z) + 1;
				return meta;
			}else if(world.getBlockMetadata(x, y, z) >= TCEApi.exchangeMeta.get(block)){
				return 0;
			}
		return 0;
		}else if(player.isSneaking()){
			if(world.getBlockMetadata(x, y, z) != 0){
				int meta = world.getBlockMetadata(x, y, z) - 1;
				return meta;
			}else if(world.getBlockMetadata(x, y, z) == 0){
				int meta = TCEApi.exchangeMeta.get(block);
				return meta;
			}
		}
		return 0;
	}
}