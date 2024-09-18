package com.filkond.sseditor

import com.filkond.sseditor.service.processor.SimpleProcessorService
import com.palmergames.bukkit.towny.event.statusscreen.NationStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.ResidentStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownBlockStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class TownyListener(
    private val processorService: SimpleProcessorService,
    private val config: FileConfiguration
) : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onTownBlockStatusScreen(event: TownStatusScreenEvent) {
        if (config.getBoolean("town-processor-enabled"))
            processorService.processEvent(event)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onNationStatusScreen(event: NationStatusScreenEvent) {
        if (config.getBoolean("nation-processor-enabled"))
            processorService.processEvent(event)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onResidentStatusScreen(event: ResidentStatusScreenEvent) {
        if (config.getBoolean("resident-processor-enabled"))
            processorService.processEvent(event)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onTownBlockStatusScreen(event: TownBlockStatusScreenEvent) {
        if (config.getBoolean("townblock-processor-enabled"))
            processorService.processEvent(event)
    }
}