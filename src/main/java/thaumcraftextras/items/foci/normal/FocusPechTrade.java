package thaumcraftextras.items.foci.normal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.items.foci.TCEItemFocus;
import thaumcraftextras.items.foci.projectile.ProjectilePechTrade;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocusPechTrade extends TCEItemFocus {

        public FocusPechTrade(int colorCode, String itemName) {
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
        	ProjectilePechTrade pechTrade = new ProjectilePechTrade(world, player);
        	if(!world.isRemote && wand.consumeAllVis(itemstack, player, getVisCost(), true, true))
        		world.spawnEntityInWorld(pechTrade);
        	return itemstack;
        }
        
        @Override
        public String getSortingHelper(ItemStack itemstack) {
                return "PECH_TRADE";
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
}