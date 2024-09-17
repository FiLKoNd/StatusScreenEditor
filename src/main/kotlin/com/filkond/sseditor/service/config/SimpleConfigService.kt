package com.filkond.sseditor.service.config

import com.filkond.sseditor.service.Reloadable
import java.io.File

class SimpleConfigService(
    private val folder: File
) : ConfigService, Reloadable {

    init {
        load()
    }

    private val processorConfigs: MutableMap<String, File> = HashMap()

    override fun getProcessorConfig(id: String): File? = processorConfigs[id]

    private fun load() {
        processorConfigs["town"] = File(folder, "town-processor.bsh")
        processorConfigs["resident"] = File(folder, "resident-processor.bsh")
        processorConfigs["nation"] = File(folder, "nation-processor.bsh")
        processorConfigs["townblock"] = File(folder, "townblock-processor.bsh")
    }

    override fun reload() = load()
}