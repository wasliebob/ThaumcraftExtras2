package thaumcraftextras.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockDarkendCore extends ItemBlock
{
	public ItemBlockDarkendCore(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}
       
    public int getMetadata(int meta)
    {
    	return meta;
    }
}