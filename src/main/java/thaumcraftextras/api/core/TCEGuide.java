package thaumcraftextras.api.core;

import java.util.ArrayList;
import java.util.HashMap;

import thaumcraftextras.api.core.pages.TCEPageText;

public class TCEGuide {
	public static void addCategory(String title){
		index.add(title);
	}
	public static ArrayList<String> index = new ArrayList<String>();

	public static void addTextEntry(String title, TCEPageText base){
		entry_text.put(title, base);
	}
	public static HashMap<String, TCEPageText> entry_text = new HashMap<String, TCEPageText>();
}