package tech.funkyra.justcord

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

import java.util

object Discord {
  def main(token: String): Unit = {
    JDABuilder.createLight(token, util.EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))
      .addEventListeners(new DiscordEvents())
      .build()
  }

  def MessageToDisord(text: String): Unit = {
    Main.log.info(s"[Minecraft] $text")
  }

  def MessageToMinecraft(text: String): Unit = {
    Main.log.info(s"[Discord] $text")
  }
}
