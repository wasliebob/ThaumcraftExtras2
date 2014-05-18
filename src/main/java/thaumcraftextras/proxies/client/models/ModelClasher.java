package thaumcraftextras.proxies.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelClasher extends ModelBase
{
    ModelRenderer Base;
    ModelRenderer Pillar;
    ModelRenderer Platform;
  
  public ModelClasher()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 1F, 0F, 7, 2, 7);
      Base.setRotationPoint(-4F, 21F, -4F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Pillar = new ModelRenderer(this, 0, 0);
      Pillar.addBox(0F, 0F, 0F, 3, 9, 3);
      Pillar.setRotationPoint(-2F, 13F, -2F);
      Pillar.setTextureSize(64, 32);
      Pillar.mirror = true;
      setRotation(Pillar, 0F, 0F, 0F);
      Platform = new ModelRenderer(this, 0, 0);
      Platform.addBox(0F, 0F, 0F, 16, 2, 16);
      Platform.setRotationPoint(-8F, 11F, -8F);
      Platform.setTextureSize(64, 32);
      Platform.mirror = true;
      setRotation(Platform, 0F, 0F, 0F);
  }
  
  public void render()
  {
	  final float scale = 1F / 16F;
	  Base.render(scale);
	  Pillar.render(scale);
	  Platform.render(scale);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	  setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  Base.render(f5);
	  Pillar.render(f5);
	  Platform.render(f5);
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