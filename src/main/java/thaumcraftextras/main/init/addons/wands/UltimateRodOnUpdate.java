package thaumcraftextras.main.init.addons.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class UltimateRodOnUpdate implements IWandRodOnUpdate {

	@SideOnly(Side.CLIENT)
	@Override
	public void onUpdate(ItemStack stack, EntityPlayer player) {
		if(stack != null && player != null)
		{
			if(stack.getItem() instanceof ItemWandCasting)
			{
				ItemWandCasting wand = (ItemWandCasting)stack.getItem();
				for(Aspect asp : Aspect.getPrimalAspects()){
					if(wand.getVis(stack, asp) < wand.getMaxVis(stack)){
						wand.addVis(stack, asp, (wand.getMaxVis(stack) - wand.getVis(stack, asp)), true);
					}
				}
			}
		}
	}

}
