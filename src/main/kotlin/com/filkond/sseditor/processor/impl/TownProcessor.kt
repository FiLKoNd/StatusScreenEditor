package com.filkond.sseditor.processor.impl

import bsh.Interpreter
import com.filkond.sseditor.processor.AbstractProcessor
import com.palmergames.bukkit.towny.`object`.Town

class TownProcessor(ctx: String, content: Collection<String>) : AbstractProcessor<Town>(ctx, content) {
    private val interpreter = Interpreter()

    override fun getInterpreter(entity: Town) = interpreter.also {
        interpreter.reset()
        interpreter.set("town", entity)
    }
}