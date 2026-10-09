package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class SaviorStageCoreProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity Player, Entity Savior, String Message) {
		if (Player == null || Savior == null || Message == null)
			return false;
		if (Message.contains("\u00A7c")) {
			if (Savior instanceof SaviorEntity animatable)
				animatable.setTexture("_savior4");
			if (Savior instanceof SaviorEntity) {
				((SaviorEntity) Savior).setAnimation("Tremble");
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("soul_gauntlet:tremblesfx")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("soul_gauntlet:tremblesfx")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			SoulGauntletMod.queueServerWork(20, () -> {
				if (Savior instanceof SaviorEntity animatable)
					animatable.setTexture("_savior3");
				if (Savior instanceof SaviorEntity) {
					((SaviorEntity) Savior).setAnimation("IdleTransform");
				}
			});
		}
		if (!(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) && Savior.getPersistentData().getBoolean("Speaking") == true && Savior.getPersistentData().getDouble("Points of anger") < 1) {
			String[] Data = Component.translatable("Speaks out of ignorance").getString().split(",");
			int Index = (int) (Math.random() * Data.length);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("§c" + Savior.getPersistentData().getString("Prefixo") + Data[Index]), false);
			StageControllsProcedure.execute(true, Savior, "End");
			Savior.getPersistentData().putDouble("Points of anger", 1);
			return false;
		}
		return true;
	}
}
