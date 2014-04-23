package thaumcraftextras.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraftextras.blocks.itemblocks.ItemBlockDarkendCore;
import thaumcraftextras.blocks.tiles.TileEntityDarkendCore;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCEBlocks;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkendCore extends BlockContainer {

	public BlockDarkendCore(String blockName) {
		super(Material.iron);
		setCreativeTab(TCETabs.tabMain);
		name = blockName;
		setHardness(1.0F);

		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + name.toLowerCase());
		
		GameRegistry.registerBlock(this, ItemBlockDarkendCore.class, name);
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
    	blockIcon = ir.registerIcon("thaumcraft:woodplain");
    }
    
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
	{
		if(!world.isRemote)
		{
			if(hasMultiblock(world, x, y, z)){
				world.setBlock(x +1, y, z +1, TCEBlocks.darkendAltar, 1, 2);
				world.setBlock(x +1, y, z -1, TCEBlocks.darkendAltar, 1, 2);
				world.setBlock(x -1, y, z +1, TCEBlocks.darkendAltar, 1, 2);
				world.setBlock(x -1, y, z -1, TCEBlocks.darkendAltar, 1, 2);
				world.setBlock(x, y + 3, z, TCEBlocks.darkendMatrix, 1, 2);
				
				world.setBlock(x, y, z, this, 1, 2);
			}
		}
	}
	
	public boolean hasMultiblock(World world, int x, int y, int z)
	{
		Block brick = ConfigBlocks.blockCosmeticSolid;
		Block altar = TCEBlocks.darkendAltar;
		if(world.getBlock(x +1, y, z) == brick && world.getBlock(x -1, y, z) == brick &&
				world.getBlock(x, y, z +1) == brick && world.getBlock(x, y, z -1) == brick &&
				world.getBlock(x +1, y, z +1) == altar && world.getBlock(x +1, y, z -1) == altar && world.getBlock(x -1, y, z +1) == altar && world.getBlock(x -1, y, z -1) == altar && world.getBlock(x, y +3, z) == TCEBlocks.darkendMatrix){
			return true;}else{return false;}
	}
	
//    @Override
//    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) 
//    {
//    	if(player.isSneaking())
//    		return false;
//    	
//    	if(!world.isRemote && !player.isSneaking()) 
//    	{
//    		if(world.getBlockMetadata(x, y, z) == 1)
//    			player.addChatComponentMessage(new ChatComponentText("Succesfull"));
//    	}
//    	return true;
//    }
	

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if(meta == 1)
			return new TileEntityDarkendCore();
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
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
		Block brick = ConfigBlocks.blockCosmeticSolid;
		Block altar = TCEBlocks.darkendAltar;
		if(world.getBlockMetadata(x, y, z) != 0){
			if(world.getBlock(x, y, z +1) != brick || world.getBlock(x, y, z -1) != brick || world.getBlock(x +1, y, z) != brick || world.getBlock(x -1, y, z) != brick ||
				world.getBlock(x +1, y, z +1) != altar || world.getBlock(x +1, y, z -1) != altar || world.getBlock(x -1, y, z +1) != altar || world.getBlock(x -1, y, z -1) != altar || world.getBlock(x, y +3, z) != TCEBlocks.darkendMatrix){
				world.setBlock(x +1, y, z +1, TCEBlocks.darkendAltar, 0, 2);
				world.setBlock(x +1, y, z -1, TCEBlocks.darkendAltar, 0, 2);
				world.setBlock(x -1, y, z +1, TCEBlocks.darkendAltar, 0, 2);
				world.setBlock(x -1, y, z -1, TCEBlocks.darkendAltar, 0, 2);
				world.setBlock(x, y + 3, z, TCEBlocks.darkendMatrix, 0, 2);
				world.setBlock(x, y, z, this, 0, 2);
			}
		}
    }
}