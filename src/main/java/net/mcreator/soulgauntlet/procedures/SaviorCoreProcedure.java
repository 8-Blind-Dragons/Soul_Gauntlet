package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.Comparator;

public class SaviorCoreProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String Message = "";
		double Ticks = 0;
		Entity Player = null;
		Player = (Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()
				&& !(Player instanceof ServerPlayer _plr2 && _plr2.level() instanceof ServerLevel
						&& _plr2.getAdvancements().getOrStartProgress(_plr2.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_2"))).isDone())
				&& (entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 0) {
			entity.getPersistentData().putString("PlayerName", (Player.getDisplayName().getString()));
			String[] Data = Component.translatable("Initial Call").getString().split("#");
			int Index = (int) (Math.random() * Data.length);
			if (Player instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("§c" + entity.getPersistentData().getString("Prefixo") + Data[Index]), true);
			if (Player instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_2"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
		if ((entity.getPersistentData().getBoolean("Speaking") == false && entity.getPersistentData().getBoolean("Fury")) == true && !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
			String[] Data2 = Component.translatable("Result of Arrogance").getString().split("#");
			int Index2 = (int) (Math.random() * Data2.length);
			entity.getPersistentData().putBoolean("Speaking", true);
			SoulGauntletMod.queueServerWork(25, () -> {
				if (entity instanceof SaviorEntity animatable)
					animatable.setTexture("_savior2");
				SoulGauntletMod.queueServerWork(8, () -> {
					if (entity instanceof SaviorEntity) {
						((SaviorEntity) entity).setAnimation("Transform3");
					}
					if (entity instanceof SaviorEntity animatable)
						animatable.setTexture("_savior3");
					SoulGauntletMod.queueServerWork(20, () -> {
						if (entity instanceof SaviorEntity) {
							((SaviorEntity) entity).setAnimation("IdleTransform");
						}
						SoulGauntletMod.queueServerWork(40, () -> {
							if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
							if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal("§c" + entity.getPersistentData().getString("Prefixo") + Data2[Index2]), false);
							entity.getPersistentData().putBoolean("Waiting_Response", true);
						});
					});
				});
			});
		}
	}
}
