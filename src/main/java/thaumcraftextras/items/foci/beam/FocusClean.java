package thaumcraftextras.items.foci.beam;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraftextras.items.foci.TCEItemFocus;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocusClean extends TCEItemFocus {        
        public FocusClean(int colorCode, String itemName) {
        	color = colorCode;
        	name = itemName;
        	
        	initCleaning();
        	setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "focus" + "." + itemName.toLowerCase());
        	GameRegistry.registerItem(this, name);
        }
        private static final AspectList aspectNeed = new AspectList().add(Aspect.AIR, 5);
        ArrayList<Block> canBeCleaned = new ArrayList<Block>();
        String name;
        int color;
        int time;
        
        public void initCleaning()
        {
        	canBeCleaned.add(Blocks.dirt);
        	canBeCleaned.add(Blocks.stone);
        	canBeCleaned.add(Blocks.cobblestone);
        	canBeCleaned.add(Blocks.grass);
        	canBeCleaned.add(Blocks.sand);
        	canBeCleaned.add(Blocks.gravel);
        	canBeCleaned.add(Blocks.log);
        	canBeCleaned.add(Blocks.snow);
        	canBeCleaned.add(Blocks.snow_layer);
        	canBeCleaned.add(Blocks.ice);
        	canBeCleaned.add(ConfigBlocks.blockFluxGas);
        	canBeCleaned.add(ConfigBlocks.blockFluxGoo);
        	canBeCleaned.add(Blocks.leaves);
        	canBeCleaned.add(Blocks.leaves2);
        }
        
        @Override
        public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int time)
        {
            ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
            MovingObjectPosition mop = BlockUtils.getTargetBlock(player.worldObj, player, false);
            
            	if(mop != null)
            	{
        			int x,y,z;
        			x = mop.blockX;
        			y = mop.blockY;
        			z = mop.blockZ;
        			if(player.worldObj.canMineBlock(player, x, y, z) && player.worldObj.getBlock(x, y, z).canEntityDestroy(player.worldObj, x, y, z, player) && canBeCleaned.contains(player.worldObj.getBlock(x, y, z))){
        				if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), !player.worldObj.isRemote, false)){
            				if(!player.worldObj.isRemote){            
            		            player.worldObj.playAuxSFX(2001, mop.blockX, mop.blockY, mop.blockZ, Block.getIdFromBlock(player.worldObj.getBlock(x, y, z)) + (player.worldObj.getBlockMetadata(x, y, z) << 12));
                	            player.worldObj.setBlock(x, y, z, Blocks.air);
            				}
            	            if(player.worldObj.isRemote)
            	                Thaumcraft.proxy.beamCont(player.worldObj, player, mop.blockX + 0.5, mop.blockY + 0.5, mop.blockZ + 0.5, 2, color, true, 0F, null, 1);

            			}
            		}
            	}
        }

        @Override
        public String getSortingHelper(ItemStack itemstack) {
                return "CLEAN";
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
        public boolean isUseItem(ItemStack stack) {
                return true;
        }

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack arg0,
				int arg1) {
			return null;
		}
}