package thaumcraftextras.helpers;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHelper {
	public static String getLocalization(String name, int line)
	{
		return LanguageRegistry.instance().getStringLocalization(name + "." + line);
	}
}
