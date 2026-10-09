package net.mcreator.soulgauntlet.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.soulgauntlet.init.SoulGauntletModItems;
import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class RightbuttonV2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		if (((entity.getPersistentData().getString("PlayerName")).equals(sourceentity.getDisplayName().getString()) || (entity.getPersistentData().getString("PlayerName")).equals(""))
				&& entity.getPersistentData().getBoolean((sourceentity.getDisplayName().getString())) == false && entity.getPersistentData().getBoolean("Speaking") == false && entity.getPersistentData().getBoolean("Waiting_Response") == false) {
			entity.getPersistentData().putBoolean("Speaking", true);
			entity.getPersistentData().putString("PlayerName", (sourceentity.getDisplayName().getString()));
			if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 0) {
				StageControllsProcedure.execute(true, entity, "Start");
				SoulGauntletMod.queueServerWork(60, () -> {
					if (entity instanceof SaviorEntity _datEntSetI)
						_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 1);
					entity.getPersistentData().putBoolean("Speaking", false);
					RightbuttonV2Procedure.execute(world, x, y, z, entity, sourceentity, itemstack);
				});
			} else if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 1) {
				SaviorStage1Procedure.execute(world, x, y, z, sourceentity, entity, entity);
			} else if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 2) {
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == SoulGauntletModItems.GAUNTLETDAMAGED.get()) {
					StageControllsProcedure.execute(true, entity, "Start");
					SoulGauntletMod.queueServerWork(60, () -> {
						SaviorStage2Procedure.execute(world, x, y, z, sourceentity, entity, entity);
					});
				} else {
					entity.getPersistentData().putBoolean("Speaking", false);
				}
			} else if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 3) {
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == SoulGauntletModItems.GAUNTLETDAMAGED.get()) {
					SaviorStage3Procedure.execute(world, x, y, z, sourceentity, entity, entity, sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY, itemstack);
				} else {
					entity.getPersistentData().putBoolean("Speaking", false);
				}
			} else if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 4) {
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == SoulGauntletModItems.SOULGEM.get()) {
					StageControllsProcedure.execute(true, entity, "Start");
					SoulGauntletMod.queueServerWork(60, () -> {
						SaviorStage4Procedure.execute(world, x, y, z, sourceentity, entity, entity);
					});
				} else {
					entity.getPersistentData().putBoolean("Speaking", false);
				}
			}
		}
	}
}
