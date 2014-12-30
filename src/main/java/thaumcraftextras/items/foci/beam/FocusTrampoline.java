package thaumcraftextras.items.foci.beam;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.items.foci.TCEItemFocus;
import thaumcraftextras.main.ThaumcraftExtras;
import wasliecore.helpers.MathHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocusTrampoline extends TCEItemFocus {        
        public FocusTrampoline(int colorCode, String itemName) {
        	color = colorCode;
        	name = itemName;
        	
        	setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "focus" + "." + itemName.toLowerCase());
        	GameRegistry.registerItem(this, name);
        }
        private static final AspectList aspectNeed = new AspectList().add(Aspect.AIR, 5);
        String name;
        int color;
        
        @SuppressWarnings("unchecked")
		@Override
        public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int time){
            ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
            if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), !player.worldObj.isRemote, false)){
            	if(ThaumcraftExtras.proxy.renderView()){
            		double range = 8.0D*calcDistanceMod(wand, itemstack);

            		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);
            		List<Entity> entities = player.worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
            			
            		for(Entity entity : entities){
            			double xPos = entity.posX;
            			double yPos = entity.posY;
            			double zPos = entity.posZ;
            			
            			float distance = (float) ((player.posX - xPos) * (player.posX - xPos) + (player.posY - yPos) * (player.posY - yPos) + (player.posZ - zPos) * (player.posZ - zPos));
            		
            			if(distance < 8*calcDistanceMod(wand, itemstack) && entity instanceof EntityLiving && !(entity instanceof EntityPlayer)){
            				EntityLiving living = (EntityLiving)entity;
            					
            				if(ThaumcraftExtras.proxy.renderView())
            					living.performHurtAnimation();
            					
            				living.motionY = 2.0F*calcDistanceMod(wand, itemstack);
            				living.hurtResistantTime = MathHelper.secondToTick(10);
        						
            				for (int a = 0; a < 5; a++) {
            					Thaumcraft.proxy.sparkle((float)xPos + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.6F, (float)yPos + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.6F, (float)zPos + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.6F, 2.0F + player.worldObj.rand.nextFloat(), 2, 0.05F + player.worldObj.rand.nextFloat() * 0.05F);
            				}
            			}
            		}
            	}
            }
        }
        
        public int calcDistanceMod(ItemWandCasting wand, ItemStack stack){
        	return 2;
        }
        
        public IEntitySelector getSelector(){
    		return new IEntitySelector(){
    			@Override
    			public boolean isEntityApplicable(Entity entity) {
    				return entity instanceof EntityLiving;}};}


        @Override
        public String getSortingHelper(ItemStack itemstack) {
                return "TRAMPOLINE";
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
        public boolean isUseItem(ItemStack arg0) {
                return true;
        }

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack arg0,
				int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
}