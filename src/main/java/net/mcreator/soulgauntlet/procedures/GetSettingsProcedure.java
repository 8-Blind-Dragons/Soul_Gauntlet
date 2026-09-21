package net.mcreator.soulgauntlet.procedures;

import net.minecraft.network.chat.Component;

public class GetSettingsProcedure {
	public static String execute(String Localization_Data, String Part) {
		if (Localization_Data == null || Part == null)
			return "";
		return (((Component.translatable(Localization_Data).getString()).replaceAll("^.*?(?=<P" + Part + "\\[)", "")).replaceAll("<PEND>[\\s\\S]*", "")).replaceAll("]>.*", "");
	}
}
