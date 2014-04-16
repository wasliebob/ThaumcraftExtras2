package thaumcraftextras.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraftextras.api.interfaces.IMagicEnergyContainerItem;
import thaumcraftextras.api.misc.tiles.MagicEnergyTile;
import thaumcraftextras.blocks.tiles.TileEntityMagicCrystalCharger;
import thaumcraftextras.items.ItemMagicEnergyReader;
import thaumcraftextras.items.ItemMagicWrench;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMagicCrystalCharger extends BlockContainer{

	public BlockMagicCrystalCharger(Material material, String blockName) {
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
		return new TileEntityMagicCrystalCharger();
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
							player.addChatComponentMessage(new ChatComponentText("Energy Stored: " + tile.getEnergy()));
							world.markBlockForUpdate(x, y, z);
						}else if(player.getCurrentEquippedItem().getItem() instanceof IMagicEnergyContainerItem){
							TileEntityMagicCrystalCharger charger = (TileEntityMagicCrystalCharger)world.getTileEntity(x, y, z);
							charger.setInventorySlotContents(0, player.getCurrentEquippedItem().copy());
							
							if(player.getCurrentEquippedItem().stackSize > 1)
								player.getCurrentEquippedItem().stackSize--;
							else
								player.setCurrentItemOrArmor(0, null);
							
							world.markBlockForUpdate(charger.xCoord, charger.yCoord, charger.zCoord);
						}else{
						}
					}
				}
			}
		}else{
			if(player.isSneaking()){
				if(player.getCurrentEquippedItem() == null){
//					TileEntityMagicWandCharger tile = (TileEntityMagicWandCharger)world.getTileEntity(x, y, z);
					dropItems(world, x, y, z);
				}
			}
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) 
	{
		world.removeTileEntity(x, y, z);	
		dropItems(world, x, y, z);
	}
	
	private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                        if (item.hasTagCompound()) {
                                entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                        item.stackSize = 0;
                }
        }
    }
}