package com.filkond.sseditor.processor.impl

import bsh.Interpreter
import com.filkond.sseditor.processor.AbstractProcessor
import com.palmergames.bukkit.towny.`object`.Resident
import java.io.File

class ResidentProcessor : AbstractProcessor<Resident> {
    private val interpreter = Interpreter()
    constructor(ctx: String) : super(ctx)
    constructor(config: File) : super(config)

    override fun getInterpreter(entity: Resident) = interpreter.also {
        interpreter.reset()
        interpreter.set("resident", entity)
    }
}