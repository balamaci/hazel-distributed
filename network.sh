#!/bin/bash

N1=172.17.0.2
N2=172.17.0.3
N3=172.17.0.4
N4=172.17.0.5

function partition_NETWORK() {
echo 'Partitioning N1,N2 from N3,N4
ssh root@$N1 'bash -s' <<-ENDSSH
	iptables -A INPUT -s $N3 -j DROP
	iptables -A INPUT -s $N4 -j DROP
ENDSSH

ssh root@$N2 'bash -s' <<-ENDSSH
        iptables -A INPUT -s $N3 -j DROP
	    iptables -A INPUT -s $N4 -j DROP
ENDSSH

echo 'Partitioning N3,N4 from N1,N2
ssh root@$N3 'bash -s' <<-ENDSSH
	iptables -A INPUT -s $N1 -j DROP
	iptables -A INPUT -s $N2 -j DROP
ENDSSH

ssh root@$N4 'bash -s' <<-ENDSSH
        iptables -A INPUT -s $N1 -j DROP
	    iptables -A INPUT -s $N2 -j DROP
ENDSSH

}

function heal_NETWORK() {

ssh root@$N1 'bash -s' <<-ENDSSH
	iptables -F
ENDSSH

ssh root@$N2 'bash -s' <<-ENDSSH
    iptables -F
ENDSSH

ssh root@$N3 'bash -s' <<-ENDSSH
    iptables -F
ENDSSH

ssh root@$N4 'bash -s' <<-ENDSSH
    iptables -F
ENDSSH

}

case "$1" in
partition)
        partition_NETWORK;
    ;;

heal)
        heal_NETWORK;
    ;;

*)
    echo "Usage : $0 {start|stop}"
esac
