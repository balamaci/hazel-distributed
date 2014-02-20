package com.balamaci.hztest.startup.client;

import com.balamaci.hztest.util.NetworkUtil;
import com.balamaci.hztest.startup.client.task.AddEntriesTask;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.query.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.balamaci.hztest.startup.client.ClientConfigUtil.buildHzClient;

/**
 * @author Serban Balamaci
 */
public class StartClient {

    private static final Logger log = LoggerFactory.getLogger(StartClient.class);

    public static void main(String[] args) throws Exception {
        System.setProperty("hazelcast.logging.type", "slf4j");


        String hzConfigLocation1 =  "./config/client_n1.xml";
        HazelcastInstance clientInstanceN1 = buildHzClient(hzConfigLocation1);

        String hzConfigLocation2 =  "./config/client_n5.xml";
        HazelcastInstance clientInstanceN2 = buildHzClient(hzConfigLocation2);

        AddEntriesTask addEntriesTask = new AddEntriesTask(clientInstanceN1, 1);
        addEntriesTask.addEntries(0, 100);
        log.info("Added first test entries");

        NetworkUtil.partitionNetwork();

        log.info("Adding second batch test entries");
        addEntriesTask.addEntries(100, 200);

        int n1InstancesC1 = clientInstanceN1.getMap("users").
                values(new Predicates.EqualPredicate("clientNr", 1)).size();
        log.info("C1: N1 users size {}", n1InstancesC1);

        int n1InstancesC2 = clientInstanceN2.getMap("users").
                values(new Predicates.EqualPredicate("clientNr", 1)).size();
        log.info("C2: N1 users size {}", n1InstancesC2);
    }

}
