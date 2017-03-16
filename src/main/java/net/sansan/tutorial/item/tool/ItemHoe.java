package net.sansan.tutorial.item.tool;

import net.minecraft.item.Item;
import net.sansan.tutorial.TutorialMod;
import net.sansan.tutorial.item.ItemModelProvider;

public class ItemHoe extends net.minecraft.item.ItemHoe implements ItemModelProvider {
	
	private String name;
	
	public ItemHoe(ToolMaterial material, String name) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
		
		setCreativeTab(TutorialMod.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		TutorialMod.proxy.registerItemRenderer(this, 0, name);
	}

}
