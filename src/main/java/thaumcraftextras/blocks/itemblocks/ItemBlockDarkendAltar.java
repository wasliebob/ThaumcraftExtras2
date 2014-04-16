package thaumcraftextras.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockDarkendAltar extends ItemBlock
{
	public ItemBlockDarkendAltar(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}
       
    public int getMetadata(int meta)
    {
    	return meta;
    }
}