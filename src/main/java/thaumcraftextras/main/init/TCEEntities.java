package thaumcraftextras.main.init;

import thaumcraftextras.items.foci.projectile.ProjectilePechTrade;
import thaumcraftextras.main.ThaumcraftExtras;
import cpw.mods.fml.common.registry.EntityRegistry;

public class TCEEntities {
	public static void init(){
	    EntityRegistry.registerModEntity(ProjectilePechTrade.class, "PechTrade", 0, ThaumcraftExtras.instance, 64, 20, true);
	}
}
