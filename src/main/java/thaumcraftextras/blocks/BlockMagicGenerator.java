package thaumcraftextras.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraftextras.blocks.tiles.TileEntityMagicGenerator;
import thaumcraftextras.helpers.DyeHelper;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMagicGenerator extends BlockContainer{

	public BlockMagicGenerator(Material material, String blockName) {
		super(material);
		
		setCreativeTab(TCETabs.tabMain);
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
        
        if(!world.isRemote && tile != null){
        	if(tile instanceof TileEntityMagicGenerator){
        		TileEntityMagicGenerator magic = (TileEntityMagicGenerator)tile;
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
		return new TileEntityMagicGenerator();
	}
}