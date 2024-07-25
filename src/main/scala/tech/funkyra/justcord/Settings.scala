package tech.funkyra.justcord

import net.minecraftforge.common.config.Configuration
import tech.funkyra.justcord.Main.cfgFile

import java.io.File

object Settings {
	private val cfg: Configuration = new Configuration(cfgFile.get)

	private[justcord] val token: String = cfg.getString(
		"token",
		"general",
		"secure",
		"Token for discord bot"
	)

	private[justcord] val channelID: String = cfg.getString(
		"channelID",
		"general",
		"0000000000000000",
		"channelID for communication"
	)

	if (cfg.hasChanged) cfg.save()
}
