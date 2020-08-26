# Computational Coalition Formulation - Part 2 

$(A,v)$ with $A=\{a_1,...,a_n\}$

$(CS,\bar{x})$

$\varphi_i=\frac{1}{n!}\sum_{\pi \in \Pi^A}\Delta_\pi(a_i)$

***Shapley Value's Properties***

- <u>Efficiency</u>  
  $\varphi_1+\varphi_2+\dots+\varphi_n=v(A)$  
  a *Dummy Player* (null player) is an agent $a_i$ such that:  
  $v(C)=v(C \cup \{a_i\}) \ \ \ \ \forall C \subseteq A \ \ a_i \notin C$  
  If we have a dummy player then its Shapley Value is $0$.

- <u>Symmetry</u>  
  players $a_i,a_j$ are symmetric if and only if   
  $v(C \cup \{a_i\})= v(C \cup \{a_j\}) \ \ \ \ \forall C \subseteq A \ \ \ \ a_i,a_j \notin C$  
  In this case *their Shapley Values are equal*.

- <u>Additivity</u>    

  Consider $3$ different games  
  $1: (A,v_1)$  
  $2:(A,v_2)$    
  $3:(A,u=v_1+v_2)$  
  $\to$ $\varphi^1_i+\varphi^2_i=\varphi^3_i$

==Shapley Values are the only way to distribute the payoffs that respect all these $3$ properties.==

Can we have a situation in which we can not compute the Shapley Values?  
No, we can always compute a payoff vector with such values.

Is this payoff vector always stable? no, but:

==for convex games, the payoff vector composed of Shapley values is always stable.==

In case of convex games we can say that fair outcomes are also stable.

***Biggest issues***

It's related to the representation of the characteristic function games $(A,v)$.

for every subset of agents $S$, $v$ should return a number. So if we have $100$ agents we need $2^{100}$ numbers to be returned.

The problem is solved If the game is additive. It means that I have to compute $v$ for each agent $\to$ consequently a coalition made of agents $1$ and $2$ will be the sum of the values associated to $1$ and $2$ separately.

### Best Coalition Structure

Let's assume we have $3$ agents: $A=\{a_1,a_2,a_3\}$.   
What are the possible coalitions?
$$
\{a_1\},\{a_2\},\{a_3\},\{a_1,a_2\},\{a_1,a_3\},\{a_2,a_3\},\{a_1a_2a_3\}
$$
How many possible coalition structures do we have? (a coalition structure is a partition of the set of agents)
$$
\Big\{\{a_1\}\{a_2\}\{a_3\}\Big\}
$$

$$
\Big\{\{a_1,a_2\}\{a_3\}\Big\}
$$

$$
\Big\{\{a_1\}\{a_2,a_3\}\Big\}
$$

$$
\Big\{\{a_2\}\{a_1,a_3\}\Big\}
$$

$$
\Big\{\{a_1,a_2,a_3\}\Big\}
$$

In general, what is the number of coalition structures? it is a number $n^{n/2}\le\alpha\le n^n$.   
It is a very large space in which we are looking for.  
Let's define the characteristic function for each coalition, because we'll need it for finding the best coalition structure.

$v(\{a_1\})=30$

$v(\{a_2\})=40$

$v(\{a_3\})=25$

$v(\{a_1,a_2\})=50$

$v(\{a_1,a_3\})=60$

$v(\{a_2,a_3\})=55$

$v(\{a_1,a_2,a_3\})=90$

This game is not superadditive so the grand coalition is not going to form $\to$ we need to find the best coalition structure.    
The best coalition structure is 
$$
CS^*=\arg\max_{CS\in P^A}\sum_{C \in CS}v(C)
$$
So, for this example we have:
$$
\Big\{\{a_1\}\{a_2\}\{a_3\}\Big\}\to90
$$

$$
\Big\{\{a_1,a_2\}\{a_3\}\Big\} \to 75
$$

$$
\Big\{\{a_1\}\{a_2,a_3\}\Big\}\to85
$$

$$
\Big\{\{a_2\}\{a_1,a_3\}\Big\} \to100
$$

$$
\Big\{\{a_1,a_2,a_3\}\Big\}\to90
$$

We select the last but one coalition!

The problem is that if the agents are a lot this is not easily computable because the dimension of $P^A$ would be very huge.  
How to find a more efficient solution?  
There are two possible algorithms:

- Centralized Algorithm
- Distributed Algorithm

#### Centralized Algorithm based on DP

It is based on dynamic programming.  

Notation:

$f(C)=\text{the value of the optimal partition of $C$}$, where $C$ is a coalition.

$t(C)= \text{ the optimal partition of $C$}$
$$
f(C)=	\begin{cases}v(C)  \ \ \ \ \ \  \text{         if $|C|=1$} 
\\
\max\Bigg\{v(C),\max_{\{C',C''\}\in P^C}\Big\{f(C')+f(C'')\Big\} \Bigg\} \ \ \ \ \ \text{otherwise}   
\end{cases}
$$
It is just enough if you iterate over all the possible partitions in two sets.  

We'll exploit this result in the following algorithm:

We'll compute all $f$ and $t$ for all the coalitions of one agent. then we compute such values for all coalitions of two agents, and so on until we consider the coalition with $n$ agents.

| $C$               | $ f(C) $ | $ t(C) $              |
| ----------------- | -------- | --------------------- |
| $\{a_1\}$         | $30$     | $\{a_1\}$             |
| $\{a_2 \}$        | $40$     | $\{a_2\}$             |
| $\{a_3 \}$        | $25$     | $\{a_3\}$             |
| $\{a_1,a_2\}$     | $^*=70$  | $\{a_1\},\{a_2\}$     |
| $\{a_1,a_3\}$     | $60$     | $\{a_1,a_3\}$         |
| $\{a_2,a_3\}$     | $65$     | $\{a_2\},\{a_3\}$     |
| $\{a_1,a_2,a_3\}$ | $**=100$ | $\{a_2\},\{a_1,a_3\}$ |

I'm going to explain just one of the cases in which $|C|=2$. in particular it is the one for which  $C=\{a_1,a_2\}$ 

$^*=$ we are no more in the first case of $f$ because the cardinality is $2$, so let's do the other case:  
We have just one possible pair $(C',C'')$, so:
$$
\max\Bigg\{v(\{a_1,a_2\}),\max\Big\{f(\{a_1\})+f(\{a_2\})\Big\} \Bigg\}
$$

$$
\max\Bigg\{v(\{a_1,a_2\}),\bigg(f(\{a_1\})+f(\{a_2\})\bigg) \Bigg\}
$$

$$
\max\Bigg\{50,\bigg(30+40\bigg) \Bigg\}=70
$$



$** = $ Let's consider $C= A$:

We have $3$ possible pairs $(C',C'')$, so:
$$
\max\Bigg\{v(\{a_1,a_2,a_3\}),\max\Big\{f(\{a_1\})+f(\{a_2,a_3\}) \ , \ f(\{a_2\})+f(\{a_1,a_3\}) \ , \ f(\{a_3\})+f(\{a_1,a_2\}) \Big\} \Bigg\}
$$

$$
\max\Bigg\{90,\max\Big\{30+65 \ , \ 40+60 \ , \ 25+70 \Big\} \Bigg\}
$$

$$
\max\Bigg\{90,\max\Big\{95 \ , \ 100 \ , \ 95 \Big\} \Bigg\}
$$

$$
\max\Bigg\{90,100 \Bigg\}=100
$$



Drawbacks:  

- The number of rows of the table are exponential.  
- I've to look at all possible partitions in each subset (I'm talking about the inner maximum operator)

Observations:

- sometimes $v$ is not equal to $f$. this means that making a partition out of a coalition sometimes is convenient! 

#### Distributed Algorithm (Shehory - Kraus)

$(1) \ C_i\leftarrow \text{coalitions that include $a_i$}$

$(2) \ C_i^*\leftarrow \arg\max_{C \in C_i}\frac{v(C)}{|C|}$  

$(3) \ \text{broadcast}(a_i,C_i^*),\text{receive}(a_j,C^*_j)$

$(4) \ C_{max}\leftarrow \text{largest set of agents such that, for all $a_j \in C_{max}$},(a_j,C_{max})$

$(5) \ \text{if }a_i \in C_{max} \text{ then join $C_{max}$ and return }$

$(6) \ \text{delete from $C_i$ coalitions that contain agents in $C_{max}$}$

$(7) \ \text{goto}  \ (2)$

This algorithm is performed by all agents in parallel knowing the same information.

We'll talk about it next time.