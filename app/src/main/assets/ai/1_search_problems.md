# Search Problems

### Definition of a Search Problem

- *Define the states.*
- *What is the initial state.*
- *Actions Function :* $\mathcal{Actions}(s)$
- *Result Function:* $\mathcal{Result(s,a)=s'}$
- *Costs associated with each action.*
- *Goal test.*

### Search Algorithms

Legenda:

- ${b}$  =  Branching Factor:   
  what is the maximum number of children a node can have, the maximum cardinality of the set returned
- ${\epsilon}$ is the smallest path cost you have.



| Strategy             | Complete?                                       | Optimal?                                           | T C      | S        |
| -------------------- | ----------------------------------------------- | -------------------------------------------------- | -------- | -------- |
|                      |                                                 |                                                    |          |          |
| Breadth First Search | Yes (no when the branch factor (b) is infinite) | No (yes if the cost function increases with depth) | $O(b^d)$ | $O(b^d)$ |
|                      |                                                 |                                                    |          |          |
|                      |                                                 |                                                    |          |          |

| Uniform Cost               | Yes (not guaranteed if some costs = 0)   | Yes                                                       | $O(b^c\cdot \epsilon)$ | $O(b^c\cdot  \epsilon)$ |
| -------------------------- | ---------------------------------------- | --------------------------------------------------------- | ---------------------- | ----------------------- |
|                            |                                          |                                                           |                        |                         |
| Depth First                | tree search: no (loop) graph search: yes | No                                                        |                        |                         |
|                            |                                          |                                                           |                        |                         |
|                            |                                          |                                                           |                        |                         |
| Depth Limited Search       | No                                       | No                                                        | $O(b^l)$               | $O(bl)$                 |
|                            |                                          |                                                           |                        |                         |
|                            |                                          |                                                           |                        |                         |
| Iterative Deepening Search | Yes(but not when it's infinite)          | No (but if all costs are 1 it finds the optimal solution) | $O(b^d)$               | $O(bd)$                 |
|                            |                                          |                                                           |                        |                         |
|                            |                                          |                                                           |                        |                         |



**Comments**

- Breadth First Search
  - if we check if a node corresponds to a state that is a goal-state when we generate the node, and not when we pick up the node. This improves the worst case to  O(b^(d+1)).
- Uniform cost Search
  - A property is g(n) that is the path cost. Chooses first from the frontier the node with the smallest path cost. 
    You can never have a loop if costs are different from zero
- Depth First Search
  - Depth-first strategy: when you have to choose a node from the frontier choose the deepest node. "m" is the longest path i can have in the state space.
  - Backtracking : when you expand a node you don't generate all successors but only one, you keep in a separate data structure a track of which are the successor already tried. 
    A memory saving version of depth first. Here spatial complexity is "m", excluding the data structure. 
- Depth Limited Search
  - I will fix a limit called "l" that is the maximum depth at which i can
    generate successors. I will never generate successors that are deeper than
    "l". i check if the node I'm trying to expand is greater than
    "l", if it is i will not expand.
- Iterative Deepening Search
  - The idea is, size of L is a problem, if it's too little i cannot find any solution.<br />Here we start from l=0, if i find a solution then => good  <br />Every time I keep in memory just one path, just one path at a time.<br />Time complexity: How many nodes I have to generate? (d+1) +bd+b^2x(d-1)+…+b^d



### Admissibility and Consistency

- #### Admissibility of a heuristic:

  The heuristic of a node is admissible iff
  $$
  h(A)\le MinPathFrom(A)
  $$
  Where the right member is the minimum path to get to the goal state from A

- #### Consistency of a heuristic:

  1 and 2 are two nodes of the graph, h(1) is the heuristic of 1 and h(2) is the heuristic of 2.
  If such nodes are connected in the following way

  ​                                                                                 $1 \to 2$ 
  Their heuristics are consistence iff
  $$
  c_{12}\ge h(1)-h(2)
  $$



### A* Star Algorithm Completeness & Optimality

- Like breadth-first search,  A* is *complete* and will always find a solution if one exists provided c(node_1,node_2) > epsilon > 0 for fixed epsilon
- Optimal if h() is admissible, with tree search (no elimination of repeated nodes)
- Optimal if h() is consistent, with graph search (elimination of repeated nodes)