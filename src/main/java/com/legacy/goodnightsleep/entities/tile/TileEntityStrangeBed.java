package com.legacy.goodnightsleep.entities.tile;

import com.legacy.goodnightsleep.blocks.BlockGNSBed;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

// вместо IInventory использовать кастомный ITileInventory

public class TileEntityStrangeBed extends TileEntity implements IInventory
{
    private static int[] fields = {0, 0, 0, 0};
    // обязательные поля для TE
    protected World world;							// чтоб ссылаться на мир
    protected BlockPos pos = BlockPos.ORIGIN;		// коорды текущего экземпляра
    protected boolean tileEntityInvalid;
    @SuppressWarnings("unused")
	private int blockMetadata = -1;
    /** the Block type that this TileEntity is contained within */
    protected Block blockType; 						// к какому блоку отнoсится данное TE
    /**
     * тут тоже нет ни getBedType() ни setBedType()
     * имплементирован net.minecraft.inventory.IInventory
     * Обязательные к имплементации методы:
     * 	getSizeInventory() 
     *  getStackInSlot(int i)
     * 	decrStackSize(int index, int count) 
     * 	openInventory(EntityPlayer player) 
     * 	setInventorySlotContents(int index, ItemStack stack)
     * 
     * Лучшим вариантом будет создать абстрактный интерфейс, в который занести все, относящееся к инвентарю
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
    @Override
    public int getField(int id){
        return fields[id];
    }
    /**
     * Тут нужно организовать фильтрацию входящих предметов, чтобы в слот вставали только те предметы, которые нужны для работы механизма
     */
    @Override
    public boolean isItemValidForSlot(int id,ItemStack stack){
        return true;
    }
    @Override
    public void closeInventory(EntityPlayer player){
        System.out.println("*** Player closes his bed's inventory!");
    }
    @Override
    public void openInventory(EntityPlayer player){
        System.out.println("*** Player opens his bed's inventory!");
    }
    /**
     * Метод может быть использован для ограничения доступа пользователя к GUI, идентифицировать правильнее по UID. На начальном этапе защиты нет.
     */
    @Override
    public boolean isUsableByPlayer(EntityPlayer player){
        return true;
    }
    @Override
    public int getInventoryStackLimit(){
        return 1;   // only one item must be installed at any slot
    }
    @Override 
    public void setInventorySlotContents(int id,ItemStack stack){
        // тут тоже можно устраивать проверку на вилидность итемов
        // стоп! А как мы получим доступ к инвентарю, если он в классе контейнера а не в этом???
        return;
    }

    @Override
    public ItemStack removeStackFromSlot(int slot){
        return null;
    }
    // Иногда весь код, используемый во всех контейнерах, выносится в интерфейс
    @Override
    public ItemStack decrStackSize(int index,int count){
        // проверять ли count на наличие предмета? как минимум проверить содержимое слота на null
        // можно переадресовать весь этот код нужному слоту - пусть он делает работу
        // decrStackSize(int amount) слота
        // но предварительно проверим номер слота на валидность, это просто
        return null;
    }
    @Override
    public ItemStack getStackInSlot(int index){
        return null;
    }
    // когда в слоты можно будет класть предметы, придется делать проверку!
    @Override
    public boolean isEmpty(){
        return true;
    }
    // имя чего? GUI? или контейнера?
    @Override
    public boolean hasCustomName(){
        return false;
    }
    @Override
    public String getName(){
        return "container";         // если это название GUI, оно должно быть задано в каждом из производных классов!
    }
}