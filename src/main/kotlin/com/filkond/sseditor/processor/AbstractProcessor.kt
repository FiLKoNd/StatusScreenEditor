package com.filkond.sseditor.processor

import bsh.EvalError
import bsh.Interpreter
import com.palmergames.adventure.text.Component
import com.palmergames.bukkit.towny.`object`.TownyObject
import com.palmergames.bukkit.towny.utils.TownyComponents
import java.io.File
import kotlin.Throws

@Suppress("UnstableApiUsage")
abstract class AbstractProcessor<T : TownyObject> {
    private val ctx: String

    constructor(ctx: String) {
        this.ctx = ctx
    }
    constructor(config: File) : this(config.readText())

    abstract fun getInterpreter(entity: T): Interpreter
    @Throws(EvalError::class)
    fun getProcessedLines(entity: T): List<Component> = eval(entity).map { TownyComponents.miniMessage(it) }

    @Throws(EvalError::class)
    private fun eval(entity: T): Collection<String> {
        val interpreter = getInterpreter(entity)
        interpreter.set("out", ArrayList<String>())
        interpreter.eval(ctx)
        val result = interpreter.get("out")
        if (result !is Collection<*>) {
            return emptyList()
        }
        return result.map { it.toString() }
    }
}

