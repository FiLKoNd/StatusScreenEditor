package com.filkond.sseditor.processor

import bsh.Interpreter
import com.palmergames.adventure.text.Component
import com.palmergames.bukkit.towny.`object`.TownyObject
import com.palmergames.bukkit.towny.utils.TownyComponents
import java.io.File

@Suppress("UnstableApiUsage")
abstract class AbstractProcessor<T : TownyObject> {
    private val ctx: String

    constructor(ctx: String) {
        this.ctx = ctx
    }
    constructor(config: File) : this(config.readText())

    abstract fun getInterpreter(entity: T): Interpreter
    fun getProcessedLines(entity: T): List<Component> = eval(entity).map { TownyComponents.miniMessage(it) }

    private fun eval(entity: T): Collection<String> {
        val result = getInterpreter(entity).eval(ctx)
        if (result !is Collection<*>) {
            return emptyList()
        }
        return result.map { it.toString() }
    }
}

