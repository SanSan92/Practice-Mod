package net.sansan.tutorial.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.sansan.tutorial.block.ModBlocks;
import net.sansan.tutorial.item.ModItems;

public class ModRecipes {
	
	public static void init() {
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.cornSeed, 8), ModItems.corn);
		GameRegistry.addShapedRecipe(new ItemStack(Items.RABBIT_STEW),
				" R ",
				"CPM",
				" B ",
				'R', Items.COOKED_RABBIT, 'C', ModItems.corn, 'P', Items.BAKED_POTATO, 'M', Blocks.BROWN_MUSHROOM, 'B', Items.BOWL);
		GameRegistry.addRecipe(new ShapedOreRecipe(Items.BUCKET,
				"I I",
				" I ", 'I', "ingotCopper"));
		
		GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.7f);
	}
	

}
