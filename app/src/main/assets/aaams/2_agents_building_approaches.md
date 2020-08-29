# Agents' Building Approaches

The behavior of an agent can be summarized in a cycle:
$$
\text{Perception} \to \text{Decision} \to \text{Action}
$$
 The classical way to build a single agent has been for many years the following: 

- build a ***Reasoning Agent*** (**deliberative** agent):   
  - you represent some knowledge the agent has and implement some reasoning process over this knowledge.   
  - you have a knowledge base and then the agent is able to react on this knowledge and find some actions that can perform in the environment $$\to$$ symbolic reasoning $$\to$$ **PLANNING**
  - Main problem of this kind of agents: from the computational point of view they are heavy.

 As a reaction to this approach there was a new proposal: 

- build **Reactive agents**:   
  Intelligent behavior as a sort of emerging property with the interactions between a system and its environment.   
  No embedding of knowledge, intelligence.   
  The intelligence of the system will emerge while interacting with the environment.   
  $$\to$$ elephants don’t play chess. Intelligence does not coincide with abstract reasoning. 
  Till that point intelligence was meant to abstract reasoning.

  -  <u>Subsumption architecture</u>      
    It is a collection of behaviors where each behavior is telling the system      what to do. The idea is that you have multiple of these behaviors in      the same system and they are competing to make the agent do something.       
    You can attach priorities, meta behaviors, etc.  
    Each single behavior is really simple. Global behavior can be called intelligent.   
    Each behavior is a finite state machine. 
  
  - Main problem of this approach: difficult to predict the interaction among several behaviors. 

Other approaches:

- ***Markov Decision Processes***   
  Explained later.
  
- ***BDI architecture*** (Belief Desire Intention)   
  Approach that is an evolution of the classical reasoning approaches.   
  In the memory of the agent there are 3 types of knowledge:
  - <u>Believe</u>   
    what you know about the world 
  - <u>Desire</u>   
    goal (long term concept) 
  - <u>Intention</u>   
    what you plan to do next (short term concept).  
    For satisfying one goal you have a limited number of intentions to reach it. 

- ***Layered architectures***  
  Popular architecture.  
  We create an agent that includes part of every architecture already mentioned.    
  <img src="file:///android_asset/aaams/img/20091.png">    
  
  In the image above there are 3 layers, each Layer proposes an action.  
  <img src="file:///android_asset/aaams/img/20092.png" >   
  It tries to keep together all the advantages of the architectures mentioned before.   
  The <u>bottom layer</u> is a *reactive* layer, going up you find more *reasoning* layers.   
  The perceptions from the sensors are received from the bottom layer.  
if the bottom layer can just implement something like a direct command to the effectors (for example in emergency cases), it is useful when no reasoning is needed.
  
If it’s not an emergency, the perception can go up to other layers. 
  
  <mark>This is the typical approach for satellites</mark>. 

### Markov Decision Processes, an introduction

It is a formalism that is defined by 4 elements:

- Set of states $$S$$
- Set of actions $$A$$
- Transition function: $$p(s'|s,a)$$
- Reward function: $$r(s,a)$$

The set of states can be seen as the set of perceptions.

The agent has some knowledge about how the environment is working. Such knowledge is embedded in the transition function. 

The ***transition function*** has $$2$$ states $$s',s$$ and one action $$a$$ $$\to$$ it returns the probability of ending up in $$s'$$ starting from s and performing $$a$$.

When the agent is in state $$s$$, he decides which action to perform. After the decision is made, what will happen to the environment is not deterministic. The only thing the agent knows is the probability distribution.
$$
\sum_{s' \in S}p(s'|s,a)=1  \ \ \ \ \forall s \in S , \forall a \in A
$$
***Reward function***: given a state $$s$$ and an action $$a$$ that the agent performs in that state, it returns a real number that is the gain the agent gets by doing so.

<img src="file:///android_asset/aaams/img/20093.png" >



***Example***

<img src="file:///android_asset/aaams/img/20094.png" >

$$

p(s_2|s_1,a_1)=0.8	 \\

p(s_1|s_1,a_1)=0.2 \\

p(s_3|s_1,a_1)=0 \\ 
\text{deterministic action}:p(s_2|s_3,a_4)=1 \\

$$ 



***comment on the concept of state***

It is easy to think about the state of the environment as physical locations in the environment, but actually this is an abuse of language, because a state is not the physical location.   
The state could be how long has the agent travelled, or how much battery remained to the agent. 



***Markovian Property***

The next state depends only on the current state, not on the previous ones $$\to$$ the past is embedded in the current state.



***Policy***

 it’s a function that given a state, returns an action. 
$$
\pi : S \to A
$$
<mark>The decision process is embedded in the policy</mark>.

If you know the policy of an agent, it does not mean that you know the sequence of states that the agent will go through, because what happens to the environment is non deterministic.

 We need to find the optimal policy $$\to$$

It should maximize the sum of the rewards I will get.

Suppose we have $$s, s’, s’’$$

and consequently $$r(s,a)+r(s’,a’)+r(s’’,a’’)+…$$

Idea of looking to the future.

Problem:   
in principle you are summing up an infinite number of rewards, so it doesn’t matter which policy you choose because you will always get to infinite. 



**Discount factor**: 0<λ<1  (sometimes $$\gamma$$ is used instead)

You are counting less and less in the reward you'll receive in the future;   
You are more interested in close rewards than in far ones. 

Suppose we have
$$
r(s,a)+ λ^1  r(s’,a’)+ λ^2  r(s’’,a’’)+…
$$
This trick makes the sum not infinite. 

If  $$λ=0$$ $$\to$$ I just look at what happens now $$\to$$ shortsighted.

If $$λ=1$$ $$\to$$ you wait for the future $$\to$$ farsighted.

We want $$\lambda$$ such that: 

$$≠0$$ because $$λ^0= 0^0$$ is a problem

$$≠1$$ you sum to infinite

$$=1$$ use only in case in which you are sure that the agent cannot run forever $$\to$$ if there is a sink state. 

So we want a policy that maximizes $$r(s,a)+ λ^1 * r(s’, a’) + λ^2 * r(s’’,a’’)+…$$

