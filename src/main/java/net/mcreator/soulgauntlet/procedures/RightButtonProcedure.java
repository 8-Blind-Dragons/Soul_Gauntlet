package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.loading.progress.Message;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.soulgauntlet.init.SoulGauntletModItems;
import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.concurrent.atomic.AtomicReference;

public class RightButtonProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		double Ticks = 0;
		String Message = "";
		if ((sourceentity.getDisplayName().getString()).equals(entity.getPersistentData().getString("PlayerName"))
				|| (entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 0 && entity.getPersistentData().getBoolean("Speaking") == false) {
			entity.getPersistentData().putString("Division",
					"\u2605\u5F61\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u5F61\u2605");
			if (!(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 25, 25, 25), e -> true).isEmpty()) && entity.getPersistentData().getBoolean("Speaking") == false && entity.getPersistentData().getBoolean("Fury") == false) {
				entity.getPersistentData().putBoolean("Speaking", true);
				String[] Data = Component.translatable("Speaks out of ignorance").getString().split(",");
				SoulGauntletMod.queueServerWork(40, () -> {
					if (sourceentity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("Division"))), false);
					if (sourceentity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("§c <" + entity.getPersistentData().getString("Prefixo") + ">" + " " + Data[1]), false);
					if (entity instanceof SaviorEntity animatable)
						animatable.setTexture("saviortexture1");
					if (entity instanceof SaviorEntity) {
						((SaviorEntity) entity).setAnimation("Transform4");
					}
					SoulGauntletMod.queueServerWork(20, () -> {
						if (entity instanceof SaviorEntity) {
							((SaviorEntity) entity).setAnimation("Idle");
						}
						entity.getPersistentData().putBoolean("Fury", true);
						entity.getPersistentData().putDouble("OldStage", (entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0));
						if (entity instanceof SaviorEntity _datEntSetI)
							_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 1000);
						entity.getPersistentData().putBoolean("Speaking", false);
					});
				});
			} else {
				if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 0 && entity.getPersistentData().getBoolean("Speaking") == false) {
					entity.getPersistentData().putString("PlayerName", (sourceentity.getDisplayName().getString()));
					entity.getPersistentData().putBoolean("Speaking", true);
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
									if (entity instanceof SaviorEntity _datEntSetI)
										_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 1);
									entity.getPersistentData().putDouble("Message", 1);
									entity.getPersistentData().putBoolean("Speaking", false);
									RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
								});
							});
						});
					});
				}
				if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) >= 1 && entity.getPersistentData().getBoolean("Speaking") == false) {
					Message = GetMessageProcedure.execute("Savior_History", "" + Math.round(entity.getPersistentData().getDouble("Message")));
					entity.getPersistentData().putString("TalksMessage", Message);
					Ticks = (GetMessageProcedure.execute("Savior_History", "" + Math.round(entity.getPersistentData().getDouble("Message")))).length() + 20;
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 1 && entity.getPersistentData().getBoolean("Speaking") == false) {
						entity.getPersistentData().putBoolean("Speaking", true);
						SoulGauntletMod.queueServerWork((int) Ticks, () -> {
							if (Math.round(entity.getPersistentData().getDouble("Message")) < 11) {
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("Division"))), false);
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("TalksMessage"))), false);
								if ((entity.getPersistentData().getString("TalksMessage")).contains("Tashia Dolrein")) {
									entity.getPersistentData().putString("Prefixo", "Tashia Dolrein");
								} else {
									entity.getPersistentData().putString("Prefixo", "???");
								}
								entity.getPersistentData().putDouble("Message", (entity.getPersistentData().getDouble("Message") + 1));
								entity.getPersistentData().putBoolean("Speaking", false);
								RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
							} else {
								SoulGauntletMod.queueServerWork(40, () -> {
									if (entity instanceof SaviorEntity animatable)
										animatable.setTexture("saviortexture1");
									if (entity instanceof SaviorEntity) {
										((SaviorEntity) entity).setAnimation("Transform4");
									}
									SoulGauntletMod.queueServerWork(20, () -> {
										if (entity instanceof SaviorEntity) {
											((SaviorEntity) entity).setAnimation("Idle");
										}
										if (sourceentity instanceof ServerPlayer _serverPlayer)
											_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("soul_gauntlet:gauntlet_damaged_craft")});
										if (sourceentity instanceof Player _player) {
											ItemStack _setstack = new ItemStack(SoulGauntletModItems.GAUNTLETBLUEPRINT.get()).copy();
											_setstack.setCount(1);
											ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
										}
										if (entity instanceof SaviorEntity _datEntSetI)
											_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 2);
										entity.getPersistentData().putBoolean("Speaking", false);
									});
								});
							}
						});
					}
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 2 && entity.getPersistentData().getBoolean("Speaking") == false) {
						{
							AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
							if (_iitemhandlerref.get() != null) {
								for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
									ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
									if (itemstackiterator.getItem() == SoulGauntletModItems.GAUNTLETDAMAGED.get()) {
										entity.getPersistentData().putBoolean("Speaking", true);
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
													entity.getPersistentData().putBoolean("Speaking", false);
													if (entity instanceof SaviorEntity _datEntSetI)
														_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 3);
													RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
												});
											});
										});
									}
								}
							}
						}
					}
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 3 && entity.getPersistentData().getBoolean("Speaking") == false) {
						entity.getPersistentData().putBoolean("Speaking", true);
						SoulGauntletMod.queueServerWork((int) Ticks, () -> {
							if (Math.round(entity.getPersistentData().getDouble("Message")) < 17) {
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("Division"))), false);
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("TalksMessage"))), false);
								entity.getPersistentData().putDouble("Message", (entity.getPersistentData().getDouble("Message") + 1));
								entity.getPersistentData().putBoolean("Speaking", false);
								RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
							} else {
								if (entity instanceof SaviorEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 4);
								entity.getPersistentData().putBoolean("Speaking", false);
							}
						});
					}
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 4
							&& (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == SoulGauntletModItems.GAUNTLETDAMAGED.get() && entity.getPersistentData().getBoolean("Speaking") == false) {
						entity.getPersistentData().putBoolean("Speaking", true);
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
							if (entity instanceof SaviorEntity _datEntSetI)
								_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 5);
							entity.getPersistentData().putBoolean("Speaking", false);
							RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
						}
					}
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 5 && entity.getPersistentData().getBoolean("Speaking") == false) {
						entity.getPersistentData().putBoolean("Speaking", true);
						SoulGauntletMod.queueServerWork((int) Ticks, () -> {
							if (Math.round(entity.getPersistentData().getDouble("Message")) < 21) {
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("Division"))), false);
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("TalksMessage"))), false);
								entity.getPersistentData().putDouble("Message", (entity.getPersistentData().getDouble("Message") + 1));
								entity.getPersistentData().putBoolean("Speaking", false);
								RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
							} else {
								SoulGauntletMod.queueServerWork(60, () -> {
									if (entity instanceof SaviorEntity animatable)
										animatable.setTexture("saviortexture1");
									if (entity instanceof SaviorEntity) {
										((SaviorEntity) entity).setAnimation("Transform4");
									}
									SoulGauntletMod.queueServerWork(20, () -> {
										if (entity instanceof SaviorEntity) {
											((SaviorEntity) entity).setAnimation("Idle");
										}
										entity.getPersistentData().putBoolean("Speaking", false);
										if (entity instanceof SaviorEntity _datEntSetI)
											_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 6);
									});
								});
							}
						});
					}
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 6
							&& ((sourceentity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(SoulGauntletModItems.SOULGEM.get())) : false)
									|| (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == SoulGauntletModItems.SOULGEM.get())
							&& entity.getPersistentData().getBoolean("Speaking") == false) {
						entity.getPersistentData().putBoolean("Speaking", true);
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
									if (entity instanceof SaviorEntity _datEntSetI)
										_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 7);
									entity.getPersistentData().putBoolean("Speaking", false);
									RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
								});
							});
						});
					}
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 7 && entity.getPersistentData().getBoolean("Speaking") == false) {
						entity.getPersistentData().putBoolean("Speaking", true);
						SoulGauntletMod.queueServerWork((int) Ticks, () -> {
							if (Math.round(entity.getPersistentData().getDouble("Message")) < 30) {
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("Division"))), false);
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal((entity.getPersistentData().getString("TalksMessage"))), false);
								entity.getPersistentData().putDouble("Message", (entity.getPersistentData().getDouble("Message") + 1));
								entity.getPersistentData().putBoolean("Speaking", false);
								RightButtonProcedure.execute(world, x, y, z, entity, sourceentity, itemstack);
							} else {
								if (sourceentity instanceof ServerPlayer _serverPlayer)
									_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("soul_gauntlet:soul_gauntlet_craft")});
								SoulGauntletMod.queueServerWork(100, () -> {
									if (entity instanceof SaviorEntity animatable)
										animatable.setTexture("saviortexture1");
									if (entity instanceof SaviorEntity) {
										((SaviorEntity) entity).setAnimation("Transform4");
									}
									SoulGauntletMod.queueServerWork(20, () -> {
										if (entity instanceof SaviorEntity) {
											((SaviorEntity) entity).setAnimation("Idle");
										}
										if (entity instanceof SaviorEntity _datEntSetI)
											_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 8);
										entity.getPersistentData().putBoolean("Speaking", false);
										if (sourceentity instanceof ServerPlayer _player) {
											Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_4"));
											AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
											if (!_ap.isDone()) {
												for (String criteria : _ap.getRemainingCriteria())
													_player.getAdvancements().award(_adv, criteria);
											}
										}
									});
								});
							}
						});
					}
				}
			}
		}
	}
}
