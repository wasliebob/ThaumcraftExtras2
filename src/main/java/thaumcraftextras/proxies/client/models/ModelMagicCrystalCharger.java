package thaumcraftextras.proxies.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMagicCrystalCharger extends ModelBase
{
    ModelRenderer Bottom;
    ModelRenderer Connector;
    ModelRenderer Top;
    ModelRenderer SideL;
    ModelRenderer SideR;
  
  public ModelMagicCrystalCharger()
  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      Bottom = new ModelRenderer(this, 0, 0);
	      Bottom.addBox(0F, 0F, 0F, 12, 1, 12);
	      Bottom.setRotationPoint(-6F, 23F, -7F);
	      Bottom.setTextureSize(64, 32);
	      Bottom.mirror = true;
	      setRotation(Bottom, 0F, 0F, 0F);
	      Connector = new ModelRenderer(this, 0, 0);
	      Connector.addBox(0F, 0F, 0F, 12, 10, 1);
	      Connector.setRotationPoint(-6F, 14F, 5F);
	      Connector.setTextureSize(64, 32);
	      Connector.mirror = true;
	      setRotation(Connector, 0F, 0F, 0F);
	      Top = new ModelRenderer(this, 0, 0);
	      Top.addBox(0F, 0F, 0F, 12, 1, 12);
	      Top.setRotationPoint(-6F, 14F, -7F);
	      Top.setTextureSize(64, 32);
	      Top.mirror = true;
	      setRotation(Top, 0F, 0F, 0F);
	      SideL = new ModelRenderer(this, 0, 0);
	      SideL.addBox(0F, 0F, 0F, 1, 8, 1);
	      SideL.setRotationPoint(-6F, 15F, -7F);
	      SideL.setTextureSize(64, 32);
	      SideL.mirror = true;
	      setRotation(SideL, 0F, 0F, 0F);
	      SideR = new ModelRenderer(this, 0, 0);
	      SideR.addBox(0F, 0F, 0F, 1, 8, 1);
	      SideR.setRotationPoint(5F, 15F, -7F);
	      SideR.setTextureSize(64, 32);
	      SideR.mirror = true;
	      setRotation(SideR, 0F, 0F, 0F);
  }
  
  public void render()
  {
	  final float scale = 1F / 16F;
	  Bottom.render(scale);
	  Top.render(scale);
	  Connector.render(scale);
	  SideL.render(scale);
	  SideR.render(scale);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Bottom.render(f5);
    Top.render(f5);
    Connector.render(f5);
    SideL.render(f5);
    SideR.render(f5);
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