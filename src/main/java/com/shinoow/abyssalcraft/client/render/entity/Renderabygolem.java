package com.shinoow.abyssalcraft.client.render.entity;

import com.shinoow.abyssalcraft.common.entity.Entityabygolem;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;


public class Renderabygolem extends RenderLiving
{
 protected ModelBiped model;
 
 private static final ResourceLocation field_110865_p = new ResourceLocation("abyssalcraft:textures/model/aby_warden.png");

 public Renderabygolem (ModelBiped ModelBiped, float f)
 {
  super(ModelBiped, f);
  model = ((ModelBiped)mainModel);
 }
 
 public void renderabygolem(Entityabygolem entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(entity, par2, par4, par6, par8, par9);
    }
 
 public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
	  renderabygolem((Entityabygolem)par1EntityLiving, par2, par4, par6, par8, par9);
    }
 
 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
	  renderabygolem((Entityabygolem)par1Entity, par2, par4, par6, par8, par9);
    }

@Override
protected ResourceLocation getEntityTexture(Entity entity) {
	
	return field_110865_p;
}
}