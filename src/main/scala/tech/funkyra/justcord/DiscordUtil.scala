package tech.funkyra.justcord

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.entities.{Guild, WebhookClient}
import net.dv8tion.jda.api.interactions.InteractionHook
import net.dv8tion.jda.api.requests.GatewayIntent.{GUILD_MESSAGES, MESSAGE_CONTENT}
import net.dv8tion.jda.api.{JDA, JDABuilder}
import net.minecraft.server.MinecraftServer
import net.minecraft.util.ChatComponentText
import tech.funkyra.justcord.Settings.{channelID, guildID, token, webhooksUrl}
import tech.funkyra.justcord.events.DiscordEvents

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
		InteractionHook.from(getClient, webhooksUrl.split("/")[-1])


		Main.log.info(s"[Minecraft] $nickname: $message")
	}

	def messageToMinecraft(text: String): Unit = {
		MinecraftServer.getServer.addChatMessage(new ChatComponentText(text))
		Main.log.info(s"[Discord] $text")
	}
}
