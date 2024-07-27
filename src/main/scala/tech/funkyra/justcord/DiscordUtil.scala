package tech.funkyra.justcord

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.entities.{Guild, WebhookClient}
import net.dv8tion.jda.api.requests.GatewayIntent.{GUILD_MESSAGES, MESSAGE_CONTENT}
import net.dv8tion.jda.api.{JDA, JDABuilder}
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ChatComponentText
import tech.funkyra.justcord.Settings.{channelID, guildID, token, webhooksUrl}
import tech.funkyra.justcord.events.DiscordEvents
import tech.funkyra.justcord.handlers.MinecraftHandler.usr

import java.util

object DiscordUtil {
	private lazy final val Client: JDA = JDABuilder.createLight(token, util.EnumSet.of(GUILD_MESSAGES, MESSAGE_CONTENT))
		.addEventListeners(DiscordEvents)
		.build()
		.awaitReady()

	def getClient: JDA = Client

	def getGuild: Guild = getClient.getGuildById(guildID)

	def getChannel: TextChannel = getGuild.getTextChannelById(channelID)

	def messageToDiscord(nickname: String, message: String, skin: String): Unit = {
		WebhookClient.createClient(getClient, webhooksUrl).sendMessage(message).setAvatarUrl(skin).setUsername(nickname).queue()

		Main.log.info(s"[Minecraft] $nickname: $message")
	}

	def messageToMinecraft(nickname: String, message: String): Unit = {
		if (message.startsWith("/")) usr.addChatMessage(new ChatComponentText(message))
		else MinecraftServer.getServer.getConfigurationManager.sendChatMsg(new ChatComponentText(nickname + ": " +message))
//		Main.log.info(s"[Discord] $message")
	}
}
