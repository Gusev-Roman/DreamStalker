package com.legacy.goodnightsleep.capabilities.player;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Класс, реализующий сохранение и восстановление данных о расширенных способностях объекта Игрок
 */

public class PlayerModifierStorage implements Capability.IStorage<IPlayerModifier> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IPlayerModifier> capability, IPlayerModifier instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setInteger("ability", instance.getAbility());
        nbt.setInteger("current_dim", instance.getCurDim());
        // здесь же нужно сохранять и восстанавливать "теневой" инвентарь игрока (а как быть с расширенным инвентарем?)

        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerModifier> capability, IPlayerModifier instance, EnumFacing side, NBTBase nbtIn) {
        if(nbtIn instanceof NBTTagCompound){
            NBTTagCompound nbt = ((NBTTagCompound) nbtIn);
            instance.setCurDim(nbt.getInteger("current_dim"));
            instance.setAbility(nbt.getInteger("ability"));
        }
    }
}
