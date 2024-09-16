package com.filkond.sseditor.processor

import bsh.Interpreter
import com.palmergames.adventure.text.Component
import com.palmergames.bukkit.towny.`object`.TownyObject
import com.palmergames.bukkit.towny.utils.TownyComponents

@Suppress("UnstableApiUsage")
abstract class AbstractProcessor<T : TownyObject>(
    private val ctx: String,
    private val content: Collection<String>
) {
    abstract fun getInterpreter(entity: T): Interpreter

    fun getProcessedLines(entity: T): List<Component> = content.map { TownyComponents.miniMessage(processLine(entity, it)) }

    private fun processLine(entity: T, line: String): String =
        regex.replace(line) {
            val code = it.value.substring(2, it.value.length - 1)
            return@replace eval(entity, code).toString()
        }

    private fun eval(entity: T, code: String): Any = getInterpreter(entity).eval("$ctx\n$code")

    companion object {
        private val regex: Regex = "\\$\\{(.*?)}".toRegex()
    }
}