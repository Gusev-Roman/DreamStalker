package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.CommonProxy;
import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.renders.GNSEntityRenderingRegistry;
import com.legacy.goodnightsleep.client.renders.blocks.BlockRendering;
import com.legacy.goodnightsleep.client.renders.items.ItemRendering;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInitialization()
	{
		registerEvent(new BlockRendering());
		registerEvent(new ItemRendering());
		GNSEntityRenderingRegistry.initialize();

		registerEvent(new GNSMusicHandler());
		
		MinecraftForge.EVENT_BUS.register(new GNSClientEventHandler());
	}

	@Override
	public void initialization()
	{
	}	

	@Override
	public EntityPlayer getThePlayer()
	{
		return Minecraft.getMinecraft().player;
	}

	/**
	 * Open bed GUI on client side
	 */
	@Override
	public void openBedGui()
	{
    	Minecraft.getMinecraft().displayGuiScreen(new DreamStakerGui());
	}
}