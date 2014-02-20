package com.balamaci.hztest.startup.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;

import java.io.File;
import java.io.IOException;

/**
 * @author Serban Balamaci
 */
public class ClientConfigUtil {

    public static HazelcastInstance buildHzClient(String clientConfigLocation) throws IOException {
        File configFile = new File(clientConfigLocation);
        if(! configFile.exists()) {
            throw new RuntimeException("Could not load config file " + clientConfigLocation);
        }

        ClientConfig cfg = new XmlClientConfigBuilder(configFile).build();
        return HazelcastClient.newHazelcastClient(cfg);
    }

}
