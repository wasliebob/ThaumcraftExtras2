package thaumcraftextras.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import thaumcraft.common.tiles.TilePedestal;
import thaumcraftextras.api.core.recipes.ClasherRecipeManager;
import thaumcraftextras.main.ThaumcraftExtras;

public class TileEntityClasher extends TileEntity implements ISidedInventory{
    public TileEntityClasher(){
    	time = 40;
    }
	ItemStack[] stacks = new ItemStack[1];
    public int time;
    int base = 40;
    
	@Override
	public void updateEntity()
	{		
		if(!worldObj.isRemote){
			if(hasTile()){
				if(getMatrix() == "m1"){
					TilePedestal back = (TilePedestal)worldObj.getTileEntity(xCoord, yCoord, zCoord -2);
					TilePedestal front = (TilePedestal)worldObj.getTileEntity(xCoord, yCoord, zCoord +2);
					if(isValidRecipe(back.getStackInSlot(0), front.getStackInSlot(0))){
						if(time != 0)
							time--;
						
							doFancyStuff(back.getStackInSlot(0).getItem(), front.getStackInSlot(0).getItem(), back, front);
						
						ItemStack stack1 = back.getStackInSlot(0);
						ItemStack stack2 = front.getStackInSlot(0);
						if(time == 0){
							setInventorySlotContents(0, getOutput(stack1, stack2));
							front.setInventorySlotContents(0, null);
							back.setInventorySlotContents(0, null);
							worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
							time = base;
						}
					}
				}else if(getMatrix() == "m2"){
					TilePedestal left = (TilePedestal)worldObj.getTileEntity(xCoord -2, yCoord, zCoord);
					TilePedestal right = (TilePedestal)worldObj.getTileEntity(xCoord +2, yCoord, zCoord);
					if(isValidRecipe(left.getStackInSlot(0), right.getStackInSlot(0))){
						if(time != 0)
							time--;
						
							doFancyStuff(left.getStackInSlot(0).getItem(), right.getStackInSlot(0).getItem(), left, right);
						
						ItemStack stack1 = left.getStackInSlot(0);
						ItemStack stack2 = right.getStackInSlot(0);
						if(time == 0){
							setInventorySlotContents(0, getOutput(stack1, stack2));
							left.setInventorySlotContents(0, null);
							right.setInventorySlotContents(0, null);
							worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
							time = base;
						}
					}
				}
			}
		}
	}
	
	public void doFancyStuff(Item item1, Item item2, TilePedestal t1, TilePedestal t2)
	{
		ThaumcraftExtras.proxy.spawnTrail(worldObj, t1.xCoord + 0.5F, t1.yCoord + 0.7F, t1.zCoord + 0.5F, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, 0);
		ThaumcraftExtras.proxy.spawnTrail(worldObj, t2.xCoord + 0.5F, t2.yCoord + 0.7F, t2.zCoord + 0.5F, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, 0);

		//		Thaumcraft.packetPipeline.sendToAllAround(new PacketFXInfusionSource(this.xCoord, this.yCoord, this.zCoord, (byte)(this.xCoord - t1.xCoord), (byte)(this.yCoord - t1.yCoord), (byte)(this.zCoord - t1.zCoord), 0), new NetworkRegistry.TargetPoint(getWorldObj().provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 32.0D));
	}
	
	public boolean isValidRecipe(ItemStack stack1, ItemStack stack2){
		if(stack1 != null && stack2 != null){
			Item item1 = stack1.getItem();
			Item item2 = stack2.getItem();
	 		if(ClasherRecipeManager.clasher.containsKey(item1) && ClasherRecipeManager.clasher.get(item1) == item2){
 				return true;
			}
		}
		return false;
	}
	
	public ItemStack getOutput(ItemStack stack1, ItemStack stack2){
		Item item1 = stack1.getItem();
		Item item2 = stack2.getItem();
 		if(ClasherRecipeManager.clasher.containsKey(item1) && ClasherRecipeManager.clasher.get(item1) == item2){
 			return ClasherRecipeManager.clasherOut.get(item1);
		}
		return null;
	}
	
	public boolean hasTile(){
		TileEntity back = worldObj.getTileEntity(xCoord, yCoord, zCoord -2);
		TileEntity front = worldObj.getTileEntity(xCoord, yCoord, zCoord +2);
		TileEntity left = worldObj.getTileEntity(xCoord -2, yCoord, zCoord);
		TileEntity right = worldObj.getTileEntity(xCoord +2, yCoord, zCoord);
		boolean m1 = back != null && front != null && back instanceof TilePedestal && front instanceof TilePedestal;
		boolean m2 = left != null && right != null && left instanceof TilePedestal && right instanceof TilePedestal;
		if(m1 || m2)
			return true;

		return false;
	}
	
	public String getMatrix(){
		TileEntity back = worldObj.getTileEntity(xCoord, yCoord, zCoord -2);
		TileEntity front = worldObj.getTileEntity(xCoord, yCoord, zCoord +2);
		TileEntity left = worldObj.getTileEntity(xCoord -2, yCoord, zCoord);
		TileEntity right = worldObj.getTileEntity(xCoord +2, yCoord, zCoord);
		boolean m1 = back != null && front != null && back instanceof TilePedestal && front instanceof TilePedestal;
		boolean m2 = left != null && right != null && left instanceof TilePedestal && right instanceof TilePedestal;
		if(m1)
			return "m1";
		else if(m2)
			return "m2";
		return null;
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
		return "tce.clasher";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
}