package thaumcraftextras.main.init.addons.wands;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandCap;

public class TCEWandCap extends WandCap{
	
	public TCEWandCap(String tag, float discount, ItemStack item, int craftCost, ResourceLocation res) 
	{
		super(tag, discount, item, craftCost);
		loc = res;
	}
	ResourceLocation loc;
	
	@Override
	public ResourceLocation getTexture() {
		return loc;
	}
}
