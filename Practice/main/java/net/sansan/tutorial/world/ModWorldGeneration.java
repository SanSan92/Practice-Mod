package net.sansan.tutorial.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.sansan.tutorial.block.ModBlocks;
import net.sansan.tutorial.structures.BlockMound;

import java.util.Random;

public class ModWorldGeneration implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		
		switch(world.provider.getDimension()) {
		case -1: 
			generateNether(world, random, blockX, blockZ, chunkGenerator, chunkProvider);
			break;
		case 0:
			generateOverworld(world, random, blockX, blockZ, chunkGenerator, chunkProvider);
			break;
		case 1:
			generateEnd(world, random, blockX, blockZ, chunkGenerator, chunkProvider);
			break;
		}
		
	}
	
	private void generateNether(World world, Random rand, int blockX, int blockZ, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		// Temporarily blank
	}
	
	private void generateOverworld(World world, Random rand, int blockX, int blockZ, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		//generateOre(ModBlocks.oreCopper.getDefaultState(), world, rand, blockX, blockZ, 16, 64, 10 + rand.nextInt(4), 8);
		//generateMounds(ModBlocks.oreCopper.getDefaultState(), world, rand, blockX, blockZ, 10 + rand.nextInt(4), 8);
	}
	
	private void generateEnd(World world, Random rand, int blockX, int blockZ, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		// Temporarily blank
	}
	
	/** HELPER METHODS **/
	
	private int groundLvl(World world, int x, int z) {
		int y = 255; // Start at the world height
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0) {
			Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = ((blockAt == Blocks.DIRT) || 
						   (blockAt == Blocks.GRASS_PATH) || 
						   (blockAt == Blocks.STONE) ||
						   (blockAt == Blocks.SAND));
		}
		return y;
	}
	
	// generate Ore Spires
	private void generateMounds(IBlockState ore, World world, Random random, int x, int z, int size, int chances) {
		int minY = groundLvl(world, x, z);
		
		for(int i = 0; i < chances; i++) {
			//the central base block
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY, z + random.nextInt(16));
			
			if (world.isAirBlock(pos) &&  // If the block is positioned in an air block AND
				(!world.isAirBlock(pos.down())) &&
				(world.getBlockState(pos.down()).getBlock() != Blocks.WATER)) { // The block below it is NOT an air block
				
				new BlockMound().createMound(world, pos, ore);
				/*
				 * For now, this will set one block where the central base block is.
				 * Later, however, this will call a "Mound" class which will use the params:
				 * BlockPos pos, IBlockState ore, int x radius, int y radius, int z radius
				 * 
				 * This class will be in charge of creating the blocks. Later on, more complicated blocks can be made.
				 * These mounds will replace all air, water, lava, and grass blocks. However, they will be
				 * replaced by any "solid" block.
				 */
				
			}
		}
	}
	
	// generates Ore
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);	
		}
	}
}
