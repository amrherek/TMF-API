package com.orange.apibss.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {

	private LoggingUtils(){
		//not called
	}

	/**
	 * Static variable used to log execution informations of this class
	 */
	protected final static Logger logger = LoggerFactory
			.getLogger(LoggingUtils.class);

    private static final long mega = 1024L * 1024;

	public static void logEntry(String description) {
		logger.info("=====>[Enter]:" + description);
		logger.debug("=====>[Processing]Available processors (cores):{} ",
				Runtime.getRuntime().availableProcessors());
		logger.debug(
				"=====>[Processing]Free memory (bytes) / Total memory (bytes):{}",
				(Runtime.getRuntime().freeMemory() / mega) + "/"
						+ (Runtime.getRuntime().totalMemory() / mega));
	}

	public static void logExit(String description) {
		logger.debug(
				"<=====[Processing]Free memory (bytes) / Total memory (bytes):{}",
				(Runtime.getRuntime().freeMemory() / mega) + "/"
						+ (Runtime.getRuntime().totalMemory() / mega));
		logger.debug("<=====[Processing]Available processors (cores): {}",
				Runtime.getRuntime().availableProcessors());
		logger.info("<=====[Exit]:" + description);

	}
}
