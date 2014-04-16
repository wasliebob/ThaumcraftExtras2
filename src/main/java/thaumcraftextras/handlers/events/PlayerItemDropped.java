package thaumcraftextras.handlers.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumcraftextras.main.init.TCEItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerItemDropped {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void dropEvent(LivingDropsEvent e)
    {
		if(e.source.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)e.source.getEntity();
			if(player.getEquipmentInSlot(2) != null && player.getEquipmentInSlot(2).getItem() == TCEItems.fireChestplate){
				for(EntityItem item : e.drops){
					ItemStack stack = item.getEntityItem();
					if(FurnaceRecipes.smelting().getSmeltingList().containsKey(stack)){
						e.drops.add(new EntityItem(Minecraft.getMinecraft().theWorld, e.source.getEntity().posX, e.source.getEntity().posY, e.source.getEntity().posZ, FurnaceRecipes.smelting().getSmeltingResult(stack)));
						e.drops.remove(item);
					}
				}
			}
		}
    }
}