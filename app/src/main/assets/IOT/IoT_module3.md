# The Long Range Communication Technologies for IoT

## Cellular IoT Operators

They cost low both for operators (to deploy) and the users since they rely on unlicensed spectrum so they do not have to pay for the band. They consume very low energy and has an out of the box connetivity (no need for extra hardware to use them). The trade off is perfomance (mainly in data rate) for low cost and simplicity.

## LoraWAN Architecture (Lora = Long Range)

**End nodes** (sensing devices) are connected through wireless links to **Gateways** which are connected to the network servers through the usual interfaces (3G, ethernet...). We will focus on the wireless links between end nodes and gateway. These are association-less: end nodes are not associated to any gateway in order to increase the **simplicity**.

**LoraWAN Protocol Stack**:

<img src="file:///android_asset/IOT/img/img4.png" >

### Physical Layer

LoRa uses a proprietary spread spectrum modulation that is similar to and a derivative of Chirp spread spectrum (CSS) modulation. The spread spectrum **LoRa modulation** is performed by representing each bit of payload information by multiple chirps of information. The rate at which the spread information is sent is referred to as the symbol rate, the ratio between the nominal symbol rate and chirp rate is the spreading factor (SF) and represents the number of symbols sent per bit of information. LoRa can trade off data rate for sensitivity with a fixed channel bandwidth by selecting the amount of spread used (a selectable radio parameter from 7 to 12). Lower SF means more chirps are sent per second; hence, you can encode more data per second. Higher SF implies fewer chirps per second; hence, there are fewer data to encode per second. Compared to lower SF, sending the same amount of data with higher SF needs more transmission time, known as airtime. More airtime means that the modem is up and running longer and consuming more energy. The benefit of high SF is that more extended airtime gives the receiver more opportunities to sample the signal power which results in better sensitivity.

### Medium Access Control

LoRaWAN defines the communication protocol and system architecture for the network, while the LoRa physical layer enables the long-range communication link. LoRaWAN is also responsible for managing the communication frequencies, data rate, and power for all devices. Devices in the network are asynchronous and transmit when they have data available to send. Data transmitted by an end-node device is received by multiple gateways, which forward the data packets to a centralized network server. The network server filters duplicate packets, performs security checks, and manages the network. Data is then forwarded to application servers. The technology shows high reliability for the moderate load, however, it has some performance issues related to sending acknowledgements.

There are 3 types of end devices in LoraWAN:

- Class A: they have the highest Downlink latency (so the slowest downlink) but the smallest energy consumption
- Class B: medium downlink latency and medium energy consumption
- Class C: lower DL latency but the most energy consuming.

In reality we use almost never use Class C devices since they consume too much power.

LoraWAN uses ALOHA-like procedure to handle channel access and retransmissions. If a confirmed message is not acknowledged, the message is retransmitted after a random time-out (ACK_TIMEOUT).

### ALOHA Protocol

Time is continuous. The first packet in the transmission queue is transmitted as soon as ready. If the ACK does not come, the transmission is reattempted after a random number of slots X.

*Assumptions:*

- Stationarity: $S_{in}=S_{out} \to$ incoming traffic = outgoing traffic
- Traffic G distributed according Poisson process: packet arrivals is a Poisson point process with parameter $\lambda$. Transmissions last T. $G=T\times \lambda$

G traffic on the channel: transmissions + retransmissions so $S_{out}<= G$

The probability $P_S$ for a packet transmission to be successful is the probability that no other packet starts transmission in his "conflict" period 2T
$$
P_S=P(N(t-T,t+T)=0)=e^{-2G}
$$
The throughput is: $S=GP_S=Ge^{-2G}$

### LoraWAN Performance Evaluation

LoraWAN stations may have different SF so different transmission durations. If we have $N_1$ stations with arrival frequency $\lambda_1$ and transmission duration $T_1$ and $N_2$ stations with arrival frequency $\lambda_2$ and transmission duration $T_2$ the probability that a transmission of type 1 collides, $P_1$, is:
$$
P_1 \sim 1-e^{-(N_1\lambda_1)2T_1}e^{-N_2\lambda_2(T_1+T_2)}
$$
The probability that a generic transmission collides is:
$$
P=\frac{\lambda_1N_1}{\lambda_1N_1+\lambda_2N_2}P_1+\frac{\lambda_2N_2}{\lambda_1N_1+\lambda_2N_2}P_2
$$
The previous expression can be generalized in case of n different classes of stations with transmission durations $T_i$, scale $N_i$ and arrival frequency $\lambda_i$
$$
P=\sum_{i=1}^n \frac{\lambda_1N_i}{\sum_{i=1}^n \lambda_iN_i}P_i
$$
**Are Mobile Radio Networks IoT-ready?** Not really. MRN were designed to work with limited type of applications, the goal in MRN is to maximaze the throughput and operates with HighEnd terminal (such as smartphones, pc). IoT instead works with fragmented application which means many different type of traffic, the througput is not the only KPI and operates with simple (cheap) terminals.