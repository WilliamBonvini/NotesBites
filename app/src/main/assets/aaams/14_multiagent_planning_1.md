# Multi Agent Planning - Part 1

There is not a local objective for each agent but a global goal for the system.

We can say that agents are cooperative/benevolent.  
We'll be building a plan: a sequence of actions.

*You need to build a plan when your choice or the action that you perform now will affect the action you will perform far in the future.*

***2 big areas of planning***

- <u>Path planning</u>   
  you have a physical environment and you want to find a path in this environment.   
  It can be geometrical or a graph.   
  You have a sequence of actions/vertices that you have to visit one after the other. 
- <u>Task planning</u>   
  the actions are done to perform a task (example: moving a block from one place to another). 

 

 ***2 ways to represent a plan*** 

- <u>Explicitly</u> 
- <u>Implicitly</u>   
  by using a policy.   
  A plan is a policy in Markov decision process. 

### Single agent planning

you can have a planning model like STRIPS or like Markov decision problems.    Kind of knowledge you have to use in both:    
you need to know a model of the environment and a model of how your actions change the environment. 

For single agents this is difficult because the space in which you have to look for your plan grows exponentially with the number of steps of the plan $\to$ the problem of planning is hard for single agents.

### Multi agent planning 

When the action of an agent performed now affects the actions of the agents in the future.   
What we need:

- Model of the environment 
- Model of how your actions impact the environment 
- Some models of the other agent’s plan (what the other agents are doing)

The space of possible plans grows in the number of possible actions and also in the number of agents. 

<u>Features of multi agent planning</u>

1. Agents are cooperative.  
   They try to maximize some joint performance measure, there is a global utility for the system that depends on the joint action performed by the agents.
2. There are some things that depend on single agents and other from a bunch of agents. 
3. Locality.  
   when you plan a sequence of actions for one agent, and for the second, and for the third, one action, performed by one agent, will affect the actions of a small amount of agents. 

 ***Multi Agent Planning Possibilities***

Two main possibilities:

1. You have a multi agent aspect in producing the plan:   
   ==every agent comes up with its own plan==. 
2. You have a multi agent aspect in executing the plan:   
   ==I have a central entity (a supervisor) that builds the plan for the three agents==.   
   Then I will assign this plan to agents and they will execute the plan in a distributed way. 

A combination of the two is also possible. 

*We will have a centralized planner and distributed execution*

***How can we build a plan for several agents?***

$\downarrow$

#### Families of approaches to Multi Agent Planning

- <u>Coordination prior to Local Planning</u>   
  I will set at the beginning some rules for coordination.   
  Then I will leave the agents decide their local plans.      
  I’m assuming that the local plans are built by the agents.   
  The coordination between local plans is achieved because everybody respects some (traffic) rules   
  $\to$ "traffic rules" are "social laws" = rules that everybody knows.   
  Somebody (the designer) has established social laws. Everybody in the system knows that and builds their plans according to social laws.   
  If social laws are well designed, when agents build their local plan, this will be coordinated.   
  You also have to assume that everybody else knows the traffic rules and assumes that everybody knows that you know the traffic rules $\to$ common knowledge.   
  ***Organizational structure***: this is what is done in companies: I impose some constraints on the freedom of the local plan. Rules are established first, if rules are good, everything is then coordinated.
- <u>Local Planning prior to Coordination</u>  
  At the beginning agents make plans as they want, then they are merged and modified to make them coordinated.  
  Different approaches to modify the plan: Freedom in planning $\to$ then modify. 

*In both cases we are trying to detach the problem of planning and the problem of coordination.* 

- <u>Decision Theoretic Multi Agent Planning</u>   
  We interleave at the same time local planning and coordination $\leftarrow$ *we will see this in details*
- <u>Dynamic multi agent planning / Replanning / Interleaving Planning and Execution</u>   
  Each agent builds its own plan, every agent starts executing his plan.   
  Agents decide what to do if something happens. 

####  Stochasticity in Multi Agent Planning

*Elements and observations:*

- transition function:   
  next state determined by current state + joint actions of all the agents  
  $\to$ I can provide a probability distribution over the next state

- The environment is not directly observable:   
  Agents just receive some observations about the environment.   
  They don’t know in which state the environment is. 
- There is a single reward function for the whole system which the agents try to maximize. 

#### DecPOMDPs 

*DECentralized Partially Observable Markov Decision Problems*.

- $\text{Set of Agents I }= \{1,2,...,n \} $  

- $\text{Set of States: }S=\{1,...\} \text{ not known by the agents directly} $  

- $\text{Set of Actions agent $i$ can perform: }A_i$  

  with $\bar{A}=\otimes_{i=1,..,n}A_i$ being the set of joint actions, and $\bar{a}$ denotes a joint action.   

- $\text{Transition Function: }P:S\times \bar{A}\to\Delta S: \text{Set of all the probability distributions over the set $S$}$  
  $P(s'|s,\bar{a})$ is the probability  of reaching $s'$ being in $s$ and performing the joint action $\bar{a} $ 

- $\Omega_i$ is a finite set of observations available to agent $i$ , and $\bar{\Omega}=\otimes_{i=1,..,n}\Omega_i$ is the set of joint observations, where $\bar{o}=<o_1,...,o_n>$ denotes a joint observation.

- $\text{Observation Function $\to$ }O:\bar{A}\times S \to \Delta \bar{\Omega}$  
  $O(\bar{o}|\bar{a},s')$ denotes the probability of observing joint observation $\bar{o}$ given that joint action $\bar{a}$ was taken and led to state $s'$.  Here $s' \in S,\bar{a}\in \bar{A}, \bar{o} \in \bar{\Omega}$.

- $\text{Reward Function} \to R(s,\bar{a})$  
  What is the immediate reward the system gets when the world is in $s$ and agents are jointly performing $\bar{a}$.  
  $R$ is global, it's the system's reward. Single agents do not have a reward.

This model is *Partially Observable* because the states are not known to the agents. 

***Example***

You can find a clear explanation of the setting of this example in section 5.1 of this [pdf](https://www.google.it/url?sa=t&rct=j&q=&esrc=s&source=web&cd=18&cad=rja&uact=8&ved=2ahUKEwjApNDww5TnAhVCLFAKHVTFCIEQFjARegQIARAB&url=ftp%3A%2F%2Fftp.cs.brown.edu%2Fpub%2Ftechreports%2F96%2Fcs96-08.pdf&usg=AOvVaw11nB1VK1yw8m5u4TwSM2fL) (it is for the single agent version though).

The are 2 doors, behind one there is a tiger, behind the other a treasure. 

If one agent opens the door with the tiger, both die $\to$ large penalty

If one agent opens the door with the money $\to$ they share the treasure

Immediately after one agent opens a door and receives a reward or penalty, the problem resets, randomly relocating the tiger behind one of the two doors.

$I=\{1,2\} \text{ agents}$

$S=\{T_L,T_R\}$ (the tiger is behind the left door or behind the right door)

$A_i=\{\color{red}O_L\color{black},\color{orange}O_R\color{black},\color{purple}L\color{black}\}$ 

$\color{red}\text{Open Left door}$, $\color{orange}\text{Open Right door}$, $\color{purple}\text{Listen}$.

$\bar{A}=\{<O_L,O_L>,<O_L,O_R>,...\}$

Transition function:  

$P(T_L|T_L,<L,L>)=1 \to$ probability that in the new setting of the world the tiger is behind left, given it was behind left and both agents were listening.

$P(T_R|T_L,<L,L>)=0$

$P(T_L|T_L,<OR,L>)=0.5 \to$ the game restarts randomly when a door is opened, like a next episode.

$P(T_R|T_L,<OR,L>)=0.5 $

$\Omega_i=\{hl,hr\}$ (the agent hears the tiger behind the left or the right door)

$O(<hl,hr>|<L,L>,T_L)=<0.85,0.15> \to$ the tiger is behind the left door. both agents were listening. Agents have the right perception (***h***ear the tiger on the ***l***eft) with probability $0.85$.

$R(<L,L>,*)=-2 \to$ both perform the listening action independently from where the tiger is. Listening has a cost.

$R(<L,O_R>,T_R)=-101$

continues in *Multi Agent Planning Part 2*.