# 6LowPAN

Tries to make the internet compliant with IoT.

Is an Adaptation Layer to fit IPv6 over Low-Power wireless Area Networks

<img src="file:///android_asset/IOT/img/img7.png" >

There are 3 type of architectures:

- Simple LoWPAN is the most common one. There is a *edge router* that is a device that interconnects the IoT network to the backend (the common internet). In 6LoWPAN we need a router which is a layer 3 (network layer) device to orchestrate the interoperability between IoT and classical internet. In ZigBee we need a proxy on the border: application layer
- Extended LoWPAN:  there are multiple LoWPANs, each one controlled by an edge router. The router are then connected by a common backbone link
- Ad-hoc LoWPAN: no connection to the internet

With 6LoWPAN there are some internet integration issues that must be solved:

- Maximum transmission unit
- Application protocols
- IPv4 interconnettivity

## Adaptation Features

We need to introduce at the network layer some adaptation in order to make IPv6 compliant with the lower level.

IPv6 packet have very large header w.r.t. the limited sizes that we have at Medium Access Control (127 Byte) in 6LoWPAN so we need to **compress the header**, both of IPv6 and UDP.

Packet size at network layer (IPv6) [1280 Byte] are larger then the frame size at 802.15.4 layer (MAC) [127 Byte] so we need to implement a **fragmentation**

6LoWPAN defines also its own routing protocol

## Internet Protocol v6

Is the latest version of IP. 128 bits address vs 32 bits of IPv4. The header of an IPv6 is divided in many fields:

- Version: indicate the version of IP, so 6
- Traffic Class and Flow Label: fields to support QoS (quality of service) policies; define the priority of data and the type of traffic
- Payload Lenght: how long is the payload
- Next Header: specify the type of protocol whose data are carried in the IPv6 packet
- Hop Limit: maximum lifetime of the packet; indicates the maximum number of retransmissions
- Source address: IP address of the source of information $\to$ who generated the packet first
- Destination address: the final destination of the packet

## 6LowPAN header compression

The goal is to take the IPv6 header and try to reduce it as much as possible.

Stateless compression: each single IPv6 LowPAN entity compress and decompress the packet singularly without maintaining any information locally.

The compression is performed using some simple tricks:

- Common values for header fields might have a compact form
- version is always 6 so the version field in cancelled
- Traffic Class and Flow label: once the network is up and running most likely we have on that network one (or very few) type of traffic which means that these fields may be reduced
- Payload Lenght is always derived from the lower (level 2) header so we don't need it
- Source and destination addresses can be elided or compressed depending on the transmission

### IPv6 addressing

The 128 bits are divided in two part of 64 bits:

- Prefix: used to specify the general context network interface (IP network)
- Interface ID (IID): specify the specific interface that is connected to that network. Typically is formed from the interface MAC address

### The header compression header

To compress the IPv6 header we need to add to this header few additional bits that form the header compression header. The number of bits that we are adding is lower to the number of bits we are eliding.

All the fields in the header compression header contain information on how the original fields in IPv6 header are compressed.

### UDP header compression

Even for the UDP header we need a string of bits to add to the UDP header that says how the UDP header is compressed.

The UDP header is composed of 4 fields:

- Source port
- Destination port
- Lenght
- Checksum: string of bits that tell us if the segment was received correctly

We use some tricks to reduce the header:

- in 6LowPAN the ports are assigned into a limited range (from 61616 to 61632, 4 bits are necessary)
- Lenght is derived from IPv6 lenght so the lenght field is elided
- checksum may be elided if other integrity checks are in use

## Fragmentation

IPv6 has packets of lenght of 1280 bytes. 802.15.4 has maximum payload of 127 bytes. Since IPv6 does not support fragmentation 6lowPAN must provide to IPv6 a fragmentation service.

**Fragmentation header:** add few bits to each fragment (fragmentation header) to recognize the specific fragments with respect to the original packets. Is composed of 3 fields:

- *dgram_size*: size of the fragment in bytes
- *dgrma_tag*: identifies all the fragments belonging to the same packet
- *dgram_offset*: fragmentation offset

**In practice** the performance of large IPv6 packets fragmented over low-power wireless mesh network is poor. So fragmentation is bringed up to application layer (COAP)