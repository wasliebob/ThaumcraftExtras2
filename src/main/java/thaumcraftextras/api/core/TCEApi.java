package thaumcraftextras.api.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraftextras.api.core.recipes.DarkInfuserRecipeManager;
public class TCEApi {
	public static DarkInfuserRecipeManager darkInfuser;

	public static void addDarkInfusionRecipe(Item input, ItemStack output)
	{
		DarkInfuserRecipeManager.darkInfusion.put(input, output);
	}
}
