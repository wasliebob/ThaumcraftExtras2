package thaumcraftextras.main.init.addons.wands;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
         
            list.add(getWand(30, TCEWands.rod_iron, ConfigItems.WAND_CAP_THAUMIUM));
            list.add(getWand(40, TCEWands.rod_gold, ConfigItems.WAND_CAP_THAUMIUM));
            list.add(getWand(65, TCEWands.rod_diamond, ConfigItems.WAND_CAP_THAUMIUM));
            list.add(getWand(75, TCEWands.rod_emerald, ConfigItems.WAND_CAP_THAUMIUM));
            list.add(getWand(1000, TCEWands.rod_darkSilverwood, TCEWands.cap_darkThaumium));
            list.add(getWand(9999999, TCEWands.rod_ultimate, TCEWands.cap_darkThaumium));

            /** Empty */
            list.add(getWand(0, ConfigItems.WAND_ROD_SILVERWOOD, ConfigItems.WAND_CAP_THAUMIUM));
            list.add(getWand(0, TCEWands.rod_darkSilverwood, TCEWands.cap_darkThaumium));

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
}