package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.client.gui.GuiLoadingDreams;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.legacy.goodnightsleep.registry.VariableConstants;

/**
 * Открытие GUI - сугубо клиентская привилегия, поэтому регистрация данного хэндлера вызывается из клиентского прокси
 */
public class GNSClientEventHandler
{	
	private final Minecraft mc = FMLClientHandler.instance().getClient();

	@SubscribeEvent
	public void onOpenGui(GuiOpenEvent event)
	{
		if (mc.player != null && event.getGui() instanceof GuiDownloadTerrain) 
		{
			VariableConstants.log.info("*** GuiDownloadTerrain detected!");
			GuiLoadingDreams guiEnterDream = new GuiLoadingDreams(false);
			GuiLoadingDreams guiEnterNightmare = new GuiLoadingDreams(true);
			
			if (mc.player.dimension == GNSConfig.getNightmareDimensionID())
			{
				event.setGui(guiEnterNightmare);
			}
			
			if (mc.player.dimension == GNSConfig.getDreamDimensionID())
			{
				event.setGui(guiEnterDream);
			}
		}
	}
	
}