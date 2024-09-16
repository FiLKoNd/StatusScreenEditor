package com.filkond.sseditor

import com.filkond.sseditor.config.ProcessorConfig
import com.filkond.sseditor.service.ConfigService
import com.filkond.sseditor.service.ProcessorService
import org.bukkit.plugin.java.JavaPlugin

class StatusScreenEditor : JavaPlugin() {
    lateinit var configService: ConfigService
    lateinit var processorService: ProcessorService
    override fun onEnable() {
        saveDefaultConfig()
        saveResource("processors/town/town-processor.bsh", false)
        saveResource("processors/town/town-processor.yml", false)

        configService = ConfigService(dataFolder.absolutePath)
        processorService = ProcessorService(configService)

        server.pluginManager.registerEvents(TownyListener(processorService), this)
    }
}
