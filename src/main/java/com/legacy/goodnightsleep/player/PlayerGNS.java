package com.legacy.goodnightsleep.player;

import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.player.capability.GNSManager;
import com.legacy.goodnightsleep.world.TeleporterGNS;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// INBT отсутствует в 1.12, но есть в позднейших версиях
// здесь интерфейс описан в INBT.java
// этот класс представляет собой "Возможность" Forge
public class PlayerGNS implements INBT
{
	public EntityPlayer player;
	
	public Random rand;
	
	public Minecraft mc;
	
	public BlockPos lastBedPos;
	
	private int bedX, bedY, bedZ;
	public int ticks_elapsed;

	public PlayerGNS(EntityPlayer player)
	{
		this.player = player;
		this.lastBedPos = new BlockPos(bedX, bedY, bedZ);	// а откуда берутся эти координаты?
		this.ticks_elapsed = 0;
	}

	public static PlayerGNS get(EntityPlayer player)
	{
		// getCapability() описана в GNSProvider - возвращает PlayerGNS по EntityPlayer
		return player.getCapability(GNSManager.PLAYER, null);
	}

	// вызов этой ф-и выше ограничен сервером но все равно падает при SideOnly
	//@SideOnly(Side.SERVER)
	public void onUpdate()
	{	
		ticks_elapsed++;
		if(ticks_elapsed % 40 == 0){
			System.out.print("Sleepertime:");
			System.out.println(ticks_elapsed);
		}
	}
	
	// implements @INBT
	/**
	 * Yгроку дополнительно назначается всего лишь три интегера - координаты использованной койки */
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("bedX", this.bedX);
		compound.setInteger("bedY", this.bedY);
		compound.setInteger("bedZ", this.bedZ);
	}

	// implements @INBT
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.bedX = compound.getInteger("bedX");
		this.bedY = compound.getInteger("bedY");
		this.bedZ = compound.getInteger("bedZ");
	}

	/**
	 * Параметр не используется!
	 * перенос в хороший сон и обратно)
	 */
	public void teleportPlayer(boolean shouldSpawnPortal) 
	{
		this.player.dismountRidingEntity();
		this.player.removePassengers();

		if (this.player instanceof EntityPlayerMP)	// только для многопользовательских игр??
		{
			EntityPlayerMP player = (EntityPlayerMP) this.player;
			PlayerList scm = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();

			// если игрок во сне - его переносит в overworld, иначе - в сон
			int transferToID = player.dimension == GNSConfig.getDreamDimensionID() ? 0 : GNSConfig.getDreamDimensionID();

			scm.transferPlayerToDimension(player, transferToID, new TeleporterGNS(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(transferToID)));
		}
	}
	
	/**
	 * Перенос в страшный сон и обратно
	 */
	public void teleportPlayerNightmare(boolean shouldSpawnPortal) 
	{
		this.player.dismountRidingEntity();
		this.player.removePassengers();

		if (this.player instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) this.player;
			PlayerList scm = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();

			int transferToID = player.dimension == GNSConfig.getNightmareDimensionID() ? 0 : GNSConfig.getNightmareDimensionID();

			scm.transferPlayerToDimension(player, transferToID, new TeleporterGNS(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(transferToID)));
		}
	}

	public boolean isInBlock(Block blockID)
	{
		int x = MathHelper.floor(this.player.posX);
		int y = MathHelper.floor(this.player.posY);
		int z = MathHelper.floor(this.player.posZ);
		BlockPos pos = new BlockPos(x, y, z);

		return this.player.world.getBlockState(pos).getBlock() == blockID || this.player.world.getBlockState(pos.up()).getBlock() == blockID || this.player.world.getBlockState(pos.down()).getBlock() == blockID;
	}
}