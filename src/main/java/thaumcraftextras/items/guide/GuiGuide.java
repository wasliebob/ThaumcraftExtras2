package thaumcraftextras.items.guide;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;

import thaumcraftextras.api.core.TCEGuide;

public class GuiGuide extends GuiScreen{
	public static GuiGuide index = new GuiGuide();
	public static final ResourceLocation background = new ResourceLocation("thaumcraftextras:textures/gui/guide.png");

	int gwidth = 170;
	int gheight = 180;
	int left, top;
	int curPage;
	
	@Override
	public void initGui(){
		super.initGui();
		index = this;
		curPage = 1;
		left = (this.width/2) - (gwidth/2);
		top = (this.height/2) - (gheight/2);
		
		this.buttonList.clear();
	}
	
	@Override
	public void drawScreen(int i0, int i1, float f1){
		this.mc.renderEngine.bindTexture(background);
		drawTexturedModalRect(left, top, 0, 0, gwidth, gheight);

		/** Title */
		String str = TCEGuide.page.get(curPage).title;
		this.drawCenteredString(fontRendererObj, str, this.left + gwidth / 2, top - 15, 0x336666);
		String[] sa = TCEGuide.page.get(curPage).text;

		/** Page View */
		this.drawCenteredString(fontRendererObj, curPage + "/" + TCEGuide.page.size(), this.left + gwidth / 2, top + 160, TCEGuide.page.get(curPage).color);

		/** Text */ 
		if(sa != null && sa.length > 0)
		for(int j = 0; j < sa.length; j++){
			String s = sa[j];
			this.drawString(fontRendererObj, s, this.left + gwidth / 2 - 50, (top + 15) + (9*j), 0x00FF33);
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
            
            if(i == 32){
            	if(curPage == TCEGuide.page.size())
            		return;
            	else
            		curPage++;
            	
            	return;
            }

            this.keyTyped(c0, i);
        }
    }
}