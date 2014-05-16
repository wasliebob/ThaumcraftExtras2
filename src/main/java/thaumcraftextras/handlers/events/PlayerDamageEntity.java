package thaumcraftextras.handlers.events;

import baubles.api.BaublesApi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import thaumcraftextras.main.init.TCEItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerDamageEntity {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void attackEvent(LivingAttackEvent e)
    {
		if(e != null && e.source.getEntity() != null && e.source.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)e.source.getEntity();
			if(player.getCurrentArmor(2) != null){
				Item armor = player.getCurrentArmor(2).getItem();
				if(armor == TCEItems.fireChestplate){
					e.entity.setFire(5);
				}
			}
			if(BaublesApi.getBaubles(player).getStackInSlot(0) != null && BaublesApi.getBaubles(player).getStackInSlot(0).getItem() == TCEItems.amulet_ghost){
				e.setCanceled(true);
			}
		}
    }
}