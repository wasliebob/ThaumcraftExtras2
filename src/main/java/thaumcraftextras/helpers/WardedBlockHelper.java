package thaumcraftextras.helpers;

import java.util.ArrayList;

import thaumcraftextras.main.ThaumcraftExtras;

public class WardedBlockHelper {

	public static void init()
	{
		for(int i = 0; i < 16; i++)
			warded.add(ThaumcraftExtras.modName.toLowerCase() + ":" + "warded" + "_" + i);
	}
	public static ArrayList<String> warded = new ArrayList<String>();
}