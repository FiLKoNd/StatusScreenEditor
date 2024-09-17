package com.filkond.sseditor.service.processor

import com.palmergames.bukkit.towny.event.statusscreen.NationStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.ResidentStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownBlockStatusScreenEvent
import com.palmergames.bukkit.towny.event.statusscreen.TownStatusScreenEvent

interface ProcessorService {
    fun processEvent(event: TownStatusScreenEvent)
    fun processEvent(event: NationStatusScreenEvent)
    fun processEvent(event: ResidentStatusScreenEvent)
    fun processEvent(event: TownBlockStatusScreenEvent)
}