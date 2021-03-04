package com.legacy.goodnightsleep.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import com.legacy.goodnightsleep.GoodNightSleep;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class VariableConstants 
{

	public static final String NAME = "Good Night's Sleep";

	public static final String VERSION = "0.2.2";

	public static final String MODID = "goodnightsleep";

	public static final String CLIENT_PROXY_LOCATION = "com.legacy.goodnightsleep.client.ClientProxy";

	public static final String COMMON_PROXY_LOCATION = "com.legacy.goodnightsleep.CommonProxy";

	public static Logger log = LogManager.getLogger(GoodNightSleep.class.getName());

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}
}