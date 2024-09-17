package com.filkond.sseditor.processor.impl

import bsh.Interpreter
import com.filkond.sseditor.processor.AbstractProcessor
import com.palmergames.bukkit.towny.`object`.TownBlock
import java.io.File

class TownblockProcessor : AbstractProcessor<TownBlock> {
    private val interpreter = Interpreter()
    constructor(ctx: String) : super(ctx)
    constructor(config: File) : super(config)

    override fun getInterpreter(entity: TownBlock) = interpreter.also {
        interpreter.reset()
        interpreter.set("tb", entity)
    }
}