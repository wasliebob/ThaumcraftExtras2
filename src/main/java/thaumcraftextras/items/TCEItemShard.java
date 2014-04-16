package thaumcraftextras.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCEItemShard extends Item{

	public TCEItemShard(String itemName, int colorCode) {
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "shard" + "." + itemName.toLowerCase());
		setCreativeTab(TCETabs.tabMain);
		name = itemName;
		color = colorCode;
		init();
	}
	String name;
	int color;
	
	public void init()
	{
		OreDictionary.registerOre("shard" + name, this);
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "shard_empty");
	}
	
    @Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		return color;
	}
}