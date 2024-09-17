package com.filkond.sseditor.ext

import org.bukkit.plugin.Plugin
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.nio.file.*
import java.util.function.Consumer


@Throws(URISyntaxException::class, IOException::class)
fun Plugin.walkResources(path: String, depth: Int, consumer: Consumer<Path>) {
    val uri: URI = this.javaClass.getResource(path)?.toURI() ?: return
    var fileSystem: FileSystem? = null
    val myPath: Path
    try {
        if (uri.scheme.equals("jar")) {
            fileSystem = FileSystems.newFileSystem(uri, emptyMap<String, Any>())
            myPath = fileSystem.getPath(path)
        } else {
            myPath = Paths.get(uri)
        }

        Files.walk(myPath, depth).use { it.forEach(consumer) }
    } finally {
        fileSystem?.close()
    }
}