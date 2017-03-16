package net.sansan.tutorial.structures;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMound {

	private BlockPos origin;
	private int xdiam;
	private int ydiam;
	private int zdiam;
	
	public BlockMound() {
		xdiam = 2 + new Random().nextInt(2);
		ydiam = 2 + new Random().nextInt(4);
		zdiam = 2 + new Random().nextInt(2);
	}
	
	public void createMound(World world, BlockPos pos, IBlockState moundBlock) {
		origin = pos;
		BlockPos tmpPos; // a temporary holder for the block's last known position
		
		int i;
		int j;
		int k;
		for(i = 0; i < xdiam; i++) { // Go length x
			for(j = 0; j < ydiam; j++) {  // Go length y
				for(k = 0; k < zdiam; k++) { // Go length z
					world.setBlockState(pos, moundBlock); // Place a block at these coords
					pos = pos.add(0, 0, 1); // Shift it over 1 block in the z direction
					
					tmpPos = pos;
					while(world.isAirBlock(pos.down())) { // while there is an air block below
						tmpPos = pos.add(0,-1,0);
						world.setBlockState(tmpPos, moundBlock);
					}
				}
				pos = pos.add(0,1,-k); // Reset back to the beginning z position
			}
			pos = pos.add(1,-j,0); // Reset back to the beginning y position
		}
	}
	
	public BlockPos[] safePosition() {
		
		// Check for safezones around the origin by taking in the x, y, and z diameters
		
		BlockPos[] safePos = new BlockPos[16]; // Get an array of possible positions
		// Maybe only one position is needed? TBD
		
		
		return safePos;
	}

}
