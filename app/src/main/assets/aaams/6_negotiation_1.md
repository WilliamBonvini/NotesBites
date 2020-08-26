# Negotiation Part 1 

### Negotiation

Each agent has interests that conflict with the interests of other agents (conflicting interests).

- <u>Agents</u>  
  we assume we have only 2 agents but what we say can be generalized to $n$ agents.  
  Let's call the agents $a$ and $b$.

- <u>Issues</u>  
  the variables over which the negotiation takes place.  
  There can be several issues (price, insurance, modalities in which you pay for example for buying a car ).  
  We will treat negotiations over single issues.

- <u>Outcomes</u>  
  Results of your negotiation process.    
  $$
  \Theta
  $$

We need to define the conflicting measures:

- <u>Utility Function</u>  
  function whose domain is the set of outcomes and whose codomain is the set of real numbers.  
  given an outcome, the value of that outcome is different for agent $a$ and agent $b$.  
  $$
  U^i:\Theta \to \R
  $$

- <u>Protocol</u>  
  set of rules of the game.   
  The way the negotiation must be conducted cannot be violated in general (example: if you are the seller, your offer should decrease, if you are the buyers, your offer should increase).  

- <u>Strategies</u>  
  policies the single agents will adopt for conducting the negotiation.  
  within the protocol, the agent decides what to do (example: the seller decides to offer 5 less every time, the buyer to offer 10 more every time).  
  the strategies are decided by the agents.

***Cooperative Approach (Axiomatic Approach)***

The negotiation does not actually take place, you know everything about the agents, you impose some properties over the final result you would like to reach.   
You can calculate a priori what is the final result. If $a$ knows a set of properties , then I can come up with a solution analytically.   
The properties that you are imposing on the solution are like axioms. 

We will focus on the approach just described.

***Non Cooperative Approach (Algorithmic Approach)***

You run the negotiation process, you exchange offers and counteroffers until you reach some agreement. Every agent can actually do what they want, there is no agreement over the agents. 

### Cooperative/Axiomatic Approach

This approach has this structure:

1. We list a number of properties/characteristics that our solution must have
2. We come up with a solution that respects these properties

We assume that the 2 agents are perfectly rational: they prefer outcomes according to their utility function.

We assume that the set of outcomes is partitioned in:

- $A:$ set of possible ***agreements*** (all the legal prices for example).
- $D:$ the ***disagreement***, it is a single element. It is like a special outcome that says that no agreement was found (a special price that says that no agreement was found).

$$
\Theta =A \cup D
$$

- $S= $ ***Bargaining Set***$:$ set of pairs of utilities $S=\{(U^a(z),U^b(z))|z \in A\}$ 

- $d:$ set of pairs of utilities of the agents when the disagreement will happen.  
  $$
  d=(d^a,d^b)=(U^a(D),U^b(D))
  $$

***Bargaining Problem***

A Bargaining problem is defined as the pair $(S,d)$.

the solution to such problem is defined as the result of a function $f$ defined as follows:

given a Bargaining problem $f$ returns *one* element in $S$.
$$
\text{Bargaining Solution}=f:\color{red}(S,d)\color{black}\to\color{orange}(U^a(z),U^b(z)) \in S
$$
$\color{red}Domain\color{black};\color{orange}Codomain$.

A negotiation can be viewed as a search in the space of the pairs of utilities   
connected to the agreement.     
$f$ just picks up one possibility in the bargaining set. the output of $f$ is the ***bargaining solution***.

***Assumptions on the Bargaining Set $S$***

We assume that $S$ is *closed* and *convex*. 

- <u>Convex</u>  
  if we take 2 elements belonging to $S$, their linear combination is still in $S$.  
  $$
  x=(x^a,x^b); \ \ \ \  y=(y^a,y^b) \ \ \ \ \ \ \ \ \ \ \ \ x,y \in S
  $$

  $$
  \theta x+(1-\theta)y \in S \ \ \ \ \ \ \ \ \ \ \ \theta \in [0,1]
  $$

We also assume that there is always an agreement for both agents that will be at least as good as the disagreement $\to$ players will always prefer the agreements with respect to the disagreements $\to$ it is not possible that the disagreement is the best possible result.
$$
S \cap\{(x^a,x^b)|x^a\ge d^a \and x^b \ge d^b\}\ne \empty
$$
(Assuming this we can avoid assuming that $S$ contains the disagreement).

#### Properties we want the Bargain Solution to satisfy

Now we can start to discuss the theory that will bring us to the final result of the axiomatic approach.  
We will list a number of properties we would like the bargaining solution to satisfy.  
At the end we will discover that, if we want our bargain solution to satisfy all these properties, then the bargaining solution will have a certain form.  
First we need to introduce Pareto Efficiency, then I'll list all the properties.

***Pareto Efficiency***

*Pareto efficiency or Pareto optimality is a state of allocation of resources from which it is impossible to reallocate so as to make any one individual or preference criterion better off without making at least one individual or preference criterion worse off*.

if you are maximizing a single objective function, among the outputs $5$ and $7$ you choose $7$.  
Now assume you are maximizing $2$ objective functions:

$(5,7),(8,9),(6,4)$. if we have to compare the first and the third, which one is best?  
You cannot compare them:   
If we consider the first pair and the third pair we notice that $6$ is better than $5$ but $4$ is not better than $7$.  
Surely I can say that the second pair of utilities is better than the first and the third one.  
So we say that $(8,9)$ dominates both first and third pairs.    
Moreover, we notice 2 possibilities:

- One dominates the others
- No comparison is possible:  
  You cannot have a total ordering over the elements because some elements cannot be ordered.

<img src="img/15111.PNG" style="zoom:40%">  



The bargaining solution $S=(x^a,x^b)$ is such that:
$$
f(S,d):\nexists(y^a,y^b)\in S| (y^a,y^b)\ge(x^a,x^b) \text{ and }y^i>x^i \text{ for some agent $i$}
$$
Meaning: *It makes no sense to select an outcome that is dominated by another one*.

***Properties***

1. <u>Individual Rationality</u>  
   $$
   \color{red}f\color{orange}(S,d)\color{black}\ge d
   $$
   $\color{red}\text{Bargaining Solution}\color{black};	\color{orange}\text{Bargaining Problem}$.  
   I will accept nothing that gives me less than the disagreement.

2. <u>Symmetry</u>     
   The outcome should not change if the utilities are the same but I swap the names of the agents.   

3. <u>Efficiency</u>  
   The bargaining solution returned by the function $f$ must be *Pareto Efficient*.  

4. <u>Invariance</u>  
   The solution that is returned by $f$ should not change if the utilities are linear changes.  
   if I multiply all the utilities by $\alpha$, then the final pair of utilities that is selected as the bargaining solution will be multiplied by $\alpha$ $\to$ it scales but does not change.

5. <u>Independence of irrelevant alternatives</u>  
   $\text{for any  } S' \subseteq S \ \ \ \ \  \text{    such that}\ \ \ \ \ f(S,d) \in S' \ \ \ \ \ \ \text{then } \ \ \   \ \ f(S,d)=f(S',D) $        

   <img src="img/15112.PNG" style="zoom:40%"> 

   My result does not depend on what I left out.



The final result is the ==Nash Bargaining Solution==:   
If you would like to find a bargaining solution $f$ that satisfies all these $5$ properties you have only 1 possibility: Your bargaining solution must be:
$$
f(S,d)=\underset{x=(x^a,x^b) \in S \text{ and } x\ge d}{\text{argmax}}(x^a-d^a)(x^b-d^b)
$$

$$
\text{with }d=(d^a,d^b) \ 
$$

Such solution is unique.  
In this case you don't have to negotiate.  
If you are in a set in which you know everything and you want a solution that satisfies all these properties, you don't need to run a negotiation: just compute the solution.  
This is what we mean with Axiomatic $\to$ no need to run the iterative process, just apply the formula!

<img src="img/08111.PNG" style="zoom:40%">