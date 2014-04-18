package thaumcraftextras.main.init;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import thaumcraftextras.blocks.BlockDarkendAltar;
import thaumcraftextras.blocks.BlockDarkendCore;
import thaumcraftextras.blocks.BlockMagicBattery;
import thaumcraftextras.blocks.BlockMagicCrystalCharger;
import thaumcraftextras.blocks.BlockMagicGenerator;
import thaumcraftextras.blocks.BlockMagicVoid;
import thaumcraftextras.blocks.BlockMagicWandCharger;
import thaumcraftextras.blocks.BlockMatrix;
import thaumcraftextras.blocks.BlockNoMove;
import thaumcraftextras.blocks.BlockShocker;
import thaumcraftextras.blocks.BlockWardedGhost;
import thaumcraftextras.blocks.TCEBlock;

public class TCEBlocks {

	public static void init()
	{
		initBlocks();
	}
	
	public static void initBlocks()
	{
		blockIgnis = new TCEBlock(Material.rock, "Ignis Block", "block_ignisFuel", true, new ItemStack(TCEItems.ignisFuel));
		specialWardedGhost = new BlockWardedGhost("Warded Block: Ghost");
		shocker = new BlockShocker(Material.iron, "Magical Tesslacoil");
		darkendCore = new BlockDarkendCore("Darkend Core Block");
		darkendAltar = new BlockDarkendAltar("Darkend Altar Block");
		darkendMatrix = new BlockMatrix("Darkend Matrix Block");
		noMove = new BlockNoMove(Material.iron, "Entity Containment");
		battery = new BlockMagicBattery(Material.iron, "Magic Battery");
		generator = new BlockMagicGenerator(Material.iron, "Magic Generator");
		wandCharger = new BlockMagicWandCharger(Material.iron, "Magic Wand Charger");
		crystalCharger = new BlockMagicCrystalCharger(Material.iron, "Magic Crystal Charger");
		magicVoid = new BlockMagicVoid(Material.iron, "Magic Energy Void");
	}
	public static TCEBlock blockIgnis;
	public static BlockWardedGhost specialWardedGhost;
	public static BlockShocker shocker;
	public static BlockDarkendCore darkendCore;
	public static BlockDarkendAltar darkendAltar;
	public static BlockMatrix darkendMatrix;
	public static BlockNoMove noMove;
	public static BlockMagicBattery battery;
	public static BlockMagicGenerator generator;
	public static BlockMagicWandCharger wandCharger;
	public static BlockMagicCrystalCharger crystalCharger;
	public static BlockMagicVoid magicVoid;
	
}
