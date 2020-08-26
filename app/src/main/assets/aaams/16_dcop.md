# Distributed Constraint Optimization

### Constraint Network

$$
X=\{x_1,x_2,...\} \text{ variables}
$$

$$
D=\{D_1,D_2,...\} \text{ domains}
$$

$$
C=\{C_1,C_2,...\} \text{ constraints}
$$

***Hard constraint***  
relation that enumerates every combination of values for the variables involved in the constraint that are allowed.

- $C_i$ hard $R_i \subseteq D_{i1}\times \dots \times D_{ik}$  
  Scope of $C_i$: $x_{i1},x_{i2},...,x_{ik}$
- $C: x_ix_j,$  $D_i=D_j=\{0,1\}$  
- $R=\{(0,1),(1,0)\}$ list of combinations that are valid.  
  $R$ is the relation representing an hard constraint.

***Soft constraints***  
It's a function that has as domain the cartesian product of the domains and as codomain the set of real numbers.  
*Example*  
$C:x_ix_j,$      $D_i=D_j=\{0,1\}$

$R=\{(0,1),(1,0)\} $  

$F:$

| $x_i$ | $x_j$ | F    |
| ----- | ----- | ---- |
| 0     | 0     | 2    |
| 0     | 1     | 0    |
| 1     | 0     | 0    |
| 1     | 1     | 1    |

$F$ is a cost (or a utility, it depends on the formulation).

### Binary Constraint Networks

every constraint network could be translated in a binary constraint network by adding variables and constraints.

binary relations can be represented as a graph where edges are the constraints and the nodes are the variables:  
<img src="img/29112.PNG" style="zoom:40%">

Given a constraint network, the solution is an assignment such that an objective function is optimized. In particular, the objective function to be optimized is the sum of the soft constraints
$$
\sum_iF_i
$$
(Maximize or minimize accordingly to the fact that soft constraints are utilities or costs)

### Distributed Constraint Optimization Problem - DCOP

Defined by 
$$
A=\{A_1,A_2,...\}  \  \text{set of agents}
$$

$$
\text{a constraint network}
$$

- In a DCOP each agent can control one variable (in general a set of variables).  
  Variable controls cannot be shared.
- Each agent knows only about the constraints that involve its variable/s.
- An agent can only communicate with its neighbors (with agents that share a constraint).

***Optimal Algorithms***

This algorithm guarantees to find the optimal solution, but its complexity is somehow exponential.  
$2$ types:

1. exponential number of messages to be exchanged by agents (adopt)
2. exchange small number of messages but every message can be exponentially large (DPOP).   
   The size of the messages grows exponentially.  
   This algorithm does not scale to realistically large problems. 
   we will study this last type.

***Suboptimal Algorithm***

Algorithm that does not guarantee to find the optimal solution but it is efficient.

#### DPOP

Let's make it clear: DPOP is a possible algorithm to solve a problem called DCOP.

DPOP stands for Dynamic Programming Optimization Protocol.

<img src="img/29113.PNG" style="zoom:40%">

where $F_{ij}$ is a utility.

We have $4$ variables, which translates into having $4$ agents.

The goal is to select the values for each variable such that the sum of the soft constraints is maximized (because it is a utility perspective, not a cost one in this case).

solution: give $0$ to every variable.

Who are the neighbors of agents $x_3$? $x_1$ and $x_2$.  
In fact, $x_3$ knows about $F_{2,3}$ and $F_{1,3}$, and he does not know about the existence of $x_4$.

Find a DFS (Depth First Search) arrangement:  
build a tree (pseudo tree) starting from the constraint graph:

- start from a node
- go in depth with $1$ node

<img src="img/29114.PNG" style="zoom:40%">

I can go no more in depth so I start backtracking:

(I can build different trees)

<img src="img/29115.PNG" style="zoom:40%"> <img src="img/29116.PNG" style="zoom:40%">

Is the tree on the left completely representing the graph? no, so we introduce *pseudo arcs*.

<img src="img/29117.PNG" style="zoom:40%">

terminology:

- *induced width*  
  the maximum number of parents and pseudo parents a node can have in a pseudo tree

$x_2$ has $1$ parent

$x_3$ has $2$ parent

$x_4$ has $2$ parent

$x_1$ has $0$ parent

so the induced width is $max(2,2,1)=2$ (considering $x_1$ would be useless).

##### DPOP Computation

1. utils propagation:  
   start from the bottom of the tree and report some "util messages".
2. value propagation:  
   start from the top and send down some "value messages".

*Aim of propagating values messages from top to bottom:*  aggregate info from the bottom to allow agents at the top to make an optimal decision.

*Value messaging*:  
once the top agents make the best decision allow other agents to do the same.

***Separator***

$Sep_i:$  set of nodes that contains parent and pseudo parents of $i$

<img src="img/29117.PNG" style="zoom:40%" >

<img src="img/24011.PNG" style="zoom:40%" >  
<img src="img/24012.PNG" style="zoom:40%" >  
<img src="img/24013.PNG" style="zoom:40%" >  
<img src="img/24014.PNG" style="zoom:40%" >  

