package com.balamaci.hztest.startup.client.task;

import com.balamaci.hztest.domain.User;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Serban Balamaci
 */
public class AddEntriesTask {

    private int instanceNr;

    private HazelcastInstance clientInstance;

    private static final Logger log = LoggerFactory.getLogger(AddEntriesTask.class);

    public AddEntriesTask(HazelcastInstance clientInstance, int instanceNr) {
        this.clientInstance = clientInstance;
        this.instanceNr = instanceNr;
    }

    public void addEntries(int from, int to) {

        IMap<Long, User> users = clientInstance.getMap("users");

        for(long i=from; i <= to; i ++) {
            User user = new User();
            user.setId(i);
            user.setClientNr(instanceNr);

            log.info("Adding {} to Map", i);
            users.put(i, user);
        }
    }

}
