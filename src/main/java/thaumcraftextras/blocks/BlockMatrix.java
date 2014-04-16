package thaumcraftextras.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraftextras.blocks.tiles.TileEntityDarkendAltarSpecial;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMatrix extends BlockContainer {

	public BlockMatrix(String blockName) {
		super(Material.iron);
		setCreativeTab(TCETabs.tabMain);
		name = blockName;
		setHardness(1.0F);
		setLightLevel(1.0F);
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + name.toLowerCase());
		
		GameRegistry.registerBlock(this, name);
	}
	String name;
	
//	@Override
//	public boolean renderAsNormalBlock()
//	{
//		return false;
//	}
//
//
//	@Override
//    public boolean isOpaqueCube()
//    {
//        return false;
//    }
//    

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
    	blockIcon = Blocks.beacon.getBlockTextureFromSide(0);
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
			return new TileEntityDarkendAltarSpecial();
		else
			return null;
	}
}