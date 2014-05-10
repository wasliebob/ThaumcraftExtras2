package thaumcraftextras.main.init.intergration.NEI.recipes;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipe;
import thaumcraftextras.api.core.recipes.AdvancedAltarRecipeManager;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class AltarRecipes extends TemplateRecipeHandler{

    public class SmeltingPair extends CachedRecipe
    {
    	public SmeltingPair(ItemStack ingredients, ItemStack item)
        {
            ingredients.stackSize = 1;
            this.input = ingredients.getItem();
            this.ingred = new PositionedStack(ingredients, 51, 24);
            this.result = new PositionedStack(item, 111, 24);
        }
    	Item input;
		PositionedStack result; 
		PositionedStack ingred;
		
		public Item getInput()
		{
			return input;
		}
		
		 public PositionedStack getIngredient()
	        {
	            int cycle = cycleticks / 48;
	            if(ingred.item.getItemDamage() == -1)
	            {
	                PositionedStack stack = ingred.copy();
	                int maxDamage = 0;
	                do
	                {
	                    maxDamage++;
	                    stack.item.setItemDamage(maxDamage);
	                }
	                while(NEIClientUtils.itemListContains(stack.item));
	                
	                stack.item.setItemDamage(cycle % maxDamage);
	                return stack;
	            }
	            return ingred;
	        }
		 
		@Override
		public PositionedStack getResult()
		{
			return result;
		}
		
		@Override
		public PositionedStack getOtherStack()
		{
				return null;
		}
    }

	@Override
	public String getRecipeName() 
	{
		return "Advanced Altar";
	}

	@Override
	public String getGuiTexture() 
	{
		return "thaumcraftextras:textures/gui/base.png";
	}
    
	@Override
	public void loadTransferRects()
	{
		transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "altar"));
	}


	//public static Class<? extends GuiContainer> guiclass;

	  @Override
	    public void loadCraftingRecipes(String outputId, Object... results)
	    {
	        if(outputId.equals("altar") && getClass() == AltarRecipes.class)
	        {
	            HashMap<Item, AdvancedAltarRecipe> recipes = (HashMap<Item, AdvancedAltarRecipe>) AdvancedAltarRecipeManager.advancedAltarList();

	            for(Entry<Item, AdvancedAltarRecipe> recipe : recipes.entrySet())
	            {
	            	ItemStack item = recipe.getValue().output;
	                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey(), 1, -1), item));
	            }
	        }
	        else
	        {
	            super.loadCraftingRecipes(outputId, results);
	        }
	    }
	  	    
	    @Override
	    public void loadCraftingRecipes(ItemStack result)
	    {
            HashMap<Item, AdvancedAltarRecipe> recipes = (HashMap<Item, AdvancedAltarRecipe>) AdvancedAltarRecipeManager.advancedAltarList();
	        
	        for(Entry<Item, AdvancedAltarRecipe> recipe : recipes.entrySet())
	        {
            	ItemStack item = recipe.getValue().output;
	            if(NEIServerUtils.areStacksSameType(item, result))
	            {
	                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey(), 1, -1), item));
	            }
	        }
	    }
	    
	    @Override
	    public void loadUsageRecipes(String inputId, Object... ingredients)
	    {
	        if(inputId.equals("fuel") && getClass() == AltarRecipes.class)
	        {
	            loadCraftingRecipes("infuse");
	        }
	        else
	        {
	            super.loadUsageRecipes(inputId, ingredients);
	        }
	    }
	    
	    @Override
	    public void loadUsageRecipes(ItemStack stacks)
	    {
            HashMap<Item, AdvancedAltarRecipe> recipes = (HashMap<Item, AdvancedAltarRecipe>) AdvancedAltarRecipeManager.advancedAltar;
	        Item ingredients = stacks.getItem();
	        
	        for(Entry<Item, AdvancedAltarRecipe> recipe : recipes.entrySet())
	        {
            	ItemStack item = recipe.getValue().output;
	            if(ingredients == recipe.getKey())
	            {
	                arecipes.add(new SmeltingPair(new ItemStack(ingredients), item));
	            }
	        }
	    }
	    
	    @Override
	    public void drawExtras(int arg0){
	        CachedRecipe recipe = arecipes.get(arg0); 
	        Item input = recipe.getIngredient().item.getItem();
	        Aspect asp = AdvancedAltarRecipeManager.advancedAltarList().get(input).aspect;
	        int amount = AdvancedAltarRecipeManager.advancedAltarList().get(input).amount;
	        FontRenderer f = Minecraft.getMinecraft().fontRenderer;
	        f.drawString(asp.getName(), 71, 10, asp.getColor());
	        f.drawString(Integer.toString(amount), 85, 20, asp.getColor());
	        
	    }
}