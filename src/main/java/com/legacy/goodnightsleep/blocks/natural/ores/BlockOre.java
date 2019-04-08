/*package com.legacy.goodnightsleep.blocks.natural.ores;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockDelusionOre extends Block
{
    public BlockDelusionOre()
    {
        this(Material.ROCK.getMaterialMapColor());
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (this == Blocks.COAL_ORE)
        {
            return Items.COAL;
        }
        else if (this == Blocks.DIAMOND_ORE)
        {
            return Items.DIAMOND;
        }
        else if (this == Blocks.LAPIS_ORE)
        {
            return Items.DYE;
        }
        else if (this == Blocks.EMERALD_ORE)
        {
            return Items.EMERALD;
        }
        else
        {
            return this == Blocks.QUARTZ_ORE ? Items.QUARTZ : Item.getItemFromBlock(this);
        }
    }


    public int quantityDropped(Random random)
    {
        return this == Blocks.LAPIS_ORE ? 4 + random.nextInt(5) : 1;
    }

    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;
t
            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (this == Blocks.COAL_ORE)
            {
                i = MathHelper.getInt(rand, 0, 2);
            }
            else if (this == Blocks.DIAMOND_ORE)
            {
                i = MathHelper.getInt(rand, 3, 7);
            }
            else if (this == Blocks.EMERALD_ORE)
            {
                i = MathHelper.getInt(rand, 3, 7);
            }
            else if (this == Blocks.LAPIS_ORE)
            {
                i = MathHelper.getInt(rand, 2, 5);
            }
            else if (this == Blocks.QUARTZ_ORE)
            {
                i = MathHelper.getInt(rand, 2, 5);
            }

            return i;
        }
        return 0;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }

    public int damageDropped(IBlockState state)
    {
        return this == Blocks.LAPIS_ORE ? EnumDyeColor.BLUE.getDyeDamage() : 0;
    }
}*/