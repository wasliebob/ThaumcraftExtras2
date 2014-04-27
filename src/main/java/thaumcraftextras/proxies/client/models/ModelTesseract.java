package thaumcraftextras.proxies.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTesseract extends ModelBase
{
    ModelRenderer Bar;
    ModelRenderer Base;
  
  public ModelTesseract()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Bar = new ModelRenderer(this, 0, 0);
      Bar.addBox(0F, 0F, 0F, 16, 7, 16);
      Bar.setRotationPoint(-8F, 17F, -8F);
      Bar.setTextureSize(64, 32);
      Bar.mirror = true;
      setRotation(Bar, 0F, 0F, 0F);
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 6, 21, 6);
      Base.setRotationPoint(-3F, -4F, -3F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
  }
  
  public void render()
  {
	  final float scale = 1F / 16F;
	  Bar.render(scale);
	  Base.render(scale);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bar.render(f5);
    Base.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}