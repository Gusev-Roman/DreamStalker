package com.legacy.goodnightsleep.world;

import java.util.Random;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.player.PlayerGNS;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterGNS extends Teleporter
{
	Random random;

	public TeleporterGNS(WorldServer worldIn)
	{
		super(worldIn);

		this.random = new Random(worldIn.getSeed());
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw)
	{
		int i = MathHelper.floor(entityIn.posX);
		int k = MathHelper.floor(entityIn.posZ);
		PlayerGNS player = PlayerGNS.get((EntityPlayer) entityIn);

		if (player.lastBedPos !=null && !(entityIn.dimension == GNSConfig.getDreamDimensionID() || entityIn.dimension == GNSConfig.getNightmareDimensionID()))
		{
			entityIn.setLocationAndAngles((double) player.lastBedPos.getX(), this.world.getTopSolidOrLiquidBlock(player.lastBedPos).getY(), (double) player.lastBedPos.getZ(), entityIn.rotationYaw, 0.0F);
		}
		else
		{
			entityIn.setLocationAndAngles((double) i, this.world.getTopSolidOrLiquidBlock(new BlockPos(i, 0, k)).getY(), (double) k, entityIn.rotationYaw, 0.0F);
		}
        
		entityIn.motionX = 0.0D;
		entityIn.motionY = 0.0D;
		entityIn.motionZ = 0.0D;
	}

	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw)
	{
		return false;
	}

	@Override
	public boolean makePortal(Entity entityIn)
	{
		return false;
	}
}