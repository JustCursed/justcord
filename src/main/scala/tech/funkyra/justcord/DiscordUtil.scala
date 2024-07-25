package tech.funkyra.justcord

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.{JDA, JDABuilder}
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent.{GUILD_MESSAGES, MESSAGE_CONTENT}
import net.minecraft.server.MinecraftServer
import net.minecraft.util.{ChatComponentText, IChatComponent}
import tech.funkyra.justcord.Settings.{channelID, guildID, token}
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

	def messageToDiscord(text: String): Unit = {
		Main.log.info(s"[Minecraft] $text")
	}

	def messageToMinecraft(text: String): Unit = {
		MinecraftServer.getServer.addChatMessage(new ChatComponentText(text))
		Main.log.info(s"[Discord] $text")
	}
}
