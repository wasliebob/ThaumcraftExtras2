package thaumcraftextras.api.core.recipes;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

public class AdvancedAltarRecipe {
	public AdvancedAltarRecipe(ItemStack output, Aspect aspect, int amount)
	{
		this.aspect = aspect;
		this.amount = amount;
		this.output = output;
	}
	public Aspect aspect;
	public int amount;
	public ItemStack output;
}