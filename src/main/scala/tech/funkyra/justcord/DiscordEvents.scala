package tech.funkyra.justcord

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import tech.funkyra.justcord.Discord.MessageToMinecraft

class DiscordEvents extends ListenerAdapter {
    override def onMessageReceived(event: MessageReceivedEvent): Unit = {
      MessageToMinecraft("%#s: %s\n".format( event.getAuthor, event.getMessage.getContentDisplay))
    }
}
