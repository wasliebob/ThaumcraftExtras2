package thaumcraftextras.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraftextras.api.misc.tiles.MagicEnergyTile;
import thaumcraftextras.blocks.itemblocks.ItemBlockMagicEnergyBattery;
import thaumcraftextras.blocks.tiles.TileEntityMagicBattery;
import thaumcraftextras.items.ItemMagicEnergyReader;
import thaumcraftextras.items.ItemMagicWrench;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMagicBattery extends BlockContainer{

	public BlockMagicBattery(Material material, String blockName) {
		super(material);
		setCreativeTab(TCETabs.tabMain);
		setHardness(1.0F);
		
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + blockName);
		GameRegistry.registerBlock(this, ItemBlockMagicEnergyBattery.class, this.getUnlocalizedName());
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if(!player.isSneaking()){
			if(!world.isRemote){
				if(player.getCurrentEquippedItem() != null){
					MagicEnergyTile tile = (MagicEnergyTile)world.getTileEntity(x, y, z);
					if(tile != null){
						if(player.getCurrentEquippedItem().getItem() instanceof ItemMagicWrench){
						
						}else if(player.getCurrentEquippedItem().getItem() instanceof ItemMagicEnergyReader){
							world.markBlockForUpdate(x, y, z);
							player.addChatComponentMessage(new ChatComponentText("Energy Stored: " + tile.getEnergy()));
						}else{
						}
					}
				}
			}
		}else{
			if(player.isSneaking()){
				if(player.getCurrentEquippedItem() == null){
//					MagicEnergyTile tile = (MagicEnergyTile)world.getTileEntity(x, y, z);
				}
			}
		}
		return true;
	}
}