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
import net.minecraft.util.DamageSource;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.fx.FXLightningBolt;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.items.foci.TCEItemFocus;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FocusTessela extends TCEItemFocus {        
        public FocusTessela(int colorCode, String itemName) {
        	color = colorCode;
        	name = itemName;
        	
        	setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "focus" + "." + itemName.toLowerCase());
        	GameRegistry.registerItem(this, name);
        }
        private static final AspectList aspectNeed = new AspectList().add(Aspect.AIR, 5);
        String name;
        int color;
        double base = 8.0D;
        
        @SuppressWarnings("unchecked")
		@Override
        public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int time)
        {
        	Minecraft mc = Minecraft.getMinecraft();
            ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
            	if (!player.worldObj.isRemote && wand.consumeAllVis(itemstack, player, getVisCost(), !player.worldObj.isRemote, false))
            	{
            		if(mc.renderViewEntity != null){
            		double range = 10.0D * calcDistanceMod(wand, itemstack);

            			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);
            			List<Entity> entities = player.worldObj.selectEntitiesWithinAABB(Entity.class, bb, getSelector());
            			
            			for(Entity entity : entities){
            				double xPos = entity.posX;
            				double yPos = entity.posY;
            				double zPos = entity.posZ;
            			
            				float distance = (float) ((player.posX - xPos) * (player.posX - xPos) + (player.posY - yPos) * (player.posY - yPos) + (player.posZ - zPos) * (player.posZ - zPos));
            		
                			if(distance < 10 * calcDistanceMod(wand, itemstack) && entity instanceof EntityLiving && !(entity instanceof EntityPlayer)){
            					EntityLiving living = (EntityLiving)entity;
            					living.setHealth(living.getHealth() - 1.0F);
            					living.performHurtAnimation();

            					if(living.getHealth() - 1.0F == 0)
            						living.onDeath(DamageSource.generic);

            					FXLightningBolt light = new FXLightningBolt(player.worldObj, player.posX, player.posY + 1.5F, player.posZ, xPos, yPos + 0.5F, zPos, player.worldObj.rand.nextLong(), 6, 0.5F, 5);
            					light.defaultFractal();
            					light.setType(2);
            					light.setWidth(0.125F);
            					light.finalizeBolt();
            					
            					for (int a = 0; a < 5; a++) {
            						Thaumcraft.proxy.sparkle((float)xPos + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.6F, (float)yPos + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.6F, (float)zPos + (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.6F, 2.0F + player.worldObj.rand.nextFloat(), 2, 0.05F + player.worldObj.rand.nextFloat() * 0.05F);
            	    		        player.worldObj.playSoundEffect(xPos + 0.5D, yPos + 0.5D, zPos + 0.5D, "thaumcraft:jacobs", 0.25F, 1.0F);
            					}
            				}
            			}
            		}
            	}
        }
        
        public int calcDistanceMod(ItemWandCasting wand, ItemStack stack)
        {
        	switch(EnchantmentHelper.getEnchantmentLevel(Config.enchPotency.effectId, wand.getFocusItem(stack)))
        	{
        	case 0: return 1;
        	case 1: return 2;
        	case 2: return 3;
        	case 3: return 4;
        	case 4: return 5;
        	case 5: return 6;
        	default: return 1;
        	}
        }
        
        public IEntitySelector getSelector(){
    		return new IEntitySelector(){
    			@Override
    			public boolean isEntityApplicable(Entity entity) {
    				return entity instanceof EntityLiving;}};}

        @Override
        public String getSortingHelper(ItemStack itemstack) {
                return "TESSELA";
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
        	if(id == Config.enchPotency.effectId)
        		return true;
        	
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