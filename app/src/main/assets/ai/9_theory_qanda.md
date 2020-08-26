# Theory Questions

- **Explain the differences between forward planning and backward planning for solving planning problems formulated in STRIPS.**   
  Forward planning formulates a search problem that starts from the initial state of the planning problem and applies all the applicable actions, in order to reach a state that satisfies the goal of the planning problem. Backward planning, instead, formulates a search problem that starts from the goal of the planning problem and applies all the regressions of the goal through relevant and consistent actions, in order to reach a state that is satisfied by the initial state of the planning problem. 

- **Why are forward planning and backward planning said to search in the space of states and in the space of goals, respectively?**    
  The states of the search problem formulated by forward planning are states of the planning problem. The states of the search problem formulated by backward planning are goals of the planning problem. 

- **Which one between forward planning and backward planning can generate inconsistent situations? Why? How can these inconsistencies be managed?**   
  Backward planning can generate states of the search problem (= goals of the planning problem) that are inconsistent (for example, they can contain On(A,B) and On(B,A) literals).  This situation can be managed by resorting to procedures that are external to the planning process. These procedures check the consistency of goals and allow to stop the search if a goal refers to inconsistent situations, because that goal cannot be satisfied.  

- **Consider using forward chaining for deriving the sentences entailed by your KB, can you list all the sentences entailed by such KB?**  
  Yes because forward chaining is a sound and complete inference procedure, so every derived sentence is correct (sound) and there are no sentences that can be derived other than the ones obtained by using the algorithm (complete).

- **What is the difference between "sentence $\alpha$ entails sentence $\beta$ " ($\alpha \models \beta$) and "sentence $\beta$ can be derived from sentence $\alpha$ by inference algorithm $i$"($\alpha \vdash_i \beta$)?**  
  $\alpha \models \beta$ means that every model that satisfies $\alpha$ satisfies $\beta$ as well, $\alpha \vdash_i \beta$ means that $\beta$ can be obtained by applying $i$ to $\alpha$.  
  The difference consists in the fact that the latter doesn't ensure that every model that satisfies $\alpha$ satisfies $\beta$ as well. In fact it could be that $i$ is not sound (sound means that $i$ derives only implied sentences) therefore $\beta$ wouldn't be implied.  
  The two sentences would be equivalent if we specify that $\alpha \vdash_i \beta$ and that $i$ is sound. 

- **Clarify the main ideas underlying Monte Carlo Tree Search (MCTS): explain what MCTS is, what it is used for, and describe its main features. In particular, explain the meaning of the six technical terms reported in the figure below (which is often used to illustrate the general logic of MCTS).**  MCTS is a method for ﬁnding optimal solutions by randomly sampling the solution space and building a search tree accordingly. For example, it is used in games with very large configuration spaces. 

  MCTS works by iterating four steps, as shown in the figure:   

  - selection:   
    a child selection policy is recursively applied to descend through the tree until the most urgent expandable node  is reached (a node is expandable if it represents a nonterminal state and has unexpanded children)
  - expansion:   
    one (or more) child nodes are added to expand the tree, according to the available actions
  - simulation:   
    a simulation is run from the new node(s) according to the default policy to produce an outcome
  - backpropagation:   
    the simulation result is “backed up” (i.e., backpropagated) through the selected nodes to update their statistics. 

  Tree policy selects or creates a leaf node from the nodes already contained within the search tree (selection and expansion).   
  Default policy plays out the problem (game) from a given non-terminal state to produce a value estimate (simulation). 

- **What is the difference between PDLL and STRIPS?**

  PDLL goals are function free, but allow variables and negative literals. STRIPS doesn't allow variables and negative predicates  
  Curiosity: Both in PDLL and STRIPS negative predicates in the preconditions of actions are allowed!