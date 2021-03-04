package com.legacy.goodnightsleep;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;

import com.legacy.goodnightsleep.capabilities.GNSCapabilityHandler;

public class CommonProxy 
{

	@Mod.EventHandler
	public void preInitialization() {
		GNSCapabilityHandler.register();
	}

	public void initialization() { }

	public EntityPlayer getThePlayer() { return null; }

	@SuppressWarnings("deprecation")
	public static void registerEvent(Object event)
	{
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}
}