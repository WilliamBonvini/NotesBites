# Computational Social Choice - Part 1

Applications:

- Aggregation of preferences.
- Formation of coalitions.
- Sometimes you have multiple robots that have to keep a formation, for example military patterns.  
  It is based on the fact that there is a leader that is moving and all the other robots are keeping a fixed distance and angle from him. They should also avoid to collide with each other.  
  What happens if the leader fails? In this case there is a kind of voting for choosing the new leader, and this is the social choice.

Let's start from the model that we will adopt for studying computational social choice.

we'll have:

- a number of ***agents*** $N=\{1,2...,n\}$
- a set of **alternatives**, also called **candidates** (in the case of political elections for example)  $U$. we assume $|U|\ge2$.
- ***preferences***, denoted by the symbol ==*preference relation*== $>_{\sim i}$ (it is an ordering over the alternatives).  
  for it being a proper ordering we need to constrain such relations in the following way. we need to say that such relations are 
  - complete: $\forall a,b \in U: a >_{\sim i } b \text{ or } b >_{\sim i}a  $  (the or is not generally exclusive)
  - transitive: $\forall a,b,c \in U: \text{if } a >_{\sim i } b \text{ and } b>_{\sim i }c \text{ then }  a >_{\sim i } c$  

Observations: agents and candidates could be disjoint or not depending on the case. In political elections, agents are people and politicians, while candidates are politicians.

Now let's introduce some symbols:

- ***preference profile*** $R=(>_{\sim 1 },>_{\sim 2 },>_{\sim n })$ (it is an array of preferences, one for each agent).  
  a preference can be structured as a pair of agents, candidate. So a preference profile can be represented as a list of pairs.
- a ***set*** of all possible ***preference relations over $U$*** :   $\scr{R}$$(U)$   
  $>_{\sim i}$ $\in$ $\scr{R}$$(U)$

Mathematicians game:  
We are at a party in which people can drink only one beverage.  
6 mathematicians want to drink, in preference order, milk,wine,beer.  
5 mathematicians want to drink, in preference order, beer,wine,milk.  
4 mathematicians want to drink, in preference order, wine, beer,milk.  
So:

- we have 15 agents: $n=15$.  
- we have 3 alternatives: $|U|=3$
- for the mathematician number $1$, who belongs to the 6 mathematicians cited first, we'll have   
  $\text{milk}$$>_{\sim 1}$ $\text{wine}$ $>_{\sim 1}$$\text{beer}$.  and so on for all the mathematicians.

It is not easy to aggregate together different preference relations with a global choice that is satisfactory for all people. It depends even on the mechanism of voting.

### Social Welfare Function

It is a function that given a preference profile returns a preference relation.

$f: \scr R$$(U)^n \to \scr R$$(U)$ 

$f(R) \ = \ >_\sim$

This is what the function should do, there are different ways to implement it.

We want this function to have the following properties

- <u>Pareto Optimality</u>  
  Informal definition: assume that every agent thinks that candidate $a$ is better than $b$. then it is reasonable to assume that $a$ will be better than $b$ even in the global relation.  
  Formal definition:   
  $\text{if  }\  \forall i \ \ a >_i b  \ \text{ then }a >b$  
  the one above has been written assuming the following: $a>_{\sim i}b \text{ but not }b >_{\sim i} a$, which is not the general case.  
  the consequence assumes instead that: $a>_\sim b \text{ but not } b >_\sim a$.  
  I had to write the formal definition this way because the actual formal definition would be too complex to be written, so Amigoni preferred specifying the assumptions afterwards.

- <u>Independence of irrelevant alternatives (IIA)</u>    
  imagine this starting situation
  $$
  f \begin{cases}
  \color{red}...\color{black}>_{\sim 1}a>_{\sim 1}\color{yellow}...\color{black}>_{\sim 1}b>_{\sim 1}...
  \\
  ...>_{\sim 2}a>_{\sim 2}...>_{\sim 2}b>_{\sim 2}...
  \\
  ...>_{\sim 3}a>_{\sim 3}...>_{\sim 3}b>_{\sim 3}...
  
  \end{cases} 
   \ \ \ \ \ \ \ \ \ \ \ \ \Bigg\}\to ...>_{\sim }a>_{\sim }...>_{\sim }b>_{\sim }...
  $$
  In a nutshell we want to say that if you swap the red and yellow elements, and, as we can see, $a$ and $b$ have the same ordering over all the agents, and in case $a$ and $b$ have anyways the same order in the global relation, we can say that our formulation has IIA.

  In case of IIA the ordering of $a$ and $b$ in the global relation does not dependent on how it is called the yellow element.  
  Formally:  
  $\text{if } R|\{a,b\}=R'|\{a,b\} \text{ then } >_\sim|\{a,b\}=>_\sim '|\{a,b\} \text{ where }f(R)=>_\sim \text{ and } f(R')=>_\sim ' $

- <u>Non-Dictatorship</u>  
  A good social welfare function is such that there is no such an agent which imposes the ordering over the other agents. This is Non-Dictatorship.  
  Formally:  
  $\text{there is no }i \in N \text{ such that } f(R)=>_{\sim i} \forall>_{\sim i}$

#### Arrow's Theorem

There is no any social welfare function that satisfies the 3 properties above when you have at least 3 candidates.

The property that is mostly dropped is the IIA.

### Social Choice Function

The Social Welfare Function can be too powerful in many situations. take for example the problem of electing 3 new members in parliament over 10 possible candidates. you just need to know the 3 most voted ones, you don't care about the others.

So now we'll define the social choice function

$f:{\scr R }(U)^n \times {\scr F}(U) \to {\scr F}(U) $

where $\scr F$ is the powerset of $U$: ${\scr F} = 2 ^U$, which is the set of all feasible sets of alternatives.

$A \in {\scr F}(U), \ A \subseteq U$

$f(R,A)=A', \ \ \ \ \ \ A' \subseteq A$

#### Voting Rule

one special case of the social choice function is the ***voting rule***.

Given a preference profile it returns a set of candidates.

$f:{\scr R}(U)^n \to {\scr F}(U)$  
$f(R)\to A' $

$f$ is resolute when it returns exactly one candidate: $|f(R)|=1$