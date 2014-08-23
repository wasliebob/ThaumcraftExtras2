package thaumcraftextras.main.init.addons.wands;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.addons.TCETools;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCEItemCap extends Item{

	public TCEItemCap(String itemName) {
		setUnlocalizedName(ThaumcraftExtras.modName.toLowerCase() + "." + "cap" + "." + itemName.toLowerCase());
		setCreativeTab(TCETools.tabTools);
		name = itemName;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	String name;
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
        itemIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + "cap_" + name);
	}
}