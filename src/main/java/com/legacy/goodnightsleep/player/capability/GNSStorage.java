package com.legacy.goodnightsleep.player.capability;

import com.legacy.goodnightsleep.player.PlayerGNS;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/** Похоже, что этот класс просто пока не реализован!
 * 
 */
public class GNSStorage implements IStorage<PlayerGNS>
{

	@Override
	public NBTBase writeNBT(Capability<PlayerGNS> capability, PlayerGNS instance, EnumFacing side) 
	{
		return new NBTTagCompound();	// всегда пустой!
	}

	@Override
	public void readNBT(Capability<PlayerGNS> capability, PlayerGNS instance, EnumFacing side, NBTBase nbt) 
	{
		// load from the NBT tag
	}

}