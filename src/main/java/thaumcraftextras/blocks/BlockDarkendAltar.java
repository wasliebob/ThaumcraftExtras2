package thaumcraftextras.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraftextras.blocks.itemblocks.ItemBlockDarkendAltar;
import thaumcraftextras.blocks.tiles.TileEntityDarkendAltar;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkendAltar extends BlockContainer {

	public BlockDarkendAltar(String blockName) {
		super(Material.iron);
		setCreativeTab(TCETabs.tabMain);
		name = blockName;
		setHardness(1.0F);

		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + name.toLowerCase());
		
		GameRegistry.registerBlock(this, ItemBlockDarkendAltar.class, name);
	}
	String name;
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
    	blockIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "block_altar");
    }
    
//    @Override
//    @SideOnly(Side.CLIENT)
//    public IIcon getBlockTextureFromSide(int side)
//    {
//        return this.getIcon(side, acces.getBlockMetadata(x, y, z));
//    }
    
	

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if(meta == 1)
			return new TileEntityDarkendAltar();
		else
			return null;
	}
	
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
    	for(int i = 0; i < 2; i++)
    		list.add(new ItemStack(item, 1, i));
    }
}