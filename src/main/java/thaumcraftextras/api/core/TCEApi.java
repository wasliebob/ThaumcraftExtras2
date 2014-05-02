package thaumcraftextras.api.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipeManager;
import thaumcraftextras.api.core.recipes.DarkInfuserRecipeManager;
public class TCEApi {
	/**
	 * Entries using null will not be registered!
	 * @param input
	 * Input Item
	 * @param output
	 * Output ItemStack
	 */
	public static void addDarkInfusionRecipe(Item input, ItemStack output) {
		if(input == null || output == null){
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		}else{
			DarkInfuserRecipeManager.addDarkInfusionRecipe(input, output);
		}
	}
	public static DarkInfuserRecipeManager darkInfuser;

	public static void addAdvancedAltarRecipe(Item input, AdvancedAltarRecipe recipe)
	{
		if(input == null || recipe == null)
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		else
			AdvancedAltarRecipeManager.advancedAltar.put(input, recipe);

	}
	public static AdvancedAltarRecipeManager advancedAltar;
}