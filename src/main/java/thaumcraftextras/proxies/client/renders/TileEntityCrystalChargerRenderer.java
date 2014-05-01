package thaumcraftextras.proxies.client.renders;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import thaumcraftextras.api.interfaces.IMagicEnergyContainerItem;
import thaumcraftextras.blocks.tiles.TileEntityMagicCrystalCharger;
import thaumcraftextras.proxies.client.models.ModelMagicCrystalCharger;

public class TileEntityCrystalChargerRenderer extends TileEntitySpecialRenderer{
	ModelMagicCrystalCharger model = new ModelMagicCrystalCharger();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float var8) {
		renderBlockModel(x, y, z);
		if(tile != null && tile instanceof TileEntityMagicCrystalCharger){
			TileEntityMagicCrystalCharger te = (TileEntityMagicCrystalCharger)tile;
			if(te.getStackInSlot(0) != null && te.getStackInSlot(0).getItem() instanceof IMagicEnergyContainerItem){
				GL11.glPushMatrix();
				GL11.glTranslated(x + 2.0D, y + 2.0D, z + 2.0D);

				EntityItem ei = new EntityItem(te.getWorldObj(), x, y, z, te.getStackInSlot(0));
				RenderManager.instance.renderEntityWithPosYaw(ei, 0, 0, 0, 0, 0);
				GL11.glPopMatrix();

			}
		}
	}
	
	public void renderBlockModel(double x, double y, double z)
	{
		GL11.glPushMatrix();
		this.bindTexture(new ResourceLocation("thaumcraft:textures/blocks/arcane_stone.png"));
		GL11.glTranslatef((float)x + 0.5F, (float)y + 2.2F, (float)z + 0.5F);
		GL11.glScaled(1, 1.5d, 1);
		GL11.glRotatef(180, 1, 0, 0);
		model.render();	
		GL11.glPopMatrix();
	}
}