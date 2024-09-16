package com.filkond.sseditor.service

import com.filkond.sseditor.processor.impl.TownProcessor

class ProcessorService(
    private val configService: ConfigService
) {
    val townProcessor = TownProcessor(
        configService.getProcessorConfig("town")!!.script.readText(),
        configService.getProcessorConfig("town")!!.config.getStringList("content")
    )
}