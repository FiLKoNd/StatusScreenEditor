package com.filkond.sseditor

import com.filkond.sseditor.service.processor.SimpleProcessorService
import com.palmergames.bukkit.towny.event.statusscreen.NationStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.ResidentStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownBlockStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class TownyListener(
    private val processorService: SimpleProcessorService
) : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onTownBlockStatusScreen(event: TownStatusScreenEvent) {
        processorService.processEvent(event)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onNationStatusScreen(event: NationStatusScreenEvent) {
        processorService.processEvent(event)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onResidentStatusScreen(event: ResidentStatusScreenEvent) {
        processorService.processEvent(event)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onTownBlockStatusScreen(event: TownBlockStatusScreenEvent) {
        processorService.processEvent(event)
    }
}