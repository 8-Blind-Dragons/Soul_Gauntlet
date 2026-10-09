package net.mcreator.soulgauntlet.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.Comparator;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class SorryCommandProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		if (BoolArgumentType.getBool(arguments, "Response") == true) {
			SoulGauntletMod.queueServerWork(20, () -> {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("<" + entity.getDisplayName().getString() + "> " + Component.translatable("Apology").getString())), false);
				SoulGauntletMod.queueServerWork(40, () -> {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal((Component.translatable("Division").getString())), false);
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component
								.literal((Component.translatable("\u00A7b").getString() + "" + ((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().getString("Prefixo") + Component.translatable("Sorrys accepted").getString())), false);
					((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putDouble("Points of anger", 2);
					SoulGauntletMod.queueServerWork(100, () -> {
						((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putBoolean("Speaking", true);
						if ((((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 1) {
							((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putDouble("Message", 0);
							SaviorStage1Procedure.execute(world, x, y, z, entity, (Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null), entity);
						} else if ((((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 2) {
							((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putDouble("Message", 10);
							SaviorButton2Procedure.execute();
						} else if ((((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 3) {
							((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putDouble("Message", 16);
							SaviorStage3Procedure.execute(world, x, y, z, entity, (Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null), entity, new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR));
						} else if ((((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 4) {
							((Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getPersistentData().putDouble("Message", 20);
							SaviorStage4Procedure.execute(world, x, y, z, entity, (Entity) world.getEntitiesOfClass(SaviorEntity.class, AABB.ofSize(new Vec3(x, y, z), 10000, 10000, 10000), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null), entity);
						}
					});
				});
			});
		}
	}
}
