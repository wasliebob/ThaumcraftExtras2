package thaumcraftextras.items.foci.beam;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
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
        public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int time)
        {
        	Minecraft mc = Minecraft.getMinecraft();
            ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
            	if (wand.consumeAllVis(itemstack, player, getVisCost(), !player.worldObj.isRemote, false))
            	{
            		if(mc.renderViewEntity != null){
            		double range = 8.0D;

            			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);
            			List<Entity> entities = player.worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
            			
            			for(Entity entity : entities){
            				double xPos = entity.posX;
            				double yPos = entity.posY;
            				double zPos = entity.posZ;
            			
            				float distance = (float) ((player.posX - xPos) * (player.posX - xPos) + (player.posY - yPos) * (player.posY - yPos) + (player.posZ - zPos) * (player.posZ - zPos));
            		
                			if(distance < 8 && entity instanceof EntityLiving && !(entity instanceof EntityPlayer)){
            					EntityLiving living = (EntityLiving)entity;
            					
                				living.performHurtAnimation();
            					
        						living.motionY = 2.0F;
        						living.hurtResistantTime = MathHelper.secondToTick(10);
            				}
            			}
            		}
            	}
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
        
        @Override
        public boolean isUseItem() {
                return true;
        }
}