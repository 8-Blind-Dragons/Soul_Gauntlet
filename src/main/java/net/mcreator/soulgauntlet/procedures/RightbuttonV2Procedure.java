package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.loading.progress.Message;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import net.mcreator.soulgauntlet.init.SoulGauntletModItems;
import net.mcreator.soulgauntlet.entity.SaviorEntity;
import net.mcreator.soulgauntlet.SoulGauntletMod;

import java.util.concurrent.atomic.AtomicReference;

public class RightbuttonV2Procedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		String Message = "";
		double Ticks = 0;
		if (((entity.getPersistentData().getString("PlayerName")).equals(sourceentity.getDisplayName().getString()) || (entity.getPersistentData().getString("PlayerName")).equals(""))
				&& entity.getPersistentData().getBoolean((sourceentity.getDisplayName().getString())) == false && entity.getPersistentData().getBoolean("Speaking") == false) {
			entity.getPersistentData().putBoolean("Speaking", true);
			entity.getPersistentData().putString("PlayerName", (sourceentity.getDisplayName().getString()));
			if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 0) {
				StageControllsProcedure.execute(true, entity, "Start");
				SoulGauntletMod.queueServerWork(60, () -> {
					if (entity instanceof SaviorEntity _datEntSetI)
						_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 1);
					entity.getPersistentData().putBoolean("Speaking", false);
					RightbuttonV2Procedure.execute(world, entity, sourceentity);
				});
			} else {
				if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) >= 1) {
					entity.getPersistentData().putDouble("Message", (entity.getPersistentData().getDouble("Message") + 1));
					Message = GetMessageProcedure.execute("Savior_History", "" + Math.round(entity.getPersistentData().getDouble("Message")));
					Ticks = GetSecondsProcedure.execute("" + Math.round(entity.getPersistentData().getDouble("Message")), GetSettingsProcedure.execute("Savior_History", "" + Math.round(entity.getPersistentData().getDouble("Message"))));
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 1) {
						SaviorStage1Procedure.execute(world, sourceentity, entity, entity, sourceentity, Ticks, Message);
					}
					if ((entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 2) {
						{
							AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
							sourceentity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
							if (_iitemhandlerref.get() != null) {
								for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
									ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
									if (itemstackiterator.getItem() == SoulGauntletModItems.GAUNTLETDAMAGED.get() || (entity instanceof SaviorEntity _datEntI ? _datEntI.getEntityData().get(SaviorEntity.DATA_Stage) : 0) == 3) {
										if (entity instanceof SaviorEntity _datEntSetI)
											_datEntSetI.getEntityData().set(SaviorEntity.DATA_Stage, 3);
										entity.getPersistentData().putBoolean("Speaking", true);
										StageControllsProcedure.execute(true, entity, "Start");
										assert Boolean.TRUE; //#dbg:RightbuttonV2:IniciouOStagio2
										entity.getPersistentData().putString("Message", Message);
										SoulGauntletMod.queueServerWork(60, () -> {
											SaviorStage2Procedure.execute(world, sourceentity, entity, entity, sourceentity, entity.getPersistentData().getString("Message"));
										});
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
