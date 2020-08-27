# Communication Technologies for the IoT

Both for IoT and IIoT (industrial IoT) there is much heterogeneity in the type of communication and technologies.

**What is the traffic in IoT?**:

- **Cyclic Traffic**: periodic transfer of sensor measurements (field devices) and set points
- **Acyclic Traffic**: traffic generated by unpredictable events
- **Multimedia Traffic**: images/videos (smarth camera that sends images when there is an intrusion)
- **Backhand Traffic**: aggragated flows (aggregated traffic)

## IoT Key Performance Indicators- KPIs

**Throughput**

*Throughput (rate)*: number of bits (bytes) per second that can be sent across a link (Unit measure: [bit/s] or [byte/s])

**Delay**

*Delivery Time*: time necessary to send one *service data unit* from source to destination (Unit measure: [s]). Depends on: transmission time, propagation time, protocol execution time (processing data when it arrives).

*Minimum Cycle Time (MCT):* minimum time required to execute a cycle in a control loop.

**Precision**

*Jitter:* precision/reliability in performing periodic operations

## Networking fundamentals

A communication technology is composed by: 

- Physical infrastructure: hosts (sensors, personal devices), links (fiber), network nodes (switch, router)
- Network architectures/ topologies (bus, star...)
- A set of communication protocols (language)

**Communication Protocols**

Set of rules underpinning the exchange of information between two entities

- Message format
- Connection set up procedures
- Connection tear down procedures 
- Semantics

IoT communication networks protocols exchange packets (like the Internet).

**Layered Architecture**

Communication protocols are commonly layered. Each layer provides some services/functionalities to upper layers

![Schermata 2020-04-01 alle 11.16.37](/Users/filippoantonielli/Desktop/Internet of Things/Foto/Schermata 2020-04-01 alle 11.16.37.png)

## Bits over cables

**Channel Access:**

- Scheduled access: central entity which grants permission when to communicate.
  - no conflicts
  - polling schemes
  - centralized scheduling schemes
  - PROs: "guarenteed" performance (bounded delay/throughtput)
  - CONs: coordination required (central node, synchronization ...)
- Random access: everybody randomly access the channel
  - collisions may happen
  - conflicts are resolved using distributed procedures based on radom retransmission delay
  - PROs: easy to implement, oppurtunistic access to the resource
  - CONs: only "statistical" guarantees on performance, poor performance under heavy traffic (many collisions)

### Ethernet

**MAC addresses**: 48 bits long. First 3 bytes set the manufactured, last 3 bytes identify the interface

At first Ethernet used **CSMA/CD**:

- Carries Sensing Multiple Access: sense the BUS before transmitting; if BUS is free transmit otherwise refrain and try later
- Collision Detect: if collisions are detected, all the transmission are aborted after a while

Then Ehternet used **hubs**: physical layer device that repeat the string of bits

Now Ethernet use **Switch**: no more collision, no more CSMA/CD. All connection are full duplex

## Bits in the Air

3 type of wireless connectivity:

- Mobile Radio Network: RAN (Radio Access Network) and CN (Core Network)
- Cellular IoT Operator: Low Power Long Range Technology
- Capilarity Multi-hop Networks: short/medium range