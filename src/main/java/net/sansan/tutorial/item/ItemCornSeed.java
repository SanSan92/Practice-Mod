package net.sansan.tutorial.item;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.sansan.tutorial.TutorialMod;
import net.sansan.tutorial.block.ModBlocks;

public class ItemCornSeed extends ItemSeeds implements ItemModelProvider {

	public ItemCornSeed() {
		super(ModBlocks.cropCorn, Blocks.FARMLAND);
		setUnlocalizedName("cornSeed");
		setRegistryName("cornSeed");
		
		setCreativeTab(TutorialMod.creativeTab);
	}
	
	@Override
	public void registerItemModel(Item item) {
		TutorialMod.proxy.registerItemRenderer(item,  0, "cornSeed");
	}
	
}
