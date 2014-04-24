package thaumcraftextras.main.init.intergration.NEI.recipes;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraftextras.api.core.recipes.DarkInfuserRecipeManager;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class DarkInfuserRecipes extends TemplateRecipeHandler{

    public class SmeltingPair extends CachedRecipe
    {
    	public SmeltingPair(ItemStack ingredients, ItemStack item)
        {
            ingredients.stackSize = 1;
            this.ingred = new PositionedStack(ingredients, 51, 6);
            this.result = new PositionedStack(item, 111, 24);
        }
		PositionedStack result; 
		PositionedStack ingred;
		
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
		return "Dark Infuser";
	}

	@Override
	public String getGuiTexture() 
	{
		return "thaumcraftextras:textures/gui/base.png";
	}
    
	@Override
	public void loadTransferRects()
	{
		transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "infuse"));
	}


	//public static Class<? extends GuiContainer> guiclass;

	  @Override
	    public void loadCraftingRecipes(String outputId, Object... results)
	    {
	        if(outputId.equals("infuse") && getClass() == DarkInfuserRecipes.class)
	        {
	            HashMap<Item, ItemStack> recipes = (HashMap<Item, ItemStack>) DarkInfuserRecipeManager.darkInfuserList();
	            
	            for(Entry<Item, ItemStack> recipe : recipes.entrySet())
	            {
	            	ItemStack item = recipe.getValue();
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
            HashMap<Item, ItemStack> recipes = (HashMap<Item, ItemStack>) DarkInfuserRecipeManager.darkInfuserList();
	        
	        for(Entry<Item, ItemStack> recipe : recipes.entrySet())
	        {
	        	ItemStack item = recipe.getValue();
	            if(NEIServerUtils.areStacksSameType(item, result))
	            {
	                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey(), 1, -1), item));
	            }
	        }

	    }
	    
	    @Override
	    public void loadUsageRecipes(String inputId, Object... ingredients)
	    {
	        if(inputId.equals("fuel") && getClass() == DarkInfuserRecipes.class)
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
            HashMap<Item, ItemStack> recipes = (HashMap<Item, ItemStack>) DarkInfuserRecipeManager.darkInfuserList();
	        Item ingredients = stacks.getItem();
	        
	        for(Entry<Item, ItemStack> recipe : recipes.entrySet())
	        {
	        	ItemStack item = recipe.getValue();
	            if(ingredients == recipe.getKey())
	            {
	                arecipes.add(new SmeltingPair(new ItemStack(ingredients), item));
	            }
	        }
	    }
}