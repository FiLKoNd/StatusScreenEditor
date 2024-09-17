package com.filkond.sseditor.processor.impl

import bsh.Interpreter
import com.filkond.sseditor.processor.AbstractProcessor
import com.palmergames.bukkit.towny.`object`.Nation
import java.io.File

class NationProcessor : AbstractProcessor<Nation> {
    private val interpreter = Interpreter()
    constructor(ctx: String) : super(ctx)
    constructor(config: File) : super(config)

    override fun getInterpreter(entity: Nation) = interpreter.also {
        interpreter.reset()
        interpreter.set("nation", entity)
    }
}