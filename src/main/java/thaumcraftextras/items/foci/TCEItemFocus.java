package thaumcraftextras.items.foci;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;

public abstract class TCEItemFocus extends ItemFocusBasic {

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
                AspectList cost = getVisCost(stack);
                if (cost != null && cost.size() > 0) {
                        list.add(StatCollector.translateToLocal(isVisCostPerTick(stack) ? "item.Focus.cost2" : "item.Focus.cost1"));
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
        public IIcon getOrnament(ItemStack stack) {
                return ornament;
        }
        
        @Override
        public IIcon getFocusDepthLayerIcon(ItemStack stack) {
                return depth;
        }

        @Override
        public WandFocusAnimation getAnimation(ItemStack stack) {
                return WandFocusAnimation.WAVE;
        }

        @Override
        public boolean isVisCostPerTick(ItemStack stack) {
                return false;
        }

        public boolean isUseItem(ItemStack stack) {
                return isVisCostPerTick(stack);
        }
        
        @Override
        public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop) {
                if(isUseItem(stack))
                        player.setItemInUse(stack, Integer.MAX_VALUE);

                return stack;
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
    	public AspectList getVisCost(ItemStack stack)
        {
			return new AspectList().add(Aspect.AIR, 100);
        }
}