package net.mcreator.soulgauntlet.procedures;

import net.minecraft.network.chat.Component;

public class MessageFilterProcedure {
	public static String execute(String Localization_Data, String Part) {
		if (Localization_Data == null || Part == null)
			return "";
		String Texto = "";
		Texto = Component.translatable(Localization_Data).getString();
		Texto = Texto.substring(Texto.indexOf(Part, 0));
		return ((Texto.replace(Texto.substring(Texto.indexOf("<Part=End>", 0)), "")).replace(Part, "")).replace(">", "");
	}
}
