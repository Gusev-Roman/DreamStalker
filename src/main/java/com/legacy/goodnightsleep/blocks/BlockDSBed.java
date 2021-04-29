package com.legacy.goodnightsleep.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDSBed extends BlockContainer {

	protected BlockDSBed(Material material, MapColor color) {
		super(material, color);									// all works to BlockContainer's constructor 
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * Called when the block is right clicked by a player.
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
        	/*
        	 // ilockablecontainer это TE
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityForceFurnace)
            {
                playerIn.openGui(dartcraftReloaded.instance, DCRGUIHandler.FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
                playerIn.addStat(StatList.FURNACE_INTERACTION);	// убрать, т.к. статистики снов не ведется. Точнее, вести свою!
            }
            -------------------------- примерно так для печки
            // Opens a GUI with this player, uses FML's IGuiHandler system.
            // кстати, ванильная печь делает не так
            
        	 */
            return true;
        }
    }

}
