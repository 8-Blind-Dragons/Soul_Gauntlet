package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class SaviorStage3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity Player, Entity Savior, Entity entity, ItemStack Item, ItemStack itemstack) {
		if (Player == null || Savior == null || entity == null)
			return;
		String Message = "";
		double SumMessage = 0;
		double Ticks = 0;
		if (itemstack.getOrCreateTag().getBoolean("Sacrifices") == false) {
			itemstack.getOrCreateTag().putBoolean("Sacrifices", true);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 50, 3, 3, 3, 1);
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.allay.ambient_without_item")), SoundSource.NEUTRAL, 4, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.allay.ambient_without_item")), SoundSource.NEUTRAL, 4, 1, false);
				}
			}
			SaviorStage3Procedure.execute(world, x, y, z, Player, Savior, entity, itemstack, itemstack);
		} else {
			if (Savior.getPersistentData().getDouble("Message") < 20) {
				SumMessage = SummessageProcedure.execute(Savior);
				Message = GetMessageSaviorProcedure.execute(SumMessage);
				Ticks = GetTikcsProcedure.execute(SumMessage);
				if (Player instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
				if (Player instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(Message), false);
				if (SaviorStageCoreProcedure.execute(world, x, y, z, Player, Savior, Message) == true) {
					SoulGauntletMod.queueServerWork((int) Ticks, () -> {
						SaviorStage3Procedure.execute(world, x, y, z, Player, Savior, entity, Item, itemstack);
					});
				}
			} else {
				StageControllsProcedure.execute(true, Savior, "End");
				Savior.getPersistentData().putBoolean("Speaking", false);
				if (Savior instanceof SaviorEntity _datEntSetI)
					_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 4);
			}
		}
	}
}
