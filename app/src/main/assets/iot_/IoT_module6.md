# Application Layer Protocols for the IoT

An application is a program (piece of software) in execution at a host able to communicate with another program through a network.

Communication technologies in IoT are highly fragmented and different technologies (languages) cohexist. Moreover Field/Factory technologies do not "speak internet". **Application layer procols** accomplish **interoperability** .

There are 2 big families of Application layer protocols:

- Client/Server: HTTP, COAP
- Publish/Subscribe: MQTT

## COnstrained Application Protocol (COAP)

The goal is to enable web-based services in constrained wireless networks, so networks with limited power and memory devices. The problem is that WEB solutions are hardly applicable in IoT so the solution is to use COAP: a re-designed web-based services for constrained network.

Resources in the Web are managed by the servers, identified by URIs and accessed synchronously by clients through request/response paradigm. In a word, Representational State Transfer (REST).

**The CoAP architecture:**

<img src="file:///android_asset/IOT/img/img8.png" >

**CoAP at a Glance:**

- Embedded web transfer protocol (coap://)
- Asynchronous transaction model: allows delay between request and response; resource may not be ready due to duty cycle
- UDP binding with reliability and multicast support: COAP builds on top of UDP and not TCP for simplicity
- GET, POST, PUT, DELETE methods (used by HTTP)
- URI support
- 4 byte header (header very limited)

The transport in manly on UDP. The message exchange between two endpoints is:

- Messages with 4 bytes header (shared by request and responses) containing a message ID (16 bits)
- Reliable exchange through Confirmable Messages which must be acknowledged (through ACK or Reset Messages). Simple Stop-and-Wait retransmission with exponential back- off.
- Unreliable exchange through Non-Confirmable Message
- Duplicate detection for both confirmable and non-confirmable messages (through message ID)

**Dealing with Packet Loss:**

REST Request/Response are piggybacked on CoAP messages. Stop and wait approach: before sending a new message must receive the ACK. Repeat a request after a time-out in case ACK (or RST) is not coming back.

**COAP Observation**: the Rest paradigm in often "PULL" type, so data is obtained by issuing an explicit request. Information/data in WSN is often periodic/triggered (e.g., get me a temperature sample every 2 seconds or get me a warning if temperature goes below 5°C). The solution is to use Observation on COAP resources.

## The Message Queuing Telemetry Transport (MQTT)

*MQTT is a Client Server publish/subscribe messaging transport protocol.* It has features like:

- Simple to implement especially at the sensor side since all the complexity is in the backend
- QoS support
- Lightweight and bandwidth efficient
- Data agnostic
- Session awareness

**Publish/subscribe paradigm:** the clients don't know each other. Every clients can be a publisher and a subcriber. Is a One-to-Many paradigm: the publisher publish some data in the cloud; the cloud has the responsibility of informing the subscribers; a client can become a subscriber for a specific topic like Temperature Topic.

In MQTT the cloud is a **broker**: is a physical device with a local hard disk. All the compexity is at the broker.

### MQTT topics

Example: deib/antlab/room5/temperature; from left to right we add more details, the *topic level* is delimited with a / *topic separator*.

deib/antlab/ + /temperature: the plus indicates that i want the temperature in all the rooms.

deib/antlab/room5/ #: the # points in all resources in room5, can be used only at the end.

### MQTT Connections

Each MQTT client opens one TCP connection to the MQTT broker (so it uses TCP at transport layer).

**Open Connections**: if the first message exchange. The client sends to the Broker a CONNECT message and the broker replies with a CONNACK message.

The connect message contains among the following fields:

- ClientID which is stored in the broker
- cleanSession
- some fields for last will
- keepAlive: if the client doesn't ping the broker in T seconds the connection is not kept alive

The CONNACK message contains:

- sessionPresent: if it's true is a persistent session
- returnCode: a number between 0-4, if 0 everything is OK, otherwise there is an error

**Publishing:** once the connection is established, the client can start publishing.

The MQTT client sends a PUBLISH message to the MQTT broker, that will retransmit the PUBLISH message to all the clients that are subscribed to the topic.

The PUBLISH message fields are:

- packetID
- topicName
- QoS: quality of service
- retainFlag: if it is true it is a *retained message:* the broker will store locally the message so when a client makes a new subscription will receive immediately the saved retained message.
- Payload
- dupFlag

**QoS 0: at most once**: The QoS field can have three values among 0 and 2. With 0 it means that the client publish the message (sends the message to the broker) and then he doesn't care no more.

**QoS 1: at least once:** the MQTT clients stores the message and keep retransmitting it until it is acknowledged by the MQTT broker but the message can be received multiple times.

**QoS 2: exaclty once**: there is a double check. First there is the PUBLISH message, then the ACK (PUBREC). Then the client sends a PUBREL saying that has received the ACK (is an ACK for the ACK). Lastly the broker sends a PUBCOMP to indicate the completion of the exchange

**Subscribing**: the client sends a SUBSCRIBE message to the MQTT broker which replies with a SUBACK.

The SUBSCRIBE message fields are:

- packetID
- one QoS/topic couple for each subscription

the SUBACK has:

- packetID
- One returnCode for each topic in the subscription

**Unsubscribing:** clients sends an UNSUBSCRIBE message to the broker which replies with a UNSUBACK.

UNSUBSCRIBE:

- packetID
- Topic 1
- ... list of topics he wants to unsubscribe from

**Persisten Sessions:**

In default operation mode when the client disconnects (doesn't ping the server in time), all the client-related status at the broker is flushed (list of subscription, QoS pending messages, etc.). In persistent sessions both client and broker mantain a session:

Broker:

- Existence of a session, even if there are no subscriptions
- All subscriptions
- All messages in QoS 1 or 2 flow, which are not confirmed by the client
- All new QoS 1 or 2 messages, which the client missed while it was offline
- All received QoS 2 messages, which are not yet confirmed to the client

Client:

- All messages in a QoS 1 or 2 flow, which are not confirmed by the broker
- All received QoS 2 messages, which are not yet confirmed to the broker

That means even if the client is offline all the above will be stored by the broker and are available right after the client reconnects.

**Retained Messages:** A client subscribing to a topic pattern may not get any message on that topic until some other client publishes on it. Retained messages are PUBLISH messages with the retained Flag set to one. The broker stores locally the retained message and send it to any other client which subscribes to a topic pattern matching that of the retained message.

**Last Will Message**: The Last Will and Testament (LWT) notifies other clients about an hard disconnect by a specific client. Each client can specify its last will message when connecting to a broker. The broker will store the message until it detects client hard disconnection. The broker sends the message to all subscribed clients on the specific topic. The stored LWT message will be discarded if a client disconnects gracefully by sending a DISCONNECT message.

**Keepalive:** It is responsibility of the client to keep the MQTT connection active. Upon expiration of the keepalive, if no other interaction has happened with broker, the client ”pings” the broker which “pings it back”. PINGREQ and PINGRESP messages have null payload.