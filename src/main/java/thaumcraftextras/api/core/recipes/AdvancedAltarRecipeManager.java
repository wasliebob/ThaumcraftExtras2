package thaumcraftextras.api.core.recipes;

import java.util.HashMap;

import net.minecraft.item.Item;

public class AdvancedAltarRecipeManager {
	/**
	 * Entries using null will not be registered!
	 * @param input
	 * Input Item
	 * @param output
	 * Output ItemStack
	 */
	public static void addAdvancedAltarRecipe(Item input, AdvancedAltarRecipe recipe)
	{
		if(input == null || recipe == null)
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		else
			AdvancedAltarRecipeManager.advancedAltar.put(input, recipe);

	}
	
	/**
	 * Entries using null with not be removed!
	 * @param input
	 * Input item (registered key)
	 */
	public static void removeAltarRecipe(Item input)
	{
		if(input == null)
			System.out.println("[TCE2] " +  " A mod is trying to remove an invalid recipe, ignoring");
		else
			advancedAltar.remove(input);
	}
	
	public static HashMap<Item, AdvancedAltarRecipe> darkInfuserList()
	{
		return advancedAltar;
	}
	
	public static HashMap<Item, AdvancedAltarRecipe> advancedAltar = new HashMap<Item, AdvancedAltarRecipe>();
}