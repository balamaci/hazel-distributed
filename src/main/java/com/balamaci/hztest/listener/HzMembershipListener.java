package com.balamaci.hztest.listener;

import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Serban Balamaci
 */
public class HzMembershipListener implements MembershipListener {

    private static final Logger log = LoggerFactory.getLogger(HzMembershipListener.class);

    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        log.info("Member added {}", membershipEvent.getMember());
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        log.info("Member removed {}", membershipEvent.getMember());
    }
}
