package com.legacy.goodnightsleep.player.capability;

import com.legacy.goodnightsleep.player.PlayerGNS;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class GNSProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{

	private final PlayerGNS playerGNS;

	public GNSProvider(PlayerGNS playerAether)
	{
		this.playerGNS = playerAether;
	}

	// implements ICapabilityProvider
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(capability == GNSManager.PLAYER) return true;
//		return super.hasCapability(capability, facing);
		return false;
	}

	// implements ICapabilityProvider
	// реализована только одна возможность - PlayerGNS
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if (capability == GNSManager.PLAYER)
		{
			return (T) this.playerGNS;
		}

		//return super.getCapability(capability, facing);
		return null;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();

		this.playerGNS.writeEntityToNBT(compound);

		return compound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound compound)
	{
		this.playerGNS.readEntityFromNBT(compound);
	}
	
}