package thaumcraftextras.items.guide.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import thaumcraftextras.helpers.IconHelper;

public class ButtonPrevPage extends GuiButton {

	public ButtonPrevPage(int id, int xPos, int yPos, int width, int height, String string) {
		super(id, xPos, yPos, width, height, string);
	}

	@Override
	public void drawButton(Minecraft mc, int x, int y) {

		x = this.xPosition + width / 2 - 30;
		y = this.yPosition + (height - 6) / 2;
//		y = (this.yPosition + 15) + (16);

		mc.getTextureManager().bindTexture(new ResourceLocation("thaumcraftextras:textures/gui/arrow.png"));
		IconHelper.drawIcon(x, y, 16, 16, 0);
	}
}