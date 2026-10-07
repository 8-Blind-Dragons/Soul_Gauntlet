package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class SaviorStage2Procedure {
	public static void execute(LevelAccessor world, Entity Player, Entity Savior, Entity entity, Entity sourceentity, String Message) {
		if (Player == null || Savior == null || entity == null || sourceentity == null || Message == null)
			return;
		double Ticks = 0;
		if (Math.round(Savior.getPersistentData().getDouble("Message")) < 17) {
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(Message), false);
			SoulGauntletMod.queueServerWork((int) Ticks, () -> {
				Savior.getPersistentData().putBoolean("Speaking", false);
				RightbuttonV2Procedure.execute(world, entity, sourceentity);
			});
		} else {
			StageControllsProcedure.execute(true, Savior, "End");
			if (Savior instanceof SaviorEntity _datEntSetI)
				_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 4);
		}
	}
}
