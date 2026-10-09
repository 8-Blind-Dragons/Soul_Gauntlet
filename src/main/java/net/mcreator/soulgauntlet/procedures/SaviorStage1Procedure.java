package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.soulgauntlet.init.SoulGauntletModItems;
import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class SaviorStage1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity Player, Entity Savior, Entity entity) {
		if (Player == null || Savior == null || entity == null)
			return;
		String Message = "";
		double SumMessage = 0;
		double Ticks = 0;
		if (Savior.getPersistentData().getDouble("Message") < 10) {
			SumMessage = SummessageProcedure.execute(Savior);
			Message = GetMessageSaviorProcedure.execute(SumMessage);
			Ticks = GetTikcsProcedure.execute(SumMessage);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(Message), false);
			if (SaviorStageCoreProcedure.execute(world, x, y, z, Player, Savior, entity, Message) == true) {
				SoulGauntletMod.queueServerWork((int) Ticks, () -> {
					SaviorStage1Procedure.execute(world, x, y, z, Player, Savior, entity);
				});
			}
		} else {
			StageControllsProcedure.execute(true, Savior, "End");
			if (Savior instanceof SaviorEntity _datEntSetI)
				_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 2);
			if (Player instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("soul_gauntlet:gauntlet_damaged_craft")});
			if (Player instanceof Player _player) {
				ItemStack _setstack = new ItemStack(SoulGauntletModItems.GAUNTLETBLUEPRINT.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
