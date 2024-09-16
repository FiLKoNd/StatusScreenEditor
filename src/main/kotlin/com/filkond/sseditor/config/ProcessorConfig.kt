package com.filkond.sseditor.config

import org.bukkit.configuration.file.FileConfiguration
import java.io.File

data class ProcessorConfig(
    val config: FileConfiguration,
    val script: File
)