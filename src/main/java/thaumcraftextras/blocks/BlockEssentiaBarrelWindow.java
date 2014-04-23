package thaumcraftextras.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrelWindow;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCEBlocks;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEssentiaBarrelWindow extends BlockContainer{

	public BlockEssentiaBarrelWindow(Material material, String name) {
		super(material);
		setCreativeTab(TCETabs.tabMain);
		setHardness(1.0F);
		
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + name);
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}

	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
		blockIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "window");
	}	
	
	@Override
	public TileEntity createNewTileEntity(World world, int arg1) {
		return new TileEntityEssentiaBarrelWindow();
	}
	
	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		world.markBlockForUpdate(x, y, z);

		if(world.getBlock(x, y -1, z) != null && world.getBlock(x, y -1, z) == TCEBlocks.window_barrel_essentia)
			world.markBlockForUpdate(x, y -1, z);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) 
	{
		world.removeTileEntity(x, y, z);	
	}
	
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_)
    {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess acces, int x, int y, int z)
    {    	
        return 0;
    }
}