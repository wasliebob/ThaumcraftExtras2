package thaumcraftextras.items.foci.normal;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.items.foci.TCEItemFocus;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocusPotionEffect extends TCEItemFocus {

        public FocusPotionEffect(int colorCode, String itemName) {
        	color = colorCode;
        	name = itemName;
        	
        	setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "focus" + "." + itemName.toLowerCase());
        	GameRegistry.registerItem(this, name);
        }
        private static final AspectList aspectNeed = new AspectList().add(Aspect.ORDER, 100);
        String name;
        int color;

        
        @Override
        public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        	ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
        		if (wand.consumeAllVis(itemstack, player, getVisCost(), true, true)) {
        			getEffect(player);
        		}
        		return itemstack;
        }
        
        @Override
        public String getSortingHelper(ItemStack itemstack) {
                return "POTION";
        }

        @Override
        public int getFocusColor() {
                return color;
        }

        @Override
        public AspectList getVisCost() {
                return aspectNeed;
        }
        
        @Override
        public boolean acceptsEnchant(int id) {
        	return false;
        }
        
        @Override
    	@SideOnly(Side.CLIENT)
    	public int getColorFromItemStack(ItemStack stack, int pass)
    	{
    		return color;
    	}
        
        public boolean getEffect(EntityPlayer player)
        {
        	for(int i = 0; i < player.inventory.getSizeInventory(); i++){
        		if(player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() instanceof ItemPotion){
        			ItemPotion potion = (ItemPotion)player.inventory.getStackInSlot(i).getItem();
        			for(PotionEffect p : genEffect(player, potion, i)){
            			player.addPotionEffect(new PotionEffect(p.getPotionID(), p.getDuration()));
        			}
        			return true;
        		}
        	}
        	return false;
        }
        
        public List<PotionEffect> genEffect(EntityPlayer player, ItemPotion potion, int slot){
        	List<?> s = potion.getEffects(player.inventory.getStackInSlot(slot));
        	List<PotionEffect> b = new ArrayList<PotionEffect>();
        	
        	for(Object o : s){
        		PotionEffect e = (PotionEffect)o;
        		b.add(e);
        	}
        	return b;
        }
}