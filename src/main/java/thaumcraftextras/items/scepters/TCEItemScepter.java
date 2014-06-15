package thaumcraftextras.items.scepters;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraftextras.api.core.TCEApi;
import thaumcraftextras.main.init.TCETabs;
import wasliecore.helpers.ColorHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCEItemScepter extends Item{

	public TCEItemScepter(String name) {
		super();
		setMaxStackSize(1);

		itemName = name;
		
		setCreativeTab(TCETabs.tabMain);

		setUnlocalizedName("thaumcraftextras" + "." + "scepter" + "." + name);
				
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	IIcon primary;
	IIcon secondary;
	String itemName;
	
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
    public void onUpdate(ItemStack stack, World wolrd, Entity entity, int par4, boolean par5){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("AMOUNT", 50);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{		
		if(pass > 0) 
			return ColorHelper.getColorCodeFromRGB(255, 255, 255);
		
		return TCEApi.fullAspectList.get(stack.getItemDamage()).getColor();

	}
	
	@Override
    public void registerIcons(IIconRegister ir) 
	{
		this.primary = ir.registerIcon("thaumcraftextras:scepter_0");
		this.secondary = ir.registerIcon("thaumcraftextras:scepter_1");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id){
		if(TCEApi.fullAspectList.get(stack.getItemDamage()).getName() != null){
			list.add(EnumChatFormatting.RED + "Contains: " + EnumChatFormatting.GRAY + TCEApi.fullAspectList.get(stack.getItemDamage()).getName());
			
			if(stack.hasTagCompound())
				list.add(EnumChatFormatting.RED + "Contains: " + EnumChatFormatting.GRAY + stack.getTagCompound().getInteger("AMOUNT"));
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void getSubItems(Item item, CreativeTabs tabs, List list){
    	for(int i = 0; i < TCEApi.fullAspectList.size(); i++){
    		Aspect asp = TCEApi.fullAspectList.get(i);
    		if(asp != null){
    			ItemStack stack = new ItemStack(item, 1, i);
    			stack.setTagCompound(new NBTTagCompound());
    			stack.getTagCompound().setInteger("AMOUNT", 50);
    			list.add(stack);
    		}
    	}
    }
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
		if(player.isSneaking())
			return false;
		Aspect asp = TCEApi.fullAspectList.get(stack.getItemDamage());
		
		if(stack.hasTagCompound() && canDrain(stack, asp) && TCEApi.scepterAction.get(asp) != null){
			TCEApi.scepterAction.get(asp).onClick(player, stack, world, x, y, z, side);
			stack.getTagCompound().setInteger("AMOUNT", TCEApi.scepterAction.get(asp).getCost());
		}
		return true;
    }
	
	public boolean canDrain(ItemStack stack, Aspect asp){
		int amount = stack.getTagCompound().getInteger("AMOUNT");
		if(amount - TCEApi.scepterAction.get(asp).getCost() >= 0)
			return true;
		return false;
	}
}