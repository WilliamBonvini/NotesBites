# Computational Coalition Formulation - Part 1 

We are talking about cooperative games:  
agents are playing games in which there are two big differences with respect to classical games that are studied in game theory: 

- there is some benefit in cooperating
- if agents make some agreement, than this agreement is binding (there are ways outside of the game for enforcing this agreement)

In this kind of games, actions are taken by groups of agents.

We are going to look to a specific kind of cooperative game, called ***Transferable Utility Games***: *the utility is not individual, but given to the group of agents in the coalition* (such utility can be then divided within the coalition).

We are going to introduce the concept of ***characteristic function games***: *as a group my utility depends only on what I do, not on what other groups do*.  

### Characteristic Function & TU Games

$$
A=\{a_1,a_2,...,a_n\}  \ \ \ \ \ \ \ \ \text{are agents}
$$

$$
v:2^A\to \R \ \ \ \ \ \ \ \ \text{is our characteristic function}
$$

such function is called characteristic function and associates a real number to a specific coalition.

assumptions:
$$
v(\{\})=0  \\ v(C) \ge0 \ \ \ \ \ \ \ \ \   \forall C \subseteq A
$$
each $C$ is called coalition.

The whole set of agents $A$ is called sometimes *grand coalition*.

**Introductory Example**

There are 3 children.   
Charlie has 6 dollars.  
Marcy has 4 dollars.  
Patty has 3 dollars.  
Charlie wants to buy 500g of ice-cream and it costs 7 dollars. The others are explained mathematically.  
$w_c=500g; \ c_c=7 \$$  
$w_m=750g; \ c_m=9 \$ $    
$w_p=1000g; \ c_p=11\$ $  

$A=\{c,m,p\}$  
Let's write the characteristic function:

$v(\{c\})=v(\{m\})=v(\{p\})=0 $   
$v(\{c,m\})=v(\{c,p\})=750$  
$v(\{m,p\})=500$  
$v(\{c,m,p\})=1000$

We don't know though how these children will split the ice cream.  
Let's introduce the concept of ***outcome***.   
the outcome is a pair $(CS,\bar{x})$  where 

- $CS$ is the ***Coalition Structure***: simply a partition of the set of agents.  
  $CS=\{c_1,c_2,c_3,...,c_k\}$  
  $U_{i=1}^kc_i=A \ \ \ \ c_i \cap c_j=\{\} \  \forall 1\le i,j \le j$
- $\bar{x}$ is the ***Payoff Vector***, $\bar{x}=(x_1,x_2,...,x_n)$   
  $\sum_{i \in C}x_i=v(C) \ \ \ \forall C \in S$

**Another example**

$v(\{1,2,3\})=9$   
$v(\{4,5\})=4$  
$(\color{orange}(\{1,2,3\},\{4,5\})\color{black},\color{red}(3,3,3,3,1)\color{black})$  where the orange part is the $CS$ and the red one is $\bar{x}$.  
This outcome respects the efficiency principle which says that the sum of the individual payoffs should sum up to the coalition's payoff.

#### Superadditivity

An important property that a TU game can have is superadditivity:
$$
v(\{C \cup D\}) \ge v(C) + v(D)
$$
It is always convenient, overall, to form the grand coalition.   

I said overall because individual utilities could be lower than in other coalitions.  
In this case $CS=S= \text{grand coalition}$.

is the game $v(C)=|C|^2$ superadditive? yes it is.  

#### Convex Property

A game is convex when   
$v(C \cup D) +v(C \cap D)\ge v(C)+v(D) \ \  \forall C,D\subseteq A$

If a game is convex then it is even superadditive.

#### Simple Property

$v(C) \in \{0,1\} \ \forall C \in A$

### How to choose the payoff vector?

Let's modify a little the introductory example:

There are 3 children.   
Charlie has 4 dollars.  
Marcy has 3 dollars.  
Patty has 3 dollars.  
Charlie wants to buy 500g worth of ice cream and it costs 7 dollars. The others are explained mathematically.  
$w_c=500g; \ c_c=7 \$$  
$w_m=750g; \ c_m=9 \$ $    
$w_p=1000g; \ c_p=11\$ $ 

$v(\{c\})=v(\{m\})=v(\{p\})=0 $   
$v(\{c,m\})=v(\{c,p\})=500$  
$v(\{m,p\})=0$  
$v(\{c,m,p\})=750$

This game is superadditive so we can say that the grand coalition is going to form.

Now let's look how we can decide the payoff vector

$(A,(0,0,0))$ is possible? no, not efficient.  
$(A,(200,200,350))$: the payoff vector is possible but it's not a good one because Charlie and Marcy are going to get less than Patty. Charlie and Marcy can split from Patty and get more each ($250$ instead of $200$).  
We  say that the above outcome is not ***stable***.  
A stable outcome is captured though the concept of ***core***.

#### Core

$$
\text{core}=\{(CS,\bar{x})|\sum_{i \in C }x_i \ge v(C) \ \ \forall C \subseteq A\}
$$

The core embeds the idea of stability: the sum of payoffs of the agents in any sub-coalition $C$ is at least as large as the amount that these agents could earn by splitting from $A$ and forming $C$. 
So, given a certain $\bar{x}$ we check whether it is a solution acceptable by everyone.  
the payoff $(250,250,250)$ is in the core because, for example, $250\ge 0$ if we consider $C=\{1\}$.  
Even the payoff $(750,0,0)$ is stable, but it is not **fair**!  
Even $(250,250,250)$ is not completely fair because Charlie has more money than the others two so he should get more ice cream.

***Example about stability***

$A=\{1,2,3\}$  
$v(C)= \begin{cases} 1 \ \ if |C|\ge 2 \\ 0 \ \ otherwise \end{cases} $

This game is superadditive and it has no solution in the core.  
The core can tell you if a payoff vector is stable but it cannot tell you if it is not fair.  
Property: ==*the core of a convex game is never empty*==.  

#### Shapley Value

the Shapley value embeds the idea of fairness

***Example about fairness***

$A=\{1,2\}$  
$v(\{1\})=v(\{2\})=5$  
$v(\{1,2\})=20$  
The game is superadditive.  
A fair outcome is $((\{1,2\}),(10,10))$, but even $((\{1,2\}),(5,15))$ and many more.

***Shapley Value***

Let's introduce some terminology:

$\Pi^A=\text{permutations over }A $ (it is an ordering over the agents)

$ c_{\pi}(a_i)\text{the number of the agents that in the ordering p are appearing before agent $a_i$}$ 

$\Delta_\pi(a_i)=v(c_\pi(a_i)\cup \{a_i\})-v(c_\pi(a_i))=\text{marginal contribution of agent $a_i$}$

The formula is:
$$
\varphi_i=\frac{1}{n!}\sum_{\pi \in \Pi^A}\Delta_\pi(a_i)
$$

This was the formula provided by Amigoni, but I prefer the following:
$$
\varphi_i(v)=\sum_{S\subseteq N  \backslash \{i\}}\frac{|S|! \ (n-|S|-1)!}{n!}(v(S \cup \{i\})-v(S))
$$

with $n=|A|$

<div style="page-break-after:always"></div>