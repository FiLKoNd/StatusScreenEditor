package com.filkond.sseditor.service.processor

import com.filkond.sseditor.processor.impl.NationProcessor
import com.filkond.sseditor.processor.impl.ResidentProcessor
import com.filkond.sseditor.processor.impl.TownProcessor
import com.filkond.sseditor.processor.impl.TownblockProcessor
import com.filkond.sseditor.service.ConfigService
import com.filkond.sseditor.service.Reloadable
import com.palmergames.bukkit.towny.event.statusscreen.NationStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.ResidentStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownBlockStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent
import java.util.logging.Logger

class SimpleProcessorService(
    configService: ConfigService,
    private val logger: Logger
) : ProcessorService, Reloadable {
    private val townProcessor = TownProcessor(configService.getProcessorConfig("town")!!)
    private val nationProcessor = NationProcessor(configService.getProcessorConfig("nation")!!)
    private val residentProcessor = ResidentProcessor(configService.getProcessorConfig("resident")!!)
    private val townBlockProcessor = TownblockProcessor(configService.getProcessorConfig("townblock")!!)

    override fun processEvent(event: TownStatusScreenEvent) {
        val lines = townProcessor.getProcessedLines(event.town)
        if (lines.isNotEmpty()) {
            event.statusScreen.componentKeys.toMutableList().forEach { event.statusScreen.removeStatusComponent(it) }
            event.addLines(lines)
        } else {
            logger.warning("Result of town processor is not a collection")
        }
    }
    override fun processEvent(event: NationStatusScreenEvent) {
        val lines = nationProcessor.getProcessedLines(event.nation)
        if (lines.isNotEmpty()) {
            event.statusScreen.componentKeys.toMutableList().forEach { event.statusScreen.removeStatusComponent(it) }
            event.addLines(lines)
        } else {
            logger.warning("Result of nation processor is not a collection")
        }
    }

    override fun processEvent(event: ResidentStatusScreenEvent) {
        val lines = residentProcessor.getProcessedLines(event.resident)
        if (lines.isNotEmpty()) {
            event.statusScreen.componentKeys.toMutableList().forEach { event.statusScreen.removeStatusComponent(it) }
            event.addLines(lines)
        } else {
            logger.warning("Result of resident processor is not a collection")
        }
    }

    override fun processEvent(event: TownBlockStatusScreenEvent) {
        val lines = townBlockProcessor.getProcessedLines(event.townBlock)
        if (lines.isNotEmpty()) {
            event.statusScreen.componentKeys.toMutableList().forEach { event.statusScreen.removeStatusComponent(it) }
            event.addLines(lines)
        } else {
            logger.warning("Result of townblock processor is not a collection")
        }
    }

    override fun reload() {
        TODO("Not yet implemented")
    }
}