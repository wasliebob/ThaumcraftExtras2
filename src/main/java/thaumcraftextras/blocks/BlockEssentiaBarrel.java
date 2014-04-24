package thaumcraftextras.blocks;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemResource;
import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCEItems;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEssentiaBarrel extends BlockContainer{

	public BlockEssentiaBarrel(Material material, String name) {
		super(material);
		setCreativeTab(TCETabs.tabMain);
		setHardness(1.0F);
		
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + name);
		GameRegistry.registerBlock(this, this.getUnlocalizedName());
	}
	public IIcon glow;
    
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
		blockIcon = ir.registerIcon("thaumcraft:arcaneearbottom");
		glow = ir.registerIcon("thaumcraft:animatedglow");
	}	
	
	@Override
	public TileEntity createNewTileEntity(World world, int arg1) {
		return new TileEntityEssentiaBarrel();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	        float hitY, float hitZ){
    	TileEntity tile = world.getTileEntity(x, y, z);
        ItemStack stack = player.getHeldItem();
        
        if(tile != null){
        	if(tile instanceof TileEntityEssentiaBarrel){
                TileEntityEssentiaBarrel barrel = (TileEntityEssentiaBarrel)tile;
                if(stack != null && stack.getItem() == ConfigItems.itemResource && stack.getItemDamage() == 13){
                	placeLabel(barrel, world, player, stack);
                }else if(stack != null && stack.getItem() instanceof IEssentiaContainerItem){
                	addEssentia(barrel, world, player, stack);
                }else if(stack != null && stack.getItem() == TCEItems.essenceMagic){
                	addSpace(barrel, world, player, stack);
                	if(!player.capabilities.isCreativeMode){
                		if(stack.stackSize > 1)
                			stack.stackSize--;
                		else if(stack.stackSize == 1)
                			player.setCurrentItemOrArmor(0, null);
                	}
            	}else if(stack != null && stack.getItem() == TCEItems.scepter && barrel.getAspect() != null){
            		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
            			barrel.setMod(barrel.getMod()*100);
            		}
            		barrel.amount = 64*barrel.getMod();
            	}
        	}
        }
        return true;
	}

	public void addSpace(TileEntityEssentiaBarrel barrel, World world, EntityPlayer player, ItemStack stack){
		if(barrel != null){
			barrel.setMod(barrel.getMod() + 1);
			if(!world.isRemote)
				player.addChatMessage(new ChatComponentText("This barrel can now store " + barrel.getMod() * 64 + " Essentia"));
		}
	}
	
	public void placeLabel(TileEntityEssentiaBarrel barrel, World world, EntityPlayer player, ItemStack stack){
		ItemResource label = (ItemResource)stack.getItem();
		
    	if (barrel.amount == 0 && label.getAspects(stack) != null && barrel.aspectFilter == null)
    	{
    		barrel.aspectFilter = label.getAspects(stack).getAspects()[0];
    		
        	if(!player.capabilities.isCreativeMode){
        		if(stack.stackSize > 1)
        			stack.stackSize--;
        		else if(stack.stackSize == 1)
        			player.setCurrentItemOrArmor(0, null);
        	}
        	onBlockPlacedBy(world, barrel.xCoord, barrel.yCoord, barrel.zCoord, player, (ItemStack)null);
    	}
        else if (barrel.aspectFilter == null && barrel.amount != 0 && barrel.aspect != null) 
        {
        	barrel.aspectFilter = barrel.aspect;
        	if(!player.capabilities.isCreativeMode){
        		if(stack.stackSize > 1)
        			stack.stackSize--;
        		else if(stack.stackSize == 1)
        			player.setCurrentItemOrArmor(0, null);
        	}
        	
            onBlockPlacedBy(world, barrel.xCoord, barrel.yCoord, barrel.zCoord, player, (ItemStack)null);
        }
	}
	
		public void addEssentia(TileEntityEssentiaBarrel barrel, World world, EntityPlayer player, ItemStack stack){
			IEssentiaContainerItem phial = (IEssentiaContainerItem)stack.getItem();
			AspectList aspects = phial.getAspects(stack);
			if(aspects != null){
				if(barrel.aspect == null){
					if(player.capabilities.isCreativeMode){
						barrel.addToContainer(aspects.getAspects()[0], aspects.getAmount(aspects.getAspects()[0]));
					}else{
						barrel.addToContainer(aspects.getAspects()[0], aspects.getAmount(aspects.getAspects()[0]));
						if(stack.stackSize > 1)
							stack.stackSize--;
						else if(stack.stackSize == 1)
		        			player.setCurrentItemOrArmor(0, null);
						player.inventory.addItemStackToInventory(new ItemStack(ConfigItems.itemEssence, 1, 0));
					}
				}
				else if(barrel.aspect != null && barrel.amount > 0){
					if(player.capabilities.isCreativeMode){
						barrel.addToContainer(aspects.getAspects()[0], aspects.getAmount(aspects.getAspects()[0]));
					}else{
						barrel.addToContainer(aspects.getAspects()[0], aspects.getAmount(aspects.getAspects()[0]));
						if(stack.stackSize > 1)
							stack.stackSize--;
						else if(stack.stackSize == 1)
		        			player.setCurrentItemOrArmor(0, null);
						player.inventory.addItemStackToInventory(new ItemStack(ConfigItems.itemEssence, 1, 0));
					}
				}else if(barrel.aspectFilter != null && barrel.aspectFilter == aspects.getAspects()[0]){
					if(player.capabilities.isCreativeMode){
						barrel.addToContainer(aspects.getAspects()[0], aspects.getAmount(aspects.getAspects()[0]));
					}else{
						barrel.addToContainer(aspects.getAspects()[0], aspects.getAmount(aspects.getAspects()[0]));
						if(stack.stackSize > 1)
							stack.stackSize--;
						else if(stack.stackSize == 1)
							player.setCurrentItemOrArmor(0, null);
						player.inventory.addItemStackToInventory(new ItemStack(ConfigItems.itemEssence, 1, 0));
					}
				}
			}
		}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) 
	{
		dropItems(world, x, y, z);
		world.removeTileEntity(x, y, z);	
	}
	
	private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tile = world.getTileEntity(x, y, z);
        
        if(tile instanceof TileEntityEssentiaBarrel){
        	TileEntityEssentiaBarrel barrel = (TileEntityEssentiaBarrel)tile;
        	
        	if(barrel.getMod() > 1){
        		ItemStack item = new ItemStack(TCEItems.essenceMagic, barrel.getMod() -1, 0);
        		float rx = rand.nextFloat() * 0.8F + 0.1F;
        		float ry = rand.nextFloat() * 0.8F + 0.1F;
        		float rz = rand.nextFloat() * 0.8F + 0.1F;

        		EntityItem entityItem = new EntityItem(world,
        				x + rx, y + ry, z + rz,
        				new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
            
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