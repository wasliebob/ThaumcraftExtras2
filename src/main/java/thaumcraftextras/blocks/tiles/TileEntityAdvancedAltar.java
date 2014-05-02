package thaumcraftextras.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.Thaumcraft;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipeManager;

public class TileEntityAdvancedAltar extends TileEntity implements ISidedInventory, IAspectContainer, IEssentiaTransport{
    ItemStack[] stacks = new ItemStack[1];
    
    @Override
    public void updateEntity()
    {
    	if(!worldObj.isRemote){
    		if(getStackInSlot(0) != null){    			
    			if(AdvancedAltarRecipeManager.advancedAltar.containsKey(getStackInSlot(0).getItem())){
    				if(drawFromTube() == AdvancedAltarRecipeManager.advancedAltar.get(getStackInSlot(0).getItem()).amount){
    					stacks[0] = AdvancedAltarRecipeManager.advancedAltar.get(getStackInSlot(0).getItem()).output;
    					Thaumcraft.proxy.burst(worldObj, (double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D, 2);
						worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    				}
    			}
    		}
    	}
    }

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < stacks.length; i++) {
                ItemStack stack = stacks[i];
                if (stack != null) {
                        NBTTagCompound tag = new NBTTagCompound();
                        tag.setByte("Slot", (byte) i);
                        stack.writeToNBT(tag);
                        itemList.appendTag(tag);
                }
        }
        nbt.setTag("Inventory", itemList);
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		
		  NBTTagList tagList = nbt.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
          for (int i = 0; i < tagList.tagCount(); i++) {
                  NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
                  byte slot = tag.getByte("Slot");
                  if (slot >= 0 && slot < stacks.length) {
                	  stacks[slot] = ItemStack.loadItemStackFromNBT(tag);
                  }
          }
	}
	
	@Override
	public Packet getDescriptionPacket() {
	    NBTTagCompound tagCompound = new NBTTagCompound();
	    writeToNBT(tagCompound);
	    
	    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, -999, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
		this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public int getSizeInventory() {
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return stacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (stacks[i] != null)
        {

            if (stacks[i].stackSize <= j)
            {
                ItemStack itemstack = stacks[i];
                stacks[i] = null;
                return itemstack;
            }
            else
            {
                ItemStack itemstack1 = stacks[i].splitStack(j);

                if (stacks[i].stackSize == 0)
                {
                	stacks[i] = null;
                }

                return itemstack1;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
	      if (stacks[i] != null)
	        {
	            ItemStack itemstack = stacks[i];
	            stacks[i] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	      }

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		stacks[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		} 
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		  if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
	         {
	                 return false;
	         }

	         return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	     }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public String getInventoryName() {
		return "dm.infuser";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {		
	}
	

	 public int drawFromTube()
	    {
	    	 ForgeDirection orientation = getOrientation();
	         TileEntity tile = ThaumcraftApiHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, orientation);

	         if (tile != null) {
	                 IEssentiaTransport ic = (IEssentiaTransport) tile;

	                 if (!ic.canOutputTo(orientation.getOpposite()))
	                         return 0;

             		Aspect aspect = AdvancedAltarRecipeManager.advancedAltar.get(getStackInSlot(0).getItem()).aspect;
             		int amount = AdvancedAltarRecipeManager.advancedAltar.get(getStackInSlot(0).getItem()).amount;
	                 if(ic.getSuctionType(orientation.getOpposite()) == aspect && ic.getSuctionAmount(orientation.getOpposite()) < getSuctionAmount(orientation) && ic.takeEssentia(aspect, 1, orientation) == 1)
	                 	return amount;
	                 return 0;
	         }
	         return 0;
	    }
	 
	    ForgeDirection getOrientation() 
	    {
	        return ForgeDirection.UP;
	    }
	    

		@Override
	    public AspectList getAspects() {
			if(getStackInSlot(0) != null && AdvancedAltarRecipeManager.advancedAltar.containsKey(getStackInSlot(0).getItem()))
				return new AspectList().add(AdvancedAltarRecipeManager.advancedAltar.get(getStackInSlot(0).getItem()).aspect, AdvancedAltarRecipeManager.advancedAltar.get(getStackInSlot(0).getItem()).amount);
			return null;
		}

	    @Override
	    public void setAspects(AspectList paramAspectList) { }

	    @Override
	    public boolean doesContainerAccept(Aspect paramAspect) 
	    {
	            return false;
	    }

	    @Override
	    public int addToContainer(Aspect paramAspect, int paramInt) {
	            return 0;
	    }

	    @Override
	    public boolean takeFromContainer(Aspect paramAspect, int paramInt) {
	            return false;
	    }

	    @Override
	    public boolean takeFromContainer(AspectList paramAspectList) {
	            return false;
	    }

	    @Override
	    public boolean doesContainerContainAmount(Aspect paramAspect, int paramInt) {
	            return false;
	    }

	    @Override
	    public boolean doesContainerContain(AspectList paramAspectList) {
	            return false;
	    }

	    @Override
	    public int containerContains(Aspect paramAspect) {
	            return 0;
	    }

	    @Override
	    public boolean isConnectable(ForgeDirection paramForgeDirection) {
	            return paramForgeDirection == getOrientation();
	    }

	    @Override
	    public boolean canInputFrom(ForgeDirection paramForgeDirection) {
	            return false;
	    }

	    @Override
	    public boolean canOutputTo(ForgeDirection paramForgeDirection) {
	            return isConnectable(paramForgeDirection);
	    }
	    
	    @Override
	    public int getMinimumSuction() {
	            return 0;
	    }

	    @Override
	    public boolean renderExtendedTube() {
	            return false;
	    }

		@Override
		public int getEssentiaAmount(ForgeDirection arg0) {
			return 0;
		}

		@Override
		public Aspect getEssentiaType(ForgeDirection dir) {
			return null;
		}

		@Override
		public int getSuctionAmount(ForgeDirection arg0) {
			return arg0 == getOrientation() ? 128 : 0;
		}

		@Override
		public Aspect getSuctionType(ForgeDirection arg0) {
			return arg0 == getOrientation() ? AdvancedAltarRecipeManager.advancedAltar.get(getStackInSlot(0).getItem()).aspect : null;
		}

		@Override
		public void setSuction(Aspect arg0, int arg1) {		
		}

		@Override
		public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
			return 0;
		}

		@Override
		public int takeEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
			return 0;
		}
}