package thaumcraftextras.items.foci.normal;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.items.foci.TCEItemFocus;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocusReturn extends TCEItemFocus {

        public FocusReturn(int colorCode, String itemName) {
        	color = colorCode;
        	name = itemName;
        	
        	setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "focus" + "." + itemName.toLowerCase());
        	GameRegistry.registerItem(this, name);
        }
        private static final AspectList aspectNeed = new AspectList().add(Aspect.AIR, 10);

        Map<String, Double> xPos = new HashMap<String, Double>();
        Map<String, Double> yPos = new HashMap<String, Double>();
        Map<String, Double> zPos = new HashMap<String, Double>();
        Map<String, Integer> dim = new HashMap<String, Integer>();
        
        String name;
        int color;

        
        @Override
        public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        	ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
        	if(!world.isRemote && player.isSneaking()){
        		xPos.put(player.getDisplayName(), player.posX);
        		yPos.put(player.getDisplayName(), player.posY);
        		zPos.put(player.getDisplayName(), player.posZ);

        		if(!world.isRemote)
        			player.addChatComponentMessage(new ChatComponentText("Bound return focus to a new location"));
		        world.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "thaumcraft:wand", 0.25F, 1.0F);
        	}else{
        		if (!world.isRemote && wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, true)) {
        			String name = player.getDisplayName();
        			if(xPos.containsKey(name) && yPos.containsKey(name) && zPos.containsKey(name)){
        				player.setPosition(xPos.get(name), yPos.get(name), zPos.get(name));
        		        world.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "thaumcraft:wand", 0.25F, 1.0F);
        			}
        		}
        	}
        	return itemstack;
        }
        
        @Override
        public String getSortingHelper(ItemStack itemstack) {
                return "RETURN";
        }

        @Override
        public int getFocusColor(ItemStack stack) {
                return color;
        }

        @Override
        public AspectList getVisCost(ItemStack stack) {
                return aspectNeed;
        }
        
        @Override
    	@SideOnly(Side.CLIENT)
    	public int getColorFromItemStack(ItemStack stack, int pass)
    	{
    		return color;
    	}

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack arg0,
				int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
}