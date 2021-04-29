package com.legacy.goodnightsleep.blocks.natural;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;
import javax.swing.Icon;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// блок цельнотянутый из BlockCauldron 1.6.2, с именами полей беда
public class BlockPotOfGold extends BlockCauldron
{

	@SideOnly(Side.CLIENT)
	private Icon field_94378_a;

	@SideOnly(Side.CLIENT)
	private Icon field_94376_b;

	@SideOnly(Side.CLIENT)
	private Icon field_94377_c;

	public BlockPotOfGold()
	{
		super();
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

	public boolean func_71926_d()
	{
		return false;
	}

	public int func_71857_b()
	{
		return 24;
	}

	public boolean func_71886_c()
	{
		return false;
	}

	@SuppressWarnings("deprecation")
	public void func_71860_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		//boolean modifyX = false;
		byte modifyY = 0;
		byte modifyZ = 0;
		int var10;
		for (var10 = modifyY + 1; var10 < 20 && world.getBlockState(new BlockPos(x, y + var10, z + modifyZ)) == Blocks.AIR.getDefaultState(); ++var10)
		{
			world.setBlockState(new BlockPos(x, y + var10, z), BlocksGNS.rainbow.getStateFromMeta(0), 2);
		}
		if (world.getBlockState(new BlockPos(x, y + var10, z + modifyZ)) == Blocks.AIR.getDefaultState())
		{
			world.setBlockState(new BlockPos(x, y + var10, z), BlocksGNS.rainbow.getStateFromMeta(2), 2);
			int var11;
			for (var11 = modifyZ + 1; var11 < 40 && world.getBlockState(new BlockPos(x, y + var10, z + var11)) == Blocks.AIR.getDefaultState(); ++var11)
			{
				world.setBlockState(new BlockPos(x, y + var10, z + var11), BlocksGNS.rainbow.getStateFromMeta(2), 2);
			}
			if (world.getBlockState(new BlockPos(x, y + var10, z + var11)) == Blocks.AIR.getDefaultState())
			{
				world.setBlockState(new BlockPos(x, y + var10, z + var11), BlocksGNS.rainbow.getStateFromMeta(3), 2);
				--var10;
				while (world.getBlockState(new BlockPos(x, y + var10, z + var11)) == Blocks.AIR.getDefaultState())
				{
					world.setBlockState(new BlockPos(x, y + var10, z + var11), BlocksGNS.rainbow.getStateFromMeta(4), 2);
					--var10;
				}
			}
		}
	}

	public boolean func_71903_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		//par5EntityPlayer.addPotionEffect(new PotionEffect(10, 180, 0, true));
		return true;
	}

	public int func_71885_a(int par1, Random par2Random, int par3)
	{
		return 1;
		//return ItemGNS.itemPotOfGold.field_77779_bT;
	}

	@SideOnly(Side.CLIENT)
	public int func_71922_a(World par1World, int par2, int par3, int par4)
	{
		return 1;
		//return ItemGNS.itemPotOfGold.field_77779_bT;
	}
}
