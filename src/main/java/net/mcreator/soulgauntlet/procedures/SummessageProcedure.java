package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.entity.Entity;

public class SummessageProcedure {
	public static double execute(Entity Savior) {
		if (Savior == null)
			return 0;
		String Message = "";
		double Ticks = 0;
		Savior.getPersistentData().putDouble("Message", (Savior.getPersistentData().getDouble("Message") + 1));
		return Math.round(Savior.getPersistentData().getDouble("Message"));
	}
}
