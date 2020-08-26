# Negotiation Part 2 

***Protocols***

We will now focus on the agreement found by the agents, not on the policy.

We'll define a protocol, see how the agents behave in it, and then study the possible outcomes that will be reached by the agents.

Let's start with the first protocol:

### Rubinstein's Alternating Offer Protocol

I make an offer, the other agent makes a counter offer, I make a new offer, and so on, until we find some agreement.

We assume that we have 2 agents, $a,b$.

We assume there is one issue over which the agents are negotiating. It can be thought as an object that can be divided by the 2 agents.   
Let's think of a cake. It can be divided by the 2 agents. We'll call $x^a$ the piece of cake that goes to the agent $a$.   
$x^b$ is the part of the cake that goes to $b$.

we assume that $x^a+x^b=1$ (it forms the complete cake).

The set of outcomes is trivially the set of all the pairs $(x^a,x^b)$ such that $x^a+x^b=1$.

in math: $O={(x^a,x^b)|x^a+x^b=1}$ where $O$ stands for the set of possible outcomes.

We assume that time is evolving in discrete period: $t=1,2,...$.

We assume that players play in turn, with player $a$ playing first:  
==So agent $a$ makes offers in odd timestamps, while $b$ makes offers in even timestamps==. This is the core of "Alternating" Offer protocol.

the ones above are all and only the assumptions that the agents must have.

Let's talk about the utility of $a$: it depends on how big its part of the cake is and on the timestamp in which the offer is agreed.
$$
U^a(x^a,t)=x^a\cdot \delta_a^{t-1}
$$
where $\delta_a$ is the discount factor and $0\le \delta_a \le 1$

$\delta=1 \to$ the agent is very patient.

$\delta=0 \to$ the agent must accept the first offer otherwise it gets zero.

and obviously:
$$
U^b(x^b,t)=x^b\cdot \delta_b^{t-1}
$$
Rubinstein shows that it is possible to compute a ==subgame perfect equilibrium==:
$$
x^a=\frac{1-\delta_b}{1-\delta_a\delta_b}
$$

$$
x^b=\frac{\delta_b- \delta_{a}\delta_b}{1-\delta_a\delta_b}
$$

It means that the agent will not find convenient to deviate from these values.

we notice that if 
$$
\delta_a=0;\delta_b=1 \to x_a=0; \ x_b=1
$$

$$
\delta_a=1;\delta_b=0 \to x_a=1; \ x_b=0
$$

$$
\delta_a=0.5;\delta_b=0.5 \to x_a=\frac{2}{3}; \ x_b=\frac{1}{3}
$$

in the last case, the agent $a$ gets more cake because it started first.

The cases above have been computed considering that the negotiation can go on forever, but what happens if we have a deadline $t=n$ ? ($n$ is the last timestamp in which an offer can be done).

Let's suppose even that $\delta_a=\delta_b=\delta$

$n=1 \to$ agent $b$ will just accept, can't do anything else. So agent $a$ will propose $offer(1,0)$  
more precisely:
$$
\text{STRAT-A}(1)=\text{OFFER}(1,0)
\\
\text{STRAT-B}(1)=ACCEPT
$$
$n=2$  
We have 2 rounds, if you are agent $a$ what do you do? 

*non rational move*:

1. if $a$ offers $(1,0)$ $\to b $ rejects 
2. $b$ offers $(0,1)$ $\to a$ has to accept

so $a$ should not offer $(1,0)$ at the first round.

the last round is in power of agent $b$. 

So, if we consider the non rational move we would have $U^a(x^a,t)=U^a(0,2)=0$ and $U^b(1,2)=\delta$ . 

*rational move*:    
agent $a$ offers to $b$ a fraction equal to $\delta$, this way he would not makes sense for him to refuse. the game ends in time step $1$, without waiting for time step $2$.
$$
\text{STRAT-A}(1)=\text{OFFER}(1-\delta,\delta)\\
\text{STRAT-B}(1)=\text{ACCEPT}
$$
  which translates into:
$$
U^a(1-\delta,1)=1-\delta
\\
U^b(\delta,1)=\delta
$$
let's revise again what we have just written:  
The agent $a$ prefers to offer to $b$ $\delta$ part of the cake because the utility of $b$ can't be more than $\delta$ (in fact $U^b(1,2)=\delta$), because this way $a$ gets at least $1-\delta$ as utility, so it's a win win.

let's generalize:

deadline $n$:   
$\delta_a=\delta_b=\delta$

With "$a$'s turn" we mean that $n$ is odd.  
With "$b$'s turn" we mean that $n$ is even.  
$$
\text{STRAT-A}(n)=
\begin{cases}
\text{if a's turn: OFFER(1,0) }
\\
\text{if b's turn: ACCEPT}
\end{cases}
$$

$$
U^a(n)=
\begin{cases}
\text{if a's turn: }
\delta^{n-1}

\\
\text{if b's turn: }
0 \ \ \ \ \  \ 

\end{cases}
$$

$t<n$
$$
\text{STRAT-A}(t)=
\begin{cases}
\text{if a's turn: OFFER}(1-\delta x^b(t+1),\delta x^b(t+1))
\\
\text{if b's turn:} \text{ if } U^a(x^a,t)\ge UA(t+1) \text{ then ACCEPT else REJECT}
\end{cases}
$$

### Monotonic Concession Protocol

- agents $a,b$ ;  
- utilities $U^a,U^b$
- $O$
- simultaneous offer protocol
- $t=1,2,... $

From the point of view of agent $a$:

$\text{1. } x^{(a)}\leftarrow \arg\max_{x \in O}{U^a}(x)$  

$\text{2. propose }x^{(a)}$

$\text{3. receive }x^{(b)}$

$\text{4. if }U^a(x^{(b)})\ge U^a(x^{(a)}) \text{ then ACCEPT } x^{(b)} \text{ and RETURN}
\\
 \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \  \  \text{else }x^{(a)}\leftarrow x \in O \text{ such that }U^{b}(x)\ge U^b(x^{(a)}) \text{ and }U^a(x)\ge0
\\$

$\text{5. goto 2}$

This algorithm can be applied only when you know the utility of the other agent, which does not happen very often in the real world...

Moreover, the convergence of this algorithm can be very long. 

Another problem:  It can happen that, at the same time step, agent $a$ accepts the offer of agent $b$, and $b$ accepts the offer of $a$,  but they are not the same! we have two different agreements: we need to introduce some tie-breaking to decide the agreement. we can pickup just one at random, or we can find the middle point of the offer and come up with a single agreement.

There is a variant of this protocol that is called the Zeuthen Strategy.

### Zeuthen Strategy

This is the point of view of $a$

$\text{1. } x^{(a)}\leftarrow \arg\max_{x \in O}{U^a}(x)$  

$\text{2. propose }x^{(a)}$

$\text{3. receive }x^{(b)}$

$\text{4. if }U^a(x^{(b)})\ge U^a(x^{(a)}) \text{ then ACCEPT } x^{(b)} \text{ and RETURN}
\\$

$\text{5. }\text{risk}_a \leftarrow \frac{U^a(x^{(a)})-U^a(x^{(b)})}{U^a(x^{(a)})}; \text{risk}_b \leftarrow \frac{U^b(x^{(b)})-U^b(x^{(a)})}{U^b(x^{(b)})}$ 

$\text{6. if } \text{risk}_a <\text{risk}_b \text{ then } x^{(a)}\leftarrow x \in O \text{ such that }\text{risk}_a>\text{risk}_b$

$\text{7. goto 2}$

The idea is that you have a high risk when you are close to the situation in which your utility is very small, when you are close to the no-agreement. The agent that has the smallest risk will make the next offer.  
Property: ==the final agreement found via this algorithm is a pareto optimal agreement==, which means that there is no another agreement that is not worse for one agent and strictly better for the other one.

==it finds an agreements which is a Nash BARGAINING solution==. It is an agreement that maximizes the product of the two utilities.