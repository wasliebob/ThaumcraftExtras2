package thaumcraftextras.main.init.intergration.computercraft;

import thaumcraft.common.tiles.TileThaumatorium;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class AlchemicPeripheral implements IPeripheral {

	public AlchemicPeripheral(TileThaumatorium tile)
	{
		this.te = tile;	
	}
	TileThaumatorium te;
	
	@Override
	public String getType() {
		return "alchemic";
	}

	@Override
	public String[] getMethodNames() {
		return new String[]{"getInput", "getOutput", "help"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arg) throws Exception {
		TileThaumatorium tes = (TileThaumatorium)te;
		switch(method){
		case 0: return new Object[]{tes.inputStack.getDisplayName()};
		case 1: return new Object[]{tes.outputStack.get(Integer.parseInt(arg[0].toString())).getDisplayName()};
		case 2: return new Object[]{"getInput", "getOutput", "getEssentia", "help"};
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