package com.filkond.sseditor.service

import java.io.File

class ConfigService(
    folder: String
) {
    private val processorConfigs: MutableMap<String, File> = hashMapOf(
        "town" to File(folder, "town-processor.bsh"),
        "resident" to File(folder, "resident-processor.bsh"),
        "nation" to File(folder, "nation-processor.bsh"),
        "townblock" to File(folder, "townblock-processor.bsh")
    )

    fun getProcessorConfig(id: String): File? {
        return processorConfigs[id]
    }
}