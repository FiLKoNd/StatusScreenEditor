package com.filkond.sseditor.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class SSECommand : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.getOrNull(0) == "reload") {
            sender.sendMessage("Reloading...")
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
        if (args.getOrNull(0) == "reload") return listOf("town", "nation", "resident", "townblock")
        return emptyList()
    }
}