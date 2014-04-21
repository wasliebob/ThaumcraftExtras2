package thaumcraftextras.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraftextras.blocks.itemblocks.ItemBlockMagicBattery;
import thaumcraftextras.blocks.tiles.TileEntityMagicBattery;
import thaumcraftextras.helpers.DyeHelper;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMagicBattery extends BlockContainer{

	public BlockMagicBattery(Material material, String tier) {
		super(material);
		setCreativeTab(TCETabs.tabMain);
		setHardness(1.0F);
		
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + tier);
		GameRegistry.registerBlock(this, ItemBlockMagicBattery.class, this.getUnlocalizedName());
	}
	IIcon side;
	IIcon top;
	IIcon bottom;
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
		top = ir.registerIcon("thaumcraft:arcaneearbottom");
		side = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "block_charger");
	}	
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	        float hitY, float hitZ){
    	TileEntity tile = world.getTileEntity(x, y, z);
        
        if(!world.isRemote && tile != null){
        	if(tile instanceof TileEntityMagicBattery){
        		TileEntityMagicBattery magic = (TileEntityMagicBattery)tile;
        		if(player.getHeldItem() != null && player.getHeldItem().getItem() == Items.dye){
        			magic.setColor(DyeHelper.getColorCode(player.getHeldItem().getItemDamage()));
        		}else{
        			player.addChatMessage(new ChatComponentText("Color: " + magic.getColor()));
        		}
        	}
        }
        return true;
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
		return new TileEntityMagicBattery();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) 
	{
		TileEntity tile = world.getTileEntity(x, y, z);    
		if(tile instanceof TileEntityMagicBattery){
			TileEntityMagicBattery magic = (TileEntityMagicBattery)tile;
			ItemStack item = new ItemStack(ItemBlockMagicBattery.getItemFromBlock(this));
			if(!item.hasTagCompound())
				item.setTagCompound(new NBTTagCompound());
			item.stackTagCompound.setInteger("BATTERY_MCE", magic.getEnergy());	
			dropBlockAsItem(world, x, y, z, item);	
			world.removeTileEntity(x, y, z);
	        }
	}
	
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
    {
        return new ArrayList<ItemStack>();
    }
}