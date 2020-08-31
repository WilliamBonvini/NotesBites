# Markov Decision Process

We have already introduced them in the previous lecture, so check it out if you haven't.

***Little Recap***

a *Markov Decision Process* is defined by the followings:

- $$S$$, a set of states
- $$A$$, a set of actions
- $$P(s',s,a)$$, a transition function
- $$R(s,a)$$, a reward function
- $$\gamma$$, a discount factor within $$0$$ and $$1$$
- $$\pi:S\to A$$, a policy

<img src="file:///android_asset/aaams/img/24091.png" style="zoom:40%">



***Agent's behavior***   
Considering future in deciding next steps, an agent has to look forward to future reward, not only to immediate reward. example: from $$s_3 \to a_3$$ better than $$s_3 \to a_4$$

If we had an *oracle* providing us a sequence of states $$s,s',s''$$ the agent would maximize $$r(s,a)+ λ^1  r(s’,a’)+ λ^2  r(s’’,a’’)$$, but we don't know the actual sequence of states so we introduce the *value function*.

### Value Function $$V^\pi(s)$$

$$V$$ is a function that, given a state $$s$$ and a policy $$\pi$$, will return the estimated total discounted reward for being in states $$s$$ and following policy $$\pi$$.

Now look at the image above (the so-called figure 1):

$$V^d(s_1) < v^d(s_4)$$:
for an intuitive point of view, being in state $$s_4$$ is better than being in state $$s_1$$.

$$V^{d_1}(s_3)< v^{d_2}(s_3) $$ if  $$d_1:s_3 \to a_4$$  and  $$d_2:s_3 \to a_3$$

***Optimal Policy*** $$ d^* $$

let's call it $$ \pi^* $$, I prefer it.

It is the policy that maximizes the expected total reward.

The value function obtained through $$ \pi^* $$ is referred to as $$ V^* $$.

***Bellman Expectation Equation***
$$
V^\pi(s)= \sum_{a \in A}\pi(a|s)\bigg[R(s,\pi(s))+\lambda\sum_{s' \in S}P\big(s'|s,\pi(s)\big)V^\pi(s')\bigg]
$$
***Bellman Optimality Equation***
$$
V^*(s)=\max_{a\in A}\bigg\{R(s,a)+\lambda\sum_{s' \in S}P(s'|s,a)V^*(s')\bigg\}
$$

- can be computed only if you know $$V^*(s') \to$$ recursion
- except for the value of $$V^*$$ everything is known
- $$\lambda$$ is the discount factor
- composed of 2 parts
  - $$R(s,a)$$ is the immediate reward
  - the expected value of $$V^*$$ in the next state.
- summarizing:   
  the value of the current state is the immediate reward $$+$$ any future reward.  
  we want to maximize this value so we look for the best next action to perform.

***Example***

Let's look back to figure $$1$$, I'll copy here for visualization purposes:

<img src="file:///android_asset/aaams/img/24091.png" style="zoom:40%">

$$

V^*(s_1)= \max_{(a_1,a_2)} \begin{bmatrix} 0+ \lambda \bigg(0.2V^*(s_1)+0.8V^*(s_2) \bigg) \\

0+ \lambda \bigg(0.2V^*(s_1)+0.8V^*(s_4)\bigg) \end{bmatrix} 

$$

Problem: I'm looking for something that is in my equation.

- solution 1:   
  write all equations, $$n$$ equations $$\to$$ $$n$$ unknowns.  
  It's not linear to solve though, because there is the $$\max$$ operator.  
  We can use this solution for little problems. 
  1. some assumptions are needed to go to a linear system
  2. solve it
  3. check if the assumptions hold
     1. yes $$\to$$ done
     2. no $$\to$$ back to step 1
- solution 2:  We need to solve $$ V^*(s) $$ in order to find the optimal policy

$$
\pi^*(s)=\underset{a\in A}{\text{argmax}}\bigg[R(s,a)+\lambda \sum_{s' \in S}P(s'|s,a)V^*(s')\bigg]
$$

​		<mark>so if we have $$V^*$$ we have $$\pi^*$$!</mark>

The second solution is explained in detail here:

### Value Iteration

Iteratively find a better value function until convergence.

$$(S,A,P,R,\lambda)$$ returns $$\hat{V}^*$$m that is an approximation of $$V^*$$

***Algorithm***

_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________

$$\text{initialize }\hat{V}^*$$

$$\text{repeat}$$

​			$$V \leftarrow \hat{V}^*$$

​			$$\text{for each $$s \in S$$ do}$$

​						$$\hat{V}^*(s) \leftarrow \max_{a \in A}\big[R(s,a)+\lambda\sum_{s'\in S}P(s'|s,a)V(s')\big]$$

​			$$\text{end for}$$

$$\text{until }\max_{s \in S}|V(s)-\hat{V}^*(s)|< \varepsilon$$

_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________

Here I'm not looking for something that is in my equation, I have an estimate of $$V$$!

***Properties***

- always converges
- the approximated value will have, between some bounds, the optimal values

***Example***

As always, let's consider figure 1:

<img src="file:///android_asset/aaams/img/24091.png" style="zoom:40%">

and consider $$\lambda = 0.5$$.

Let's initialize $$\hat{V}$$.   
we can put them all to $$0$$ or initialize them randomly. let's go with the first possibility.

|               | $$s_1$$ | $$s_2$$ | $$s_3$$ | $$s_4$$ |
| ------------- | ------- | ------- | ------- | ------- |
| $$\hat{V}^*$$ | $$0$$   | $$0$$   | $$0$$   | $$0$$   |
| $$V$$         | $$0$$   | $$0$$   | $$0$$   | $$0$$   |

*Iteration 1:*  
$$
\hat{V}^*(s_1)\leftarrow \max_{(a_1,a_2)}
\begin{bmatrix}
0+0.5 \bigg(0.2\cdot 0+0.8\cdot 0\bigg)
\\
0+0.5 \bigg(0.2\cdot 0+0.8\cdot 0\bigg)
\end{bmatrix}=0
$$
similarly
$$
\hat{V}^*(s_2)\leftarrow \dots =0
$$

$$
\hat{V}^*(s_4)\leftarrow \dots = 0
$$


$$
\hat{V}^*(s_3)\leftarrow \max_{(a_3,a_4)}
\begin{bmatrix}
1+0.5 \bigg(1\cdot 0\bigg)
\\
1+0.5 \bigg(1\cdot 0\bigg)
\end{bmatrix}=1
$$

*Considering* $$\varepsilon=0.1$$, we have not yet reached convergence, because $$1>0.1$$.

*Let's update our table*

|               | $$s_1$$ | $$s_2$$ | $$s_3$$          | $$s_4$$ |
| ------------- | ------- | ------- | ---------------- | ------- |
| $$\hat{V}^*$$ | $$0$$   | $$0$$   | $$\textcolor{red}{1}$$ | $$0$$   |
| $$V$$         | $$0$$   | $$0$$   | $$0$$            | $$0$$   |

*Iteration 2:*
$$
\hat{V}^*(s_1) \leftarrow \dots = 0
$$

$$
\hat{V}^*(s_2)=\max_{a_2,a_3}
\begin{bmatrix}
0+0.5 \bigg(0.8\cdot 1+0.2 \cdot 0\bigg)
\\
0+0.5 \bigg(0.8\cdot 0+ 0.2 \cdot 0\bigg)
\end{bmatrix}=0.4
$$

$$
\hat{V}^*(s_3)=\max_{a_3,a_4}
\begin{bmatrix}
1+0.5 \bigg(1\cdot 0\bigg)
\\
1+0.5 \bigg(1\cdot 0\bigg)
\end{bmatrix}=1 \text{ (no need to update)}
$$

$$
\hat{V}^*(s_4)\leftarrow \dots =0.45
$$

|               | $$s_1$$ | $$s_2$$            | $$s_3$$            | $$s_4$$             |
| ------------- | ------- | ------------------ | ------------------ | ------------------- |
| $$\hat{V}^*$$ | $$0$$   | $$ \textcolor{red}{0.4}$$ | $$1$$              | $$\textcolor{red}{0.45}$$ |
| $$V$$         | $$0$$   | $$0$$              | $$ \textcolor{green}{1}$$ | $$0$$               |

we have not yet reached convergence, because, if you compute the left term of the inequality we'll obtain  $$0.45>0.1$$.

So you should continue with the third iteration.

***Complexity of Value Iteration***

It is quadratic in the number of states $$\cdot$$ number of actions.

***Comparison between STRIPS and MDP***

- STRIPS  
  - deterministic transition
  - more expensive representation of the states with respect to MDP
- MDP
  - it assumes probabilistic transition (more general)
  - a state is an atomic element (only the designer knows at what real world state corresponds state $$ s_i$$)