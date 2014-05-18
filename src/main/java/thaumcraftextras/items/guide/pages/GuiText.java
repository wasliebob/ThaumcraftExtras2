package thaumcraftextras.items.guide.pages;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import thaumcraftextras.api.core.TCEGuide;
import thaumcraftextras.api.core.pages.TCEPageText;
import thaumcraftextras.api.core.recipes.ClasherRecipeManager;
import thaumcraftextras.helpers.IconHelper;
import thaumcraftextras.items.guide.GuiGuide;

public class GuiText extends GuiScreen{
	public static final ResourceLocation background = new ResourceLocation("thaumcraftextras:textures/gui/guide.png");
	
	public GuiText(String title){
		this.title = title;
	}
	String title;
	
	int gwidth = 170;
	int gheight = 180;
	int left, top;
	int curPage;
	
	@Override
	public void initGui(){
		super.initGui();
		curPage = 1;
		left = (this.width/2) - (gwidth/2);
		top = (this.height/2) - (gheight/2);
		this.buttonList.clear();
	}
	
	public String[] getPage(){
		if(this.curPage == 1)
			return TCEGuide.entry_text.get(this.title).page1;
		if(this.curPage == 2)
			return null;
		return null;
	}
	
	@Override
	public void drawScreen(int i0, int i1, float f1){
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.renderEngine.bindTexture(background);
		drawTexturedModalRect(left, top, 0, 0, gwidth, gheight);
		TCEPageText page = TCEGuide.entry_text.get(this.title);
		
		int x, y;
		
		/** Title */
		String str = this.title;
		this.drawCenteredString(fontRendererObj, str, this.left + gwidth / 2, top - 15, 0x336666);
		String[] sa = getPage();

		/** Current Page */
		this.drawCenteredString(fontRendererObj, curPage + "/" + page.size, this.left + gwidth / 2, top + 160, 0x336666);

		/** Text */ 
		if(sa != null && curPage != 2 && sa.length > 0){
			for(int j = 0; j < sa.length; j++){
				String s = sa[j];
				mc.fontRenderer.drawString(s, this.left + gwidth / 2 - 50, (top + 15) + (9*j), new Color(55, 55, 55).getRGB());
			}
		}else if(curPage == 2){
			ItemStack item;
			
			for(int i = 0; i < page.recipe.input.length; i++){
				if(i <= 2){
					x = this.left + gwidth / 2 - (50-32-2);
					y = (gheight/2) + (16*i);
				}else if(i > 2 && i <= 5){
					x = this.left + gwidth / 2 - (50-48-2);
					y = (gheight/2) + (16*(i-3));
				}else if(i > 5 && i <= 9){
					x = this.left + gwidth / 2 - (50-(48+16)-2);
					y = (gheight/2) + (16*(i-6));
				}else{
					x = this.left + gwidth / 2 - (50-32-2);
					y = (gheight/2) + (16*i);
				}
				item = (ItemStack)page.recipe.input[i];
				GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
			}
			x = this.left + gwidth/2 - (50-(48+48)-2);
			y = (gheight/2) + 16;
			item = (ItemStack)page.recipe.output;
			GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
	
			x = this.left + gwidth/2 - (50-(48+(48-16))-2);
			y = (gheight/2) + 16;
			mc.getTextureManager().bindTexture(new ResourceLocation("thaumcraftextras:textures/gui/arrow.png"));
			IconHelper.drawIcon(x, y, 16, 16, 0);
			
			for(int i = 0; i < page.recipe.aspects.size(); i++){
				x = this.left + gwidth/2 - (50-(32+(16*i)));
				y = (gheight/2 + (16*3));
				mc.getTextureManager().bindTexture(page.recipe.aspects.getAspects()[i].getImage());
				IconHelper.drawAspect(x, y, 16, 16, 0, page.recipe.aspects.getAspects()[i]);
			}
		}else if(sa == null && TCEGuide.entry_text.get(this.title).aaRecipe != null && TCEGuide.entry_text.get(this.title).aaRecipe.length > 0){
			ItemStack item;
			for(int j = 0; j < page.aaRecipe.length; j++){
				x = this.left + gwidth / 2 - 50;
				y = (top + 15) + (16*j);
				item = page.aaRecipe[j].input;
				GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
			
				x = this.left + gwidth / 2 + 50;
				y = (top + 15) + (16*j);
				item = page.aaRecipe[j].output;
				GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
				
				x = this.left + gwidth / 2 - 25;
				y = (top + 20) + (16*j);
				this.drawString(fontRendererObj, Integer.toString(page.aaRecipe[j].amount), x, y, Color.gray.getRGB());

				x = this.left + gwidth / 2;
				y = (top + 15) + (16*j);
				Minecraft.getMinecraft().getTextureManager().bindTexture(page.aaRecipe[j].aspect.getImage());
				IconHelper.drawAspect(x, y, 16, 16, j, page.aaRecipe[j].aspect);
				
				x = this.left + gwidth / 2 + 25;
				y = (top + 15) + (16*j);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraftextras:textures/gui/arrow.png"));
				IconHelper.drawIcon(x, y, 16, 16, 0);
			}
		}else if(sa == null && TCEGuide.entry_text.get(this.title).cRecipe != null && TCEGuide.entry_text.get(this.title).cRecipe.length > 0){
			ItemStack item;
			for(int j = 0; j < page.cRecipe.length; j++){
				if(ClasherRecipeManager.clasher.containsKey(page.cRecipe[j])){
					x = this.left + gwidth / 2 - 50;
					y = (top + 15) + (16*j);
					item = new ItemStack(page.cRecipe[j]);
					GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
				
					x = this.left + gwidth / 2 - 25;
					y = (top + 15) + (16*j);
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraftextras:textures/gui/plus.png"));
					IconHelper.drawIcon(x, y, 16, 16, 0);
				
					x = this.left + gwidth / 2;
					y = (top + 15) + (16*j);
					item = new ItemStack(ClasherRecipeManager.clasher.get(page.cRecipe[j]));
					GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
					
					x = this.left + gwidth / 2 + 50;
					y = (top + 15) + (16*j);
					item = ClasherRecipeManager.clasherOut.get(page.cRecipe[j]);
					GuiScreen.itemRender.renderItemIntoGUI(fontRendererObj, Minecraft.getMinecraft().getTextureManager(), item, x, y);
				
					x = this.left + gwidth / 2 + 25;
					y = (top + 15) + (16*j);
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("thaumcraftextras:textures/gui/arrow.png"));
					IconHelper.drawIcon(x, y, 16, 16, 0);
				}
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
            	if(curPage == TCEGuide.entry_text.get(this.title).size)
            		return;
            	else
            		curPage++;
            	
            	return;
            }
            
            if(i == 14){
				mc.displayGuiScreen(new GuiGuide());
            	
            	return;
            }
            
            

            this.keyTyped(c0, i);
        }
    }
}