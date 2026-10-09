package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.fml.loading.progress.Message;

public class GetMessageSaviorProcedure {
	public static String execute(double Message) {
		return GetMessageProcedure.execute("Savior_History", "" + Math.round(Message));
	}
}
