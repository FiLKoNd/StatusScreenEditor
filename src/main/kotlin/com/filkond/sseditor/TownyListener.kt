package com.filkond.sseditor

import com.filkond.sseditor.service.ProcessorService
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class TownyListener(
    private val processorService: ProcessorService
) : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onTownBlockStatusScreen(event: TownStatusScreenEvent) {
        val town = event.town
        event.statusScreen.componentKeys.toMutableList().forEach {event.statusScreen.removeStatusComponent(it)}
        event.addLines(processorService.townProcessor.getProcessedLines(town))
    }
}