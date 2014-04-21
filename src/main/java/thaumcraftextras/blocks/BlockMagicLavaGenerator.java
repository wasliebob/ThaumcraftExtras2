package thaumcraftextras.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import thaumcraftextras.blocks.tiles.TileEntityLavaGen;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMagicLavaGenerator extends BlockContainer{

	public BlockMagicLavaGenerator(Material material, String blockName) {
		super(material);
		setCreativeTab(TCETabs.tabMain);
		setHardness(1.0F);
		
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + blockName);
		GameRegistry.registerBlock(this, blockName);
	}
	IIcon side;
	IIcon top;
	IIcon bottom;
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
		top = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "block_charger");
		side = ir.registerIcon("thaumcraft:arcaneearbottom");
	}	
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	        float hitY, float hitZ){
    	TileEntity tile = world.getTileEntity(x, y, z);
        ItemStack stack = player.getHeldItem();
        
        if(tile != null){
        	if(tile instanceof TileEntityLavaGen){
        		TileEntityLavaGen gen = (TileEntityLavaGen)tile;
        		if(stack != null && stack.getItem() instanceof IFluidContainerItem){
                	fillContainer(player, world, gen, x, y, z, stack);
                }
        	}
        }
        return true;
	}
	
	public void fillContainer(EntityPlayer player, World world, TileEntityLavaGen gen, int x, int y, int z, ItemStack stack)
	{
		IFluidContainerItem con = (IFluidContainerItem)stack.getItem();

		if(con.getFluid(stack) == null){
			if(gen.tank.getFluidAmount() >= FluidContainerRegistry.BUCKET_VOLUME){
				con.fill(stack, new FluidStack(gen.tank.getFluid(), FluidContainerRegistry.BUCKET_VOLUME), true);
				
				if(!player.capabilities.isCreativeMode){
					if(stack.stackSize > 1)
						stack.stackSize--;
					else if(stack.stackSize == 1)
						player.setCurrentItemOrArmor(0, null);
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int fside, int metadata) {
		if(fside == 0)
			return top;
		if(fside == 1)
			return top;
		else
			return side;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int arg1) {
		return new TileEntityLavaGen();
	}
}