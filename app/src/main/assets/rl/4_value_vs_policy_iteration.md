# Value Iteration vs Policy Iteration

***Describe and compare Value Iteration and Policy Iteration algorithms.***   

Value iteration and Policy Iteration are two algorithms used to do control in Model-Based environments.

Value Iteration can be considered a particular case of Modified Policy Iteration.

***Policy Iteration***  

It's divided in two steps:

- Policy Evaluation 
- Policy Improvement

*Policy Evaluation* consists in evaluating a certain policy ${\pi}$ by iteratively applying the *Bellman Expectation Equation*
$$
V_{k+1}(s)\leftarrow \sum_{a\in A}\pi (a|s)\Bigg[R(s,a)+\gamma \sum_{s'\in S}P(s'|s,a)V_k(s') \Bigg]
$$
It means that the value function at the iteration ${k+1}$ is given by the immediate reward obtained following policy ${\pi}$ plus the discounted average total reward obtained from the successor state ${s'}$.  
The evaluation is completed (the value function converges to the true value function for that policy) when ${k \to \infty}$. 

*Policy Improvement* consists in coming up with a better policy ${\pi'}$ starting from a policy ${ \pi}$. This is achieved by acting greedily wrt to the value function evaluated in the first step of policy iteration.

${\pi'(s)=\underset{a \in A}{\text{argmax}}}\ {Q^\pi(s,a)}$

By repeating evaluation and improvement we are certain of obtaining in the end the optimal policy ${\pi^*}$

***Value Iteration***  
*Value Iteration* consists in applying iteratively the *Bellman Optimality Equation*
$$
V^*(s)=\max_{a \in A}{\bigg\{R(s,a)+\gamma \sum_{s' \in S}P(s'|s,a)V^*(s') \bigg\}}
$$
until the actual optimal value function is found.

The optimal value function is found when the old value function ${V_k}$ and the new one ${V_{k+1}}$ differ less than a small number ${\epsilon}$.  
Value iteration is based on the principle of Optimality:  
A policy ${\pi(a|s)}$ achieves the optimal value from state ${s}$, ${v_\pi (s)=v_* (s)}$, if and only if, for any state ${s'}$ reachable from ${s}$,  ${\pi}$ achieves the optimal value from state ${s'}$, ${v_\pi (s')=v_*(s')}$.

This algorithm assures convergence to the optimal value function, and consequently to the optimal policy.

<div style="page-break-after: always;"></div> 

***Differences***

- in Value Iteration we are not building a policy at each step, we are working directly in value space. in Policy Iteration there is an alternation between value and policy space.
- Intermediate value functions of Value Iteration may not correspond to any policy, while intermediate value functions of Policy Iteration do. What does this mean? It means that in VI, during the iteration, we could get an intermediate ${v}$,  which does not correspond to any ${v_\pi}$ for any ${\pi}$.
- We can say that Value iteration is equivalent to do Modified Policy Iteration with ${k=1}$.  Modified Policy Iteration is just Policy Iteration, but we don't wait the value function to converge to the true one, we stop the evaluation at ${k=const}$. 

(Sources: notes)