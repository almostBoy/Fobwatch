package net.bency.fobwatch;

import net.bency.fobwatch.sonic_screwdrivers.Sonic_Screwdriver;
import net.bency.fobwatch.tardis_classes.*;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fobwatch implements ModInitializer {
	public static final String MOD_ID = "fobwatch";
    public static final Logger LOGGER = LoggerFactory.getLogger("fobwatch");

	@Override
	public void onInitialize() {
		LOGGER.info("Sounds");
		FobwatchSounds.init();
		LOGGER.info("TARDIS");
		tardisBlock.init();
		LOGGER.info("Sonic Screwdriver");
		Sonic_Screwdriver.init();
		LOGGER.info("Debug Tools");
		LOGGER.info("Fobwatch initialized");
	}
}