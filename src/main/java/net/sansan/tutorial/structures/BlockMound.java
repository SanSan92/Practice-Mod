package net.sansan.tutorial.structures;

import java.util.LinkedList;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMound {

	private World world;

	private BlockPos pos;
	private BlockPos origin;

	private IBlockState moundBlock;

	private int xDiam;
	private int yDiam;
	private int zDiam;
	
	private LinkedList<BlockPos> blockPos;

	public BlockMound(World world, BlockPos pos, IBlockState moundBlock) {
		this.world = world;
		origin = pos;
		this.moundBlock = moundBlock;

		xDiam = 2 + new Random().nextInt(4);
		yDiam = 2 + new Random().nextInt(8);
		zDiam = 2 + new Random().nextInt(4);

		this.pos = pos.add(xDiam, yDiam, zDiam); // initialize the origin block at a top corner
		
		blockPos = new LinkedList<BlockPos>();
	}

	public void createMound() {
		this.moundRecursive();		

		int numBlocks = (xDiam*yDiam + xDiam*zDiam + yDiam*zDiam)/2;
		editMound(numBlocks);
	}


	/** Helper Methods **/
	public void moundRecursive() {

		world.setBlockState(pos, moundBlock); // set a block down at pos

		if((pos.getZ() - origin.getZ() == 0) && (pos.getY() - origin.getY() == 0) && (pos.getX() - origin.getX() == 0)) { // if pos is back at its initial z position
			return; // we're done, so just return the pos
		} else if((pos.getY() - origin.getY() == 0) && (pos.getX() - origin.getX() == 0)) { // if pos is back at its initial y position
			pos = pos.add(xDiam, yDiam, -1);// bring the depth over one and reset the height
		} else if(pos.getX() - origin.getX() == 0) { // if pos is back at its initial x position
			pos = pos.add(xDiam, -1, 0);  // bring the height down one and reset the row
		} else { // otherwise
			pos = pos.add(-1, 0, 0); // bring the block over one
		}
		
		blockPos.add(pos);

		moundRecursive(); 
	}

	public void editMound(int numBlocks) {
		
		BlockPos newBlock = origin;

		int startPos = 0;
		boolean lower = new Random().nextBoolean();
		boolean addBlock = new Random().nextBoolean();
		
		int xyzChoice = new Random().nextInt(3);
		boolean xConst = false;
		boolean yConst = false;
		boolean zConst = false;
		
		switch(xyzChoice) {
		case 0:
			xConst = true;
			startPos = xDiam + 1;
			break;
		case 1:
			yConst = true;
			startPos = yDiam + 1;
			break;
		case 2:
			zConst = true;
			startPos = zDiam + 1;
			break;
		}

		// Upper section means going in the positive xyz direction
		// Lower section means going in the negative xyz direction
		if(lower) { // if it's on the lower section
			if(addBlock) { // and we are adding a solid block
				startPos = -1; // set the starting position to be one less than the origin
			} else {
				startPos = 0; // make sure it's starting at the origin
			}
		} else if(!addBlock) { // if it's on the upper section and we are not adding a solid block
			startPos--; // we only need to subtract 1 from the position the block was going to be placed at
		}
		
			// Adds blocks on the upper half addedBlocks times
			if(xConst) { // if an x edge is chosen to be constant
				newBlock = newBlock.add(startPos, new Random().nextInt(yDiam + 1), new Random().nextInt(zDiam + 1));
				// move the x up to the xDiam and add one so it goes on the edge of the mound
				// make the possibility of a y block being chosen anywhere from 0 to the yDiam
				// (it has to be yDiam + 1 because otherwise the block will have a range of yDiam numbers.
				// If set to 3, it will only go up to 2 rather than at the edge of yDiam)
				// same with z.
			} else if(yConst) { // if a y edge is chosen to be constant
				newBlock = newBlock.add(new Random().nextInt(xDiam + 1), startPos, new Random().nextInt(zDiam + 1));
				// same as previous but with known y
			} else if(zConst) { // if a z edge is chosen to be constant
				newBlock = newBlock.add(new Random().nextInt(xDiam + 1), new Random().nextInt(yDiam + 1), startPos);
				// same as previous but with known z
			}
		
		if(!addBlock) {
			world.setBlockToAir(newBlock);
		} else {
			world.setBlockState(newBlock, moundBlock); 
		}

		if(numBlocks <= 0) {
			return;
		}
		else {
			editMound(numBlocks-1);
		}

	}
	
	public BlockPos getOrigin() {
		return this.origin;
	}
	
	public LinkedList<BlockPos> getBlockPos() {
		return this.blockPos;
	}

}
