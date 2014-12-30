package thaumcraftextras.items.foci.normal;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.api.core.TCEApi;
import thaumcraftextras.items.foci.TCEItemFocus;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocusExchange extends TCEItemFocus {

        public FocusExchange(int colorCode, String itemName) {
        	color = colorCode;
        	name = itemName;
        	
        	setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "focus" + "." + itemName.toLowerCase());
        	GameRegistry.registerItem(this, name);
        }
        private static final AspectList aspectNeed = new AspectList().add(Aspect.ORDER, 10);
        String name;
        int color;

        
        @Override
        public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        	ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
    		if(!world.isRemote && mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
        		int x = mop.blockX;
    			int y = mop.blockY;
    			int z = mop.blockZ;
    			Block block = world.getBlock(x, y, z);
    			if(block != null && TCEApi.exchange.containsKey(block) && wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, true)){
    				world.setBlock(x, y, z, TCEApi.exchange.get(block));
    		        world.playSoundEffect(mop.blockX + 0.5D, mop.blockY + 0.5D, mop.blockZ + 0.5D, "thaumcraft:craftstart", 0.25F, 1.0F);
    		        world.markBlockForUpdate(x, y, z);
    			}else if(isMeta(block, world, x, y, z) && wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, true)){
    				exchangeBlockWithMeta(player, block, world, x, y, z);
    		        world.playSoundEffect(mop.blockX + 0.5D, mop.blockY + 0.5D, mop.blockZ + 0.5D, "thaumcraft:craftstart", 0.25F, 1.0F);
    		        world.markBlockForUpdate(x, y, z);
    			}
    		}
    		return itemstack;
        }
        
        public void exchangeBlockWithMeta(EntityPlayer player, Block block, World world, int x, int y, int z){
        	if(!player.isSneaking()){
        		if(world.getBlockMetadata(x, y, z) < TCEApi.exchangeMeta.get(block)){
        			int meta = world.getBlockMetadata(x, y, z) + 1;
        			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        		}else if(world.getBlockMetadata(x, y, z) >= TCEApi.exchangeMeta.get(block)){
        			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        		}
        	}else if(player.isSneaking()){
        		if(!(world.getBlockMetadata(x, y, z) == 0)){
        			int meta = world.getBlockMetadata(x, y, z) - 1;
        			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        		}else if(world.getBlockMetadata(x, y, z) == 0){
        			int meta = TCEApi.exchangeMeta.get(block);
        			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        		}
        	}
        }
        
        public boolean isMeta(Block block, World world, int x, int y, int z)
        {
        	if(block != null && TCEApi.exchangeMeta.containsKey(block)){
        		return true;
        	}
        	return false;
        }
        
        @Override
        public String getSortingHelper(ItemStack itemstack) {
                return "EXCHANGE";
        }

        @Override
        public int getFocusColor(ItemStack arg0) {
                return color;
        }

        @Override
        public AspectList getVisCost(ItemStack arg0) {
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