package thaumcraftextras.items;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraftextras.main.init.TCETabs;
import wasliecore.helpers.ColorHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCEItemEssence extends Item{

	public TCEItemEssence(String name, String oreDict, Color color) {
		super();
		setMaxStackSize(64);
		setCreativeTab(TCETabs.tabMain);
		
		this.color = color.getRGB();
		
		setUnlocalizedName("thaumcraftextras" + "." + "item" + "." + name.toLowerCase());
				
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		OreDictionary.registerOre(oreDict, this);
	}
	public IIcon primary;
	public IIcon secondary;
	public int color;
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int renderPass) {
		if(renderPass > 0)
			return this.secondary;
		return this.primary;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getRenderPasses(int meta) {
		return 2;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{		
		if(pass > 0) 
			return ColorHelper.getColorCodeFromRGB(255, 255, 255);
		
		return this.color;

	}
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
		this.primary = ir.registerIcon("thaumcraftextras:essence_0");
		this.secondary = ir.registerIcon("thaumcraftextras:essence_1");
	}
}