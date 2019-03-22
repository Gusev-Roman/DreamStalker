package com.legacy.goodnightsleep.entities.tile;

import com.legacy.goodnightsleep.blocks.BlockGNSBed;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityWretchedBed extends TileEntity
{
	
	private int bedType;

    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 11, this.getUpdateTag());
    }

    @SideOnly(Side.CLIENT)
    public boolean isHeadPiece()
    {
        return BlockGNSBed.isHeadPiece(this.getBlockMetadata());
    }

    public int getBedType()
    {
        return this.getBedType();
    }

    public void setBedType(int type)
    {
        this.bedType = type;
        this.markDirty();
    }
    
    public ItemStack getItemStack()
    {
    	return new ItemStack(ItemsGNS.wretched_bed_item, 1);
    }
}