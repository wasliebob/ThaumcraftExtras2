package thaumcraftextras.helpers;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import thaumcraft.api.aspects.Aspect;

public class IconHelper {
	public static void drawAspect(int x, int y, int width, int height, float zLevel, Aspect asp)
	{
	    GL11.glColor4f(1F, 1F, 1F, 1F);
	    Tessellator t = Tessellator.instance;
	    t.startDrawingQuads();
	    t.setColorOpaque_I(asp.getColor());
	    t.addVertexWithUV(x + 0, y + height, zLevel, 0D, 1D);
	    t.addVertexWithUV(x + width, y + height, zLevel, 1D, 1D);
	    t.addVertexWithUV(x + width, y + 0, zLevel, 1D, 0D);
	    t.addVertexWithUV(x + 0, y + 0, zLevel, 0D, 0D);
	    t.draw();
	}
	
	public static void drawIcon(int x, int y, int width, int height, float zLevel)
	{
	    Tessellator t = Tessellator.instance;
	    t.startDrawingQuads();
	    t.setColorOpaque(255, 255, 51);
	    t.addVertexWithUV(x + 0, y + height, zLevel, 0D, 1D);
	    t.addVertexWithUV(x + width, y + height, zLevel, 1D, 1D);
	    t.addVertexWithUV(x + width, y + 0, zLevel, 1D, 0D);
	    t.addVertexWithUV(x + 0, y + 0, zLevel, 0D, 0D);
	    t.draw();
	}
	
	public static void drawIcon(int x, int y, int width, int height, float zLevel, int red, int green, int blue)
	{
	    Tessellator t = Tessellator.instance;
	    t.startDrawingQuads();
//	    t.setColorOpaque(255, 255, 51);
	    t.setColorOpaque(red, green, blue);
	    t.addVertexWithUV(x + 0, y + height, zLevel, 0D, 1D);
	    t.addVertexWithUV(x + width, y + height, zLevel, 1D, 1D);
	    t.addVertexWithUV(x + width, y + 0, zLevel, 1D, 0D);
	    t.addVertexWithUV(x + 0, y + 0, zLevel, 0D, 0D);
	    t.draw();
	}
}