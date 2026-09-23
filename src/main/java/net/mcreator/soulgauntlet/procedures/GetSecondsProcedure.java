package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.fml.loading.progress.Message;

public class GetSecondsProcedure {
	public static double execute(String Part, String message) {
		if (Part == null || message == null)
			return 0;
		String Message = "";
		double n = 0;
		if (message.contains("t")) {
			Message = message.replace("<P" + Part + "[t:", "");
			n = 0;
			n = Integer.parseInt(Message);
			return n * 20;
		}
		return 40;
	}
}
