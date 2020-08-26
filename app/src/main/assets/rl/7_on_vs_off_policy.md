# On-Policy vs Off-Policy

***Describe the diﬀerence between on-policy and oﬀ-policy reinforcement learning techniques. Make an example of an on-policy algorithm and an example of an oﬀ-policy algorithm.***   
Let's first revise some concepts:  

- a  **probability distribution** is a mathematical function that provides the probabilities of occurrence of different possible outcomes in an experiment
- A **policy** ${\pi}$ is a distribution, a mapping, at any given point in time, from states to probabilities of selecting each possible action. It decides which action the agents selects, defining its behavior.    
  A more concise definition is the following:  
  A policy ${\pi}$ is a distribution over actions given the state:  
  ${\pi(a|s)= \mathbb{P} [a|s]}$   

The difference between Off and On policy techniques is the following:  
**On-policy learning** "learns on the job". The policy that I'm following is the policy that I'm learning about.   
It learns about policy ${\pi}$ from experience sampled from ${\pi}$ itself.   
An example of on-policy technique is  *SARSA Algorithm*.   

SARSA update function (on-policy):  
${Q(S_t,A_t)\leftarrow Q(S_t,A_t)+\alpha (\color{red} R_{t+1}+\gamma Q(S_{t+1},A_{t+1}) \color{black} -Q(S_t,A_t))}$  

**Off-policy learning** "learns over someone's shoulders". It learns about the **target policy** ${\pi(a|s)}$ while following a **behavior policy** ${\bar{\pi}(a|s)}$.  
Off policies learn from observing humans or other agents.  
They re-use experience generated from old policies ${\pi_1,\pi_2,...,\pi_{t-1}}$ in order to generate the new target policy ${\pi}$.

the best known example of why off-policy learning is used is the one regarding the exploration-exploitation tradeoff. We can follow an exploratory policy and at the same time learn about the optimal policy.  
Another interesting use of off-policy learning is wanting to learn about multiple policies while following one: there might be many different behaviors we want to figure out.

An example of off-policy technique is *Q-Learning*.  

*Q-Learning* update function (off-policy)  :  
${Q(S_t,A_t)\leftarrow Q(S_t,A_t)+ \alpha( \color{red} R_{t+1}+\gamma \max_{a' \in A}  Q(S_{t+1},a') \color{black} - Q(S_t,A_t))}$  

(Sources: PMDS Notes and [Model Free Algorithms](https://medium.com/deep-math-machine-learning-ai/ch-12-1-model-free-reinforcement-learning-algorithms-monte-carlo-sarsa-q-learning-65267cb8d1b4) )