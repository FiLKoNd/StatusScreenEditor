package com.filkond.sseditor

import com.filkond.sseditor.ext.walkResources
import com.filkond.sseditor.service.ConfigService
import com.filkond.sseditor.service.processor.SimpleProcessorService
import org.bukkit.plugin.java.JavaPlugin

class StatusScreenEditor : JavaPlugin() {
    lateinit var configService: ConfigService
    lateinit var processorService: SimpleProcessorService
    override fun onEnable() {
        saveDefaultConfig()
        walkResources("processors/", 2) {
            saveResource(it.toString(), false)
        }

        configService = ConfigService(dataFolder.absolutePath)
        processorService = SimpleProcessorService(configService, logger)

        server.pluginManager.registerEvents(TownyListener(processorService), this)
    }
}
