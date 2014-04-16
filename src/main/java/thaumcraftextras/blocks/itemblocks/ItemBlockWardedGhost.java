package thaumcraftextras.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockWardedGhost extends ItemBlock
{
	public ItemBlockWardedGhost(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}
       
    public int getMetadata(int meta)
    {
    	return meta;
    }
}