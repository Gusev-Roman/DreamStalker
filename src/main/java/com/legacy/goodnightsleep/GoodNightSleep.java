package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.capabilities.GNSCapabilityHandler;
import com.legacy.goodnightsleep.entities.GNSEntities;
import com.legacy.goodnightsleep.entities.GNSEntityEvents;
import com.legacy.goodnightsleep.entities.tile.TileEntityRegistry;
import com.legacy.goodnightsleep.registry.GNSSmelting;
import com.legacy.goodnightsleep.registry.RegistryEventHandler;
import com.legacy.goodnightsleep.registry.VariableConstants;
import com.legacy.goodnightsleep.world.GNSOverworldWorldEvent;
import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(name = VariableConstants.NAME, version = VariableConstants.VERSION, modid = VariableConstants.MODID, updateJSON = "https://gist.githubusercontent.com/Lachney/30ab83d5fe6bd534880ba6efb8f958a3/raw/good-nights-sleep-changelog.json")
public class GoodNightSleep 
{

	@Instance(VariableConstants.MODID)
	public static GoodNightSleep instance;

	@SidedProxy(modId = VariableConstants.MODID, clientSide = VariableConstants.CLIENT_PROXY_LOCATION, serverSide = VariableConstants.COMMON_PROXY_LOCATION)
	public static CommonProxy proxy;

	/**
	 * Эвенты преинициализации, инициализации и постинициализации дропаются по одному разу, а не параллельно для серверной и клиентской частей!
	 */
	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		System.out.println("preInitialization() started...");
		GNSConfig.init(event.getModConfigurationDirectory());
		// регистрация событий регистрации, поэтому помещается в преинит
		CommonProxy.registerEvent(new RegistryEventHandler());
		// перехват событий спавна сущностей - внутри изменение их параметров (сюда же можно добавить наши фишки!?)
		CommonProxy.registerEvent(new GNSEntityEvents());
		proxy.preInitialization();		// в случае runClient - исполняются клиентские процедуры, в случае runServer - процедуры из CommonProxy
		GNSCapabilityHandler.register();
		System.out.println("preInitialization() done!");		
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		EntityRegistry.registerEgg(new ResourceLocation("minecraft:giant"), 1598464, 30652);

		GNSEntities.initialization();
		TileEntityRegistry.initialization();
		GNSWorld.initialization();
		GNSSmelting.initialization();

		proxy.initialization();
		CommonProxy.registerEvent(new GNSOverworldWorldEvent());
		CommonProxy.registerEvent(new GNSEventHandler());
	}

}