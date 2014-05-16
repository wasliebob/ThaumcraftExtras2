package thaumcraftextras.items.guide;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;

import thaumcraftextras.api.core.TCEGuide;
import thaumcraftextras.api.core.pages.TCEPage;
import thaumcraftextras.helpers.IconHelper;

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
		TCEPage page = TCEGuide.page.get(curPage);
		
		/** Title */
		String str = page.title;
		this.drawCenteredString(fontRendererObj, str, this.left + gwidth / 2, top - 15, 0x336666);
		String[] sa = page.text;

		/** Current Page */
		this.drawCenteredString(fontRendererObj, curPage + "/" + TCEGuide.page.size(), this.left + gwidth / 2, top + 160, page.color);

		/** Text */ 
		if(sa != null && sa.length > 0)    {
			for(int j = 0; j < sa.length; j++){
				String s = sa[j];
				this.drawString(fontRendererObj, s, this.left + gwidth / 2 - 50, (top + 15) + (9*j), 0x00FF33);
			}
		}else if(sa == null && page.image != null){
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(page.image));
			IconHelper.drawIcon(this.left + gwidth / 2 - 50, (top + 15) + 9, page.width, page.height, 0);
		}else if(sa == null && page.recipe != null){
			int x, y;
			ItemStack item;
			for(int j = 0; j < page.recipe.length; j++){
				x = this.left + gwidth / 2 - 50;
				y = (top + 15) + (16*j);
				item = page.recipe[j].input;
				GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
			
				x = this.left + gwidth / 2 + 50;
				y = (top + 15) + (16*j);
				item = page.recipe[j].output;
				GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
				
				x = this.left + gwidth / 2 - 25;
				y = (top + 20) + (16*j);
				this.drawString(fontRendererObj, Integer.toString(page.recipe[j].amount), x, y, page.color);

				x = this.left + gwidth / 2;
				y = (top + 15) + (16*j);
				Minecraft.getMinecraft().getTextureManager().bindTexture(page.recipe[j].aspect.getImage());
				IconHelper.drawAspect(x, y, 16, 16, j, page.recipe[j].aspect);
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