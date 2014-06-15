package thaumcraftextras.items.scepters.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thaumcraftextras.api.interfaces.IScepterAction;
import wasliecore.helpers.MathHelper;

public class ActionFire implements IScepterAction{
	public void onClick(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side){
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, MathHelper.secondToTick(15)));
	}

	@Override
	public int getCost() {
		return 5;
	}
}