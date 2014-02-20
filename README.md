hazel-distributed
=================

Repository to test distributed scenarios with Hazelcast.

I'm testing the different scenarios regarding how Hazelcast is handling.

Right now there are two scenarios:
1. Starting with 4 HZ instances, and 2 Client instances.
  - A network partition happens separating the cluster so instances 1,2 are separated from instances 3, 4 in a "Split brain scenario". 
  - Clients continues to connect to partition 1 and partition 2
  - Network is healed.
  
2. 
