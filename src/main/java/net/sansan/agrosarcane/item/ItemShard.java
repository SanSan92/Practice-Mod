package net.sansan.agrosarcane.item;

import java.util.LinkedList;

import net.minecraft.util.math.BlockPos;

public class ItemShard extends ItemBase {

	private String shardName;
	
	// Shard origin coordinates
	private int xOrigin;
	private int yOrigin;
	private int zOrigin;
	private LinkedList<BlockPos> blockPos; // So the shard knows what mound it came from.
	
	public ItemShard(String name) {
		super(name);
		
		this.shardName = name;
	}
	
	public ItemShard(String name, BlockPos origin, LinkedList<BlockPos> blockPos) {
		super(name);
		
		this.shardName = name;
		this.xOrigin = origin.getX();
		this.yOrigin = origin.getY();
		this.zOrigin = origin.getZ();
		
		this.blockPos = blockPos;
	}
	
	public int getxOrigin() {
		return xOrigin;
	}

	public int getyOrigin() {
		return yOrigin;
	}

	public int getzOrigin() {
		return zOrigin;
	}

	

}
