package net.sansan.tutorial.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.sansan.tutorial.TutorialMod;
import net.sansan.tutorial.item.ModItems;

public class BlockCropCorn extends BlockCrops {

	public BlockCropCorn() {
		setUnlocalizedName("cropCorn");
		setRegistryName("cropCorn");
		
		setCreativeTab(TutorialMod.creativeTab);
	}
	
	@Override
	protected Item getSeed() {
		return ModItems.cornSeed;
	}
	
	@Override
	protected Item getCrop() {
		return ModItems.corn;
	}
	
}
