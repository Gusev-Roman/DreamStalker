package com.legacy.goodnightsleep;

import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import com.legacy.goodnightsleep.container.ContainerBed;
import com.legacy.goodnightsleep.entities.tile.TileEntityStrangeBed;

/**
 * Шаг 2. Сам хэндлер
 */
public class GuiHandler implements IGuiHandler {
    @Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        if (tile != null) {
            /*
                В зависимости от TileEntity, который активировали, возвращается тот или иной контейнер
            */
			switch (ID) {
                case 0:
                if (tile instanceof TileEntityStrangeBed) {
                    return new ContainerBed(player, (TileEntityStrangeBed) tile);
                }                
            }
        }
        return null;
    }

    @Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if (tile != null) {
            /*
                В зависимости (от чего??) возвращается ссылка на GUI-шку
            */
			switch (ID) {
            }
        }
        return null;
    }
}
