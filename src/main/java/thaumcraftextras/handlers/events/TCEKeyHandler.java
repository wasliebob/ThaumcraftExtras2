package thaumcraftextras.handlers.events;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import thaumcraftextras.items.scepters.TCEItemScepter;
import wasliecore.helpers.MathHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCEKeyHandler {
	  public KeyBinding a1 = new KeyBinding("Effect 1", 71, "key.categories.misc");
	  public KeyBinding a2 = new KeyBinding("Effect 2", 72, "key.categories.misc");
	  
	  public TCEKeyHandler()
	  {
	    ClientRegistry.registerKeyBinding(a1);
	    ClientRegistry.registerKeyBinding(a2);
	  }
	  
	  @SideOnly(Side.CLIENT)
	  @SubscribeEvent
	  public void playerTick(TickEvent.PlayerTickEvent event)
	  {
		  if(event.side == Side.SERVER)
			  return;
		  if(event.phase == TickEvent.Phase.START){
			  if(a1.getIsKeyPressed()){
				  if(FMLClientHandler.instance().getClient().inGameHasFocus){
					  EntityPlayer player = event.player;
					  if(player != null && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof TCEItemScepter){
						  action(1, player, player.getCurrentEquippedItem());
					  }
				  }
			  }else if(a2.getIsKeyPressed()){
				  if(FMLClientHandler.instance().getClient().inGameHasFocus){
					  EntityPlayer player = event.player;
					  if(player != null && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof TCEItemScepter){
						  action(2, player, player.getCurrentEquippedItem());
					  }
				  }
			  }
		  }
	  }
	  
	  public void action(int value, EntityPlayer player, ItemStack stack)
	  {
		  if(player.worldObj.isRemote){
			  if(value == 1){
				  if(player.getHealth() + 1.0F <= player.getMaxHealth() && stack.getItemDamage() + 2 <= stack.getMaxDamage()){
					  player.heal(1.0F);
					  stack.setItemDamage(stack.getItemDamage() + 2);
				  }
			  }

			  if(value == 2){
				  if(stack.getItemDamage() + 2 <= stack.getMaxDamage()){
					  player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, MathHelper.minutesToTicks(1)));
					  stack.setItemDamage(stack.getItemDamage() + 2);
				  }
			  }
		  }
	  }
}
