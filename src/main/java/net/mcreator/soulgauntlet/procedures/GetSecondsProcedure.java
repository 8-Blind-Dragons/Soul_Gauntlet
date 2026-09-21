package net.mcreator.soulgauntlet.procedures;

import net.minecraftforge.fml.loading.progress.Message;

public class GetSecondsProcedure {
	public static double execute(String message) {
		if (message == null)
			return 0;
		String Message = "";
		double n = 0;
		if (message.contains("Time")) {
			Message = message;
			Message = (Message).toLowerCase();
			Message = ((Message.replaceAll(".*Time:", "")).replace("s", "")).replaceAll("^.*time:", "");
			n = 0;
			n = Integer.parseInt(Message);
			n = n * 20;
			return n;
		}
		return 40;
	}
}
