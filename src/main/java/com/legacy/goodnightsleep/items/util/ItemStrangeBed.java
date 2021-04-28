package com.legacy.goodnightsleep.items.util;

import com.legacy.goodnightsleep.blocks.BlockGNSBed;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.entities.tile.TileEntityStrangeBed;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStrangeBed extends Item
{

	public ItemStrangeBed()
	{
		this.setCreativeTab(GNSCreativeTabs.blocks);
		this.setMaxStackSize(1);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	 public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	        if (worldIn.isRemote)
	        {
	            return EnumActionResult.SUCCESS;
	        }
	        else if (facing != EnumFacing.UP)
	        {
	            return EnumActionResult.FAIL;
	        }
	        else
	        {
	            IBlockState iblockstate = worldIn.getBlockState(pos);
	            Block block = iblockstate.getBlock();
	            boolean flag = block.isReplaceable(worldIn, pos);

	            if (!flag)
	            {
	                pos = pos.up();	// если кликнули на блок, который нельзя примять, кровать ставится блоком выше!
	            }

	            int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	            EnumFacing enumfacing = EnumFacing.byHorizontalIndex(i);
	            BlockPos blockpos = pos.offset(enumfacing);		// позиция сопряженного блока
	            ItemStack itemstack = player.getHeldItem(hand);

	            if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
	            {
	                IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
	                boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);	// например, трава
	                boolean flag2 = flag || worldIn.isAirBlock(pos);						// слегка странно, что воздух не относится к заменяемым блокам
	                boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

	                if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isTopSolid() && worldIn.getBlockState(blockpos.down()).isTopSolid())
	                {
	                    IBlockState iblockstate2 = BlocksGNS.strange_bed.getDefaultState().withProperty(BlockGNSBed.FACING, enumfacing).withProperty(BlockGNSBed.PART, BlockGNSBed.EnumPartType.FOOT);
	                    worldIn.setBlockState(pos, iblockstate2, 10);	// куда кликнули - там ноги
	                    worldIn.setBlockState(blockpos, iblockstate2.withProperty(BlockGNSBed.PART, BlockGNSBed.EnumPartType.HEAD), 10);	// а блоком далее - подушка
	                    SoundType soundtype = iblockstate2.getBlock().getSoundType(iblockstate2, worldIn, pos, player);
	                    worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
	                    TileEntity tileentity = worldIn.getTileEntity(blockpos);

	                    if (tileentity instanceof TileEntityStrangeBed)	// выходит, что ТЕ наследовались от этого предка?
	                    {
	                        //((TileEntityGNSBed)tileentity).setType(true);
	                    }

	                    TileEntity tileentity1 = worldIn.getTileEntity(pos);

	                    if (tileentity1 instanceof TileEntityStrangeBed)
	                    {
	                    	//((TileEntityGNSBed)tileentity).setType(true);
	                    }

	                    worldIn.notifyNeighborsRespectDebug(pos, block, false);
	                    worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);

	                    if (player instanceof EntityPlayerMP)
	                    {
	                        CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
	                    }

	                    itemstack.shrink(1);
	                    return EnumActionResult.SUCCESS;
	                }
	                else
	                {
	                    return EnumActionResult.FAIL;
	                }
	            }
	            else
	            {
	                return EnumActionResult.FAIL;
	            }
	        }
	    }

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
}