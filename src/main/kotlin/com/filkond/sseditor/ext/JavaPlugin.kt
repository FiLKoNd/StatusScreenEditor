package com.filkond.sseditor.ext

import org.bukkit.plugin.java.JavaPlugin
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.nio.file.*
import java.util.function.Consumer


@Throws(URISyntaxException::class, IOException::class)
fun JavaPlugin.walkResources(path: String, depth: Int, consumer: Consumer<Path>) {
    val uri: URI = this.javaClass.classLoader.getResource(path)?.toURI() ?: return
    var fileSystem: FileSystem? = null
    val myPath: Path
    try {
        if (uri.scheme.equals("jar")) {
            fileSystem = FileSystems.newFileSystem(uri, emptyMap<String, Any>())
            myPath = fileSystem.getPath(path)
        } else {
            myPath = Paths.get(uri)
        }

        Files.walk(myPath, depth).filter { !it.toString().endsWith(path) }.use { it.forEach(consumer) }
    } finally {
        fileSystem?.close()
    }
}