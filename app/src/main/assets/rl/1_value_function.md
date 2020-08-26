# Methods to compute the value function in DMDP

***Describe which methods can be used to compute the value function $V^{\pi}$ of a policy $\pi$ in a discounted Markov Decision Process.***   

The question is asking what are the methods to do prediction in a discounted MDP.  
We have to distinguish between Model-Based environments and Model-Free environments.

***Model-Based***  
Model-Based means that you are given all the dynamics of the system: the transition matrix, and the rewards for each state action pair.

The methods to do prediction in model-based problems are dynamic programming algorithms, and these are:

- Policy Evaluation
- Modified Policy Evaluation

*<u>Policy Evaluation</u>*

It consists in iteratively applying the *Bellman Expectation Equation* to a value function following policy ${\pi}$
$$
V_{k+1}(s)\leftarrow \sum_{a\in A}\pi (a|s)\Bigg[R(s,a)+\gamma \sum_{s'\in S}P(s'|s,a)V_k(s') \Bigg]
$$
until ${k \to \infty}$.

<u>*Modified Policy Evaluation*</u>

It's a modified version of Policy Evaluation which returns an approximation of the value function ${V^\pi}$ for of the policy ${\pi}$.

It differs from Policy Evaluation just by the fact that we stop the evaluation after a certain number of steps ${\to}$ we don't wait for the full convergence.

***Model-Free***  
Model-Free means that you are not given the transition matrix and the rewards for each state action pair.

There are mainly two algorithms to do prediction in Model-Free problems:

- Monte Carlo
- Temporal Difference

<div style="page-break-after: always;"></div> 

<u>*Monte Carlo*</u>

The way Monte Carlo estimates the state-value function for a given policy from experience is simply by averaging the returns observed after visits to that state. As more returns are observed, the average should converge to the expected value. 

So Monte Carlo policy evaluations uses empirical mean return instead of the expected return and it can be computed with two different approaches:

- First-Visit MC  
  Average returns only for the first time ${s}$ is visited (**unbiased** estimator) in an episode
- Every-Visit MC  
  Average returns for every time ${s}$ is visited (**biased** but **consistent** estimator)

Once an episode is over, we proceed with updating the two values

- ${V(s)}$ : the state-value function
- ${N(s)}$: the total number of times ${s}$ has been visited

for each state ${s}$ that has been visited during the last episode.

Stationary Case:

${N(s_t)\leftarrow N(s_t)+1}$

${V(s_t)\leftarrow V(s_t)+ \frac{1}{N(s_t)}(v_t-V(s_t) )}$

Non-Stationary Case:
$$
V(s_t)\leftarrow V(s_t)+ \alpha\bigg(v_t-V(s_t)\bigg)
$$
with ${{v_t=G_t=R_{t+1}+\gamma R_{t+2}+...+\gamma^{T-1}R_T}}$

<u>*Temporal Difference*</u>  
Temporal Difference prediction consists in updating our value function towards the estimated return after one step:  
Specifically, the estimate consists in two parts: the immediate reward ${r_{t+1}}$ plus the discounted value of the next step ${\gamma V(S_{t+1})}$. 
$$
V(s_t)\leftarrow V(s_t)+\alpha \bigg(r_{t+1}+\gamma V(s_{t+1})-V(s_t)\bigg)
$$
The one above is the simplest temporal-difference learning algorithm, called ${TD(0)}$, which means Temporal Difference with ${\lambda=0}$   
The general algorithm for Temporal Difference depends on such value ${\lambda}$ and  ${0\le\lambda \le 1}$.

in ${TD(0)}$ we estimate the new return by doing just a one-step lookahead, but we could even do a two-steps lookahead or in general a ${k}$-step lookahead.

if ${\lambda = 1}$ we obtain Monte Carlo learning.

(Source: notes)