package tech.funkyra.justcord

import cpw.mods.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.event.ServerChatEvent
import tech.funkyra.justcord.Discord.MessageToDisord


object MinecraftEvents {

  @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
  def onServerChat(event: ServerChatEvent): Unit = {
    val playerName = event.username
    val message = event.message
    MessageToDisord(s"$playerName: $message")
  }
}
