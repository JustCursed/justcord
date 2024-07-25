package tech.funkyra.justcord

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.minecraft.server.MinecraftServer
import net.minecraft.util.IChatComponent

import java.util

object Discord {
  JDABuilder jda;
  def main(token: String): Unit = {
    jda = JDABuilder.createLight(token, util.EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))
      .addEventListeners(new DiscordEvents())
      .build()
  }

  def MessageToDiscord(text: String): Unit = {

    Main.log.info(s"[Minecraft] $text")
  }

  def MessageToMinecraft(text: String): Unit = {
    MinecraftServer.getServer.addChatMessage(IChatComponent.Serializer.func_150699_a(text))
    Main.log.info(s"[Discord] $text")
  }
}
