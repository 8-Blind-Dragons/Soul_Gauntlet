package net.mcreator.soulgauntlet.procedures;

public class GetTikcsProcedure {
	public static double execute(double NumberMessage) {
		return GetSecondsProcedure.execute("" + Math.round(NumberMessage), GetSettingsProcedure.execute("Savior_History", "" + Math.round(NumberMessage)));
	}
}
