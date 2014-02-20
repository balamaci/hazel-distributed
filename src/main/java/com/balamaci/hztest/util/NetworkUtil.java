package com.balamaci.hztest.util;

import com.google.common.io.CharStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Serban Balamaci
 */
public class NetworkUtil {

    private static final Logger log = LoggerFactory.getLogger(NetworkUtil.class);

    public static void partitionNetwork() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("./network.sh", "partition");
        pb.redirectErrorStream(true);

        log.info("Partitioning network");

        Process shell = pb.start();
        // To capture output from the shell
        InputStream shellIn = shell.getInputStream();

        // Wait for the shell to finish and get the return code
        int shellExitStatus = shell.waitFor();

        String stringFromStream = CharStreams.toString(new InputStreamReader(shellIn, "UTF-8"));
        log.info("OUTPUT {}", stringFromStream);

        shellIn.close();

        log.info("Partitioned exited with {}", shellExitStatus);
    }

}
