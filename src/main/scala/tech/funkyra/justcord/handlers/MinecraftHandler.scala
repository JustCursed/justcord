package tech.funkyra.justcord.handlers

import cpw.mods.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.event.ServerChatEvent
import tech.funkyra.justcord.DiscordUtil.messageToDiscord
import tech.funkyra.justcord.Settings.{botEnabled, skinUri}


object MinecraftHandler {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	def onServerChat(event: ServerChatEvent): Unit =
		if (botEnabled && !event.isCanceled) {
			messageToDiscord(event.username, event.message, skinUri format event.username)
		}
}
