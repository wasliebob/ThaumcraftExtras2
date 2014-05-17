package thaumcraftextras.blocks;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraftextras.main.ThaumcraftExtras;
import thaumcraftextras.main.init.TCETabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkMagicLog extends BlockRotatedPillar
{
    public static final String[] woodType = new String[] {"darkwood"};
    @SideOnly(Side.CLIENT)
    private IIcon tree_side;
    @SideOnly(Side.CLIENT)
    private IIcon tree_top;

    public BlockDarkMagicLog()
    {
        super(Material.wood);
        setCreativeTab(TCETabs.tabMain);
        setHardness(1.0F);
		setBlockName(ThaumcraftExtras.modName.toLowerCase() + "." + "block" + "." + "darkwood log");
        GameRegistry.registerBlock(this, this.getUnlocalizedName());
    }

    @Override
    protected IIcon getSideIcon(int par1)
    {
        return this.tree_side;
    }

    @Override
    protected IIcon getTopIcon(int par1)
    {
        return this.tree_top;
    }

    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
    	this.tree_side = ir.registerIcon("thaumcraftextras" + ":" + "darkwood");
    	this.tree_top = ir.registerIcon("thaumcraftextras" + ":" + "darkwood" + "_top");
    }
    
    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}