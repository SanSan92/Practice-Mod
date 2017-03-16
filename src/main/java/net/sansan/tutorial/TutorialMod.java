package net.sansan.tutorial;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.sansan.tutorial.block.ModBlocks;
import net.sansan.tutorial.client.TutorialTab;
import net.sansan.tutorial.item.ModItems;
import net.sansan.tutorial.proxy.CommonProxy;
import net.sansan.tutorial.recipe.ModRecipes;
import net.sansan.tutorial.world.ModWorldGeneration;

@Mod(modid = TutorialMod.modId,
		name = TutorialMod.name,
		version = TutorialMod.version,
		acceptedMinecraftVersions = "[1.10.2]")
public class TutorialMod {

	// Mod Info
	public static final String modId = "tutorial";
	public static final String name = "Tutorial Mod";
	public static final String version = "1.0.0";
	
	// Creative Tabs
	public static final TutorialTab creativeTab = new TutorialTab();
	
	// Mod Items
	public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6, 2, 14);
	public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", modId + ":copper", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	
	@Mod.Instance(modId)
	public static TutorialMod instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(name + " is loading!");
		ModBlocks.init();
		ModItems.init();
		GameRegistry.registerWorldGenerator(new ModWorldGeneration(), 3);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@SidedProxy(serverSide = "net.sansan.tutorial.proxy.CommonProxy",
				clientSide = "net.sansan.tutorial.proxy.ClientProxy")
	public static CommonProxy proxy;
}
