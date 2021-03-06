/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2017 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.world;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.structures.overworld.*;

public class DarklandsStructureGenerator {

	static List<WorldGenerator> structures = Lists.newArrayList((WorldGenerator)new AChouse1(), new AChouse2(), new ACscion1(), new ACscion2(),
			new StructureRitualGrounds(), new StructureCircularShrine(), new StructureCircularShrineColumns(), new StructureRitualGroundsColumns(),
			new StructureElevatedShrine(), new StructureElevatedShrineLarge());
	static List<WorldGenerator> shrines = Lists.newArrayList((WorldGenerator)new StructureCircularShrine(), new StructureCircularShrineColumns(),
			new StructureElevatedShrine(), new StructureElevatedShrineLarge());
	static List<WorldGenerator> ritual_grounds = Lists.newArrayList((WorldGenerator)new StructureRitualGrounds(), new StructureRitualGroundsColumns());
	static List<WorldGenerator> houses = Lists.newArrayList((WorldGenerator)new AChouse1(), new AChouse2());
	static List<WorldGenerator> misc = Lists.newArrayList((WorldGenerator)new ACscion1(), new ACscion2());

	public static void generate(int type, World world, Random random, BlockPos pos){
		generate(type, world, random, pos, ACBlocks.darklands_grass.getDefaultState(), (IBlockState[])null);
	}

	public static void generate(int type, World world, Random random, BlockPos pos, IBlockState spawnBlock, IBlockState...extra){
		switch(type){
		case 0:
			generateRandomStructure(structures, world, random, pos, 3, spawnBlock, extra);
			break;
		case 1:
			generateRandomStructure(shrines, world, random, pos, 3, spawnBlock, extra);
			break;
		case 2:
			generateRandomStructure(ritual_grounds, world, random, pos, 3, spawnBlock, extra);
			break;
		case 3:
			generateRandomStructure(houses, world, random, pos, 5, spawnBlock, extra);
			break;
		case 4:
			generateRandomStructure(misc, world, random, pos, 3, spawnBlock, extra);
			break;
		default:
			generateRandomStructure(structures, world, random, pos, 3, spawnBlock, extra);
			break;
		}
	}

	private static void generateRandomStructure(List<WorldGenerator> structures, World world, Random random, BlockPos pos, int bounds, IBlockState spawnBlock, IBlockState...extra){

		WorldGenerator structure = structures.get(random.nextInt(structures.size()));

		while(world.isAirBlock(pos) && pos.getY() > 2)
			pos = pos.down();
		if(!checkBlocks(world, pos, bounds, spawnBlock, extra)) return;

		if(random.nextFloat() < 0.03F)
			structure.generate(world, random, pos);
	}

	private static boolean checkBlocks(World world, BlockPos pos, int bounds, IBlockState spawnBlock, IBlockState...extra){
		if(spawnBlock == null) return true;
		if(extra != null)
			for(IBlockState state : extra)
				if(checkBlocks(world, pos, bounds, state))
					return true;

		return checkBlocks(world, pos, bounds, spawnBlock);
	}

	private static boolean checkBlocks(World world, BlockPos pos, int bounds, IBlockState state){
		boolean b = world.getBlockState(pos) != state;
		boolean b1 = world.getBlockState(pos.north(bounds)) != state;
		boolean b2 = world.getBlockState(pos.south(bounds)) != state;
		boolean b3 = world.getBlockState(pos.west(bounds)) != state;
		boolean b4 = world.getBlockState(pos.east(bounds)) != state;
		return b || b1 || b2 || b3 || b4 ? false : true;
	}
}
