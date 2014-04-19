package thaumcraftextras.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraftextras.blocks.itemblocks.ItemBlockEssentiaBarrel;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEssentiaBarrel extends BlockContainer{

	public BlockEssentiaBarrel(Material material, int barrelSize) {
		super(material);
		setCreativeTab(TCETabs.tabMain);
		setHardness(1.0F);
		size = barrelSize*64;
		
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "barrel" + "." + barrelSize);
		GameRegistry.registerBlock(this, ItemBlockEssentiaBarrel.class, this.getUnlocalizedName());
	}
	public int size;
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
		blockIcon = ir.registerIcon("thaumcraft:arcaneearbottom");
	}	

	@Override
	public TileEntity createNewTileEntity(World world, int arg1) {
		return new TileEntityEssentiaBarrel(size);
	}
}