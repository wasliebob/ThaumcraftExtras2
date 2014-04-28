package thaumcraftextras.main.init;

import thaumcraftextras.api.core.TCEGuide;
import thaumcraftextras.api.core.pages.TCEPage;

public class TCEGuideEntries {
	public static void init()
	{
		initTextPages();
	}
	
	public static void initTextPages()
	{
		int n = 1;
		TCEPage page;
		page = new TCEPage("Thaumcraft Extras 2", new String[]{
				"Welcome to the world",
				"of Thaumcraft Extras 2", 
				"TCE2 has a lot of new",
				"features, ", 
				"But also returns ",
				"previous once."},
				0x00FF33);
		TCEGuide.addPage(n, page);
		n++;
		
		page = new TCEPage("Thaumic Energy", new String[]{
				"TCE2 adds a whole ",
				"new energy system",
				"called Thaumic Energy."},
				0x00FF33);
		TCEGuide.addPage(n, page);
		n++;
		
		page = new TCEPage("Thaumic Energy Battery", new String[]{
				"The Thaumic Energy ",
				"Batter is one of the ",
				"core blocks of TCE2's ",
				"energy system.",
				"The battery allows you ",
				"to store energy and ",
				"take it with you."},
				0x00FF33);
		TCEGuide.addPage(n, page);
		n++;
	}
}