package com.legacy.goodnightsleep.container;

import net.minecraft.inventory.Container;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
//import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.inventory.*;

import com.legacy.goodnightsleep.entities.tile.TileEntityStrangeBed;
//import com.legacy.goodnightsleep.entities.tile.ITileGui;


/**
 * Шаг 3: класс, наследуемый от контейнера.
 * @param <T>
 */
public class ContainerBed /*<T extends ITileGui>*/ extends Container
{
	protected final @Nullable IInventory inv;	// ссылка на инвентарь самой кроватки (без слотов игрока)

	// Конструктор на входе принимает ТЕ кроватки (но пока не известно, имплементит она инвентарь или нет)
	// далее возможно тут будет более общая формулировка, чтобы этот же класс работал и с другими кроватями
	public ContainerBed(EntityPlayer player, TileEntityStrangeBed tile) {
		super();
		inv = tile instanceof IInventory ? (IInventory) tile : null;	// проще вынуть из тайла поле типового инвентаря (но методы все равно должны быть там описаны)
	}

	/* Заполнение контейнера слотами инвентаря игрока */
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

    @Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		if (inv != null) {
			listener.sendAllWindowProperties(this, inv);
		}
	}
	/*	непонятный момент - откуда клиентский код в контейнере
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		if (inv != null) {
			inv.setField(id, data);
		}
	}
	*/
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inv == null ? false : inv.isUsableByPlayer(player);
	}
}
