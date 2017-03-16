package net.sansan.tutorial.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.sansan.tutorial.TutorialMod;
import net.sansan.tutorial.item.ModItems;

public class TutorialTab extends CreativeTabs {

	public TutorialTab() {
		super(TutorialMod.modId);
		setBackgroundImageName("tutorialmod.png");
	}
	
	@Override
	public Item getTabIconItem() {
		return ModItems.ingotCopper;
	}
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}
}
