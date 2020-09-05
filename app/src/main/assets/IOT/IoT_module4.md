# Short Range Communication Technologies and Protocols

Different technologies: Bluetooth, ZigBee, NFC, RFID

**ZigBee vs 6LowPAN:** 6LowPAN extends IP to the Internet of Things, ZigBee doesn't.

## ZigBee

It has low-cost hardware (2$) and software, limited transmission range (10m), low latency and is high energy efficient. It is based on the standard IEEE 802.15.4.

**ZigBee communication stack**: 

<img src="file:///android_asset/IOT/img/img5.png" >

### 802.15.4: type of devices

- **Full Function Device (FFD)**: can send beacons, can communicate with other FFDs, can route frames, can act as PAN coordinator, typically features power supply
- **Reduced Function Device (RFD)**: cannot route frames, cannot communicate with other RFDs, can communicate with FFD, runs on batteries
- **PAN Coordinator**: is responsible of a Personal Area Network (PAN), manages PAN association/de-association

**ZigBee Supported Topology:**

- **Stars**
- **Mesh**
- **Cluster-Tree** (not in 802.15.4 standard)

### 802.15.4: PHY

Manages activation and deactivation of the radio transceiver, energy detection (ED) within the current channel (detect energy level for each channel $\to$ used to implement scanning functionalities), link quality indicator (LQI) for received packets, clear channel assessment (CCA) (used to implement the carrier sense multiple access with collision avoidance (CSMA-CA)), channel frequency selection, data transmission and reception.

3 channels are available in 868MHz bands, 30 in the 915MHz bands and 16 in the 2.4 MHz bands

**MAC: functional description**: two operation modes are defined:

- **Beacon Enabled:** PAN coordinator periodically transmits beacons, usually adopted in star topologies. Slotted CSMA/CA + scheduled transmissions
- **Non Beacon Enabled**: uncoordinated access through unslotted CSMA/CA

### 802.15.4 Channel Access

The resource to be shared is time. Combines a mixture of Scheduled and Random Access.

- Scheduled Access is implemented through PAN coordinator (only beacons-enabled mode)
- Random Access allowed between RFDs and between RFD/FFD and PAN Coordinator (allowed in both operation modes)

**Beacon Enabled: functional description**

<img src="file:///android_asset/IOT/img/img6.png" >

During CAP: random access thought CSMA/CA

Gurarenteed Time Slot (GTS) statically assigned by PAN coordinator though beacons.

### CSMA/CA

Each device shall maintain three variables for each transmission attempt: NB, CW and BE.

- ***NB*** (number of backoff) is the number of times the CSMA-CA algorithm was required to backoff (initialized to zero before each new transmission attempt)
- ***CW*** is the contention window length, defining the number of backoff periods that need to be clear of channel activity before the transmission can commence (initialized to 2, only for slotted CSMA-CA).
- ***BE*** is the backoff exponent, which is related to how many backoff periods a device shall wait before attempting to access a channel.

A trasmitting node delays for a random number of backoff periods (slots) in $[0,2^{BE}-1]$. If the channel is busy, the exponent BE and the number of backoff attempts NB are incremented and the procedure is repeated. After too many ($NB_{max}$) failed retries, the packet is discarded.

Transmission procedure (including ACK) must end within CAP. In case of collision (ACK does not come back), the procedure restarts.

### Network Formation: Scanning

**Active Scanning** (only for FFDs): a beacon request message is sent out to trigger beacon transmission. Scan every available channel with a beacon request. Upon termination of the scanning procedure a PAN ID is chosen.

**Passive Scanning** (for FFDs and RMDs): similar to active scanning but without explicit beacon request messages.

Once the entering node has decided the PAN to connect to, there is the **association**

## Network Layer

**Network layer functionalities:** configuring a new device, starting a network, addressing, neighbor discovery, routing...

**Zigbee routing: overview**

Three types of devices:

- ZB Coordinator (FFD)
- ZB Router (FFD)
- ZB End-Device (RFD o FFD)

ZigBee Routing Integrates:

- Ad-hoc On-demand Distance Vector (AODV)
- Cluster Tree Algorithm

### Cluster Tree Algorithm: Tree formation

A FFD kicks off the procedure: it scans the available channels through the proper functionalities at the lower layers, chooses a channel, sets the PAN identifier and sets its own Network Address to 0.

Other devices may now associate to the coordinator through the lower- layer association procedures. Associated devices may be:

- ZB Router (only FFD): may let other devices to associate to the network
- ZB End-Device

Address Assignment (16 bits short addresses) is performed jointly with association. Each parent device (PAN coordinator, ZB router) assigns groups of addresses to its children (other ZB routers, ZB end devices).

On the basis of its depth in the tree, a newly joined router is assigned a range of consecutive addresses (16-bit integers). The first integer in the range becomes the node address while the rest will be available for assignment to its children (routers and end-devices).

The ZigBee coordinator fixes:

- the maximum number of routers ($R_m$)
- end-devices ($D_m$) that each router may have as children
- the maximum depth of the tree ($L_m$)

### Tree-Based Routing

- If the destination address is one of children end devices $\to$ route directly
- else if destination address belongs to one of the children routers' addresses set $\to$ send to corresponding children router
- else $\to$ send to parent node

### AODV Routing

In ZigBee the first option is AODV, if it is not possible then use tree-based routing. AODV routing is expensive, it may not be implemented.

- A node willing to send to a destination broadcast a *Route Requests (RREQ)* message (shouts where is the destination)
- RREQ messages are flooded by receiving nodes
- When a node re-broadcasts a *Route Request*, it sets up a reverse path pointing towards the source (stores "who shouted at me")
- When the intended destination receives a Route Request, it replies by sending a Route Reply (shouts back "it's me")
- Route Reply travels along the reverse path set-up when Route Request is forwarded