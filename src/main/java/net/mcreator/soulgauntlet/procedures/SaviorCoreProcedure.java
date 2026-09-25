package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.loading.progress.Message;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.soulgauntlet.world.inventory.SaviorFaceMenu;
import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.Comparator;

import io.netty.buffer.Unpooled;

public class SaviorCoreProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String Message = "";
		double Ticks = 0;
		Entity Player = null;
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
			if (entity.getPersistentData().getBoolean((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)).getDisplayName().getString())) == false) {
				Player = (Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null);
				if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()
						&& !(Player instanceof ServerPlayer _plr6 && _plr6.level() instanceof ServerLevel
								&& _plr6.getAdvancements().getOrStartProgress(_plr6.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_2"))).isDone())
						&& (entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 0) {
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
				if ((entity.getPersistentData().getString("PlayerName")).equals(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getDisplayName().getString())) {
					if ((entity.getPersistentData().getBoolean("Speaking") == false && entity.getPersistentData().getBoolean("Fury") == true) == true
							&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
						if (entity.getPersistentData().getBoolean("Second opportunity") == true) {
							if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty() && entity.getPersistentData().getBoolean("Speaking") == false) {
								String[] Data2 = Component.translatable("Result of Arrogance").getString().split("#");
								int Index2 = (int) (Math.random() * Data2.length);
								entity.getPersistentData().putBoolean("Speaking", true);
								SoulGauntletMod.queueServerWork(40, () -> {
									String[] Data = Component.translatable("Result of Offend").getString().split("#");
									int Index = (int) (Math.random() * Data.length);
									if (entity.getPersistentData().getBoolean("Speaking") == false) {
										((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putBoolean("Speaking", true);
										if (entity instanceof Player _player && !_player.level().isClientSide())
											_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
										if (entity instanceof Player _player && !_player.level().isClientSide())
											_player.displayClientMessage(Component.literal((Component.translatable("\u00A7c").getString() + ""
													+ ((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
														Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
															return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
														}
													}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().getString("Prefixo") + entity.getPersistentData().getString("Prefixo") + Data[Index])), false);
									}
									SoulGauntletMod.queueServerWork(100, () -> {
										((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putBoolean((entity.getDisplayName().getString()), true);
										if (new Object() {
											public boolean checkGamemode(Entity _ent) {
												if (_ent instanceof ServerPlayer _serverPlayer) {
													return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
												} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
													return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
															&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
												}
												return false;
											}
										}.checkGamemode(entity)) {
											if (entity instanceof ServerPlayer _player)
												_player.setGameMode(GameType.SURVIVAL);
											if (entity instanceof ServerPlayer _ent) {
												BlockPos _bpos = BlockPos.containing(x, y, z);
												NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
													@Override
													public Component getDisplayName() {
														return Component.literal("SaviorFace");
													}

													@Override
													public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
														return new SaviorFaceMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
													}
												}, _bpos);
											}
											SoulGauntletMod.queueServerWork(10, () -> {
												if (entity instanceof Player _player)
													_player.closeContainer();
											});
											entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), 1000);
										} else {
											entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), 1000);
										}
										SoulGauntletMod.queueServerWork(40, () -> {
											if (((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
												}
											}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity animatable)
												animatable.setTexture("saviortexture1");
											if (((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
												}
											}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity) {
												((SaviorEntity) ((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null))).setAnimation("Transform4");
											}
											SoulGauntletMod.queueServerWork(20, () -> {
												if (((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity) {
													((SaviorEntity) ((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
														Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
															return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
														}
													}.compareDistOf(x, y, z)).findFirst().orElse(null))).setAnimation("Idle");
												}
												if (((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntSetL)
													_datEntSetL.getEntityData().set(SaviorEntity.DATA_Reset, true);
												if (((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntSetI)
													_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 0);
												((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
													Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
														return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
													}
												}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putDouble("OldStage", 0);
											});
										});
									});
								});
							}
						} else {
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
															("/tellraw " + "@p" + " [\"\",{\"text\":\"[" + Component.translatable("Sorry").getString()
																	+ "]\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/sorrycommand true\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\""
																	+ Component.translatable("Description of Sorry").getString() + "\"}}},{\"text\":\" \"},{\"text\":\"[" + Component.translatable("Offend").getString()
																	+ "]\",\"color\":\"#AA00AA\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/sorrycommand false\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\""
																	+ Component.translatable("Offend Message").getString() + "\"}}}]"));
												}
											}
											entity.getPersistentData().putBoolean("Waiting_Response", true);
										});
									});
								});
							});
						}
					}
				}
			}
		}
	}
}
