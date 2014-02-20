package com.balamaci.hztest.startup.server;

import com.hazelcast.config.Config;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author Serban Balamaci
 */
public class StartServer {

    private static final Logger log = LoggerFactory.getLogger(StartServer.class);

    public static void main(String[] args) throws Exception {
        System.setProperty("hazelcast.logging.type", "slf4j");

        String configDir = System.getenv().get("HZ_CONFIG_LOCATION");
        if(configDir == null) {
//            configDir = "./config";
            configDir = "./config/simple";
        }
        String hzConfigLocation = configDir + "/hazelcast.xml";

        File configFile = new File(hzConfigLocation);
        if(! configFile.exists()) {
            throw new RuntimeException("Could not load config file " + hzConfigLocation);
        }

        Config cfg = new FileSystemXmlConfig(configFile);

        String hostAddr = getInterfaceAddr().getHostAddress();
        log.info("Host Interface {}", hostAddr);
        cfg.getNetworkConfig().getInterfaces().addInterface(hostAddr);
        cfg.getNetworkConfig().getInterfaces().setEnabled(true);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        log.info("Starting server instance");
    }

    private static InetAddress getInterfaceAddr() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();

            if(networkInterface.isLoopback()) {
                continue;
            }

            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if(inetAddress instanceof Inet4Address) {
                    return inetAddress;
                }
            }
        }

        throw new RuntimeException("No NetworkInterface found");
    }

}
