package thaumcraftextras.main.init.intergration.computercraft;

import thaumcraftextras.api.interfaces.IMagicEnergyContainerItem;
import thaumcraftextras.blocks.tiles.TileEntityMagicCrystalCharger;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class CrystalChargerPeripheral implements IPeripheral {

	public CrystalChargerPeripheral(TileEntityMagicCrystalCharger tile)
	{
		this.te = tile;	
	}
	TileEntityMagicCrystalCharger te;
	
	@Override
	public String getType() {
		return "crystal_charger";
	}

	@Override
	public String[] getMethodNames() {
		return new String[]{"getEnergy", "getBattery", "getTransfer", "getCharge", "Help"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws Exception {
		IMagicEnergyContainerItem con = (IMagicEnergyContainerItem)te.getStackInSlot(0).getItem();
		switch(method){
		case 0: return new Object[]{te.getEnergy() + "/" + te.getMaxEnergy()};
		case 1: return new Object[]{te.getStackInSlot(0).getDisplayName()};
		case 2: return new Object[]{te.getMaxTransfer()};
		case 3: return new Object[]{(con.getMaxEnergy(te.getStackInSlot(0)) - con.getEnergy(te.getStackInSlot(0))) + "/" + con.getMaxEnergy(te.getStackInSlot(0))};
		case 4: return new Object[]{"getEnergy, ", "getBattery, ", "getTransfter, ", "getCharge, ", "help, "};
		}
		return null;
	}
	

	@Override
	public void attach(IComputerAccess computer) {
		
	}

	@Override
	public void detach(IComputerAccess computer) {
		
	}

	@Override
	public boolean equals(IPeripheral other) {
		return false;
	}
}