package com.mzuha

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

const val MOD_ID = "machinix"

class Machinix : ModInitializer {
    private val logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		logger.info("Hello Fabric world!")
	}
}