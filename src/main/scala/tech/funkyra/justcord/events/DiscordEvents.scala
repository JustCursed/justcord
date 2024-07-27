package tech.funkyra.justcord.events

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import tech.funkyra.justcord.DiscordUtil.{getClient, messageToMinecraft}
import tech.funkyra.justcord.Main.log

object DiscordEvents extends ListenerAdapter {
	override def onMessageReceived(event: MessageReceivedEvent): Unit = {
		if (getClient.getSelfUser == event.getAuthor || event.isWebhookMessage) return
		messageToMinecraft(event.getAuthor.getGlobalName, event.getMessage.getContentDisplay)
	}
}
