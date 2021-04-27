package com.legacy.goodnightsleep.entities.tile;

import com.legacy.goodnightsleep.blocks.BlockGNSBed;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityStrangeBed extends TileEntity implements IInventory
{
    /**
     * тут тоже нет ни getBedType() ни setBedType()
     * TODO: имплементировать net.minecraft.inventory.IInventory
     * Обязательные к имплементации методы:
     * 	getSizeInventory() 
     *  getStackInSlot(int i)
     * 	decrStackSize(int index, int count) 
     * 	openInventory(EntityPlayer player) 
     * 	setInventorySlotContents(int index, ItemStack stack)
     * 
     * Итого: дурацкая идея с имплементацией инвентаря на сущность, требуется реализация всего набора методов!
     */
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
    	return new ItemStack(ItemsGNS.strange_bed_item, 1);
    }

    // ---------- IInventory implimentation
    @Override
	public void clear() {
        //public void clear(){     // clean current inventory (drop all stacks?)
        //slots.clear();
        return;
    }

    /* поля - это то, что должно передаваться между сторонами при работе контейнера, например, прогресс или скорость 
    какие свойства будут у кровати? Уровень? Мана?
    большинство параметров сноходца связано с игроком, а не с кроватью. у кровати можно сделать три взаимозависимых движка, меняющих свойства трансферинга
    как у модовой чаровалки. Один параметр - количество предметов для переноса. Второй - шанс на успех. По дефолту они ставятся 50/50.
    общее кол-во баллов зависит от установленных апгрейдов. Также нужно иметь абилку переносить в сон инвентарь игрока. (перенесенный не возвращается, копируется заново
    или лучше просыпаться с ломаным инструментом?)
    можно предусмотреть 
    якорить пространство. В начале будет выполняться rtp но далее возвращать на место сохранения (если не умер)
    level
    points (computed)
    qnty_qlty процент
    счетчик времени сна должен быть у игрока, а не у кровати. Если спать не хочешь, кнопка не нажмется
    рандомные чары во сне... Если чарить реальные вещи, при просыпании они сбрасываются. Особое умение - не сбрасывать статы
    */
    @Override
    public int getFieldCount(){
        return 1;
    }
    @Override
    public void	setField(int id, int value){
        return;
    }

    //  тут все понятно - количество слотов в инвентаре кровати. Пусть будет три.
    @Override
    public int getSizeInventory() {
        //return this.slots.size();
        return 3;
    }
}