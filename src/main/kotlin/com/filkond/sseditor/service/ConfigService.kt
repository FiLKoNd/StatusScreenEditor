package com.filkond.sseditor.service

import com.filkond.sseditor.config.ProcessorConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigService(
    folder: String
) {
    private val processorConfigs: MutableMap<String, ProcessorConfig> = hashMapOf(
        "town" to ProcessorConfig(
            YamlConfiguration.loadConfiguration(File(folder, "processors/town/town-processor.yml")),
            File(folder, "processors/town/town-processor.bsh")
        ),
        "nation" to ProcessorConfig(
            YamlConfiguration.loadConfiguration(File(folder, "processors/nation/nation-processor.yml")),
            File(folder, "processors/nation/nation-processor.bsh")
        ),
        "resident" to ProcessorConfig(
            YamlConfiguration.loadConfiguration(File(folder, "processors/resident/resident-processor.yml")),
            File(folder, "processors/resident/resident-processor.bsh")
        ),
        "townblock" to ProcessorConfig(
            YamlConfiguration.loadConfiguration(File(folder, "processors/townblock/townblock-processor.yml")),
            File(folder, "processors/townblock/townblock-processor.bsh")
        )
    )

    fun getProcessorConfig(id: String): ProcessorConfig? {
        return processorConfigs[id]
    }
}