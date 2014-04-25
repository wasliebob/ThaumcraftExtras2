package thaumcraftextras.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class TCEBlock extends Block {

	public TCEBlock(Material mat, String blockName, String textureName, boolean isIngotBlock, ItemStack craftMaterial)
	{
		super(mat);
		setCreativeTab(TCETabs.tabMain);
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + blockName);
		setHardness(1.0F);
		name = blockName;
		isIBlock = isIngotBlock;
		craftMat = craftMaterial;
		texture = textureName;
		
		init();
	}
	String name;
	String texture;
	boolean isIBlock;
	ItemStack craftMat;
	
	public void init()
	{
		GameRegistry.registerBlock(this, name);
		
		if(isIBlock && craftMat != null)
			addRecipe();
	}
	
	public void addRecipe()
	{
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
		"XXX",
		"XXX",
		"XXX",
		'X', craftMat});
		
		GameRegistry.addShapelessRecipe(new ItemStack(craftMat.getItem(), 9, 0), this);
	}
	
	@Override
    public void registerBlockIcons(IIconRegister ir) 
	{
        blockIcon = ir.registerIcon(ThaumcraftExtras.modName.toLowerCase() + ":" + texture);
	}	
}