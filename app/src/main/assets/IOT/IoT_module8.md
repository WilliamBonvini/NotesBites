# Radio Frequency Identification

RFID is the evolution of bar codes using wireless transmissions (based on radio frequency transceiver). RFID is where IoT started.

Ingredients: 

- Transition to electronic bar codes with wireless communication capabilities
- Transition form optical to wireless “readers”

## RFID bulding blocks

Two basic devices:

**Reader**: read ID stored in the tag; composed by

- RF module (speaks same language of the tag)
- memory
- Processing unit
- Control Logic/ collision arbitration mechanism
- Battery or power supply
- Other interfaces (ethernet, WiFi): is the gateway for the tag to the internet

**Tag:** radio transceiver; composed by

- RF module: can send and receive wireless signals
- Battery scavenging circuitry (active or passive): needs to scavenge the battery
- E2PROM to store ID: similar to a sensor but with limited memory, tipically can store only an ID
- Control logic/ collision arbitration mechanism: operate all the minimal protocols used for wireless transmission
- Can have sensors
- Can have a processing unit

### Tags

There are 3 types of tags:

- Passive: they have no battery, operational power scavenged from reader radiated power. 98% of the tags are of this type.
- Semi-Passive: mostly passive, but also equipted with some battery which is used only for calculation to power the computation unit
- Active: more similar to sensor nodes; operational power provided by battery, transmitter built into tag

Tags can be attached to everything so needs to be as small as possible.

<img src="file:///android_asset/IOT/img/img9.png" >

The name of the tag is standardized: is the EPC. EPC is a 96 bit address and is composed by 4 parts:

- Header: first 8 bits. Indicate the tag version number
- EPC Manager: 28 bit. Indicate the manufacturer ID
- Object class: 24 bits. Indicates the manifacturer's product ID
- Serial number: 36 bit. Unit ID

#### Implementation Challenges

The tags need to:

- have an effective energy scavenging 
- be small
- be cheap

The reader:

- must deliver enough power from RF field to power the tag
- must discriminate backscatter modulation in presence of carrier at same frequency
- must manage high magnitude difference between transmitted and received signals

RFID commonly uses unlicensed bands. Mainly in UFH frequencies, but uses many frequencies.!<img src="file:///android_asset/IOT/img/img10.png" >

## RFID Collision Arbitration

The signal of the reader power up all tags in range. All tags turned on send back their ID to the reader, there may be conflicts. Problem at the reader to distinguish all the ID received: **tag arbitration**.

Tag arbitration is similar to classical access control but:

- fixed unknown population size
- Tags cannot implement complex protocols
- Often reader-driven algorithms (the architecture is reader centric)