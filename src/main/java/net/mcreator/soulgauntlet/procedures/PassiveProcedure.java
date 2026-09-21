package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Difficulty;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.soulgauntlet.init.SoulGauntletModAttributes;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

public class PassiveProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		ItemStack Manopla = ItemStack.EMPTY;
		double Leites = 0;
		if (!(((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().getString("Power")).equals(""))) {
			Manopla = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:cow")) {
				if (entity instanceof LivingEntity _entity)
					_entity.removeAllEffects();
				if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
						&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem())) {
					{
						Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
						if (_enchantments.containsKey(Enchantments.SHARPNESS)) {
							_enchantments.remove(Enchantments.SHARPNESS);
							EnchantmentHelper.setEnchantments(_enchantments, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
						}
					}
				} else {
					if (Manopla.getOrCreateTag().getBoolean("Activeted") == false) {
						{
							AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
							entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
							if (_iitemhandlerref.get() != null) {
								for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
									ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
									if (itemstackiterator.getItem() == Items.MILK_BUCKET) {
										if (world.getDifficulty() == Difficulty.PEACEFUL || world.getDifficulty() == Difficulty.EASY) {
											Leites = (Leites + 2) * (entity instanceof LivingEntity _livingEntity24 && _livingEntity24.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
													? _livingEntity24.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
													: 0);
										}
										if (world.getDifficulty() == Difficulty.NORMAL) {
											Leites = (Leites + 1) * (entity instanceof LivingEntity _livingEntity26 && _livingEntity26.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
													? _livingEntity26.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
													: 0);
										}
										if (world.getDifficulty() == Difficulty.HARD) {
											Leites = (Leites + 1) * (entity instanceof LivingEntity _livingEntity28 && _livingEntity28.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
													? _livingEntity28.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
													: 0);
											if (entity instanceof Player _player) {
												ItemStack _stktoremove = new ItemStack(Items.MILK_BUCKET);
												_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
											}
										}
										if (!(world.getDifficulty() == Difficulty.PEACEFUL) && !(world.getDifficulty() == Difficulty.EASY) && !(world.getDifficulty() == Difficulty.NORMAL) && !(world.getDifficulty() == Difficulty.HARD)) {
											Leites = (Leites + 1) * (entity instanceof LivingEntity _livingEntity34 && _livingEntity34.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
													? _livingEntity34.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
													: 0);
										}
									}
								}
							}
						}
						if (Leites >= 1 && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
								&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
								&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()
								&& (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
							(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putBoolean("Activeted", true);
							(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).enchant(Enchantments.SHARPNESS, (int) Leites);
						} else {
							{
								Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
								if (_enchantments.containsKey(Enchantments.SHARPNESS)) {
									_enchantments.remove(Enchantments.SHARPNESS);
									EnchantmentHelper.setEnchantments(_enchantments, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
								}
							}
						}
					}
				}
			} else {
				{
					Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
					if (_enchantments.containsKey(Enchantments.SHARPNESS)) {
						_enchantments.remove(Enchantments.SHARPNESS);
						EnchantmentHelper.setEnchantments(_enchantments, (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
					}
				}
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putBoolean("Activeted", false);
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:pig")) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem().isEdible()
						&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.GOLDEN_CARROT)
						&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.PORKCHOP)
						&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.COOKED_PORKCHOP)) {
					if (Math.random() <= 0.05 && world instanceof ServerLevel _level67 && _level67.isVillage(BlockPos.containing(x, y, z))) {
						if (Math.random() <= 0.5 && world.getDifficulty() == Difficulty.HARD && world instanceof ServerLevel _level69 && _level69.isVillage(BlockPos.containing(x, y, z)) && world.getLevelData().isRaining()) {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE).copy();
								_setstack.setCount((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount());
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						} else {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Items.GOLDEN_CARROT).copy();
								_setstack.setCount((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount());
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						}
					} else {
						if (world.getBiome(BlockPos.containing(x, y, z)).value().getBaseTemperature() * 100f >= 150 && world instanceof Level _lvl78 && _lvl78.isDay() || ("" + entity.level().dimension()).contains("minecraft:the_nether")) {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Items.COOKED_PORKCHOP).copy();
								_setstack.setCount((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount());
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						} else {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack = new ItemStack(Items.PORKCHOP).copy();
								_setstack.setCount((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getCount());
								_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						}
					}
				}
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:chicken")) {
				if (!world.getLevelData().isRaining()) {
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20,
								(int) (1 * (entity instanceof LivingEntity _livingEntity88 && _livingEntity88.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
										? _livingEntity88.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
										: 0))));
				}
				if (world.getDifficulty() == Difficulty.PEACEFUL || new Object() {
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
					if (entity instanceof Player _player) {
						_player.getAbilities().mayfly = true;
						_player.onUpdateAbilities();
					}
				} else {
					if (entity instanceof Player _player) {
						_player.getAbilities().mayfly = false;
						_player.onUpdateAbilities();
					}
				}
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:dolphin")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 0,
							(int) (9 * (entity instanceof LivingEntity _livingEntity95 && _livingEntity95.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
									? _livingEntity95.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
									: 0))));
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.DOLPHIN, x, y, z, 5, 1, 2, 1, 0.1);
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:iron_golem")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 0,
							(int) (2 * (entity instanceof LivingEntity _livingEntity99 && _livingEntity99.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
									? _livingEntity99.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
									: 0))));
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.END_ROD, x, y, z, 5, 1, 2, 1, 0.1);
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:slime")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 0,
							(int) (4 * (entity instanceof LivingEntity _livingEntity103 && _livingEntity103.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
									? _livingEntity103.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
									: 0))));
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ITEM_SLIME, x, y, z, 5, 1, 2, 1, 0.1);
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:cave_spider")) {
				if (entity instanceof LivingEntity _livEnt107 && _livEnt107.hasEffect(MobEffects.POISON)) {
					if (entity instanceof LivingEntity _entity)
						_entity.removeEffect(MobEffects.POISON);
				}
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:warden")) {
				if (entity instanceof ServerPlayer _player) {
					Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("soul_gauntlet:soulgauntletachievement_6"));
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(20 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == entity)) {
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100,
										(int) ((4 - (entityiterator instanceof LivingEntity _livingEntity112 && _livingEntity112.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
												? _livingEntity112.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
												: 0))
												* (entity instanceof LivingEntity _livingEntity113 && _livingEntity113.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
														? _livingEntity113.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
														: 0))));
						} else {
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 0,
										(int) (1 * (entity instanceof LivingEntity _livingEntity115 && _livingEntity115.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
												? _livingEntity115.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
												: 0))));
						}
					}
				}
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:sheep")) {
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(20 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == entity)) {
							if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100,
										(int) ((1 - (entityiterator instanceof LivingEntity _livingEntity120 && _livingEntity120.getAttributes().hasAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get())
												? _livingEntity120.getAttribute(SoulGauntletModAttributes.SOULRESISTANCE.get()).getBaseValue()
												: 0))
												* (entity instanceof LivingEntity _livingEntity121 && _livingEntity121.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
														? _livingEntity121.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
														: 0))));
						}
					}
				}
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:enderman")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100,
							(int) (4 * (entity instanceof LivingEntity _livingEntity125 && _livingEntity125.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
									? _livingEntity125.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
									: 0))));
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:rabbit")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 0,
							(int) (2 * (entity instanceof LivingEntity _livingEntity128 && _livingEntity128.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
									? _livingEntity128.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
									: 0))));
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:bee")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 0,
							(int) (1 * (entity instanceof LivingEntity _livingEntity131 && _livingEntity131.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
									? _livingEntity131.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
									: 0))));
			}
			if ((Manopla.getOrCreateTag().getString("Power")).equals("minecraft:turtle")) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 0,
							(int) (1 * (entity instanceof LivingEntity _livingEntity134 && _livingEntity134.getAttributes().hasAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get())
									? _livingEntity134.getAttribute(SoulGauntletModAttributes.EVOLUTION_OF_SOULS.get()).getBaseValue()
									: 0))));
			}
		}
		return true;
	}
}
