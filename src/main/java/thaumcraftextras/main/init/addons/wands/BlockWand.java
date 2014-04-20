package thaumcraftextras.main.init.addons.wands;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraftextras.main.init.TCETabs;
import thaumcraftextras.main.init.addons.TCEWands;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWand extends Block{

	public BlockWand(String blockName) {
		super(Material.iron);
		setHardness(1.0F);
//		setCreativeTab(TCETabs.tabMain);
		setBlockName("DUMMY BLOCK");
		setCreativeTab(TCETabs.tabWands);
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	
    @SuppressWarnings({"unchecked", "rawtypes"})
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
            super.getSubBlocks(item, tab, list);
         
            list.add(getWand(30, TCEWands.rod_iron, TCEWands.cap_darkThaumium));
            list.add(getWand(40, TCEWands.rod_gold, TCEWands.cap_darkThaumium));
            list.add(getWand(65, TCEWands.rod_diamond, TCEWands.cap_darkThaumium));
            list.add(getWand(75, TCEWands.rod_emerald, TCEWands.cap_darkThaumium));
            list.add(getWand(1000, TCEWands.rod_darkSilverwood, TCEWands.cap_darkThaumium));
            list.add(getWand(9999999, TCEWands.rod_ultimate, TCEWands.cap_darkThaumium));

            list.add(getWand(ConfigItems.STAFF_ROD_BLAZE.getCapacity(), ConfigItems.STAFF_ROD_BLAZE, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_BONE.getCapacity(), ConfigItems.STAFF_ROD_BONE, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_GREATWOOD.getCapacity(), ConfigItems.STAFF_ROD_GREATWOOD, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_ICE.getCapacity(), ConfigItems.STAFF_ROD_ICE, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_OBSIDIAN.getCapacity(), ConfigItems.STAFF_ROD_OBSIDIAN, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_PRIMAL.getCapacity(), ConfigItems.STAFF_ROD_PRIMAL, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_QUARTZ.getCapacity(), ConfigItems.STAFF_ROD_QUARTZ, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_REED.getCapacity(), ConfigItems.STAFF_ROD_REED, TCEWands.cap_darkThaumium));
            list.add(getWand(ConfigItems.STAFF_ROD_SILVERWOOD.getCapacity(), ConfigItems.STAFF_ROD_SILVERWOOD, TCEWands.cap_darkThaumium));

            /** Empty */
            list.add(getWand(0, TCEWands.rod_iron, TCEWands.cap_darkThaumium));
            list.add(getWand(0, TCEWands.rod_gold, TCEWands.cap_darkThaumium));
            list.add(getWand(0, TCEWands.rod_diamond, TCEWands.cap_darkThaumium));
            list.add(getWand(0, TCEWands.rod_emerald, TCEWands.cap_darkThaumium));
            list.add(getWand(0, TCEWands.rod_darkSilverwood, TCEWands.cap_darkThaumium));
            list.add(getWand(0, TCEWands.rod_ultimate, TCEWands.cap_darkThaumium));
            

    }
    
    public ItemStack getWand(int amount, WandRod rod, WandCap cap)
    {
        ItemStack wand;
        wand = new ItemStack(ConfigItems.itemWandCasting);
        
        ((ItemWandCasting) wand.getItem()).setRod(wand, rod);
        ((ItemWandCasting) wand.getItem()).setCap(wand, cap);
        int m = amount*100;
        ((ItemWandCasting) wand.getItem()).storeAllVis(wand, new AspectList().add(Aspect.AIR, m).add(Aspect.EARTH, m).add(Aspect.FIRE, m).add(Aspect.WATER, m).add(Aspect.ORDER, m).add(Aspect.ENTROPY, m));
        
        return wand;
    }
    
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if(!player.isSneaking()){
			if(!world.isRemote){
				if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemWandCasting){
					ItemWandCasting wand = (ItemWandCasting)player.getCurrentEquippedItem().getItem();
					ItemStack stack = player.getCurrentEquippedItem();
					for(Aspect asp : Aspect.getCompoundAspects())
						if(wand.getVis(stack, asp) < wand.getMaxVis(stack)){
							wand.addVis(stack, asp, (wand.getMaxVis(stack) - wand.getVis(stack, asp)), true);
						}
				}
			}
		}else{
			return false;
		}
		return true;
	}
}