package tech.funkyra.justcord.events

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import tech.funkyra.justcord.DiscordUtil.messageToMinecraft

object DiscordEvents extends ListenerAdapter {
	override def onMessageReceived(event: MessageReceivedEvent): Unit = {
		messageToMinecraft(event.getAuthor + ": " +  event.getMessage.getContentDisplay)
	}
}
