package thaumcraftextras.main.init.intergration.computercraft;

import java.awt.Color;

import thaumcraftextras.blocks.tiles.TileEntityMagicBattery;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class MagicBatteryPeripheral implements IPeripheral {

	public MagicBatteryPeripheral(TileEntityMagicBattery tile)
	{
		this.bat = tile;	
	}
	TileEntityMagicBattery bat;
	
	@Override
	public String getType() {
		return "magic_battery";
	}

	@Override
	public String[] getMethodNames() {
		return new String[]{"getEnergy", "getColor", "getTransfer", "setColor",  "help"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arg) throws Exception {
		switch(method){
		case 0: return new Object[]{bat.getEnergy() + "/" + bat.getMaxEnergy()};
		case 1: return new Object[]{bat.getColor()};
		case 2: return new Object[]{bat.getMaxTransfer()};
		case 3: return new Object[]{bat.streamColor = getColor(arg)};
		case 4: return new Object[]{"getEnergy, ", "getColor, ", "getTransfer, ", "setColor, ",  "help, "};
		}
		return null;
	}
	
	public int getColor(Object[] arg){
		return new Color(parse(arg[0].toString()), parse(arg[1].toString()), parse(arg[2].toString())).getRGB();
	}
	
	public int parse(String s){
		return Integer.parseInt(s);
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