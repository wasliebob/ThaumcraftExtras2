package thaumcraftextras.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import thaumcraftextras.blocks.itemblocks.ItemBlockLightning;
import thaumcraftextras.helpers.DyeHelper;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLight extends Block {

	public BlockLight(String blockName) {
		super(Material.iron);
		setCreativeTab(TCETabs.tabMain);
		setHardness(1F);
		setLightLevel(1F);
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + blockName.toLowerCase());
		
		
		GameRegistry.registerBlock(this, ItemBlockLightning.class, "name");
	}
	int metaId;
    
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
	
    @Override
    public int damageDropped(int meta)
    {
    	metaId = meta;
    	return meta;
    }
  
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {        
    	blockIcon = ir.registerIcon("thaumcraftextras:block_light");
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
    	for(int i = 0; i < 15; i++)
    		list.add(new ItemStack(item, 1, i));
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta)
    {
        return DyeHelper.getColorCode(meta);
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess acces, int x, int y, int z)
    {
    	return DyeHelper.getColorCode(acces.getBlockMetadata(x, y, z));
    }
}