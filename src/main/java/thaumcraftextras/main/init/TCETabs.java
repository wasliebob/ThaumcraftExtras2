package thaumcraftextras.main.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thaumcraftextras.main.init.addons.TCEWands;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCETabs {
	
    public static CreativeTabs tabMain = new CreativeTabs("tabThaumcraftExtras2") {
    	
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return TCEItems.essenceMagic;}}; 
			
		    public static CreativeTabs tabWands = new CreativeTabs("tabThaumcraftExtras2Wands") {
		    	
				@Override
				@SideOnly(Side.CLIENT)
				public Item getTabIconItem(){
					return TCEWands.item_rod_ultimate;}};  
}
