package com.filkond.sseditor

import com.filkond.sseditor.command.SSECommand
import com.filkond.sseditor.ext.walkResources
import com.filkond.sseditor.service.config.SimpleConfigService
import com.filkond.sseditor.service.processor.SimpleProcessorService
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class StatusScreenEditor : JavaPlugin() {
    lateinit var configService: SimpleConfigService
    lateinit var processorService: SimpleProcessorService
    override fun onEnable() {
        saveDefaultConfig()

        walkResources("processors", 1) {
            if (!File(dataFolder, it.toString()).exists()) {
                saveResource(it.toString(), false)
            }
        }

        configService = SimpleConfigService(File(dataFolder, "processors")).also { it.load() }
        processorService = SimpleProcessorService(configService, logger).also { it.load() }

        getCommand("statusscreeneditor")!!.setExecutor(SSECommand(processorService))
        server.pluginManager.registerEvents(TownyListener(processorService, config), this)
    }
}
