package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.capabilities.player.IPlayerModifier;
import com.legacy.goodnightsleep.items.tools.ItemGNSHoe;
import com.legacy.goodnightsleep.player.PlayerGNS;
import com.legacy.goodnightsleep.player.capability.GNSProvider;
import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import static com.legacy.goodnightsleep.capabilities.GNSCapabilityHandler.*;

public class GNSEventHandler 
{	
	private static final ResourceLocation PLAYER_LOCATION = new ResourceLocation(VariableConstants.MODID, "gns_player");

	//private final Minecraft mc = FMLClientHandler.instance().getClient();

	// Это событие срабатывает!
	// при телепорте тоже ловим это событие. Осталось выяснить, откуда и куда перенесся игрок
	// возможно надо привязать к нему id последнего посещенного измерения, оно будет одним из расширенных возможностей
	// Если произошел переход явь-сон, требуется перенести инвентарь игрока в теневой и очистить на первом этапе инвентарь, включая слоты брони. 
	// Если переход сон-явь - снова очистить инвентарь (покидать на землю?) и восстановить из теневого
	// При определенном условии (кровать?) теневой инвентарь меняется местами с реальным.
	// при повышении навыка теневые предметы с опр. вероятностью переносятся в реальный инвентарь, если там есть место. 
	// предметы могут переноситься с понижением ранга - "железная" кирка становится каменной с тем же (или ниже) остатком прочности, "алмазная" - железной
	// предметы, перенесенные в сон, должны быть возвращаемыми. Предметы, обретенные во сне - только на высоком уровне способности (включая трансмутацию и порчу).
	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent event){
		if(!event.getWorld().isRemote){	// server only
			if(event.getEntity() instanceof EntityPlayer){
				EntityPlayer me = (EntityPlayer)event.getEntity();
				int dim = event.getWorld().provider.getDimension();
				System.out.println("Player [" + me.getDisplayNameString() + "] join to dimension [" + dim + "]");
				if(me instanceof IPlayerModifier) {
					if(((IPlayerModifier)me).getCurDim() != GNSConfig.getDreamDimensionID() && ((IPlayerModifier)me).getCurDim() != GNSConfig.getNightmareDimensionID()) {
						System.out.println("Welcome to reality, [" + me.getDisplayNameString() + "]");
					}
					else if(((IPlayerModifier)me).getCurDim() == GNSConfig.getDreamDimensionID() || ((IPlayerModifier)me).getCurDim() == GNSConfig.getNightmareDimensionID()) {
						System.out.println("Have a sweet dreams, [" + me.getDisplayNameString() + "]");
					}
					((IPlayerModifier)me).setCurDim(me.dimension);					
				}
				if(me.hasCapability(CAPABILITY_PLAYERMOD, null)) {
					IPlayerModifier q = me.getCapability(CAPABILITY_PLAYERMOD, null);
					System.out.println("Teleport from [" + q.getCurDim() + "] to [" + me.dimension + "]");
					q.setCurDim(me.dimension);
				}
			}
		}
	}

	/**
	 * А это событие почему-то не срабатывает при телепорте между измерениями
	 */
	@SubscribeEvent
	public void onTravelToDimensionEvent(EntityTravelToDimensionEvent event)
	{
		Entity entity = event.getEntity();
		//boolean dreamnow = false;

		 System.out.println("onTravelToDimensionEvent()");

		if (entity instanceof EntityPlayer) {
			// Итак, переход в новое измерение зафиксирован. Новое измерение - либо сон, либо любое иное. Определить это
			int dest = event.getDimension();
			// сравнить dest с id миров сновидений
			if(dest == GNSConfig.getDreamDimensionID() || dest == GNSConfig.getNightmareDimensionID()){
				//dreamnow = true;
				// начинается сон, инвентарь должен быть перемещен в теневое хранилище
				// прикол в том, что помимо базового инвентаря игрок может иметь кучу дополнительных способностей
				// определенные предметы из этого мода должны сохраняться во сне (например, таймер, отображающий время до пробуждения и дающий возможность проснуться до срока)
				// крафты сонной механики должны сочетать редкие элементы из реального и тонких миров
				// еще один класс веществ - искаженные субстанции. Формируются при начальных попытках транспортировать предметы
				// искаженные субстанции могут быть опасны для здоровья, как радиация
				// определенный вид брони должен обладать способностью перемещаться в сон (например, пижама с единорогом становится крутой броней)
				// в общем, трансмутация инвентаря - особая фишка, под которую не жаль выделить отдельный класс
				// в начальной стадии трансмутация полностью обратима.
				EntityPlayer me = (EntityPlayer)entity;
				InventoryPlayer inv = me.inventory;
				NBTTagList nbt_inv = inv.writeToNBT(null);
				System.out.println("NBT:" + nbt_inv.toString());
			}
		}
	}

	@SubscribeEvent
	public void imWake(PlayerWakeUpEvent event){
		// есть ли смысл 
		EntityPlayer player = (EntityPlayer) event.getEntityPlayer();
		// и клиент и сервер получают инфу о пробуждении
		System.out.println("player awakening!");
		PlayerGNS dude = PlayerGNS.get(player);
		dude.ticks_elapsed = 0;						// число тиков без сна
	}
	@SubscribeEvent
	public static void playerGotTick(TickEvent.PlayerTickEvent event){
		// надо определить player 
		// но возможно все необходимое делается в onPlayerUpdate
		// пока что ничего не пишет. Возможно, он не запоминает тики?
		if (event.player instanceof EntityPlayer){
			PlayerGNS dude = PlayerGNS.get(event.player);
			if(dude != null) {
				dude.ticks_elapsed++;
				if(dude.ticks_elapsed % 40 == 0){
					System.out.println("player got 40 ticks");
				}
			}
		}
	}
	@SubscribeEvent
	public void PlayerConstructingEvent(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getObject();
			GNSProvider provider = new GNSProvider(new PlayerGNS(player));

			if (PlayerGNS.get(player) == null)
			{
				event.addCapability(PLAYER_LOCATION,  provider);
			}
		}
	}
	
	// по описанию, сюда должны попадать и респавны, и возвраты из другого измерения (только из Края?)
	// если нет - как чекать телепортации между измерениями?

	@SubscribeEvent
	public void onPlayerCloned(Clone event)
	{
		PlayerGNS original = PlayerGNS.get(event.getOriginal());

		PlayerGNS clone = PlayerGNS.get(event.getEntityPlayer());

		NBTTagCompound compound = new NBTTagCompound();

		if (original != null && clone != null)
		{
			System.out.println("onPlayerCloned: both exemplars are not null!");

			// вот оно! Копирование NBT при клонировании юзера без проверки.
			original.writeEntityToNBT(compound);
			// !!! DEBUG
			// почему-то данная строка не отобразилась при перескоке из одной реальности в другую
			System.out.println("compound:"  + compound.toString());
			clone.readEntityFromNBT(compound);
		}
		else{
			System.out.println("onPlayerCloned: one of players is null!");
		}
	}

	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			// вызывать onUpdate только на стороне сервера
			Entity pl = (Entity)event.getEntityLiving();
			if(!pl.getEntityWorld().isRemote)
				PlayerGNS.get((EntityPlayer)event.getEntityLiving()).onUpdate();
		}
	}
	
	/**
	 * Вот она, механика переноса в сон!
	 * Удивительно, что имя функции может быть любым...
	 * Сюда попадает при клике правой кнопкой по любому блоку!
	 */
	@SubscribeEvent
	public void haveDream(PlayerInteractEvent.RightClickBlock event)
	{
		Block clicked = event.getWorld().getBlockState(event.getPos()).getBlock();
		// !!! DEBUG
		System.out.println("haveDream signalled!");
		if (clicked == BlocksGNS.luxurious_bed)
		{
			if (event.getEntityLiving() instanceof EntityPlayer)
			{
				if (event.getEntityLiving().dimension == 0)
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).lastBedPos = event.getEntityLiving().getPosition();
				}
				
				PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayer(true);
				// System.out.println("Entering your Dreams");
			}
		}

		if (clicked == BlocksGNS.wretched_bed)
		{
			if (event.getEntityLiving() instanceof EntityPlayer)
			{
				if (event.getEntityLiving().dimension == 0)
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).lastBedPos = event.getEntityLiving().getPosition();
				}
				
				PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayerNightmare(true);
				// System.out.println("Entering your Nightmares");
			}
		}
		
		if (clicked == BlocksGNS.strange_bed)
		{
			if (event.getEntityLiving() instanceof EntityPlayer)
			{
				if (event.getEntityLiving().dimension == 0)
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).lastBedPos = event.getEntityLiving().getPosition();
				}
				
				if (event.getWorld().rand.nextBoolean())
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayer(true);
				}
				else
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayerNightmare(true);
				}
			}
		}
		
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = event.getItemStack();
		
		
		if ((stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemGNSHoe) && world.isAirBlock(pos.up()))
		{
			Block block = state.getBlock();

            if (block == BlocksGNS.dream_grass || block == BlocksGNS.dream_dirt)
            {
            	hoeDirt(stack, player, world, pos, BlocksGNS.dream_farmland.getDefaultState(), event);
            }
		}
	}
	
	protected void hoeDirt(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, PlayerInteractEvent.RightClickBlock event)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
        
        player.swingArm(event.getHand());
    }
	
	protected void plantSeed(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);
			if (!player.capabilities.isCreativeMode)
				stack.shrink(1);
		}
	}
            
        
}