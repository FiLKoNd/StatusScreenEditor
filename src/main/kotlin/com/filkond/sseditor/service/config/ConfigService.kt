package com.filkond.sseditor.service.config

import java.io.File

interface ConfigService {
    fun getProcessorConfig(id: String): File?
}