package tech.funkyra.justcord.handlers

import cpw.mods.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.event.ServerChatEvent
import tech.funkyra.justcord.DiscordUtil.messageToDiscord
import tech.funkyra.justcord.Settings.botEnabled


object MinecraftHandler {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	def onServerChat(event: ServerChatEvent): Unit =
		if (botEnabled && !event.isCanceled) {
			messageToDiscord(event.username, event.message, s"https://mcskill.net/MineCraft/?name=${event.username}&mode=5&fx=43&fy=43")
		}
}
