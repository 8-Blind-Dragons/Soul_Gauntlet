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
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.client.Minecraft;

import net.mcreator.soulgauntlet.world.inventory.SaviorFaceMenu;
import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.Comparator;

import io.netty.buffer.Unpooled;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class SorryCommandProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String Message = "";
		double Ticks = 0;
		for (int index0 = 0; index0 < 20; index0++) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(" "), false);
		}
		if (BoolArgumentType.getBool(arguments, "Response") == true) {
			SoulGauntletMod.queueServerWork(20, () -> {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("<" + entity.getDisplayName().getString() + "> " + Component.translatable("Apology").getString())), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(" "), false);
				SoulGauntletMod.queueServerWork(40, () -> {
					((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putBoolean("Speaking", true);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component
								.literal((Component.translatable("\u00A7b").getString() + "" + ((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().getString("Prefixo") + Component.translatable("Sorrys accepted").getString())), false);
					SoulGauntletMod.queueServerWork(100, () -> {
						((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putBoolean("Fury", false);
						if (((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntSetI)
							_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, (int) ((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().getDouble("OldStage"));
						((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putBoolean("Speaking", false);
						if (((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntSetL)
							_datEntSetL.getEntityData().set(SaviorEntity.DATA_Reset, true);
					});
				});
			});
		} else {
			SoulGauntletMod.queueServerWork(20, () -> {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("<" + entity.getDisplayName().getString() + "> " + Component.translatable("Offend Message").getString())), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(" "), false);
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
							_player.displayClientMessage(Component
									.literal((Component.translatable("\u00A7c").getString() + "" + ((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
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
								if (entity instanceof SaviorEntity _datEntSetI)
									_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 0);
								entity.getPersistentData().putDouble("OldStage", 0);
							});
						});
					});
				});
			});
		}
	}
}
