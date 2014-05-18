package thaumcraftextras.items.guide;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import thaumcraftextras.api.core.TCEGuide;
import thaumcraftextras.items.guide.button.ButtonPage;
import thaumcraftextras.items.guide.pages.GuiText;

public class GuiGuide extends GuiScreen{
	public static GuiGuide index = new GuiGuide();
	public static final ResourceLocation background = new ResourceLocation("thaumcraftextras:textures/gui/guide.png");

	int gwidth = 170;
	int gheight = 180;
	int left, top;
	int curPage;

	@SuppressWarnings("unchecked")
	@Override
	public void initGui(){
		super.initGui();
		index = this;
		curPage = 1;
		left = (this.width/2) - (gwidth/2);
		top = (this.height/2) - (gheight/2);
		this.buttonList.clear();
		int x, y;
		for(int j = 0; j < TCEGuide.index.size(); j++) {
			x = this.left + gwidth / 2 - 75;
			y = (top + 15) + (10*j);
			buttonList.add(new ButtonPage(j, x, y, 110, 10, ""));
		}
	}
	
	@Override
	public void drawScreen(int i0, int i1, float f1){
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.renderEngine.bindTexture(background);
		drawTexturedModalRect(left, top, 0, 0, gwidth, gheight);
		ArrayList<String> page = TCEGuide.index;
		
		/** Title */
		String str = "Guide of The Thaumaturge";
		this.drawCenteredString(fontRendererObj, str, this.left + gwidth / 2, top - 15, 0x336666);
		ArrayList<String> sa = page;		
		
		if(sa != null && sa.size() > 0)    {
			for(int j = 0; j < sa.size(); j++){
				String s = sa.get(j);
				ButtonPage button = (ButtonPage) buttonList.get(j);
				if(button.displayString != null)
					button.displayString = s;
			}
		}
		super.drawScreen(i0, i1, f1);
	}
	
	@Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	@Override
    public void handleKeyboardInput()
    {
        if (Keyboard.getEventKeyState())
        {
            int i = Keyboard.getEventKey();
            char c0 = Keyboard.getEventCharacter();

            if(i == 87){
                this.mc.toggleFullscreen();
                return;
            }
            
            if(i == 31){
            	if(curPage == 1)
            		return;
            	else
            	curPage--;
            	
            	return;
            }

            this.keyTyped(c0, i);
        }
    }
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch(button.id) {
			default:
				mc.displayGuiScreen(new GuiText(button.displayString));
		}
	}
}