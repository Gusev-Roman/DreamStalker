package com.legacy.goodnightsleep.client.renders.entities;

import com.legacy.goodnightsleep.client.models.entities.ModelUnicorn;
import com.legacy.goodnightsleep.entities.dream.EntityUnicorn;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUnicorn extends RenderLiving<EntityUnicorn>
{	
    public RenderUnicorn(RenderManager manager)
    {
        super(manager, new ModelUnicorn(), 0.75F);
    }

    protected ResourceLocation getEntityTexture(EntityUnicorn entity)
    {
    	String type = entity.getUnicornType() == 1 ? "green" : entity.getUnicornType() == 2 ? "yellow" : entity.getUnicornType() == 3 ? "blue" : "pink";
        return new ResourceLocation("goodnightsleep", "textures/entities/unicorn/unicorn_" + type + ".png");
    }
}