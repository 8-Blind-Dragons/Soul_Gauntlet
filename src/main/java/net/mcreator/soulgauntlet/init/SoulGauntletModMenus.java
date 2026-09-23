
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.soulgauntlet.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.soulgauntlet.world.inventory.SoulGauntletRemovePowersMenu;
import net.mcreator.soulgauntlet.world.inventory.SoulGauntletInterfaceMenu;
import net.mcreator.soulgauntlet.world.inventory.SaviorFaceMenu;
import net.mcreator.soulgauntlet.world.inventory.InterfaceVaziaMenu;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class SoulGauntletModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SoulGauntletMod.MODID);
	public static final RegistryObject<MenuType<SoulGauntletInterfaceMenu>> SOUL_GAUNTLET_INTERFACE = REGISTRY.register("soul_gauntlet_interface", () -> IForgeMenuType.create(SoulGauntletInterfaceMenu::new));
	public static final RegistryObject<MenuType<InterfaceVaziaMenu>> INTERFACE_VAZIA = REGISTRY.register("interface_vazia", () -> IForgeMenuType.create(InterfaceVaziaMenu::new));
	public static final RegistryObject<MenuType<SoulGauntletRemovePowersMenu>> SOUL_GAUNTLET_REMOVE_POWERS = REGISTRY.register("soul_gauntlet_remove_powers", () -> IForgeMenuType.create(SoulGauntletRemovePowersMenu::new));
	public static final RegistryObject<MenuType<SaviorFaceMenu>> SAVIOR_FACE = REGISTRY.register("savior_face", () -> IForgeMenuType.create(SaviorFaceMenu::new));
}
