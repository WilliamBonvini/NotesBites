# Montecarlo Tree Search

MCTS is a method for ﬁnding optimal solutions by randomly sampling the solution space and building a search tree accordingly. For example, it is used in games with very large configuration spaces. 

### Lecture's sum up

*the following section is an informal description of MCTS, it consists in a partial transcription and re-adaptation of the lecture I attended in PoliMi*.

Monte Carlo Tree Search is an algorithm used when the tree is deep in the search space:  
the time in order to find a solution is exponential.
What Does Monte Carlo try to do? It tries to find some approximate solutions to the problem.
Let's try to solve the game of chess. it is very large and so far there is no solution to it right now, because it is too large.
The problem of chess is that the payoff are available only at the end of the game so you need to finish the match in order to understand what is the outcome, but you have to compute a big number of matches and there is not enough time.  
What is the typical way of reducing the complexity? limiting the depth.  
let's fix 10 as height limit, but in the case of chess, 10 moves ahead are not enough surely. You have an agent and it needs to make a move, what can he try to do? In this tree, where the payoffs are available only at the end I stop the search at some level and I put some fictitious payoffs at that level.
At the beginning you have the initial situation, you start building the tree and after a while you stop.  
When you stop you have to put the payoff, but obviously you don't have it, so you ask yourself: is it a good state or a bad state? 

The evaluation of such state will be obtained by simulating the game starting from that state and than for instance playing randomly.  
You reach a final state and you take the value of this Montecarlo simulation with two random players and you put it to that bad/good state we were talking about.
The possible outcomes of this path are a lot obviously, they are a distribution.  
if you repeat multiple times the simulation you will get different results.  
You will take the average of these results as an indicator of "how good is this state?"

The assumption is that we can simulate this part of the game very quickly, otherwise it's not efficient.

What is the problem of the average of the result computed?
The result of this random evaluation should be computed avoiding to build the tree when I see there are some states where I will lose with very high possibility.
$\to$ we need a kind of heuristic!
I want to stay toward the states that are better for me

- Exploration: try alternatives
- Exploitation: go toward the states that are more promising.

We need to balance exploration and exploitation.
The key factor to balance is what is called *uncertainty*:
If you are very uncertain about something ,this uncertainty needs optimism.  
if you are very uncertain about the performance of a state give him a bonus, when you get more and more confident you reduce the bonus.

So here we are, we have three info for each node.

- $Q$  
  The sum of all the rewards you obtained by passing from the given state

- $N $ 
  The number of times the given state has been visited during the simulations.

- $U$:  
  An upper bound: a value computed through a function dependent from Q and N that tells how much this estimate is uncertain
  $$
  U=\frac{Q}{N}+2\sqrt{\frac{2log(N_{parent})}{N}}
  $$

### Algorithm

First of all, what are we looking for?
We just want to solve a planning problem.  
Given a state we want to understand which action to take.
$\to$ consider any state of the tree, for that state we want to answer what action to perform.

The algorithm consists in the iteration of the following 4 steps:

1. <u>***Selection***</u>

   ***if*** the root is not fully expanded  
   	$\to$  $\bigg\{$expand the root by adding one of its children (conventionally choose randomly) $\bigg\}$ 

   ***else***    
   	$\to$ $\bigg\{$select a node using the following formula:
   $$
   U=\frac{Q}{N}+2\sqrt{\frac{2log(N_{parent})}{N}}
   $$
   ​			Consider the node with the maximum value of U.
   ​			***if*** such node is not fully expanded   
   ​					$\to$ $\Big\{$select it $\Big\}$
   ​			***else***   

   ​					${\to}$ $\Big\{$compute the upperbound for its children and select the one children with the   
   ​							highest upperbound.   
   ​							***if*** there is a tie $\to$ select the left-most one, or the right-most if you are weird   
   ​								(it's up to you, do whatever you want as long as it is not specified).

   ​						 $\Big\}$

   ​	 	$\bigg\}$

   as long as you find fully expanded children iterate the upperbound computation and node selection obviously.

2. <u>***Expansion***</u>

   Expand the child selected and initialize its $Q$ and $N$ to zero.

3. <u>***Simulation***</u>

   Make up a random result for such child (win,lose, tie).

4. <u>***Update or Backup***</u>

   Update all $Qs$ and $Ns$ of the subject node and its ancestors.

*this algorithm is Any Time: we can repeat these steps as long as we want and then stop.*

A more concise definition of each step is offered here:

1. <u>***Selection:***</u>  
   A child selection policy is recursively applied to descend through the tree until the most urgent expandable node is reached (a node is expandable if it represents a nonterminal state and has unexpanded children)
2. <u>***Expansion:***</u>  
   One (or more) child nodes are added to expand the tree, according to the available actions
3. <u>***Simulation:***</u>  
   A simulation is run from the new node(s) according to the default policy to produce an outcome
4. <u>***Backpropagation:***</u>  
   The simulation result is “backed up” (i.e., backpropagated) through the selected nodes to update their statistics. 



### Definitions

- <u>***Tree policy***</u>  
  Selects or creates a leaf node from the nodes already contained within the search tree (selection and expansion). 
- <u>***Default policy***</u>   
  Plays out the problem (game) from a given non-terminal state to produce a value estimate (simulation). 



I suggest you to take a look at the Tic Tac Toe exercise offered in the course material, it helps in getting the concepts straights.