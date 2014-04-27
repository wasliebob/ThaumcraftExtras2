package thaumcraftextras.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraftextras.blocks.itemblocks.ItemBlockWardedGhost;
import thaumcraftextras.blocks.tiles.TileEntityWardedSpecial;
import thaumcraftextras.helpers.WardedBlockHelper;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWardedGhost extends BlockContainer {

	public BlockWardedGhost(String blockName) {
		super(Material.iron);
		setCreativeTab(TCETabs.tabMain);
		name = blockName;
		
		setBlockUnbreakable();
		setResistance(-1);
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + name.toLowerCase());
		WardedBlockHelper.init();
		
		GameRegistry.registerBlock(this, ItemBlockWardedGhost.class, "name");
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
	
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
  
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
        icons = new IIcon[16];
        
        for(int i = 0; i < icons.length; i++)
        	icons[i] = ir.registerIcon(WardedBlockHelper.warded.get(i));
    }
    
//    @Override
//    @SideOnly(Side.CLIENT)
//    public IIcon getBlockTextureFromSide(int side)
//    {
//        return this.getIcon(side, acces.getBlockMetadata(x, y, z));
//    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
    	if(metadata > 0)
    		return icons[metadata];
    	else
    		return icons[0];
    }
    
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
	{
		if(!world.isRemote)
		{
			TileEntityWardedSpecial tile = (TileEntityWardedSpecial)world.getTileEntity(x, y, z);
			EntityPlayer player = ((EntityPlayer)entity);

			if (tile != null)
			{
				tile.setName(player.getDisplayName());
			}
		}
	}
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) 
    {
    	if(!player.isSneaking())
    		return false;
    	
    	if(!world.isRemote && player.isSneaking()) 
    	{
    		TileEntityWardedSpecial tile = (TileEntityWardedSpecial)world.getTileEntity(x, y, z);
    		Block block = world.getBlock(x, y, z);
    		
    		if(tile != null && tile.getName().equals(player.getDisplayName()))
    		{
    			dropBlockAsItem(world, x, y, z, new ItemStack(block, 1, world.getBlockMetadata(x, y, z)));
    			world.setBlock(x, y, z, Blocks.air);
    			world.removeTileEntity(x, y, z);
    		}
    	}
    	return true;
    }
	

    public void dropBlockAsItem(World world, int x, int y, int z, ItemStack itemstack)
    {
        float f = 0.7F;
        double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, itemstack);
        world.spawnEntityInWorld(entityitem);
    }

	@Override
	public TileEntity createNewTileEntity(World world, int arg1) {
		return new TileEntityWardedSpecial();
	}
	
    @SuppressWarnings("rawtypes")
	@Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity entity)
    {
            TileEntityWardedSpecial tile = (TileEntityWardedSpecial) world.getTileEntity(x, y, z);
        	if(!(entity instanceof EntityPlayer && ((EntityPlayer) entity).getDisplayName().equals(tile.getName())))
	                super.addCollisionBoxesToList(world, x , y, z, axis, list, entity);
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
    	for(int i = 0; i < 16; i++)
    		list.add(new ItemStack(item, 1, i));
    }
}