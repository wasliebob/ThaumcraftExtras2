package thaumcraftextras.api.core.recipes;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DarkInfuserRecipeManager {
	/**
	 * Entries using null will not be registered!
	 * @param input
	 * Input Item
	 * @param output
	 * Output ItemStack
	 */
	public static void addDarkInfusionRecipe(Item input, ItemStack output)
	{
		if(input == null || output == null)
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		else
			DarkInfuserRecipeManager.darkInfusion.put(input, output);
	}
	
	/**
	 * Entries using null with not be removed!
	 * @param input
	 * Input item (registered key)
	 */
	public static void removeDarkInfusionRecipe(Item input)
	{
		if(input == null)
			System.out.println("[TCE2] " +  " A mod is trying to remove an invalid recipe, ignoring");
		else
			darkInfusion.remove(input);
	}
	
	public static HashMap<Item, ItemStack> darkInfuserList()
	{
		return darkInfusion;
	}
	
	public static HashMap<Item, ItemStack> darkInfusion = new HashMap<Item, ItemStack>();
}