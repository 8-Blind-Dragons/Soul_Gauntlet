package net.mcreator.soulgauntlet.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class SaviorResetProcedure {
	public static boolean execute(String world, Entity Savior, String Type) {
		if (world == null || Savior == null || Type == null)
			return false;
		if ((world).equals(world)) {
			if ((Type).equals("Full")) {
				if (Savior instanceof SaviorEntity _datEntSetI)
					_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 0);
				if (Savior instanceof SaviorEntity _datEntSetL)
					_datEntSetL.getEntityData().set(SaviorEntity.DATA_Reset, false);
				Savior.getPersistentData().putBoolean("Fury", false);
				Savior.getPersistentData().putBoolean("Speaking", false);
				Savior.getPersistentData().putString("PlayerName", "");
				SoulGauntletMod.queueServerWork(40, () -> {
					if (Savior instanceof SaviorEntity animatable)
						animatable.setTexture("saviortexture1");
					if (Savior instanceof SaviorEntity) {
						((SaviorEntity) Savior).setAnimation("Transform4");
					}
					SoulGauntletMod.queueServerWork(20, () -> {
						if (Savior instanceof SaviorEntity) {
							((SaviorEntity) Savior).setAnimation("Idle");
						}
						if (Savior instanceof SaviorEntity _datEntSetL)
							_datEntSetL.getEntityData().set(SaviorEntity.DATA_Reset, true);
						if (Savior instanceof SaviorEntity _datEntSetI)
							_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 0);
					});
				});
				return false;
			}
			if ((Type).equals("Fury")) {
				Savior.getPersistentData().putBoolean("Fury", false);
				Savior.getPersistentData().putBoolean("Speaking", false);
				return true;
			}
		}
		return false;
	}
}
