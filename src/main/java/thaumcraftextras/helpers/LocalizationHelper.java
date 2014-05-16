package thaumcraftextras.helpers;

import net.minecraft.util.StatCollector;

public class LocalizationHelper {
	public static String getLocalization(String name, int line)
	{
		return StatCollector.translateToLocal(name + "." + line);
//		return LanguageRegistry.instance().getStringLocalization(name + "." + line);
	}
}