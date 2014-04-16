package thaumcraftextras.main.init.addons.wands.helpers;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

public class WandCreationHelper {
	public static ShapedArcaneRecipe registerRecipe(String name, ItemStack output, ItemStack material, ItemStack core, int tier)
	{
		ShapedArcaneRecipe recipe;
		int cost = 5*tier;
		AspectList aspectlist = new AspectList().add(Aspect.ORDER, cost).add(Aspect.ENTROPY, cost);
		recipe = ThaumcraftApi.addArcaneCraftingRecipe(name, output, aspectlist, new Object[]{
				"  X",
				" I ",
				"X  ",
				'X', material,
				'I', core});
		
		return recipe;
	}
}
