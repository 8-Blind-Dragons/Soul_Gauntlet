package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class SaviorStage2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity Player, Entity Savior, Entity entity) {
		if (Player == null || Savior == null || entity == null)
			return;
		String Message = "";
		double Ticks = 0;
		double SumMessage = 0;
		if (Savior.getPersistentData().getDouble("Message") < 16) {
			SumMessage = SummessageProcedure.execute(Savior);
			Message = GetMessageSaviorProcedure.execute(SumMessage);
			Ticks = GetTikcsProcedure.execute(SumMessage);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(Message), false);
			if (SaviorStageCoreProcedure.execute(world, x, y, z, Player, Savior, Message) == true) {
				SoulGauntletMod.queueServerWork((int) Ticks, () -> {
					SaviorStage2Procedure.execute(world, x, y, z, Player, Savior, entity);
				});
			}
		} else {
			Savior.getPersistentData().putBoolean("Speaking", false);
			if (Savior instanceof SaviorEntity _datEntSetI)
				_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 3);
		}
	}
}
