package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.soulgauntlet.init.SoulGauntletModMobEffects;
import net.mcreator.soulgauntlet.init.SoulGauntletModGameRules;
import net.mcreator.soulgauntlet.init.SoulGauntletModAttributes;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.List;
import java.util.Comparator;

public class Button1Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) <= 0) {
			itemstack.getOrCreateTag().putDouble("Kills", (itemstack.getOrCreateTag().getDouble("Kills") + 1));
			if (itemstack.getOrCreateTag().getDouble("Kills") >= 1000) {
				if (sourceentity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_7"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
			if (Mth.nextInt(RandomSource.create(), 1, 100) < (world.getLevelData().getGameRules().getInt(SoulGauntletModGameRules.STEAL_CHANCE))) {
				if ((itemstack.getOrCreateTag().getString("Slot1")).equals("")) {
					itemstack.getOrCreateTag().putString("Slot1", ("" + ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString()));
				} else if ((itemstack.getOrCreateTag().getString("Slot2")).equals("")) {
					itemstack.getOrCreateTag().putString("Slot2", ("" + ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString()));
				} else if ((itemstack.getOrCreateTag().getString("Slot3")).equals("")) {
					itemstack.getOrCreateTag().putString("Slot3", ("" + ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).toString()));
				}
				if (sourceentity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_5"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
				if (sourceentity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("CollectPowers.Success").getString())), true);
			} else {
				if (sourceentity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("CollectPowers.Weaknesses").getString())), true);
			}
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:cow")) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeAllEffects();
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:pig")) {
			if (entity instanceof Player _player)
				_player.getFoodData().setFoodLevel((int) ((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) - 2));
			if (sourceentity instanceof Player _player)
				_player.getFoodData().setFoodLevel((int) ((sourceentity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) + 2));
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:chicken")) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100,
						(int) ((1 - (entity instanceof LivingEntity _livingEntity41 && _livingEntity41.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
								? _livingEntity41.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
								: 0))
								* (sourceentity instanceof LivingEntity _livingEntity42 && _livingEntity42.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
										? _livingEntity42.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
										: 0))));
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:cave_spider")) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.ambient")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.spider.ambient")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.SCULK_CHARGE_POP, x, y, z, 50, 1, 1, 1, 0.5);
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100,
						(int) ((4 - (entity instanceof LivingEntity _livingEntity48 && _livingEntity48.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
								? _livingEntity48.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
								: 0))
								* (sourceentity instanceof LivingEntity _livingEntity49 && _livingEntity49.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
										? _livingEntity49.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
										: 0))));
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:dolphin")) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(100 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator.isInWater()) {
						if (!(entityiterator == entity)) {
							if (entityiterator instanceof Mob _entity && entity instanceof LivingEntity _ent)
								_entity.setTarget(_ent);
						}
					}
				}
			}
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:iron_golem")) {
			SoulGauntletMod.LOGGER.info("Power Golem");
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(100 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).equals("minecraft:iron_golem")) {
						if (entityiterator instanceof Mob _entity && entity instanceof LivingEntity _ent)
							_entity.setTarget(_ent);
					}
				}
			}
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:slime")) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100,
						(int) ((4 - (entity instanceof LivingEntity _livingEntity64 && _livingEntity64.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
								? _livingEntity64.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
								: 0))
								* (sourceentity instanceof LivingEntity _livingEntity65 && _livingEntity65.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
										? _livingEntity65.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
										: 0))));
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:warden")) {
			if (entity instanceof LivingEntity _livEnt69 && _livEnt69.isBaby()) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 60,
							(int) ((10 - (entity instanceof LivingEntity _livingEntity70 && _livingEntity70.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
									? _livingEntity70.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
									: 0))
									* (sourceentity instanceof LivingEntity _livingEntity71 && _livingEntity71.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
											? _livingEntity71.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
											: 0))));
			}
			if (entity.isInWaterRainOrBubble()) {
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
					entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity.isSprinting()) {
				entity.setSprinting(false);
			}
			if (entity instanceof LivingEntity _livEnt77 && _livEnt77.isBlocking()) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.HARM, 0,
							(int) ((2 - (entity instanceof LivingEntity _livingEntity78 && _livingEntity78.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
									? _livingEntity78.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
									: 0))
									* (sourceentity instanceof LivingEntity _livingEntity79 && _livingEntity79.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
											? _livingEntity79.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
											: 0))));
			}
			if (entity.isUnderWater()) {
				entity.setAirSupply((int) (entity.getAirSupply() - (1 - (sourceentity instanceof LivingEntity _livingEntity83 && _livingEntity83.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
						? _livingEntity83.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
						: 0))));
			}
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
				entity.clearFire();
			}
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:sheep")) {
			if (!(entity instanceof LivingEntity _livEnt89 && _livEnt89.getMobType() == MobType.UNDEAD)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(SoulGauntletModMobEffects.HEAVY_EYES.get(), 100,
							(int) ((3 - (entity instanceof LivingEntity _livingEntity90 && _livingEntity90.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
									? _livingEntity90.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
									: 0))
									* (sourceentity instanceof LivingEntity _livingEntity91 && _livingEntity91.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
											? _livingEntity91.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
											: 0))));
			}
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:enderman")) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100,
						(int) ((1 - (entity instanceof LivingEntity _livingEntity95 && _livingEntity95.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
								? _livingEntity95.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
								: 0))
								* (sourceentity instanceof LivingEntity _livingEntity96 && _livingEntity96.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
										? _livingEntity96.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
										: 0))));
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:rabbit")) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 100,
						(int) ((1 - (entity instanceof LivingEntity _livingEntity100 && _livingEntity100.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
								? _livingEntity100.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
								: 0))
								* (sourceentity instanceof LivingEntity _livingEntity101 && _livingEntity101.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
										? _livingEntity101.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
										: 0))));
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:bee")) {
			if ((entity.getPersistentData().getString("Allergy")).equals("bee")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.POISON, 500,
							(int) ((10 - (entity instanceof LivingEntity _livingEntity106 && _livingEntity106.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
									? _livingEntity106.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
									: 0))
									* (sourceentity instanceof LivingEntity _livingEntity107 && _livingEntity107.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
											? _livingEntity107.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
											: 0))));
				sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("soul_gauntlet:dismemberment")))), 2);
			} else {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100,
							(int) ((1 - (entity instanceof LivingEntity _livingEntity111 && _livingEntity111.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
									? _livingEntity111.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
									: 0))
									* (sourceentity instanceof LivingEntity _livingEntity112 && _livingEntity112.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
											? _livingEntity112.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
											: 0))));
				sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("soul_gauntlet:dismemberment")))), 2);
			}
		}
		if ((itemstack.getOrCreateTag().getString("Power")).equals("minecraft:turtle")) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(50 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator.isInWater()) {
						if (!(entityiterator == entity)) {
							if (entityiterator instanceof Mob _entity && entity instanceof LivingEntity _ent)
								_entity.setTarget(_ent);
						}
					}
				}
			}
		}
	}
}
