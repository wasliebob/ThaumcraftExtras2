package thaumcraftextras.api.core.recipes;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

public class AdvancedAltarRecipe {
	public AdvancedAltarRecipe(ItemStack input, ItemStack output, Aspect aspect, int amount)
	{
		this.input = input;
		this.aspect = aspect;
		this.amount = amount;
		this.output = output;
	}
	public ItemStack input;
	public Aspect aspect;
	public int amount;
	public ItemStack output;
}