package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.List;
import java.util.Comparator;

public class SaviorCoreProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth(100000);
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 25, 25, 25), e -> true).isEmpty()
				&& !(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 25, 25, 25), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof ServerPlayer _plr3 && _plr3.level() instanceof ServerLevel
						&& _plr3.getAdvancements().getOrStartProgress(_plr3.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_2"))).isDone())) {
			if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 25, 25, 25), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_2"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
			if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 0) {
				if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 25, 25, 25), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A74 \u00A7k" + Component.translatable("???").getString())), true);
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1000 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 25, 25, 25), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)))) {
							if (entityiterator instanceof Mob _entity && ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 25, 25, 25), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _ent)
								_entity.setTarget(_ent);
							if (entityiterator instanceof LivingEntity _livingEntity15 && _livingEntity15.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE))
								_livingEntity15.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(
										((entityiterator instanceof LivingEntity _livingEntity14 && _livingEntity14.getAttributes().hasAttribute(Attributes.ATTACK_DAMAGE) ? _livingEntity14.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() : 0)
												* 1.5));
							if (entityiterator instanceof LivingEntity _livingEntity17 && _livingEntity17.getAttributes().hasAttribute(Attributes.MAX_HEALTH))
								_livingEntity17.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
										((entityiterator instanceof LivingEntity _livingEntity16 && _livingEntity16.getAttributes().hasAttribute(Attributes.MAX_HEALTH) ? _livingEntity16.getAttribute(Attributes.MAX_HEALTH).getBaseValue() : 0) / 50));
							if (entityiterator instanceof LivingEntity _livingEntity19 && _livingEntity19.getAttributes().hasAttribute(ForgeMod.SWIM_SPEED.get()))
								_livingEntity19.getAttribute(ForgeMod.SWIM_SPEED.get()).setBaseValue(
										((entityiterator instanceof LivingEntity _livingEntity18 && _livingEntity18.getAttributes().hasAttribute(ForgeMod.SWIM_SPEED.get()) ? _livingEntity18.getAttribute(ForgeMod.SWIM_SPEED.get()).getBaseValue() : 0)
												* 1.5));
							if (entityiterator instanceof LivingEntity _livingEntity21 && _livingEntity21.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
								_livingEntity21.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(
										((entityiterator instanceof LivingEntity _livingEntity20 && _livingEntity20.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED) ? _livingEntity20.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() : 0)
												* 1.2));
						}
					}
				}
				world.getLevelData().setRaining(true);
			}
		}
		if (entity.getPersistentData().getBoolean("Fury") == true && !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty() && entity.getPersistentData().getBoolean("Speaking") == false) {
			if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)).getDisplayName().getString()).equals(entity.getPersistentData().getString("PlayerName"))) {
				entity.getPersistentData().putBoolean("Speaking", true);
				String[] Data = Component.translatable("Result of Arrogance").getString().split("#");
				if (entity instanceof SaviorEntity) {
					((SaviorEntity) entity).setAnimation("Transform1");
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("soul_gauntlet:tremblesfx")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("soul_gauntlet:tremblesfx")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
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
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("Division"))), false);
								if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal("§c <" + entity.getPersistentData().getString("Prefixo") + ">" + " " + Data[1]), false);
								SoulGauntletMod.queueServerWork(40, () -> {
									{
										Entity _ent = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null));
										if (!_ent.level().isClientSide() && _ent.getServer() != null) {
											_ent.getServer().getCommands().performPrefixedCommand(
													new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4, _ent.getName().getString(),
															_ent.getDisplayName(), _ent.level().getServer(), _ent),
													("/tellraw " + entity.getPersistentData().getString("PlayerName") + " [\"\",{\"text\":\"[" + Component.translatable("Sorry").getString()
															+ "]\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/desculpase\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\""
															+ Component.translatable("Description of Sorry").getString() + "\",\"color\":\"white\"}}},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"[" + Component.translatable("Offend").getString()
															+ "]\",\"color\":\"dark_purple\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/ofendela\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\""
															+ Component.translatable("Description of Offend").getString() + "\",\"color\":\"white\"}}}]"));
										}
									}
									SoulGauntletMod.LOGGER.info("/tellraw " + entity.getPersistentData().getString("PlayerName") + " [\"\",{\"text\":\"[" + Component.translatable("Sorry").getString()
											+ "]\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/desculpase\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\""
											+ Component.translatable("Description of Sorry").getString() + "\",\"color\":\"white\"}}},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"[" + Component.translatable("Offend").getString()
											+ "]\",\"color\":\"dark_purple\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/ofendela\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\""
											+ Component.translatable("Description of Offend").getString() + "\",\"color\":\"white\"}}}]");
								});
							});
						});
					});
				});
			}
		}
	}
}
