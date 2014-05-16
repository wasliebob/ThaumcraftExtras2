package thaumcraftextras.handlers.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import thaumcraftextras.main.init.TCEItems;
import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBreak {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void dropEvent(BlockEvent.BreakEvent e)
    {
		EntityPlayer player = e.getPlayer();
		ItemStack stack = BaublesApi.getBaubles(player).getStackInSlot(0);
		if(stack != null && stack.getItem() == TCEItems.amulet_ghost){
			e.setCanceled(true);
		}
    }
}