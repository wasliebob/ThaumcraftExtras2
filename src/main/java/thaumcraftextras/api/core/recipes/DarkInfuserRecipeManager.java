package thaumcraftextras.api.core.recipes;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DarkInfuserRecipeManager {
	public static void addDarkInfusionRecipe(Item input, ItemStack output)
	{
		darkInfusion.put(input, output);
	}
	
	public static void removeDarkInfusionRecipe(Item input)
	{
		darkInfusion.remove(input);
	}
	
	public static HashMap<Item, ItemStack> darkInfuserList()
	{
		return darkInfusion;
	}
	
	public static HashMap<Item, ItemStack> darkInfusion = new HashMap<Item, ItemStack>();
}
