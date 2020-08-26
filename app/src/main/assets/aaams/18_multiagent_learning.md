# Multiagent Learning

### Introduction

The difference with what we have seen before is that now we have $n$ reward functions.

This is the last topic of the course.

your system is operating in some context and you'd like it to adapt to the particular contest and change it if the contest changes. this is the general idea of single agents.  
What changes in multi-agent? 

- very big space of states ( the space of joint actions is way larger with respect to the space of single actions)
- there is a problem of credit assignment (both in single and multiagent: problem of distributing the rewards over the sequence of actions that you have performed. this problem is still true in multiagent.
- you don't have only a problem of assigning a credit over a sequence of actions but you even have to assign a credit to agents based on how much they contributed to the result

***Factoredness and Learnability - two complementary concepts***

aspects of multi agent learning:

- it can happen that the local goal of an agent is aligned to the global goal *(Factoredness)*
- *learnability*: how much the actions performed by one agent affect the goal of that agent, or how much the goal of an agent depends on the actions that that agent perform.  
  example: 
  - if every agent is acting in a way that is completely independent from the other agent, this means that learnability is at maximum. (what I will do is just to maximize my local utility).  
  - in the opposite case we have that the factoredness is at maximum and learnability is at minimum.

we'll introduce a multi agent version of Markov decision processes.

we have a system that through a process of trial and error tries to perform better in a specific environment. in order for the model to work we need the environment to be episodic.

### Stochastic Markov Games

this is the model that we will consider.

we assume

- $n$ agents.
- there is a world that can be in a set of different states $S$
- for each agent we have a set of actions that such agent can perform. $A^1,...,A^n$ where $A_1 $ is the set of actions that agent $1$ can perform and so on.
- transition function $p:S\times A^1\times \dots \times A^n\to\Delta(S)$.  
  given a state of the world $s$ and a joint action it will return a probability distribution over the next states of the world.
- there are $R$ reward functions, one for each agent: $r^1,r^2,...,r^n$.  
  $r^i:S\times A^1 \times A^2 \times \dots \times A^n \to R $

in other models we assumed that all the info above are given. now we assume that the agent does not know what are the reward functions and the transition function.

a policy $\pi^i:S \to A^i$ tells, given a state, what is the action that agent $i$ should perform (we are considering the case that policies are deterministic, but it does not need to be the case. policies could be defined as probability distributions over states)

let's define a value function:
$$
v^i(s,\pi^1,\pi^2,...,\pi^n)=\sum_t{\beta^t}E[r^i_t|\pi^1,\pi^2,\dots,\pi^n,s_0=s]
$$
where $\beta$ is the discount factor, $t$ is time, $s_0$ is the initial state.

 Example:

The goal of agent 1 is to reach docking station $1$.   
Agents receive a negative reward if they bump into each other.
$$
A^i=\{W,E,N,S\}
\\
S=\{(0,0),(0,1),(0,2),...\}
$$
<img src="img/6121.PNG" style="zoom:40%">
$$
p((0,2),E,W)=(0,2) \\
p((0,2),N,N)=(3,5) \\
\dots
$$

$$
r^i=\begin{cases}100 \  \text{if one agent reaches its goal} \\ -1 \ \text{if the agents try to go to the same cell} \\ 
0 \ \text{otherwise}

\end{cases}
$$

agents do not know reward and transition functions a priori.

we do not care who reaches his station, but that one of them does.

Nash equilibrium: a set of joint policies

 $(\pi_*^1,...,\pi_*^n)$ such that 
$$
v^i(s,\pi_*^1,\dots,\pi_*^i,\dots\pi_*^n)\ge v^i(s,\pi_*^1,\dots,\pi^i,\dots\pi_*^n) \ \\ \\ \ \  \forall   \pi^i, \forall s, \forall i
$$
The goal is to learn policies that are in equilibrium.  
Reach an equilibrium state is a goal, there is not global utility.

It is possible to prove that each stochastic game admits a NE. 

let's now define policies in such a way that they are dependent only on the agent using it (we do it for agent $1$): 

| $s$   | $\pi^1$ |
| ----- | ------- |
| (0,-) | N       |
| (3,-) | E       |
| (4,-) | E       |
| (5,-) | N       |

in this case we avoided to specify the distribution on actions for each state because the environment is ***deterministic*** and I know what happens deterministically once I find myself in a state and perform an action (I mean, I do, not the agent). It is not always the case, usually it is not deterministic, so one can have an intended path but it's not sure it will be walked.

now imagine we write a policy for agent 2 that says: go up,up,left,left.

this two policies are a Nash equilibrium. in this case there are many Nash equilibria (agent 2 can keep on doing left,right,left,right and it would still be a NE).

When the environment is deterministic it is really easy to compute function $v^i$ because we get rid of the expected value (we have an exact value for each timestep).