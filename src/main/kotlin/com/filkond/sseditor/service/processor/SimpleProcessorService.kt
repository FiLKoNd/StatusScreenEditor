package com.filkond.sseditor.service.processor

import com.filkond.sseditor.processor.impl.NationProcessor
import com.filkond.sseditor.processor.impl.ResidentProcessor
import com.filkond.sseditor.processor.impl.TownProcessor
import com.filkond.sseditor.processor.impl.TownblockProcessor
import com.filkond.sseditor.service.config.SimpleConfigService
import com.filkond.sseditor.service.Reloadable
import com.palmergames.bukkit.towny.event.statusscreen.NationStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.ResidentStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownBlockStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent
import java.util.logging.Logger

class SimpleProcessorService(
    private val configService: SimpleConfigService,
    private val logger: Logger
) : ProcessorService, Reloadable {
    private lateinit var townProcessor: TownProcessor
    private lateinit var nationProcessor: NationProcessor
    private lateinit var residentProcessor: ResidentProcessor
    private lateinit var townBlockProcessor: TownblockProcessor

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

    private fun load() {
        townProcessor = TownProcessor(configService.getProcessorConfig("town")!!)
        nationProcessor = NationProcessor(configService.getProcessorConfig("nation")!!)
        residentProcessor = ResidentProcessor(configService.getProcessorConfig("resident")!!)
        townBlockProcessor = TownblockProcessor(configService.getProcessorConfig("townblock")!!)
    }

    override fun reload() {
        configService.reload()
        load()
    }
}