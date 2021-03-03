package com.legacy.goodnightsleep.capabilities;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import com.legacy.goodnightsleep.capabilities.player.IPlayerModifier;
import com.legacy.goodnightsleep.capabilities.player.PlayerModifierFactory;
import com.legacy.goodnightsleep.capabilities.player.PlayerModifierStorage;
import com.legacy.goodnightsleep.registry.VariableConstants;

public class GNSCapabilityHandler {

    //public static final ResourceLocation BANE_CAP = new ResourceLocation(References.modId, "baneMod");
    public static final ResourceLocation PLAYER_CAP = new ResourceLocation(VariableConstants.MODID, "playerMod");
    //public static final ResourceLocation SHEAR_CAP = new ResourceLocation(References.modId, "shearable");

    /*
    @CapabilityInject(IToolModifier.class)
    public static Capability<IToolModifier> CAPABILITY_TOOLMOD = null;

    @CapabilityInject(IForceRodModifier.class)
    public static Capability<IForceRodModifier> CAPABILITY_FORCEROD = null;

    @CapabilityInject(IExperienceTome.class)
    public static Capability<IExperienceTome> CAPABILITY_EXPTOME = null;

    @CapabilityInject(IBaneModifier.class)
    public static Capability<IBaneModifier> CAPABILITY_BANE = null;
    */
    @CapabilityInject(IPlayerModifier.class)
    public static Capability<IPlayerModifier> CAPABILITY_PLAYERMOD = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(IPlayerModifier.class, new PlayerModifierStorage(), new PlayerModifierFactory());

        MinecraftForge.EVENT_BUS.register(new GNSCapabilityHandler());
    }
}