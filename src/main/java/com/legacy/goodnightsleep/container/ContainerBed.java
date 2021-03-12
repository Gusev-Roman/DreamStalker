package com.legacy.goodnightsleep.container;

import net.minecraft.inventory.Container;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.inventory.*;


public class ContainerBed<T extends ITileGui> extends Container
{
	protected final @Nullable IInventory inv;	// ссылка на инвентарь игрока?

	public ContainerBed(T tile) {
		super();
		inv = tile instanceof IInventory ? (IInventory) tile : null;
	}

    @Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		if (inv != null) {
			listener.sendAllWindowProperties(this, inv);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (inv != null) {
			inv.setField(id, data);
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inv == null ? false : inv.isUsableByPlayer(player);
	}
}
