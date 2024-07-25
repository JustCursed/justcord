package tech.funkyra.justcord

import net.minecraftforge.common.config.Configuration
import tech.funkyra.justcord.Main.cfgFile

import java.io.File

object Settings {
	private val cfg: Configuration = new Configuration(cfgFile.get)

	private[justcord] val botEnabled = cfg.getBoolean(
		"botEnabled",
		"general",
		true,
		"Should enable bot?"
	)

	private[justcord] val token = cfg.getString(
		"token",
		"general",
		"secure",
		"Token for discord bot"
	)

	private[justcord] val guildID = cfg.getString(
		"guildID",
		"general",
		"0000000000000000",
		"Working guild id"
	)

	private[justcord] val channelID = cfg.getString(
		"channelID",
		"general",
		"0000000000000000",
		"Working channel id"
	)

	if (cfg.hasChanged) cfg.save()
}
