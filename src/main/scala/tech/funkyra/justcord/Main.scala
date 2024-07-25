package tech.funkyra.justcord

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPreInitializationEvent, FMLServerStartingEvent, FMLServerStoppedEvent, FMLServerStoppingEvent}
import net.minecraftforge.common.MinecraftForge
import org.apache.logging.log4j.{LogManager, Logger}
import tech.funkyra.justcord.DiscordUtil.{getChannel, getClient}
import tech.funkyra.justcord.Main.name
import tech.funkyra.justcord.Settings.botEnabled
import tech.funkyra.justcord.handlers.MinecraftHandler

import java.io.File

@Mod(
	name = name,
	modid = name,
	version = "0.0.0",
	modLanguage = "scala",
	useMetadata = true,
	acceptableRemoteVersions = "*"
)
object Main {
	final val name = "justcord"
	private var logger: Option[Logger] = None
	private[justcord] var cfgFile: Option[File] = None

	def log: Logger = logger.getOrElse(LogManager.getLogger(name))

	@EventHandler
	def preInit(e: FMLPreInitializationEvent): Unit = {
		logger = Some(e.getModLog)
		cfgFile = Some(e.getSuggestedConfigurationFile)

		log.info(s"$name started!")
		MinecraftForge.EVENT_BUS.register(MinecraftHandler)
	}

	@EventHandler
	def onServerStarting(e: FMLServerStartingEvent): Unit =
		if (botEnabled)
			getChannel.sendMessage("Лягушка встала").queue()

	@EventHandler
	def onServerStopped(e: FMLServerStoppingEvent): Unit =
		if (botEnabled) {
			getChannel.sendMessage("Лягушка выключилась").queue()
			getClient.shutdownNow()
		}
}
