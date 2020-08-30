# Introduction

***Exam mode:*** 4 questions with exercises and theory. 1 hour and a half.

### Definitions

- ***Agent***  
  Anything able to interact with the environment.  
  *Book's Definition*:  
  Systems that can decide for themselves what they need to do in order to satisfy their design objectives.

- ***Interaction***  
  Action made by the agent perceived through sensors.  
  Actions are made through interactors (wheels, arms)

- ***Agent as a software agent***  
  A program interacting in a virtual environment .  
  *Sensors* can be 

  - reading files
  - streaming of data

  *Actions* can be writing

  - writing a file
  - say something to the user
  - deleting something
  - sending some data

- ***Rational Agent***   
  An agent that is able to make the right decision based on what he knows.  He is able to optimize some performance measure.    

In developing a single agent we can have several developing solutions:

A typical way to implement an agent is to make him able to plan $$\to$$ Reinforcement Learning.  
Single agents area able to maximize some utility function. 

### Autonomy

***Book's definition***: 
"Agents that must operate robustly in rapidly changing, unpredictable, or open environments, where there is a significant possibility that actions can fail are known as intelligent agents, or sometimes autonomous agents"  

***Lecture's notes***

Autonomy is an indicator of how strict the program is imposing what the agent has to do.   
If I'm a designer I can preprogramme everything.   
In this case the agent is not autonomous.  

If I want him to be more autonomous I can provide him 3 things: 

- some *image processing* program
- some *decision making program* $$\to$$ in such a way that the agent is told by the decision making program what to do:  
  The decision of what the agent should do is now embedded in my program.  
- a program on top of them that has some *learning mechanisms* and can modify some parameters of the decision making program.

Autonomy is not a yes or no, it’s a question of degree.   
We can say if something is more autonomous than another thing, but there is not a threshold.  

The more autonomous the agent is, the less there is direct human supervision. There is no possibility to be completely out of human control. 

### Intelligent Agents

When do we consider an agent to be intelligent?   
For the purposes of this course, an intelligent agent is one that is capable of *flexible* autonomous action in order to meet its design objectives, where <mark>flexibility</mark> means three things:

- ***Reactivity***  
  intelligent agents are able to perceive their environment, and respond in a *timely fashion* to changes that occur in it in order to satisfy their design objectives.
- ***Pro-Activeness***  
  Intelligent agents are able to exhibit goal-directed behavior by *taking the initiative* in order to satisfy their design objectives.  
  We don’t want the human to remember continuously to the agent what was its purpose. 
- ***Social Ability***  
  Intelligent agents are capable of interacting with other agents (and possibly humans) in order to satisfy their design objectives.

<img src="file:///android_asset/aaams/img/17091.png" style="zoom:40%">  

We assume that agents can exchange some kind of information, independently from the technology used to exchange those information.

***Kinds of Interaction***

- ***Negotiation***  
  several agents coming up with an agreement on a variable
- ***Voting***  
  agents are able to elect *one agent* to do something
- ***Solution of an optimization problem***  
  the knowledge of the function is distributed over agents, there is no agent that knows it all.
- ***Planning***
- ***Learning***  
  learn somehow also what others are doing in such a way to perform well accordingly to such knowledge.

***Let's wrap it up - Multiagent case***

So we see that we are dealing with two different problems 

- <u>Micro Problem</u>   
  you focus on a single agent
- <u>Macro Problem</u>  
  the multi agent system

In the same way you are designing the single agent environment you can design the interaction between the agents.   
When you design the interaction, you are designing the organization of the agents (the organization can be seen as an entity).   
Example:   
individuals and the structure of the company $$\to$$ the structure has some properties itself.

<u>Designing the organization of multi agent system is what we care about.</u> 

Agents are often run on different machines. 

When using multi agent systems:

- Physical separation among agents. There is no global goal for the system.
- You logically distinguish different agents: the application doesn’t require to have multiple agents but for some reason you find it convenient to have multiple agents.     