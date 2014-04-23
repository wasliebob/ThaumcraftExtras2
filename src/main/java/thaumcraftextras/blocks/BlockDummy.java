package thaumcraftextras.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDummy extends Block {
	public BlockDummy() {
		super(Material.iron);
		setHardness(1.0F);
		setLightLevel(1.0F);
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + "dummy");
		
		GameRegistry.registerBlock(this, "Dummy");
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir)
    {
    	blockIcon = ir.registerIcon("thaumcraftextras:example");
    }
}