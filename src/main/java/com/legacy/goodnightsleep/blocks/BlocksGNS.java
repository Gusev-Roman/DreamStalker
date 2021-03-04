
package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.blocks.construction.BlockGNSCobblestone;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSFence;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSOreBlock;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSPlank;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSSlab;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSStairs;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSDirt;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSFarmland;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSFlower;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSGrass;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSLeaves;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSLog;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSMushroom;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSRainbowCrop;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSSapling;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSStone;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSTallGrass;
import com.legacy.goodnightsleep.blocks.natural.ores.BlockDelusionOre;
import com.legacy.goodnightsleep.blocks.natural.ores.BlockGNSOre;
import com.legacy.goodnightsleep.blocks.util.ItemGNSSlab;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;
import com.legacy.goodnightsleep.registry.VariableConstants;
import com.legacy.goodnightsleep.world.features.WorldGenGNSTree;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public class BlocksGNS
{
	public static Block dream_grass, dream_dirt, dream_farmland, nightmare_grass;

	public static Block tall_dream_grass, tall_nightmare_grass, lolipop_bush;

	public static Block zitrite_ore, candy_ore, rainbow_ore, positite_ore, negatite_ore, necrum_ore;
	
	public static Block coal_ore, lapis_ore;
	
	public static Block candy_leaves, dream_leaves, diamond_leaves;

	public static Block candy_block, rainbow_block, positite_block, necrum_block, zitrite_block, negatite_block; 

	public static Block dead_plank, blood_plank, white_plank, dream_plank;

	public static Block dream_sapling, candy_sapling;
	
	public static Block orange_flower, cyan_flower, dead_flower, despair_mushroom, hope_mushroom;
	
	public static Block hope_mushroom_cap, despair_mushroom_cap;

	public static Block rainbow_crop;

	public static Block white_log, dream_log, blood_log, dead_log;

	public static Block delusion_stone, delusion_cobblestone;
	
	public static Block dream_fence, white_fence, dead_fence, blood_fence;
	
	public static Block dream_slab, white_slab, dead_slab, blood_slab;
	
	public static Block dead_stairs, blood_stairs, white_stairs, dream_stairs;
	
	public static Block pot_of_gold, present, rainbow;
	
	public static Block luxurious_bed, wretched_bed, strange_bed;
	
	private static IForgeRegistry<Block> iBlockRegistry;

	private static IForgeRegistry<Item> iItemRegistry;

	public static void initialization()
	{		
		if (iBlockRegistry == null || iItemRegistry == null)
		{
			return;
		}
		
		// блоки кровати не заносятся в креативтабы, для этого есть кровати-итемы!
		luxurious_bed = registerNoTab("luxurious_bed", new BlockGNSBed()).setCreativeTab(null);
		wretched_bed = registerNoTab("wretched_bed", new BlockGNSBed()).setCreativeTab(null);
		strange_bed = registerNoTab("strange_bed", new BlockGNSBed()).setCreativeTab(null);

		tall_dream_grass = register("tall_dream_grass", new BlockGNSTallGrass());
		tall_nightmare_grass = register("tall_nightmare_grass", new BlockGNSTallGrass());
		dream_grass = register("dream_grass", new BlockGNSGrass());
		dream_dirt = register("dream_dirt", new BlockGNSDirt());
		dream_farmland = register("dream_farmland", new BlockGNSFarmland()).setCreativeTab(null);
		delusion_stone = register("delusion_stone", new BlockGNSStone());
		delusion_cobblestone = register("delusion_cobblestone", new BlockGNSCobblestone());
		nightmare_grass = register("nightmare_grass", new BlockGNSGrass());
		hope_mushroom_cap = register("hope_mushroom_cap", new BlockGNSMushroom());
		despair_mushroom_cap = register("despair_mushroom_cap", new BlockGNSMushroom());
		
		candy_ore = register("candy_ore", new BlockGNSOre());
		rainbow_ore = register("rainbow_ore", new BlockGNSOre());
		positite_ore = register("positite_ore", new BlockGNSOre());
		necrum_ore = register("necrum_ore", new BlockGNSOre());
		zitrite_ore = register("zitrite_ore", new BlockGNSOre());
		negatite_ore = register("negatite_ore", new BlockGNSOre());
		
		coal_ore = register("coal_ore", new BlockDelusionOre());
		lapis_ore = register("lapis_ore", new BlockDelusionOre());
		
		dream_leaves = register("dream_leaves", new BlockGNSLeaves());
		candy_leaves = register("candy_leaves", new BlockGNSLeaves());
		diamond_leaves = register("diamond_leaves", new BlockGNSLeaves());
		
		dream_log = register("dream_log", new BlockGNSLog());
		white_log = register("white_log", new BlockGNSLog());
		dead_log = register("dead_log", new BlockGNSLog());
		blood_log = register("blood_log", new BlockGNSLog());
		
		dream_plank = register("dream_plank", new BlockGNSPlank());
		white_plank = register("white_plank", new BlockGNSPlank());
		dead_plank = register("dead_plank", new BlockGNSPlank());
		blood_plank = register("blood_plank", new BlockGNSPlank());

		dream_sapling = register("dream_sapling", new BlockGNSSapling(new WorldGenGNSTree(false, 6, BlocksGNS.dream_log.getDefaultState(), BlocksGNS.dream_leaves.getDefaultState(), false)));
		candy_sapling = register("candy_sapling", new BlockGNSSapling(new WorldGenGNSTree(false, 6, BlocksGNS.white_log.getDefaultState(), BlocksGNS.candy_leaves.getDefaultState(), false)));
		
		hope_mushroom = register("hope_mushroom", new BlockGNSFlower());
		despair_mushroom = register("despair_mushroom", new BlockGNSFlower());
		orange_flower = register("orange_flower", new BlockGNSFlower());
		cyan_flower = register("cyan_flower", new BlockGNSFlower());
		lolipop_bush = register("lolipop_bush", new BlockGNSFlower());
		dead_flower = register("dead_flower", new BlockGNSFlower());
		
		// растение не может существовать в форме итема, но отсутствие соответстувющего json-файла приводит к ошибке
		rainbow_crop = registerNoTab("rainbow_crop", new BlockGNSRainbowCrop()).setCreativeTab(null);

		dream_fence = register("dream_fence", new BlockGNSFence());
		white_fence = register("white_fence", new BlockGNSFence());
		dead_fence = register("dead_fence", new BlockGNSFence());
		blood_fence = register("blood_fence", new BlockGNSFence());
		
		dream_stairs = register("dream_stairs", new BlockGNSStairs(dream_plank.getDefaultState()));
		white_stairs = register("white_stairs", new BlockGNSStairs(white_plank.getDefaultState()));
		dead_stairs = register("dead_stairs", new BlockGNSStairs(dead_plank.getDefaultState()));
		blood_stairs = register("blood_stairs", new BlockGNSStairs(blood_plank.getDefaultState()));
		
		dream_slab = registerSlab("dream_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		white_slab = registerSlab("white_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		dead_slab = registerSlab("dead_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		blood_slab = registerSlab("blood_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		
		candy_block = register("candy_block", new BlockGNSOreBlock(Material.IRON, SoundType.METAL));
		rainbow_block = register("rainbow_block", new BlockGNSOreBlock(Material.IRON, SoundType.METAL));
		positite_block = register("positite_block", new BlockGNSOreBlock(Material.IRON, SoundType.METAL));
		necrum_block = register("necrum_block", new BlockGNSOreBlock(Material.GROUND, SoundType.GROUND));
		zitrite_block = register("zitrite_block", new BlockGNSOreBlock(Material.IRON, SoundType.METAL));
		negatite_block = register("negatite_block", new BlockGNSOreBlock(Material.IRON, SoundType.METAL));
		
		candy_ore.setHarvestLevel("pickaxe", 0);
		rainbow_ore.setHarvestLevel("pickaxe", 1);
		positite_ore.setHarvestLevel("pickaxe", 2);
		necrum_ore.setHarvestLevel("pickaxe", 0);
		zitrite_ore.setHarvestLevel("pickaxe", 1);
		negatite_ore.setHarvestLevel("pickaxe", 2);
	}
	
	public static void setItemRegistry(IForgeRegistry<Item> iItemRegistry)
	{
		BlocksGNS.iItemRegistry = iItemRegistry;
	}

	public static void setBlockRegistry(IForgeRegistry<Block> iBlockRegistry)
	{
		BlocksGNS.iBlockRegistry = iBlockRegistry;
	}
	
	public static Block register(String name, Block block)
	{
		return register(name, block, new ItemBlock(block));
	}
	
	public static Block registerNoTab(String name, Block block)
	{
		return registerNoTab(name, block, new ItemBlock(block));
	}
	
	public static Block registerSlab(String name, Block block, CreativeTabs tab)
	{
		return registerSlab(name, block, new ItemGNSSlab(block), tab);
	}

	public static Block register(String name, Block block, ItemBlock item)
	{
		block.setTranslationKey(name);

		block.setRegistryName(VariableConstants.locate(name));
		item.setRegistryName(VariableConstants.locate(name));

		block.setCreativeTab(GNSCreativeTabs.blocks);
		
		iBlockRegistry.register(block);
		iItemRegistry.register(item);

		return block;
	}
	
	public static Block registerNoTab(String name, Block block, ItemBlock item)
	{
		block.setTranslationKey(name);

		block.setRegistryName(VariableConstants.locate(name));
		item.setRegistryName(VariableConstants.locate(name));

		block.setCreativeTab(null);
		
		iBlockRegistry.register(block);
		iItemRegistry.register(item);

		return block;
	}
	
	public static Block registerSlab(String name, Block block, ItemBlock item, CreativeTabs tab)
	{
		block.setTranslationKey(name);

		block.setRegistryName(VariableConstants.locate(name));
		item.setRegistryName(VariableConstants.locate(name));

		iBlockRegistry.register(block);
		iItemRegistry.register(item);

		block.setCreativeTab(tab);

		return block;
	}
}