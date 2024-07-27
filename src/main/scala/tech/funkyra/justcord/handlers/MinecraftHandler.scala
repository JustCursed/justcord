package tech.funkyra.justcord.handlers

import com.mojang.authlib.GameProfile
import cpw.mods.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.network.play.client.C01PacketChatMessage
import net.minecraft.network.{NetHandlerPlayServer, NetworkManager}
import net.minecraft.server.MinecraftServer
import net.minecraft.server.management.ItemInWorldManager
import net.minecraft.util.{ChunkCoordinates, IChatComponent}
import net.minecraftforge.event.ServerChatEvent
import tech.funkyra.justcord.DiscordUtil.messageToDiscord
import tech.funkyra.justcord.Settings.{botEnabled, skinUri}

import java.util.UUID


object MinecraftHandler {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	def onServerChat(event: ServerChatEvent): Unit =
		if (botEnabled && !event.isCanceled) {
			messageToDiscord(event.username, event.message, skinUri format event.username)
		}

	lazy val usr: EntityPlayerMP = new EntityPlayerMP(MinecraftServer.getServer, MinecraftServer.getServer.worldServerForDimension(0), new GameProfile(UUID.fromString("cf210557-29d6-3498-ae19-dff7aa3e52e1"), "Magicord"), new ItemInWorldManager(MinecraftServer.getServer.worldServerForDimension(0))) {
		override def addChatMessage(iChatComponent: IChatComponent): Unit = {
			val p = new NetHandlerPlayServer(MinecraftServer.getServer, new NetworkManager(false), this)
			p.processChatMessage(new C01PacketChatMessage(iChatComponent.getUnformattedTextForChat))
			p
		}

		override def canCommandSenderUseCommand(i: Int, s: String): Boolean = true

		override def getPlayerCoordinates: ChunkCoordinates = new ChunkCoordinates(0, 0, 0)
	}



}
