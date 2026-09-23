
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.soulgauntlet.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.soulgauntlet.client.gui.SoulGauntletRemovePowersScreen;
import net.mcreator.soulgauntlet.client.gui.SoulGauntletInterfaceScreen;
import net.mcreator.soulgauntlet.client.gui.SaviorFaceScreen;
import net.mcreator.soulgauntlet.client.gui.InterfaceVaziaScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SoulGauntletModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(SoulGauntletModMenus.SOUL_GAUNTLET_INTERFACE.get(), SoulGauntletInterfaceScreen::new);
			MenuScreens.register(SoulGauntletModMenus.INTERFACE_VAZIA.get(), InterfaceVaziaScreen::new);
			MenuScreens.register(SoulGauntletModMenus.SOUL_GAUNTLET_REMOVE_POWERS.get(), SoulGauntletRemovePowersScreen::new);
			MenuScreens.register(SoulGauntletModMenus.SAVIOR_FACE.get(), SaviorFaceScreen::new);
		});
	}
}
