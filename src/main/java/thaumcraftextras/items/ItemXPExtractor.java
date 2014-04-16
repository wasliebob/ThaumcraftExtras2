package thaumcraftextras.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraftextras.main.Config;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCEItems;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemXPExtractor extends Item{

	public ItemXPExtractor(String itemName) {
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "item" + "." + itemName.toLowerCase());
		setCreativeTab(TCETabs.tabMain);
		name = itemName;
		init();
	}
	String name;
	
	public void init()
	{
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	@Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		if(player.isSneaking())
			return false;

		if(player.experienceLevel >= 5)
		{
			player.experienceLevel = player.experienceLevel - Config.xpLevel;
			player.inventory.addItemStackToInventory(new ItemStack(TCEItems.xpShard));
			return false;
		}
		return false;
    }
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "extractor_xp");
	}
}