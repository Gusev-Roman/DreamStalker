package com.legacy.goodnightsleep;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;

import com.legacy.goodnightsleep.capabilities.GNSCapabilityHandler;
import com.legacy.goodnightsleep.registry.VariableConstants;

import javax.annotation.Nullable;

public class CommonProxy 
{

	public void preInitialization() {
		//VariableConstants.log.info("Registering player Capabilities...");
		// сюда не попадает!
		System.out.println("*** preInitialization(): Registering GNSCapabilityHandler...");
		GNSCapabilityHandler.register();
	}

	public void initialization() { }

	// зачем??? Возвращает EntityPlayer только на клиентской стороне!
	@Nullable
	public EntityPlayer getThePlayer() { return null; }

	@SuppressWarnings("deprecation")
	public static void registerEvent(Object event)
	{
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}
}