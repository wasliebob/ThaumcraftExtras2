package thaumcraftextras.handlers;

import net.minecraft.item.ItemStack;
import thaumcraftextras.main.init.TCEBlocks;
import thaumcraftextras.main.init.TCEItems;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler{

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel == new ItemStack(TCEItems.ignisFuel))
			return 3200;
		else if(fuel == new ItemStack(TCEBlocks.blockIgnis))
			return 3200*9;
		else
			return 0;
	}

}
