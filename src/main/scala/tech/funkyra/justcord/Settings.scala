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

	private[justcord] val skinUri = cfg.getString(
		"skinUri",
		"general",
		"https://skins.mcskill.net/?name=%s&mode=5&fx=43&fy=43",
		"Uri for get head of skin. Instead of %s will player's nick"
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

	private[justcord] val banWords = cfg.getStringList(
		"BanWordsArrow",
		"general",
		new Array[String](0),
		"Blocking word list"
	)

	private[justcord] val webhooksUrl = cfg.getString(
		"webhooksUrl",
		"general",
		"https://discord.com/api/webhooks/0000000000000000/abc",
		"Webhooks Url"
	)

	if (cfg.hasChanged) cfg.save()
}
