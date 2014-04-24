package thaumcraftextras.main.init.intergration.computercraft;

import thaumcraftextras.blocks.tiles.TileEntityEssentiaBarrel;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class EssentiaBarrelPeripheral implements IPeripheral {

	public EssentiaBarrelPeripheral(TileEntityEssentiaBarrel tile)
	{
		this.te = tile;	
	}
	TileEntityEssentiaBarrel te;
	
	@Override
	public String getType() {
		return "essentia_barrel";
	}

	@Override
	public String[] getMethodNames() {
		return new String[]{"getAspect", "getAmount", "help"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws Exception {
		switch(method){
		case 0: return new Object[]{te.getAspect().getName()};
		case 1: return new Object[]{te.amount + "/" + (64*te.getMod())};
		case 2: return new Object[]{"getAspect,", "getAmount, ", "help, "};
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