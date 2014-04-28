package thaumcraftextras.api.core;

import java.util.HashMap;

import thaumcraftextras.api.core.pages.TCEPage;

public class TCEGuide {
	/**
	 * @param entry
	 * Which page to use (overriding is possible but not recommended)
	 * @param text
	 * New Entry Page (see TCEPage.class for more info)
	 */
	public static void addPage(Integer entry, TCEPage text)
	{
		page.put(entry, text);
	}
	public static HashMap<Integer, TCEPage> page = new HashMap<Integer, TCEPage>();
}