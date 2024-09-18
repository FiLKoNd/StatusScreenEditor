package com.filkond.sseditor.service.processor

import com.filkond.sseditor.processor.AbstractProcessor
import com.filkond.sseditor.processor.impl.NationProcessor
import com.filkond.sseditor.processor.impl.ResidentProcessor
import com.filkond.sseditor.processor.impl.TownProcessor
import com.filkond.sseditor.processor.impl.TownblockProcessor
import com.filkond.sseditor.service.Loadable
import com.filkond.sseditor.service.config.SimpleConfigService
import com.filkond.sseditor.service.Reloadable
import com.palmergames.bukkit.towny.event.statusscreen.NationStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.ResidentStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.StatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownBlockStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent
import com.palmergames.bukkit.towny.`object`.TownyObject
import java.util.logging.Logger

class SimpleProcessorService(
    private val configService: SimpleConfigService,
    private val logger: Logger
) : ProcessorService, Reloadable, Loadable {
    private lateinit var townProcessor: TownProcessor
    private lateinit var nationProcessor: NationProcessor
    private lateinit var residentProcessor: ResidentProcessor
    private lateinit var townBlockProcessor: TownblockProcessor

    override fun processEvent(event: TownStatusScreenEvent) {
        process(event, event.town, townProcessor)
    }

    override fun processEvent(event: NationStatusScreenEvent) {
        process(event, event.nation, nationProcessor)
    }

    override fun processEvent(event: ResidentStatusScreenEvent) {
        process(event, event.resident, residentProcessor)
    }

    override fun processEvent(event: TownBlockStatusScreenEvent) {
        process(event, event.townBlock, townBlockProcessor)
    }

    private fun <T : TownyObject> process(event: StatusScreenEvent, entity: T, processor: AbstractProcessor<T>) {
        val lines = processor.getProcessedLines(entity)
        if (lines.isNotEmpty()) {
            event.statusScreen.componentKeys.toMutableList().forEach { event.statusScreen.removeStatusComponent(it) }
            lines.forEachIndexed { index, it ->
                event.statusScreen.addComponentOf("SSE-$index-line", it)
            }
        } else {
            logger.warning("Result of $processor is empty")
        }
    }

    override fun load() {
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