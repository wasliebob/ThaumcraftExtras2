package thaumcraftextras.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraftextras.main.Config;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemXPShard extends Item{

	public ItemXPShard(String itemName, int colorCode) {
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
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		if(player.isSneaking())
			return false;

		player.addExperienceLevel(Config.xpLevel);
		itemstack.stackSize--;
		return false;
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