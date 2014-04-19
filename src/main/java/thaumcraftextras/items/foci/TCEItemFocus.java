package thaumcraftextras.items.foci;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandFocus;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;

public abstract class TCEItemFocus extends Item implements IWandFocus {

        private IIcon ornament, depth;

        public TCEItemFocus() {
                super();
                setCreativeTab(TCETabs.tabMain);
                maxStackSize = 1;
        }

        boolean hasOrnament() {
                return false;
        }
        
        boolean hasDepth() {
                return false;
        }

    	@Override
    	public void registerIcons(IIconRegister ir) 
    	{
    		itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "focus_base");
    	}

        @Override
        public boolean isItemTool(ItemStack par1ItemStack){
                return true;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
        public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
                AspectList cost = getVisCost();
                if (cost != null && cost.size() > 0) {
                        list.add(StatCollector.translateToLocal(isVisCostPerTick() ? "item.Focus.cost2" : "item.Focus.cost1"));
                        for (Aspect aspect : cost.getAspectsSorted()) {
                                float amount = cost.getAmount(aspect) / 100.0F;
                                list.add(" " + '\u00a7' + aspect.getChatcolor() + aspect.getName() + '\u00a7' + "r x " + amount);
                        }
                }
        }

        @Override
        public int getItemEnchantability() {
                return 5;
        }

        @Override
        public EnumRarity getRarity(ItemStack itemstack) {
                return EnumRarity.rare;
        }

        @Override
        public IIcon getOrnament() {
                return ornament;
        }
        
        @Override
        public IIcon getFocusDepthLayerIcon() {
                return depth;
        }

        @Override
        public WandFocusAnimation getAnimation() {
                return WandFocusAnimation.WAVE;
        }

        @Override
        public boolean isVisCostPerTick() {
                return false;
        }

        public boolean isUseItem() {
                return isVisCostPerTick();
        }
        
        @Override
        public ItemStack onFocusRightClick(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, MovingObjectPosition paramMovingObjectPosition) {
                if(isUseItem())
                        paramEntityPlayer.setItemInUse(paramItemStack, Integer.MAX_VALUE);

                return paramItemStack;
        }

        @Override
        public void onUsingFocusTick(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt) {
        	
        }

        @Override
        public void onPlayerStoppedUsingFocus(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, int paramInt) {
        }

        @Override
        public String getSortingHelper(ItemStack paramItemStack) {
                return "00";
        }

        @Override
        public boolean onFocusBlockStartBreak(ItemStack paramItemStack, int paramInt1, int paramInt2, int paramInt3, EntityPlayer paramEntityPlayer) {
                return false;
        }
        
        @Override
    	public AspectList getVisCost()
        {
			return new AspectList().add(Aspect.AIR, 100);
        }
}