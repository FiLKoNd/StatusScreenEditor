package com.filkond.sseditor.command

import com.filkond.sseditor.service.processor.SimpleProcessorService
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class SSECommand(
    private val processorService: SimpleProcessorService
) : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.getOrNull(0) == "reload") {
            sender.sendMessage("Reloading...")
            processorService.reload()
            sender.sendMessage("Reloaded!")
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String> {
        if (args.size == 1) return listOf("reload")
        return emptyList()
    }
}