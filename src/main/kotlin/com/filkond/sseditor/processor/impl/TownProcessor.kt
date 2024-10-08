package com.filkond.sseditor.processor.impl

import bsh.Interpreter
import com.filkond.sseditor.processor.AbstractProcessor
import com.palmergames.bukkit.towny.`object`.Town
import java.io.File

class TownProcessor : AbstractProcessor<Town> {
    constructor(ctx: String) : super(ctx)
    constructor(config: File) : super(config)

    private val interpreter = Interpreter()
    override fun getInterpreter(entity: Town) = interpreter.also {
        interpreter.reset()
        interpreter.set("town", entity)
    }
}