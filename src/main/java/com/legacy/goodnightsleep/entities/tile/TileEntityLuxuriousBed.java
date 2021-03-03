package com.legacy.goodnightsleep.entities.tile;

import com.legacy.goodnightsleep.blocks.BlockGNSBed;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityLuxuriousBed extends TileEntity
{

    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 11, this.getUpdateTag());
    }

    @SideOnly(Side.CLIENT)
    public boolean isHeadPiece()
    {
        return BlockGNSBed.isHeadPiece(this.getBlockMetadata());
    }

    public ItemStack getItemStack()
    {
    	return new ItemStack(ItemsGNS.luxurious_bed_item, 1);
    }

    // нет ни getBedType() ни setBedType()
}