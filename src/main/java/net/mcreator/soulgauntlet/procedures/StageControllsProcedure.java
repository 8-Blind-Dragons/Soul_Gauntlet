package net.mcreator.soulgauntlet.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

public class StageControllsProcedure {
	public static void execute(boolean world, Entity Savior, String Type) {
		if (Savior == null || Type == null)
			return;
		if (world == true) {
			if ((Type).equals("Start")) {
				if (Savior instanceof SaviorEntity) {
					((SaviorEntity) Savior).setAnimation("Transform1");
				}
				{
					Entity _ent = Savior;
					if (!_ent.level().isClientSide() && _ent.getServer() != null) {
						_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
								_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "playsound soul_gauntlet:tremblesfx ambient @p");
					}
				}
				SoulGauntletMod.queueServerWork(25, () -> {
					if (Savior instanceof SaviorEntity animatable)
						animatable.setTexture("_savior2");
					SoulGauntletMod.queueServerWork(8, () -> {
						if (Savior instanceof SaviorEntity) {
							((SaviorEntity) Savior).setAnimation("Transform3");
						}
						if (Savior instanceof SaviorEntity animatable)
							animatable.setTexture("_savior3");
						SoulGauntletMod.queueServerWork(20, () -> {
							if (Savior instanceof SaviorEntity) {
								((SaviorEntity) Savior).setAnimation("IdleTransform");
							}
						});
					});
				});
			}
			if ((Type).equals("End")) {
				SoulGauntletMod.queueServerWork(40, () -> {
					if (Savior instanceof SaviorEntity animatable)
						animatable.setTexture("saviortexture1");
					if (Savior instanceof SaviorEntity) {
						((SaviorEntity) Savior).setAnimation("Transform4");
					}
					{
						Entity _ent = Savior;
						if (!_ent.level().isClientSide() && _ent.getServer() != null) {
							_ent.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, _ent.position(), _ent.getRotationVector(), _ent.level() instanceof ServerLevel ? (ServerLevel) _ent.level() : null, 4,
									_ent.getName().getString(), _ent.getDisplayName(), _ent.level().getServer(), _ent), "playsound soul_gauntlet:tremblesfx ambient @p");
						}
					}
					SoulGauntletMod.queueServerWork(20, () -> {
						if (Savior instanceof SaviorEntity) {
							((SaviorEntity) Savior).setAnimation("Idle");
						}
						Savior.getPersistentData().putBoolean("Speaking", false);
					});
				});
			}
		}
	}
}
