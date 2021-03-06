package com.balamaci.hztest.startup.server;

import com.hazelcast.config.Config;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Serban Balamaci
 */
public class StartLocalServer {

    private static final Logger log = LoggerFactory.getLogger(StartServer.class);

    public static void main(String[] args) throws Exception {
        System.setProperty("hazelcast.logging.type", "slf4j");

        String configDir = System.getenv().get("HZ_CONFIG_LOCATION");
        if(configDir == null) {
            configDir = "./config/simple";
        }
        String hzConfigLocation = configDir + "/hazelcast.xml";

        File configFile = new File(hzConfigLocation);
        if(! configFile.exists()) {
            throw new RuntimeException("Could not load config file " + hzConfigLocation);
        }

        Config cfg = new FileSystemXmlConfig(configFile);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        log.info("Starting server instance");
    }


}
