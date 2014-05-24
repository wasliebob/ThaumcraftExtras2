package thaumcraftextras.api.core.recipes;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ClasherRecipeManager {
	/**
	 * Entries using null will not be registered!
	 * @param input
	 * Input Item
	 * @param right 
	 * @param output
	 * Output ItemStack
	 */
	public static void addClasherRecipe(Item input1, Item input2, ItemStack output)
	{
		if(input1 == null || input2 == null || output == null){
			System.out.println("[TCE2] " +  " A mod is trying to register an invalid recipe, ignoring");
		}else{
			clasher.put(input1, input2);
			clasherOut.put(input1, output);
			ingredients.put(output.getItem(), new Item[]{input1, input2});
		}
	}
	
	/**
	 * Entries using null with not be removed!
	 * @param input
	 * Input item (registered key)
	 */
	public static void removeClasherRecipe(Item input)
	{
		if(input == null)
			System.out.println("[TCE2] " +  " A mod is trying to remove an invalid recipe, ignoring");
		else
			clasher.remove(input);
	}
	public static HashMap<Item, Item> clasher = new HashMap<Item, Item>();
	public static HashMap<Item, ItemStack> clasherOut = new HashMap<Item, ItemStack>();
	public static HashMap<Item, Item[]> ingredients = new HashMap<Item, Item[]>();

}