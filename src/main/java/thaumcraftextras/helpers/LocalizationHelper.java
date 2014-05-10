package thaumcraftextras.helpers;

public class LocalizationHelper {
	public static String getLocalization(String name, int line)
	{
		return name + "." + line;
//		return LanguageRegistry.instance().getStringLocalization(name + "." + line);
	}
}
